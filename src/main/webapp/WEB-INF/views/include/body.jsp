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

				<!-- state drop down -->
				<select name="stateSelection" id="stateSelection">
					<option value="">State</option>
						<!--populate state dropdown with external data-->
						<c:forEach var="state" items="${init.config.states}"> 
							<option value ="${state.key}">${state.value}</option>
						</c:forEach>
				</select>
				<!-- end state drop down -->

				<!-- data drop down -->
					<!-- TODO: populate option with data from db -->
					<!-- TODO: add listener, onClick, send ajax GET 
					handler adds attribute "selectedDataSet" -->
				<select name="dataSelection" id="dataSelection" disabled>
					<!-- FIXME: default year not needed from config, can just use the first option as the default -->
					<option value="${init.config.defaultYear}">Data</option> <!-- default -->
					<!-- <c:forEach var="year" items="${dataYearSet}"> 
						<option value ="${year}">${year}</option>
					</c:forEach> -->
				</select>
				<!-- end data drop down -->

				<!-- measure drop down -->
				<select name="gerrymanderingMeasure" id="gerrymanderingMeasure" disabled>
					<!--populate measure dropdown with external data-->
					<option value="">Gerrymandering Measure</option>
					<!--TODO: add event listener -->
					<c:forEach var="measure" items="${init.config.measures}"> 
						<option value ="${measure}">${measure}</option>
					</c:forEach>
				</select>
				<!-- end measure drop down -->

			</div>
			<%@include file="/WEB-INF/views/include/super-district-control.jsp" %>

		</form>
	</div>
</div>
<!-- end body -->
