
$(document).ready(function() {
  const dataSelectionOrigHTML = $("#dataSelection").html();
  const gerrymanderingMeasureOrigHTML = $("#gerrymanderingMeasure").html();

  // flag to identify locked district
  var districtLocked = null;

  function districtStyling(layer) {
    layer.setStyle({
      weight: 5,
      // color: '#666',
      dashArray: "",
      fillOpacity: 0.7
    });
    if (!L.Browser.ie && !L.Browser.opera && !L.Browser.edge) {
      layer.bringToFront();
    }
    // add to info
    $("#infoText").append("<br>");
  }

  function filterData(key, val, demogData) {
    var dataStr = "";
    $.each(val, function(key2, val2) {
      if (val2) {
        if (key == "electionData") {
          if (electionDataExcludeKey(key2)) {
            return true;
          }
          dataStr += "<p>" + translateElectionDataKeyName(key2) + ": " + translateElectionDataVal(title(val2)) + "</p>\n";
        } else if (key == "demographicData") {
          if (demogDataExcludeKey(key2)) {
            return true;
          }
          if (key2 == "population") {
            dataStr += "<p>" + title(key2) + ": " + val2 + "</p>\n";
          } else {
            demogData.labels.push(translateDemogDataKeyName(key2));
            demogData.datasets[0].data.push(val2);
            demogData.datasets[0].backgroundColor.push(generateRandomColor());
          }
        }
      }
    });
    return dataStr;
  }

  function lockDistrict(e) {
    var layer = e.target;
    districtStyling(layer);
    var dataStr = "";
    var demogData = {
      datasets: [{
        data: [],
        backgroundColor: []
      }],
      labels: []
    };
    $.each(layer.feature.properties, function(key, val) {
      if (key == "electionData" || key == "demographicData") {
        dataStr += filterData(key, val, demogData);
        return true;
      }
      if (key == "DISTRICT" && val == 0) {
        val = "At-Large";
      }
      $("#infoText").append("<p>" + translatePropKeyName(key) + ": " + val + "</p>");
    });
    $("#infoText").append(dataStr);
    if (demogData.labels) {
      $("#infoText").append('<canvas id="demogChart"></canvas>');
      var myDoughnutChart = new Chart($('#demogChart'), {
          type: 'doughnut',
          data: demogData ,
          options: Chart.defaults.doughnut
      });
    }
  }

  function resetDistrict(e) {
    $("#infoText").empty();
    districtBoundary.resetStyle(e.target);
  }

  function zoomToState(feature, layer) {
    layer.on({
      mouseover: function(e) {
        if (!districtLocked) {
          lockDistrict(e);
        }
      },
      mouseout: function(e) {
        if (!districtLocked) {
          resetDistrict(e);
        }
      },
      click: function(e) {
        if (!districtLocked) {
          $(
            '<span id="distLockLabel" class="label label-info">District Locked!</span>'
          ).insertBefore("#infoText");
        } else {
          resetDistrict(districtLocked);
          lockDistrict(e);
        }
        if (districtLocked && e.target == districtLocked.target) {
          districtLocked = null;
          $("#distLockLabel").remove();
        } else {
          map1.fitBounds(e.target.getBounds());
          districtLocked = e;
        }
      }
    });
  }

  function onGetDistDataSuccess(response, status, xhr) {
    $('#loadingAlert').remove();
    // remove old boundary
    if (districtBoundary) {
      districtBoundary.remove();
      districtBoundary = null;
    }
    // separate geojson response
    var distGeoJson = response.distGeoJson;
    // use district boundary data from response
    districtBoundary = L.geoJson(distGeoJson, {
      style: function(feature) {
        return {
          color: 'purple'
        }
      },
      onEachFeature: zoomToState
    });
    districtBoundary.addTo(map1);

    console.log("Enabling GerrymanderingMeasure drop down menu...");
    $("#gerrymanderingMeasure").prop({
      disabled: false
    });
  }

  function onGetDistDataFailure(xhr, textStatus, errorThrown) {
    console.log(
      textStatus +
        ": Cannot enable GerrymanderingMeasure drop down menu" +
        "\nIt can be caused by empty response"
    );
    $("#gerrymanderingMeasure").prop({
      disabled: true
    });
  }

  function sendGetOnDataSelect(state, year) {
    // add spiner
    var spinStr = '<div id="loadingAlert" class="alert alert-info"><i class="fa fa-circle-o-notch fa-spin" style="font-size:20px"></i> Loading</div>';
    $(spinStr).insertBefore('#infoText');
    // reset gerrymanderingMeasure
    $("#gerrymanderingMeasure").html(gerrymanderingMeasureOrigHTML);
    $.ajax({
      url: "/data",
      type: "GET",
      contentType: "application/json",
      data: { state: state, year: year },
      dataType: "json",
      success: onGetDistDataSuccess,
      error: onGetDistDataFailure
    });
  }

  function resetMapToCountry() {
    map1.setView([36.4051421, -95.5136459], 3.91);
    // reset and disable data options
    $("#dataSelection").html(dataSelectionOrigHTML);
    $("#dataSelection").prop({
      disabled: true
    });
    // reset and disable measure options
    $("#gerrymanderingMeasure").html(gerrymanderingMeasureOrigHTML);
    $("#gerrymanderingMeasure").prop({
      disabled: true
    });
    if (districtBoundary) {
      districtBoundary.remove();
      districtBoundary = null;
    }
  }

  function loadSelectedState(response, status, xhr, state, options) {
    // response is a list of years for populating data drop down
    $.each(response, function(index, v) {
      options += "<option value" + "=" + v + ">" + v + "</option>";
    });
    $("#dataSelection").html(options);
    console.log("Enabling Data drop down menu...");
    $("#dataSelection").prop({
      disabled: false
    });

    //TODO: send get request for default year (or selected year)
    var year = $("#dataSelection").val();
    sendGetOnDataSelect(state, year);
  }

  // send get on state selection
  $("#stateSelection").change(function() {
    if (districtLocked) {
      resetDistrict(districtLocked);
      districtLocked = null;
      $("#distLockLabel").remove();
    }
    var state = $(this).val();
    var options = "";
    // BASE CASE: zoom back to continental US on select no State
    if (state === "") {
      resetMapToCountry();
      return;
    }
    $.ajax({
      url: "/state",
      type: "GET",
      contentType: "application/json",
      data: { state: state },
      dataType: "json",
      success: function(response, status, xhr) {
        loadSelectedState(response, status, xhr, state, options);
      },
      error: function(xhr, status, error) {
        // disallow selecting "Data" option if response is empty
        $("#dataSelection").prop({
          disabled: true
        });
      }
    });
    // zoom to state
    map1.fitBounds(
      $.grep(allStates.getLayers(), function(selectedState) {
        // get state boundary for selected state
        return selectedState.feature.properties.STUSPS == state;
      })[0].getBounds()
    );
  });

  // send get on data selection
  $("#dataSelection").change(function() {
    // // zoom to state
    // map1.fitBounds(
    //   $.grep(allStates.getLayers(), function(selectedState) {
    //     // get state boundary for selected state
    //     return selectedState.feature.properties.STUSPS == $("#stateSelection").val();
    //   })[0].getBounds()
    // );

    if (districtLocked) {
      resetDistrict(districtLocked);
      districtLocked = null;
      $("#distLockLabel").remove();
    }
    sendGetOnDataSelect($("#stateSelection").val(), $("#dataSelection").val());
  });

  // send get on measure/test selection
  $("#gerrymanderingMeasure").change(function() {
    var stateCode = $("#stateSelection").val();
    var year = $("#dataSelection").val();
    var measure = $(this).val();
    if (measure !== "") {
      $.ajax({
        url: "/measure/" + measure,
        type: "GET",
        contentType: "application/json",
        data: { code: stateCode, year: year },
        dataType: "json",
        success: function(response, status, xhr) {
          //TODO: display measure result
          // response is a TestResult object
          alert("response: " + JSON.stringify(response));

          //TEST
          var result = "Is This State Gerrymandered? " + response.gerrymandered + "<br/>";
          result += (response.skipped ? "<p style=\"color:red\">Skipped</p>" : "");
          // $("#testResult").html(result);

        },
        error: function(xhr, textStatus, errorThrown) {
          console.log(textStatus + "; errorThrown: " + errorThrown);
        }
      });
    }
  });
});
