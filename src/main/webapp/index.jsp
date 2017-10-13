<%@include file="/WEB-INF/views/include/header.jsp" %>

<!-- body -->
<div class="row">
	<div class="col-md-8">
		<div class="row">
			<div class="col-md-12" >
			
				<!-- map -->
				<div id="mapid" style="height: 500px;"></div>
				<script>
				var mymap = L.map('mapid').setView([36.4051421,-95.5136459], 3.91);
				L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
				    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
				    maxZoom: 18,
				    id: 'mapbox.streets',
				    accessToken: 'pk.eyJ1IjoibWEzMDgiLCJhIjoiY2o4ZGxoa3hyMHJrdDMwbzA5emM5Y3pzcSJ9.ZsR3x4DhKRrkTD7goSnE3w'
				}).addTo(mymap);
				</script>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				 <span class="label label-default">Info</span>
				 
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<form role="form">
			<div class="form-group">
				 <select name="stateSelection">
					 <option value="">State</option>
					 <!-- TODO: read from an external file, 
						 move each of the "disabled" to a separate var -->
				 </select>
				 <select name="dataSelection" disabled>
					 <!-- TODO: read from an external file -->
					 <option value="">Data</option>
				 </select>
				 <select name="gerrymanderingMeasure" disabled>
					 <!-- TODO: read from an external file -->
					 <option value="">Gerrymandering Measure</option>
				 </select>
			</div>

			<div class="checkbox">
				<label>
					<input type="checkbox" disabled/> Create Super-District
					<select name="superDistricting" disabled>
						<!-- TODO: 
							 - set "disabled to a jsp variable"
							 - read from an external file -->
						<option value="">Number of Super-District</option>
					</select>
				</label>
				<button type="submit" class="btn btn-default" disabled>
					Create
				</button>
			</div> 

		</form>
	</div>
</div>

<!-- end body -->

<%@include file="/WEB-INF/views/include/footer.jsp" %>	