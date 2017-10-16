

    

<div class="checkbox">
	<label>
            <script>
        function checkSD() {
        var radio= document.getElementById("sdcheck").checked;
        if(radio===true){
            radio = document.getElementById("radioButton");
            radio.style.display ="block";
        }else{
            radio = document.getElementById("radioButton");
            radio.style.display = "none";
        }
        
    }


</script>
		<input type="checkbox" id = "sdcheck" onclick="checkSD()"/> Create Super-District
		<select name="superDistricting" >
			<!-- TODO: 
				 - set "disabled to a jsp variable"
				 - read from an external file -->
			<option value="">Number of Super-District</option>
		</select>
	</label>
    
        
    
        <label id="radioButton" style="display:none">
		<input type="radio" name="sdmode" value="manual"> Manual<br>
                <input type="radio" name="sdmode" value="automatic"> Automatic<br>
	</label>
        <label>	
            <button type="submit" class="btn btn-default" style="display:block"; %{isDisabled}>
		Create
            </button>
        </label>
</div> 