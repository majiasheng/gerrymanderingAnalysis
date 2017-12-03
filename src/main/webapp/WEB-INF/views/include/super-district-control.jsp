<div class="checkbox sdctrl">
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
        </script>
        <!--<input type="checkbox" id="sdcheck" onclick="checkSD()" disabled/> Create Super-District-->
        <input type="checkbox" id="sdcheck" disabled/> Create Super-District
    </label>

    <div id="sdModeContainer">
<!--        <input type="radio" name="sdmode" value="manual" id="manualRadio" onclick="checkManAuto()">Manual<br>
        <input type="radio" name="sdmode" value="automatic" id="autoRadio" onclick="checkManAuto()">Automatic<br>
        <select name="superDistricting" id="numOfSD" style="display:none">
            <option value="">Number of Super-District</option>
        </select>-->
    </div>

    <!--<label>-->	
        <!--<button type="submit" class="btn btn-default" id="createButton" style="display:none">Create</button>-->
    <!--</label>-->
</div> 