//should max number of SD with size of 5
const SD_SIZE_SHOULD_BE_MAX = 5;
//should min number of SD with size of 4
const SD_SIZE_SHOULD_BE_MIN = 4;
const SD_SIZE_LOWER_BOUND = 3;
const SPECIAL_CASE_1 = 7;
const SPECIAL_CASE_2 = 12;
const SQUARE_ISOPERIMETRIC_QUOTIENT = 0.7854;
//for super district validation
const SD_WITH_3_NOMINEE_SHARE_THRESHOLD = 75;
const SD_WITH_4_NOMINEE_SHARE_THRESHOLD = 80;
const SD_WITH_5_NOMINEE_SHARE_THRESHOLD = 83;

const DIST_PLACEHOLDER = "<span class=\"dist_placeholder\">&nbsp;&nbsp;</span>&nbsp;&nbsp;";

function splitDistricts(numOfDistricts) {
    if (numOfDistricts <= 5) {
        // throw new IllegalArgumentException("Number of districts must be larger than 5");
    }

    var numOf5 = 0;
    var numOf4 = 0;
    var numOf3 = 0;
    var sd = [];

    switch (numOfDistricts % SD_SIZE_SHOULD_BE_MAX) {
        case 0:
            numOf5 = Math.floor(numOfDistricts / SD_SIZE_SHOULD_BE_MAX);
            break;
        case 4:
            numOf5 = Math.floor(numOfDistricts / SD_SIZE_SHOULD_BE_MAX - 1);
            numOf3 = Math.floor((numOfDistricts - numOf5 * SD_SIZE_SHOULD_BE_MAX) / SD_SIZE_LOWER_BOUND);
            break;
        case 3:
            numOf5 = Math.floor(numOfDistricts / SD_SIZE_SHOULD_BE_MAX);
            numOf3 = Math.floor((numOfDistricts - numOf5 * SD_SIZE_SHOULD_BE_MAX) / SD_SIZE_LOWER_BOUND);
            break;
        case 2:
            if (numOfDistricts === SPECIAL_CASE_1) {
                numOf4 = 1;
                numOf3 = 1;
            } else if (numOfDistricts === SPECIAL_CASE_2) {
                numOf3 = 4;
            } else {
                numOf5 = Math.floor((numOfDistricts - SPECIAL_CASE_2) / SD_SIZE_SHOULD_BE_MAX);
                numOf3 = 4;
            }
            break;
        case 1:
            if (numOfDistricts / SD_SIZE_SHOULD_BE_MAX > 1) {
                numOf5 = Math.floor(numOfDistricts / SD_SIZE_SHOULD_BE_MAX - 1);
                numOf3 = Math.floor((numOfDistricts - numOf5 * SD_SIZE_SHOULD_BE_MAX) / SD_SIZE_LOWER_BOUND);
            } else {
                numOf3 = 2;
            }
            break;
    }

    if (numOf5 > 0) {
        for (i = 0; i < numOf5; i++) {
            sd.push(SD_SIZE_SHOULD_BE_MAX);
        }
    }
    if (numOf4 > 0) {
        for (i = 0; i < numOf4; i++) {
            sd.push(SD_SIZE_SHOULD_BE_MIN);
        }
    }
    if (numOf3 > 0) {
        for (i = 0; i < numOf3; i++) {
            sd.push(SD_SIZE_LOWER_BOUND);
        }
    }

    return sd;
}

function resetSDControls() {
    $("#sdcheck").prop("checked", false);
    $("#sdModeContainer").empty();
}

var colorSet;

