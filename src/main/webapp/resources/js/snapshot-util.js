const USERID = "userId";
const SELECTED_STATE = "selectedState";
const SELECTED_YEAR = "selectedYear";
const SELECTED_TEST = "selectedTest";
const MANUAL_SD_SET = "manualSDSet";
const AUTO_SD_SET = "autoSDSet";

/**
 * 
 * @param {type} whichSet either MANUAL_SD_SET or AUTO_SD_SET
 * @returns {String} json string in the format of 
 *      {sd_i:
 *          [a,b,c...]
 *      }
 */
function getSDSet(whichSet) {
    var sdSet = "";
    if (whichSet === AUTO_SD_SET) {
        
    } else {
        
    }
    
    return sdSet;
}

$(document).ready(function () {
//    $("#saveWorkBtn").click(function (e) {
//        /* TODO: pack all the controls and their values into an object
//         */
//        
//        var controls = new Object();
//
//        controls[STATE_SELECTION] = $("#" + STATE_SELECTION).val();
//
//        if (!controls[STATE_SELECTION]) {
//            // no state has been selected, inform user, and return
//            alert("The workspace is still a blank slate :)\n"
//                + "Add something to it");
//            return;
//        }
//
//        controls[DATA_SELECTION] = $("#" + DATA_SELECTION).val();
//        controls[MEASURE_SELECTION] = $("#" + MEASURE_SELECTION).val();
//        // sd
//
//        console.log(controls);
//        
//        /* TODO: send requst to save work */
//
//    });

    $("#snapshotForm").submit(function (e) {
        // prevent form from submitting
        e.preventDefault();

        /* pack all the controls and their values into an object*/
        var controls = new Object();

        controls[USERID] = this.userId.value;
        controls[SELECTED_STATE] = $("#" + SELECTED_STATE).val();

        if (!controls[SELECTED_STATE]) {
            // no state has been selected, inform user, and return
            alert("The workspace is still a blank slate :)\n"
                    + "Add something to it");
            return;
        }

        controls[SELECTED_YEAR] = $("#" + SELECTED_YEAR).val();
        controls[SELECTED_TEST] = $("#" + SELECTED_TEST).val();
        // TODO: make sd jsons
        controls[MANUAL_SD_SET] = "{manual:"+ getSDSet(MANUAL_SD_SET) + "}";
        controls[AUTO_SD_SET] = "{auto:"+ getSDSet(AUTO_SD_SET) + "}";
        

        console.log(controls);

        /* send requst to save work */
        $.ajax({
            url: $(this).attr('action'),
            type: "POST",
            contentType: "application/json",
            data: controls,
            dataType: "json",
            success: function (response, status, xhr) {
                if(response)
                    alert("Work saved successfully");
                else
                    alert("Sorry :( \nFailed to save work");
            },
            error: function (xhr, textStatus, errorThrown) {
                console.log(textStatus + "; errorThrown: " + errorThrown);
                alert("ERROR OCCURED WHEN SAVING WORK");
            }
        });

    });

});
