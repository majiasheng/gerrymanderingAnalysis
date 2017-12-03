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