$(document).ready(function () {
    /**
     * Interacting with Super-districting controls will trigger the following
     */
    $("#sdcheck").click(function () {

        multiSelectMap($(this).prop("checked"));

        if ($(this).prop("checked")) {

            var sdSet;

            /* add super districting options (as a form) */
            $("#sdModeContainer").append("<form id=\"sdForm\" method=\"GET\" action=\"/verifySD\">"
                    + "<input type=\"radio\" name=\"sdmode\" value=\"manual\" id=\"manualRadio\"> Manual<br>"
                    // container for manual sd controls
                    + "<div class=\"manualSDCtrl\"></div>"

                    + "<input type=\"radio\" name=\"sdmode\" value=\"auto\" id=\"autoRadio\"> Automatic<br>"
                    // container for auto sd controls
                    + "<div class=\"autoSDCtrl\"></div>"

                    + "<input id=\"createSDBtn\" type=\"submit\" value=\"Create Super Districts\" disabled/>"
                    + "</form>"
                    );
            // bind to form submit
            $("#sdForm").submit(function (e) {
                var spinStr = '<div id="loadingAlert" class="loadingAlert alert alert-info"><i class="fa fa-circle-o-notch fa-spin" style="font-size:20px"></i> Loading</div>';
                $(spinStr).insertBefore('#infoText');
                e.preventDefault();
                // get each sd's district numbers into a set of sets
                // loop through outter set, and send each sets
                var k = 1;
                for (i = 0; i < sdSet.length; i++) {
                    // get sd set
                    var data = new Object();
                    for (j = 1; j <= sdSet[i]; j++) {
                        var name = "" + j;
                        data[name] = $("#sd_" + (i + 1) + "_" + sdSet[i] + "_" + j).html().trim();
                    }
                    data["state"] = $("#stateSelection").val();
                    data["year"] = $("#dataSelection").val();

                    // send sd set
                    var success = false;
                    $.ajax({
                        url: "/verifySD",
                        type: "GET",
                        contentType: "application/json",
                        data: data,
                        dataType: "json",
                        success: function (response, status, xhr) {
                            if (response) {
                                alert("Super district " + (k++) + " passes the bill.");
                                success = true;
                            } else {
                              alert("Super district " + (i+1)
                                      + " does not conform to House Bill #3057\n"
                                      + "Please try different combinations");
                            }
                            $('.loadingAlert').remove();
                        },
                        error: function (xhr, status, error) {
                          $('.loadingAlert').remove();

                        }
                    });

                    // if (!success) {
                    //     alert("Super district " + (i+1)
                    //             + " does not conform to House Bill #3057\n"
                    //             + "Please try different combinations");
                    //     break;
                    // }
                }
            });

            // bind
            $('input[name=sdmode]:radio').change(function () {
                multiSelectMap($(this).prop("checked"));
                // disable create super district button
                $("#createSDBtn").attr("disabled","disabled");
                if (this.value === "auto") {
                    $(".manualSDCtrl").empty();

                    var audoSDNumSelection = "<select name=\"autoSDNum\" id=\"numOfSD\">"
                            + "<option value=\"\">Number of Super-District</option>"
                            + "</select>";
                    $(".audoSDCtrl").html(audoSDNumSelection);
                    //TODO: bind functions to dropdown

                } else {
                    $(".audoSDCtrl").empty();
                    //TODO: bind functions to manual

                    console.log("debug - in manual sd: number of districts: " + splitDistricts(numDist));
                    sdSet = splitDistricts(numDist);
                    sdSet.forEach(function (item, index) {
                        var placeholders = "";
                        for (i = 0; i < item; i++) {
                            placeholders = placeholders
                                    // e.g. id="sd_1_3_2" => super district 1 contains 3 districts,
                                    // this span is for the 2nd district
                                    + "<span id=\"sd_" + (index + 1) + "_" + item + "_" + (i + 1) + "\">"
                                    + DIST_PLACEHOLDER
                                    + "</span>";
                        }
                        // e.g. id="sd_1_3" => super district 1 contains 3 districts
                        $(".manualSDCtrl").append(
                                "<div id=\"sd_" + (index + 1) + "_" + item + "\">"
                                //+ "Super-district " + (index + 1) + " (Set of " + item + "): "
                                + "Super-district " + (index + 1) + ": "
                                + placeholders
                                + "</div>");

                        // TODO: add insert the following in here on district selection
                        // $(".manualSDCtrl").append("<span><input type=\"hidden\" value="+  + "/>"+  +"</span>");

                        // $(".manualSDCtrl").append();
                    });
                    colorSet = rgbColors(sdSet.length);


                }
            });

        } else {
            $("#sdModeContainer").empty();
        }
    });


});
