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

const DIST_PLACEHOLDER = "<span class=\"dist_placeholder\" style=\"background-color:rgb(248,248,248);\">&nbsp;&nbsp;</span>&nbsp;&nbsp;";

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

$(document).ready(function () {
    /**
     * Interacting with Super-districting controls will trigger the following
     */
    $("#sdcheck").click(function () {

        if ($(this).prop("checked")) {

            /* add super districting options (as a form) */
            $("#sdModeContainer").append("<form id=\"sdForm\" method=\"POST\" action=\"/createSD\">"
                    + "<input type=\"radio\" name=\"sdmode\" value=\"manual\" id=\"manualRadio\"> Manual<br>"
                    // container for manual sd controls
                    + "<div class=\"manualSDCtrl\"></div>"

                    + "<input type=\"radio\" name=\"sdmode\" value=\"auto\" id=\"autoRadio\"> Automatic<br>"
                    // container for auto sd controls
                    + "<div class=\"autoSDCtrl\"></div>"

                    + "<input type=\"submit\" value=\"Create Super Districts\"/>"
                    + "</form>"
                    );

            // bind 
            $('input[name=sdmode]:radio').change(function () {
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

                    console.log("in manual: number of districts: " + splitDistricts(numDist));

                    var sdSet = splitDistricts(numDist);
                    sdSet.forEach(function (item, index) {
                        var placeholders="";
                        for(i=0;i<item;i++) {
                            placeholders+=DIST_PLACEHOLDER;
                        }
                        // e.g. id="sd_1_3" => super district 1 contains 3 districts
                        $(".manualSDCtrl").append(
                                "<div id=\"sd_" + (index + 1) + "_" + item + "\">"
                                + "Super-district " + (index + 1) + " (Set of " + item + "): "
                                + placeholders
                                + "</div>");

                        // TODO: add insert the following in here on district selection
                        // $(".manualSDCtrl").append("<span><input type=\"hidden\" value="+  + "/>"+  +"</span>");

                        // $(".manualSDCtrl").append();
                    });


                }
            });
        } else {
            $("#sdModeContainer").empty();
        }
    });


});