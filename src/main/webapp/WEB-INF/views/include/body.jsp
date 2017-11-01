<!-- body -->
<div class="row">
	<div class="col-md-8">
		<div class="row">
			<div class="col-md-12" >
			
				<!-- map -->
				<div id="mapid" style="height: 500px;">
					<script src="/resources/js/init-map.js"></script>
				</div>
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
					 	<!--populate state dropdown with external data-->
						<c:forEach var="state" items="${init.config.states}"> 
							<option value ="${state.key}">${state.value}</option>
						</c:forEach>
					
				 </select>
				 <select name="dataSelection" disabled>
					 <!-- TODO: read from an external file -->
					 <option value="">Data</option>
				 </select>
				 <select name="gerrymanderingMeasure" >
					<!--populate measure dropdown with external data-->
					<option value="">Gerrymandering Measure</option>

					<c:forEach var="measure" items="${init.config.measures}"> 
						<option value ="${measure}">${measure}</option>
					</c:forEach>
				 </select>
			</div>
			<%@include file="/WEB-INF/views/include/super-district-control.jsp" %>

		</form>
	</div>
</div>

<!-- end body -->