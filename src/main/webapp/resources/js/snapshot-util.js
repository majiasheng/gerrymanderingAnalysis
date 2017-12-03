
const STATE_SELECTION = "stateSelection";
const DATA_SELECTION = "dataSelection";
const MEASURE_SELECTION = "gerrymanderingMeasure";
$(document).ready(function () {
    $("#saveWorkBtn").click(function (e) {
        /* TODO: pack all the controls and their values into an object
         */

        var controls = new Object();

        controls[STATE_SELECTION] = $("#" + STATE_SELECTION).val();

        if (!controls[STATE_SELECTION]) {
            // no state has been selected, inform user, and return
            alert("The workspace is still a blank slate :)\n"
                + "Add something to it");
            return;
        }

        controls[DATA_SELECTION] = $("#" + DATA_SELECTION).val();
        controls[MEASURE_SELECTION] = $("#" + MEASURE_SELECTION).val();
        // sd

        console.log(controls);
        
        /* TODO: send requst to save work */
        


    });
});
