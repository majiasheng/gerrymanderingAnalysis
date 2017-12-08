var numDist = 0;
const MIN_NUM_DIST_FOR_TEST = 5;

function add(a, b) {
    return a + b;
}

function translateDemogVal(val, sum) {
    var num = Number(val);
    var ret = "";
    // ret += num.toLocaleString(
    //   "en-US",
    //   { minimumFractionDigits: 2 }
    // );
    ret += " (" + (num / sum * 100).toFixed(2) + "%)";
    return ret;
}

// flag to identify locked district
var districtLocked = null;

var multiSelectMap = function(checked){
  console.log(checked);
};

$(document).ready(function () {
    const dataSelectionOrigHTML = $("#dataSelection").html();
    const gerrymanderingMeasureOrigHTML = $("#gerrymanderingMeasure").html();
    const MIN_NUM_OF_DIST_FOR_SD = 5;

    function districtStyling(layer, addBr) {
        layer.setStyle({
            weight: 5,
            // color: '#666',
            dashArray: "",
            fillOpacity: 0.7
        });
        if (!L.Browser.ie && !L.Browser.opera && !L.Browser.edge) {
            layer.bringToFront();
        }
        if (addBr) {
          // add to info
          $("#infoText").append("<br>");
        }
    }

    function filterData(key, val, demogData) {
        var dataStr = "";
        $.each(val, function (key2, val2) {
            if (val2) {
                if (key == "electionData") {
                    if (electionDataExcludeKey(key2)) {
                        return true;
                    }
                    var v = translateElectionDataVal(title(val2));

                    // decorate value
                    if (isNaN(v)) {
                        if (v === "Republican") {
                            v = "<span class=\"red\">" + v + "</span>";
                        } else if (v === "Democrat") {
                            v = "<span class=\"blue\">" + v + "</span>";
                        }
                    } else {
                        v = Number(v).toLocaleString('en');
                    }

                    dataStr += "<p>" + translateElectionDataKeyName(key2) + " : " + v + "</p>\n";
                    //dataStr += "<p>" + translateElectionDataKeyName(key2) + ": " + translateElectionDataVal(title(val2)) + "</p>\n";
                } else if (key == "demographicData") {
                    if (demogDataExcludeKey(key2)) {
                        return true;
                    }
                    if (key2 == "population") {
                        dataStr += "<p>" + title(key2) + " : " + Number(val2).toLocaleString('en') + "</p>\n";
                    } else {
                        demogData.labels.push(translateDemogDataKeyName(key2) + translateDemogVal(val2, val.population));
                        demogData.datasets[0].data.push(val2);
                        demogData.datasets[0].backgroundColor.push(generateColor(key2));
                    }
                }
            }
        });
        return dataStr;
    }

    function lockDistrict(e) {
        var layer = e.target;
        districtStyling(layer, true);
        var dataStr = "";
        var demogData = {
            datasets: [{
                    data: [],
                    backgroundColor: []
                }],
            labels: []
        };
        $("#infoText").append('<hr><h4>District Congressional Election</h4>');
        $.each(layer.feature.properties, function (key, val) {
            if (key == "electionData" || key == "demographicData") {
                dataStr += filterData(key, val, demogData);
                return true;
            }
            if (key == "DISTRICT" && val == 0) {
                val = "At-Large";
            }
            if (key !== "STATENAME") { // no need to display state name again
                $("#infoText").append("<p>" + translatePropKeyName(key) + " : " + val + "</p>");
            }
        });
        $("#infoText").append(dataStr);
        var cop = Chart.defaults.doughnut;
        cop.tooltips.callbacks = {
          label: function(tooltipItem, data) {
            var value = data.datasets[0].data[tooltipItem.index];
            value = value.toString();
            value = value.split(/(?=(?:...)*$)/);
            value = value.join(',');
            return value;
          }
        };
        if (demogData.labels) {
            $("#infoText").append('<hr><h4>Interactive District Demographics (Hover)</h4>');
            $("#infoText").append('<canvas id="demogChart"></canvas>');
            var myDoughnutChart = new Chart($('#demogChart'), {
                type: 'doughnut',
                data: demogData,
                options: cop
            });
        }
        for (var i = 0; i < demogData.labels.length; i++) {
          $("#infoText").append("<p>" + demogData.labels[i] + ' : ' + demogData.datasets[0].data[i].toLocaleString("en-US") + "</p>");
        }
    }

    function resetDistrict(e) {
        $("#infoText").empty();
        districtBoundary.resetStyle(e.target);
    }

    function zoomToState(feature, layer) {
        layer.on({
            mouseover: function (e) {
                if (!districtLocked) {
                    lockDistrict(e);
                }
            },
            mouseout: function (e) {
                if (!districtLocked) {
                    resetDistrict(e);
                }
            },
            click: function (e) {
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

        // assign number of districts for the state to global variable
        numDist = distGeoJson.features.length;

        // use district boundary data from response
        districtBoundary = L.geoJson(distGeoJson, {
            style: function (feature) {
                return {
                    color: 'purple'
                }
            },
            onEachFeature: zoomToState
        });
        districtBoundary.addTo(map1);

        if (numDist > MIN_NUM_DIST_FOR_TEST) {
            console.log("Enabling GerrymanderingMeasure drop down menu...");
            $("#gerrymanderingMeasure").prop({
                disabled: false
            });
        }

        // enable export button
        $(".export").prop("class", "export");
        $("#exportTo").prop("href", "export");
        $("#exportTo").prop("class", "doExport");
        // bind: make href into an url with params
        $(".doExport").click(function (e) {
            var state = $("#stateSelection").val();
            var year = $("#dataSelection").val();
            $(".doExport").prop("href", "/export?state="+state+"&year="+year);
        });


        // check number of districts, n, enable super district creation if n>5
        if (distGeoJson.features.length > MIN_NUM_OF_DIST_FOR_SD) {
            $("#sdcheck").prop('disabled', false);
        } else {
            $("#sdcheck").prop('disabled', true);
        }

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
        $("#gerrymanderingMeasure").change();
        $.ajax({
            url: "/data",
            type: "GET",
            contentType: "application/json",
            data: {state: state, year: year},
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
        $("#gerrymanderingMeasure").change();
        $("#gerrymanderingMeasure").prop({
            disabled: true
        });
        if (districtBoundary) {
            districtBoundary.remove();
            districtBoundary = null;
        }
        // disable sd checkbox
        $("#sdcheck").prop('disabled', true);
        // set number of district back to 0
        numDist = 0;
    }

    function loadSelectedState(response, status, xhr, state, options) {
        // response is a list of years for populating data drop down
        $.each(response, function (index, v) {
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
    $("#stateSelection").change(function () {
        // reset sd
        resetSDControls();

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
            data: {state: state},
            dataType: "json",
            success: function (response, status, xhr) {
                loadSelectedState(response, status, xhr, state, options);
            },
            error: function (xhr, status, error) {
                // disallow selecting "Data" option if response is empty
                $("#dataSelection").prop({
                    disabled: true
                });
            }
        });
        // zoom to state
        map1.fitBounds(
                $.grep(allStates.getLayers(), function (selectedState) {
                    // get state boundary for selected state
                    return selectedState.feature.properties.STUSPS == state;
                })[0].getBounds()
                );
    });

    // send get on data selection
    $("#dataSelection").change(function () {
        // // zoom to state
        // map1.fitBounds(
        //   $.grep(allStates.getLayers(), function(selectedState) {
        //     // get state boundary for selected state
        //     return selectedState.feature.properties.STUSPS == $("#stateSelection").val();
        //   })[0].getBounds()
        // );
        if (document.getElementById('sdcheck').checked) {
          $('#sdcheck').click()
        }

        if (districtLocked) {
            resetDistrict(districtLocked);
            districtLocked = null;
            $("#distLockLabel").remove();
        }
        sendGetOnDataSelect($("#stateSelection").val(), $("#dataSelection").val());
    });

    // send get on measure/test selection
    $("#gerrymanderingMeasure").change(function () {
        var stateCode = $("#stateSelection").val();
        var year = $("#dataSelection").val();
        var measure = $(this).val();
        $("#testResultContainer").empty();
        if (measure !== "") {
            $.ajax({
                url: "/measure/" + measure,
                type: "GET",
                contentType: "application/json",
                data: {code: stateCode, year: year},
                dataType: "json",
                success: function (response, status, xhr) {
                    //TODO: display measure result
                    // response is a TestResult object
                    $("#testResultContainer").append('<h1>' + $('#gerrymanderingMeasure').val() + ' Result</h1>');
                    var dataStr = "";
                    $.each(response, function (key, val) {
                        if (displayTestVar($("#gerrymanderingMeasure").val(), key)) {
                          if (typeof val === "object") {
                            val = JSON.stringify(val);
                          }
                          dataStr += "<p>" + translateTestKeyName(key) + " : " + val + "</p>";
                        }
                    });
                    $("#testResultContainer").append(dataStr);

                    //TEST
                    var result = "Is This State Gerrymandered? " + response.gerrymandered + "<br/>";
                    result += (response.skipped ? "<p style=\"color:red\">Skipped</p>" : "");
                    // $("#testResult").html(result);

                },
                error: function (xhr, textStatus, errorThrown) {
                    $("#testResultContainer").append('<h1>' + $('#gerrymanderingMeasure').val() + ' Not Available</h1>');
                    console.log(textStatus + "; errorThrown: " + errorThrown);
                }
            });
        }
    });

    multiSelectMap = function(checked){
      var geo = districtBoundary.toGeoJSON();
      districtBoundary.remove();
      $("#infoText").empty();
      $("#testResultContainer").empty();
      $("#distLockLabel").empty();
      if (checked) {
        districtBoundary = L.geoJson(geo, {
            style: function (feature) {
                return {
                    color: 'grey'
                }
            },
            onEachFeature: multiSelectHandler
        });
      } else {
        districtBoundary = L.geoJson(geo, {
            style: function (feature) {
                return {
                    color: 'purple'
                }
            },
            onEachFeature: zoomToState
        });
      }
      districtBoundary.addTo(map1);
    };

    function compareDistrict(c, d) {
      var br = false;
      // 4 cases: both multi, c multi d poly, c poly d multi, both poly
      if (c.geometry.type === 'Polygon') {
        var temp = c;
        c = d;
        d = temp;
      }
      // 3 cases: both multi, c multi d poly, both poly
      if (c.geometry.type === 'Polygon') {
        try {
          return turf.intersect(c, d) != null
        } catch (e) {
          console.log("poly poly");
          console.log(e);
          return true;
        }
      }
      // 2 cases: both multi, c multi d poly
      $(c.geometry.coordinates).each(function(i2, coords2) {
          var feat2 = {
              'type': 'Polygon',
              'coordinates': coords2
          };
          try {
            if (turf.intersect(d, feat2) != null) {
              br = true;
              return false;
            }
          } catch (e) {
            console.log("c multi d poly");
            console.log(e);
            br = true;
            return false;
          }
      });
      if(br){return true;}
      // both multi; too much give up
      return true;
      // $(d.geometry.coordinates).each(function(i, coords) {
      //     var feat = {
      //         'type': 'Polygon',
      //         'coordinates': coords
      //     };
      //     $(c.geometry.coordinates).each(function(i2, coords2) {
      //         var feat2 = {
      //             'type': 'Polygon',
      //             'coordinates': coords2
      //         };
      //         try {
      //           if (turf.intersect(d, feat2) != null) {
      //             br = true;
      //             return false;
      //           }
      //         } catch (e) {
      //           console.log("multi multi");
      //           console.log(e);
      //           br = true;
      //           return false;
      //         }
      //     });
      //     if(br){return false;}
      // });
      return br;
    }

    function multiSelectHandler(feature, layer) {
      layer.on({
          mouseover: function (e) {
              if ((typeof $(e.target).data("chosen")) === "undefined" && $('.manualSDCtrl').children().length) {
                districtStyling(e.target);
              }
          },
          mouseout: function (e) {
              if ((typeof $(e.target).data("chosen")) === "undefined" && $('.manualSDCtrl').children().length) {
                districtBoundary.resetStyle(e.target);
              }
          },
          click: function (e) {
              if ((typeof $(e.target).data("chosen")) === "undefined" && $('.manualSDCtrl').children().length) {
                var b = false;
                $('.manualSDCtrl').children().each(function(i,v) {
                  $(v).children().each(function(i2,v2) {
                    if (!$(v2).text().trim()) {
                      // check if adjacent to existing boundary; reject and alert if not
                      if (i2) {    // always allow on first insert
                        b = true;
                        $($(v).data("boundaryObj")).each(function(i3,v3) {
                          // v3 contains boundaryObj
                          var b1 = v3.toGeoJSON();
                          var b2 = e.target.toGeoJSON();
                          if (compareDistrict(b1, b2)) {
                            b = false;
                            return false;
                          }
                        });
                        // break if no match
                        if(b){alert("District not adjacent.");return false;}
                      }
                      // add flag to chosen
                      $(e.target).data("chosen", 0);
                      // add to DOM
                      $(v2).text(e.target.feature.properties.DISTRICT+" ");
                      // add obj to set
                      if (typeof $(v).data("boundaryObj") === "undefined") {
                        $(v).data("boundaryObj", []);
                      }
                      $(v).data("boundaryObj").push(e.target);
                      // change color
                      e.target.setStyle({
                          weight: 5,
                          color: arrayToRgb(colorSet[i]),
                          dashArray: "",
                          fillOpacity: 0.7
                      });
                      // validate if set is full
                      if (i2+1 == $(v).children().length && i+1 == $('.manualSDCtrl').children().length) {
                        $("#createSDBtn").removeAttr("disabled");
                      }
                      b = true;
                      return false;
                    }
                  });
                  if(b){return false;}
                });
              }
          }
      });
    }
});
