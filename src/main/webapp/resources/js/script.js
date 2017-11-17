
// flag to identify locked district
var districtLocked = null;

function lockDistrict(e) {
    var layer = e.target;
    layer.setStyle({
        weight: 5,
        // color: '#666',
        dashArray: '',
        fillOpacity: 0.7
    });
    if (!L.Browser.ie && !L.Browser.opera && !L.Browser.edge) {
        layer.bringToFront();
    }
    // add to info
    $("#infoText").append("<br>")
    $.each(layer.feature.properties, function(key,val){
      $("#infoText").append("<p>"+ key+": "+val +"</p>")
    });
}

function resetHighlight(e) {
    $("#infoText").empty();
    districtBoundary.resetStyle(e.target);
}

function zoomToState(feature, layer) {
    layer.on({
        mouseover: function(e){
          if (!districtLocked) {
            lockDistrict(e);
          }
        },
        mouseout: function(e){
          if (!districtLocked) {
            resetHighlight(e);
          }
        },
        click: function (e) {
          if (!districtLocked) {
            resetHighlight(e);
            $('<span id="distLockLabel" class="label label-info">District Locked!</span>').insertBefore("#infoText");
          } else {
            resetHighlight(districtLocked);
          }
          if (districtLocked && (e.target == districtLocked.target)) {
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
        data: {"state": state, "year": year},
        dataType: "json",
        success: function (response, status, xhr) {
            // display only district boundary, remove all state boundaries
            allStates.remove();

            // remove old boundary
            if (districtBoundary) {
                districtBoundary.remove();
                districtBoundary = null;
            }
            // use district boundary data from response
            districtBoundary = L.geoJson(response, {
                // style: style,
                onEachFeature: zoomToState
            });
            districtBoundary.addTo(map1);

            console.log("Enabling GerrymanderingMeasure drop down menu...");
            $("#gerrymanderingMeasure").prop({
                disabled: false
            });
        },
        error: function (xhr, textStatus, errorThrown) {
            console.log(textStatus
                    + ": Cannot enable GerrymanderingMeasure drop down menu"
                    + "\nIt can be caused by empty response");
            $("#gerrymanderingMeasure").prop({
                disabled: true
            });
        }
    });
}

$(document).ready(function () {

    const dataSelectionOrigHTML = $('#dataSelection').html();
    const gerrymanderingMeasureOrigHTML = $('#gerrymanderingMeasure').html();
    /**
     * send get on state selection
     */
    $('#stateSelection').change(function () {
        if (districtLocked) {
          resetHighlight(districtLocked);
          districtLocked = null;
          $("#distLockLabel").remove();
        }
        var state = $(this).val();
        var options = "";
        // BASE CASE: zoom back to continental US on select no State
        if (state === "") {
            map1.setView([36.4051421, -95.5136459], 3.91);
            // reset and disable data options
            $('#dataSelection').html(dataSelectionOrigHTML);
            $("#dataSelection").prop({
                disabled: true
            });
            // reset and disable measure options
            $('#gerrymanderingMeasure').html(gerrymanderingMeasureOrigHTML);
            $("#gerrymanderingMeasure").prop({
                disabled: true
            });
            if (districtBoundary) {
                districtBoundary.remove();
                districtBoundary = null;
            }
            allStates.addTo(map1);
            return;
        }
        $.ajax({
            url: "/state",
            type: "GET",
            contentType: "application/json",
            data: {"state": state},
            dataType: "json",
            success: function (response, status, xhr) {
                // zoom to state
                map1.fitBounds($.grep(allStates.getLayers(), function (selectedState) {
                    // get state boundary for selected state
                    return selectedState.feature.properties.STUSPS == state;
                })[0].getBounds());

                // response is a list of years for populating data drop down
                $.each(response, function (index, v) {
                    options += "<option value" + "=" + v + ">" + v + "</option>"
                });
                $('#dataSelection').html(options);
                console.log("Enabling Data drop down menu...");
                $("#dataSelection").prop({
                    disabled: false
                });

                //TODO: send get request for default year (or selected year)
                var year = $("#dataSelection").val();
                sendGetOnDataSelect(state, year);

            },
            error: function (xhr, status, error) {
                // disallow selecting "Data" option if response is empty
                $("#dataSelection").prop({
                    disabled: true
                });
            }
        });
    });

    /**
     * send get on data selection
     */
    $('#dataSelection').change(function () {
        sendGetOnDataSelect($("#stateSelection").val(), $("#dataSelection").val());
    });

    /**
     * send get on measure/test selection
     */
    $('#gerrymanderingMeasure').change(function () {
        var c = $("#stateSelection").val();
        var y = $("#dataSelection").val();
        var m = $(this).val();
        if (m!=="") {
            $.ajax({
                url: "/measure/" + m,
                type: "GET",
                contentType: "application/json",
                data: {"code": c, "year": y},
                dataType: "json",
                success: function (response, status, xhr) {
                    //TODO: display measure result
                    // response is a TestResult object
                    alert("response: "+JSON.stringify(response));
                    //TEST
                    // $('.info').html(m);
                },
                error: function (xhr, textStatus, errorThrown) {
                    console.log(textStatus + "; errorThrown: " + errorThrown);
                }
            });
        }

    });


});
