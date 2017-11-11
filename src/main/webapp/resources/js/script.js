var districtBoundary = null;
function sendGetOnDataSelect (stateCode, year) {
    $.ajax({
        url: "/data",
        type: "GET",
        contentType: "application/json",
        data: {"code": stateCode, "year": year},
        dataType: "json",
        success: function (response, status, xhr) {
            //TODO: display district boundary
            // response is a list of districts (with geo/election data)
            allStates.remove();
            if (districtBoundary) {
              districtBoundary.remove();
              districtBoundary = null;
            }
            districtBoundary = L.geoJson(response, {
                // style: style,
                onEachFeature: onEachFeature
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
                    + "\nCan be caused by empty response");
            $("#gerrymanderingMeasure").prop({
                disabled: true
            });
        }
    });
}


function selectState(e) {
    map1.fitBounds(e.target.getBounds());
}

$(document).ready(function () {

    const dataSelectionOrigHTML = $('#dataSelection').html();
    const gerrymanderingMeasureOrigHTML = $('#gerrymanderingMeasure').html();

    // send get on state selection
    $('#stateSelection').change(function () {
        var code = $(this).val();
        var options = "";
        // BASE CASE: zoom back to continental US on select no State
        if (code === "") {
            map1.setView([36.4051421, -95.5136459], 3.91);
            // clear the options
            $('#dataSelection').html(dataSelectionOrigHTML);
            $("#dataSelection").prop({
                disabled: true
            });
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

    // send get on data selection
    $('#dataSelection').change(function () {
        sendGetOnDataSelect($("#stateSelection").val(), $("#dataSelection").val());
    });

    // send get on measure/test selection
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
