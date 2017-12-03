<div class="checkbox">
    <label>
        <script>
            function checkSD() {
                var radio = document.getElementById("sdcheck").checked;
                if (radio === true) {
                    radio = document.getElementById("radioButton");
                    radio.style.display = "block";
                    document.getElementById("createButton").style.display = "block"
                } else {
                    radio = document.getElementById("radioButton");
                    radio.style.display = "none";
                    document.getElementById("createButton").style.display = "none"
                }
            }

            function checkManAuto() {
                var radioManual = document.getElementById("manualRadio");
                var radioAuto = document.getElementById("autoRadio");
                if (radioAuto.checked === true) {
                    document.getElementById("numOfSD").style.display = "block";
                } else {
                    document.getElementById("numOfSD").style.display = "none";
                }
            }


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
            /*
             * K = SD size, V = number of SD
             * provided numOfDistricts must be larger than 5 
             * thus, front end has already filtered out the invalid states 
             */
            function splitDistricts(numOfDistricts) {
                if (numOfDistricts <= 5) {
                    alert("Need more than 5 districts!");
                    return;
                }

                var numOf5 = 0;
                var numOf4 = 0;
                var numOf3 = 0;
                var sd = [];
                switch (numOfDistricts % SD_SIZE_SHOULD_BE_MAX) {
                    case 0:
                        numOf5 = numOfDistricts / SD_SIZE_SHOULD_BE_MAX;
                        break;
                    case 4:
                        numOf5 = numOfDistricts / SD_SIZE_SHOULD_BE_MAX - 1;
                        numOf3 = (numOfDistricts - numOf5 * SD_SIZE_SHOULD_BE_MAX) / SD_SIZE_LOWER_BOUND;
                        break;
                    case 3:
                        numOf5 = numOfDistricts / SD_SIZE_SHOULD_BE_MAX;
                        numOf3 = (numOfDistricts - numOf5 * SD_SIZE_SHOULD_BE_MAX) / SD_SIZE_LOWER_BOUND;
                        break;
                    case 2:
                        if (numOfDistricts == SPECIAL_CASE_1) {
                            numOf4 = 1;
                            numOf3 = 1;
                        } else if (numOfDistricts == SPECIAL_CASE_2) {
                            numOf3 = 4;
                        } else {
                            numOf5 = (numOfDistricts - SPECIAL_CASE_2) / SD_SIZE_SHOULD_BE_MAX;
                            numOf3 = 4;
                        }
                        break;
                    case 1:
                        if (numOfDistricts / SD_SIZE_SHOULD_BE_MAX > 1) {
                            numOf5 = numOfDistricts / SD_SIZE_SHOULD_BE_MAX - 1;
                            numOf3 = (numOfDistricts - numOf5 * SD_SIZE_SHOULD_BE_MAX) / SD_SIZE_LOWER_BOUND;
                        } else {
                            numOf3 = 2;
                        }
                        break;
                    default:

                }

                if (numOf5 !== 0) {
                    for (i = 0; i < numOf5; i++) {
                        sd.push(SD_SIZE_SHOULD_BE_MAX);
                    }
                }
                if (numOf4 !== 0) {
                    for (i = 0; i < numOf4; i++) {
                        sd.push(SD_SIZE_SHOULD_BE_MIN);
                    }
                }
                if (numOf3 !== 0) {
                    for (i = 0; i < numOf3; i++) {
                        sd.push(SD_SIZE_LOWER_BOUND);
                    }
                }

                return sd;
            }

            console.log("splitDistricts(): " + splitDistricts(6));
        </script>
        <input type="checkbox" id="sdcheck" onclick="checkSD()" disabled/> Create Super-District
    </label>

    <label id="radioButton" style="display:none">
        <input type="radio" name="sdmode" value="manual" id="manualRadio" onclick="checkManAuto()">Manual<br>
        <input type="radio" name="sdmode" value="automatic" id="autoRadio" onclick="checkManAuto()">Automatic<br>
        <select name="superDistricting" id="numOfSD" style="display:none";>
            <!-- TODO: 
                     - set "disabled to a jsp variable"
                     - read from an external file -->
            <option value="">Number of Super-District</option>
        </select>
    </label>

    <label>	
        <button type="submit" class="btn btn-default" id="createButton" style="display:none"; %{isDisabled}>Create</button>
    </label>
</div> 