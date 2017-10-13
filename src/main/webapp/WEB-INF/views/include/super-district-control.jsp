<div class="checkbox">
	<label>
		<input type="checkbox" %{isDisabled}/> Create Super-District
		<select name="superDistricting" %{isDisabled}>
			<!-- TODO: 
				 - set "disabled to a jsp variable"
				 - read from an external file -->
			<option value="">Number of Super-District</option>
		</select>
	</label>
	<button type="submit" class="btn btn-default" %{isDisabled}>
		Create
	</button>
</div> 