
function title(str) {
  if (typeof str !== "string")
    return str;
  return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
}

$(document).ready(function() {
  const dataSelectionOrigHTML = $("#dataSelection").html();
  const gerrymanderingMeasureOrigHTML = $("#gerrymanderingMeasure").html();

  // flag to identify locked district
  var districtLocked = null;

  function translatePropKeyName(val) {
    if (val === "STATENAME") {
      return "State";
    } else if (val === "DISTRICT") {
      return "District";
    } else {
      return val;
    }
  }

  function translateElectionDataKeyName(val) {
    if (val === "winner") {
      return "Winning Party";
    } else if (val === "demVotes") {
      return "Democrat Votes";
    } else if (val === "repVotes") {
      return "Republican Votes";
    } else {
      return val;
    }
  }

  function electionDataExcludeKey(key) {
    if (key === "districtNum") {
      return true;
    }
    return false;
  }

  function translateElectionDataVal(val) {
    if (val === "Democratic") {
      return "Democrat";
    } else {
      return val;
    }
  }

  function lockDistrict(e) {
    var layer = e.target;
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
    var electionDataStr = "";
    $.each(layer.feature.properties, function(key, val) {
      if (key == "electionData") {
        $.each(val, function(key2, val2) {
          if (val2) {
            if (electionDataExcludeKey(key2)) {
              return true;
            }
            electionDataStr += "<p>" + translateElectionDataKeyName(key2) + ": " + translateElectionDataVal(title(val2)) + "</p>\n";
          }
        });
        return true;
      }
      $("#infoText").append("<p>" + translatePropKeyName(key) + ": " + val + "</p>");
    });
    $("#infoText").append(electionDataStr);
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
          resetDistrict(e);
          $(
            '<span id="distLockLabel" class="label label-info">District Locked!</span>'
          ).insertBefore("#infoText");
        } else {
          resetDistrict(districtLocked);
        }
        if (districtLocked && e.target == districtLocked.target) {
          districtLocked = null;
          $("#distLockLabel").remove();
        } else {
          map1.fitBounds(e.target.getBounds());
          lockDistrict(e);
          districtLocked = e;
        }
      }
    });
  }

  function sendGetOnDataSelect(state, year) {
    $.ajax({
      url: "/data",
      type: "GET",
      contentType: "application/json",
      data: { state: state, year: year },
      dataType: "json",
      success: function(response, status, xhr) {
        // display only district boundary, remove all state boundaries
        // allStates.remove();

        // remove old boundary
        if (districtBoundary) {
          districtBoundary.remove();
          districtBoundary = null;
        }
        // separate geojson response
        var distGeoJson = response.distGeoJson;
        // use district boundary data from response
        districtBoundary = L.geoJson(distGeoJson, {
          // style: style,
          onEachFeature: zoomToState
        });
        districtBoundary.addTo(map1);

        console.log("Enabling GerrymanderingMeasure drop down menu...");
        $("#gerrymanderingMeasure").prop({
          disabled: false
        });
      },
      error: function(xhr, textStatus, errorThrown) {
        console.log(
          textStatus +
            ": Cannot enable GerrymanderingMeasure drop down menu" +
            "\nIt can be caused by empty response"
        );
        $("#gerrymanderingMeasure").prop({
          disabled: true
        });
      }
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
    // allStates.addTo(map1);
  }

  function loadSelectedState(response, status, xhr, state, options) {
    // zoom to state
    map1.fitBounds(
      $.grep(allStates.getLayers(), function(selectedState) {
        // get state boundary for selected state
        return selectedState.feature.properties.STUSPS == state;
      })[0].getBounds()
    );

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
  });

  // send get on data selection
  $("#dataSelection").change(function() {
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
          // $('.info').html(measure);
        },
        error: function(xhr, textStatus, errorThrown) {
          console.log(textStatus + "; errorThrown: " + errorThrown);
        }
      });
    }
  });
});
