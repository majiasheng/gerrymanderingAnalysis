
// flag to identify locked district
var districtLocked = null;

function addDistInfo(obj, id) {
    $(id).append("<br>")
    $.each(obj.feature.properties, function(key,val){
      $(id).append("<p>"+ key+": "+val +"</p>")
    });
}

function rmDistInfo(id) {
    $(id).empty();
}

function highlightFeature(e) {
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

    addDistInfo(layer, "#infoText");
}

function resetHighlight(e) {
    rmDistInfo("#infoText");
    districtBoundary.resetStyle(e.target);
}

function zoomToState(feature, layer) {
    layer.on({
        mouseover: function(e){
          if (!districtLocked) {
            highlightFeature(e);
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
          } else {
            resetHighlight(districtLocked);
          }
          if (districtLocked && (e.target == districtLocked.target)) {
            districtLocked = null;
          } else {
            map1.fitBounds(e.target.getBounds());
            highlightFeature(e);
            districtLocked = e;
          }
        }
    });
}


function sendGetOnDataSelect(stateCode, year) {
    $.ajax({
        url: "/data",
        type: "GET",
        contentType: "application/json",
        data: {"code": stateCode, "year": year},
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


function selectState(e) {
    console.log(e.target.feature.properties.STUSPS);
    var selected = 0;
    $("#stateSelection option").each(function (i, val) {
        if ($(val).val() === e.target.feature.properties.STUSPS) {
            selected = 1;
            $("#stateSelection").val(e.target.feature.properties.STUSPS).change();
        }
    });
    if (!selected) {
        alert("State " + e.target.feature.properties.STUSPS + " Not Available");
    }
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
        }
        var code = $(this).val();
        var options = "";
        // BASE CASE: zoom back to continental US on select no State
        if (code === "") {
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
            data: {"code": code},
            dataType: "json",
            success: function (response, status, xhr) {
                // zoom to state
                map1.fitBounds($.grep(allStates.getLayers(), function (state) {
                    // get state boundary for selected state
                    return state.feature.properties.STUSPS == code;
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
                var y = $("#dataSelection").val();
                sendGetOnDataSelect(code, y);

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
        $.ajax({
            url: "/measure/" + m,
            type: "GET",
            contentType: "application/json",
            data: {"code": c, "year": y},
            dataType: "json",
            success: function (response, status, xhr) {
                //TODO: display measure result
                // response is a TestResult object

                //TEST
                // $('.info').html(m);
            },
            error: function (xhr, textStatus, errorThrown) {
                console.log(textStatus + "; errorThrown: " + errorThrown);
            }
        });
    });


});
