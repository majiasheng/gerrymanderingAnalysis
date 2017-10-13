
		</div>
	</div>
	<!-- end map -->
	
	<!-- controls -->
	<div class="row">
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
			<%@include file="/WEB-INF/views/include/super-district-control.jsp" %>

		</form>
	</div>
	<!-- end controls -->
	
	<!-- info window -->
	<div class="row">
		<div class="col-md-12">
			 <span class="label label-default">Info</span>
			 
		</div>
	</div>
	<!-- end info window -->
	
</div>

<!-- end body -->