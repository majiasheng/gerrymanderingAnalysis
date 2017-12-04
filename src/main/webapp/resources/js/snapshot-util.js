const USERID = "userId";
const SELECTED_STATE = "stateSelection";
const SELECTED_YEAR = "dataSelection";
const SELECTED_TEST = "gerrymanderingMeasure";
const MANUAL_SD_SET = "manualSDSet";
const AUTO_SD_SET = "autoSDSet";

/**
 * 
 * @param {type} whichSet either MANUAL_SD_SET or AUTO_SD_SET
 * @param {type} numDist 
 * @returns {String} json string in the format of 
 *      
 *      Before 
 *	<div id="sd_1_5">
 *		Super-district 1: 
 *	<span id="sd_1_5_1">
 *          <span class="dist_placeholder">&nbsp;&nbsp;</span>
 *      </span>&nbsp;&nbsp;
 *          <span id="sd_1_5_2">
 *		<span class="dist_placeholder">&nbsp;&nbsp;</span>
 *          </span>&nbsp;&nbsp;
 *	</div>
 *	
 *	After	
 *	<div id="sd_1_2">
 *          Super-district 1: 
 *          <span id="sd_1_5_1">
 *              2
 *          </span>&nbsp;&nbsp;
 *          <span id="sd_1_5_2">
 *              5
 *          </span>&nbsp;&nbsp;
 *	</div>
 */
function getSDSet(whichSet, numDist) {
    
    //TODO: split dis to get sets, loop each set 
    
    var sdSet = "";
    
    var sdSets = splitDistricts(numDist);
    
    sdSets.forEach(function (numDistInCurrentSD, index) {
        if (index>0) {
            sdSet = sdSet + ",";
        }
        sdSet = sdSet + "{ \"number\": " + numDistInCurrentSD + ",";
        sdSet = sdSet + "\"districts\": " + "[";
        for(i=1;i<=numDistInCurrentSD;i++) {
            if(i>1) {
                sdSet += ",";
            }
            
            var id = "sd_"+numDistInCurrentSD+"_"+i;
            //TODO: grab value from dom using the id above
            var val = $("#"+id).html();
            if (!isNaN(val)) {
                sdSet += val;
            } else {
                // set to 0 if the span tag still has placeholder
                sdSet += "0";
            }
        }
        sdSet = sdSet + "]}";
    });
    
    if (whichSet === AUTO_SD_SET) {

    } else {

    }

    return sdSet;
}

$(document).ready(function () {

    $("#snapshotForm").submit(function (e) {
        // prevent form from submitting
        e.preventDefault();

        /* pack all the controls and their values into an object*/
        var controls = new Object();

        controls[USERID] = this.userId.value;
        controls[SELECTED_STATE] = $("#" + SELECTED_STATE).val();

        console.log(controls[SELECTED_STATE]);

        if (!controls[SELECTED_STATE]) {
            // no state has been selected, inform user, and return
            alert("The workspace is still a blank slate :)\n"
                    + "Add something to it");
            return;
        }

        controls[SELECTED_YEAR] = $("#" + SELECTED_YEAR).val();
        controls[SELECTED_TEST] = $("#" + SELECTED_TEST).val();
        
        // TODO: make sd jsons, check which mode
        
        if ($('input[name=sdmode]:radio').val() === "manual")
            controls[MANUAL_SD_SET] = "{\"manual\":[" + getSDSet(MANUAL_SD_SET, numDist) + "]}";
        else if ($('input[name=sdmode]:radio') === "auto")
            controls[AUTO_SD_SET] = "{\"auto\":[" + getSDSet(AUTO_SD_SET, numDist) + "]}";
        else 
            console.log("no sd creation in progress");


        console.log(controls);

        //TODO
        /* send requst to save work */
//        $.ajax({
//            url: $(this).attr('action'),
//            type: "POST",
//            contentType: "application/json",
//            data: controls,
//            dataType: "json",
//            success: function (response, status, xhr) {
//                if (response)
//                    alert("Work saved successfully");
//                else
//                    alert("Sorry :( \nFailed to save work");
//            },
//            error: function (xhr, textStatus, errorThrown) {
//                console.log(textStatus + "; errorThrown: " + errorThrown);
//                alert("ERROR OCCURED WHEN SAVING WORK");
//            }
//        });

    });

});
