<!-- body -->
<div class="row">
	<div class="col-md-8">
		<div class="row">
			<div class="col-md-12" >
			
				<!-- map -->
				<div id="mapid">
					<script src="/resources/js/init-map.js"></script>
				</div>
			</div>
		</div>
		
	</div>
	<div class="col-md-4">
		<form role="form">
			<div class="form-group">

				<!-- state drop down -->
				<select name="stateSelection" id="stateSelection">
					<option value="">State</option>
						<c:forEach var="state" items="${init.config.states}"> 
							<option value ="${state.key}">${state.value}</option>
						</c:forEach>
				</select>
				<!-- end state drop down -->

				<!-- data drop down -->
				<select name="dataSelection" id="dataSelection" disabled>
					<option value="${init.config.defaultYear}">Data</option> <!-- default -->
				</select>
				<!-- end data drop down -->

				<!-- measure drop down -->
				<select name="gerrymanderingMeasure" id="gerrymanderingMeasure" disabled>
					<option value="">Gerrymandering Measure</option>
					<c:forEach var="measure" items="${init.config.measures}"> 
						<option value ="${measure}">${measure}</option>
					</c:forEach>
				</select>
				<!-- end measure drop down -->

			</div>
			<%@include file="/WEB-INF/views/include/super-district-control.jsp" %>

			<!-- Info window -->
			<div class="row">
				<div class="col-md-12">
					<span class="label label-default">Info</span>
					<div class="info">
						
					</div>
				</div>
			</div>

		</form>
	</div>
</div>
<!-- end body -->
