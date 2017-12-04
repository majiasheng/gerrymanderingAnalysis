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

$(document).ready(function () {
    /**
     * Interacting with Super-districting controls will trigger the following
     */
    $("#sdcheck").click(function () {

        if ($(this).prop("checked")) {
            
            /* add super districting options */
            
            $("#sdModeContainer").append("<input type=\"radio\" name=\"sdmode\" value=\"manual\" id=\"manualRadio\"> Manual<br>");
            // container for manual sd controls
            $("#sdModeContainer").append("<div class=\"manualSDCtrl\"></div>");
            
            $("#sdModeContainer").append("<input type=\"radio\" name=\"sdmode\" value=\"auto\" id=\"autoRadio\"> Automatic<br>");
            // container for auto sd controls
            $("#sdModeContainer").append("<div class=\"audoSDCtrl\"></div>");
            
            // bind 
            $('input[name=sdmode]:radio').change(function () {
                if (this.value === "auto") {
                    var audoSDNumSelection = "<select name=\"autoSDNum\" id=\"numOfSD\">"
                            + "<option value=\"\">Number of Super-District</option>"
                            + "</select>";
                    $(".audoSDCtrl").html(audoSDNumSelection);
                    //TODO: bind functions to dropdown
                    
                } else {
                    $(".audoSDCtrl").empty();
                    //TODO: bind functions to manual 
                    //TODO: call splitDistrict?
                    console.log("in manual: number of districts: " + splitDistricts(numDist));
                    
                }
            });
        } else {
            $("#sdModeContainer").empty();
        }
    });


});