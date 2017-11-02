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
				<select name="stateSelection">
					<option value="">State</option>
						<!--populate state dropdown with external data-->
						<c:forEach var="state" items="${init.config.states}"> 
						<!-- TODO: add listener, onClick, send ajax GET 
							handler adds attribute "selectedState" -->
							<option value ="${state.key}">${state.value}</option>
						</c:forEach>
				</select>
				<!-- end state drop down -->

				<!-- data drop down -->
				<c:choose>
						<c:when test="${empty dataYearSet}">
							<select name="dataSelection" disabled><option value="">Data</option></select>
						</c:when>
						<c:otherwise>
							<select name="dataSelection">
								<!-- TODO: populate option with data from db -->
								<!-- TODO: add listener, onClick, send ajax GET 
								handler adds attribute "selectedDataSet" -->
								<option value="">Data</option>
								<c:forEach var="year" items="${dataYearSet}"> 
									<option value ="${year}">${year}</option>
								</c:forEach>
							</select>
				</c:otherwise>
				</c:choose>
				<!-- end data drop down -->

				<!-- measure drop down -->
				<c:choose>
						<c:when test="${empty dataYearSet}">
							<select name="gerrymanderingMeasure" disabled><option value="">Gerrymandering Measure</option></select>
						</c:when>
						<c:otherwise>
							<select name="gerrymanderingMeasure" >
								<!--populate measure dropdown with external data-->
								<option value="">Gerrymandering Measure</option>
								<!--TODO: add event listener -->
								<c:forEach var="measure" items="${init.config.measures}"> 
									<option value ="${measure}">${measure}</option>
								</c:forEach>
							</select>
						</c:otherwise>
				</c:choose>
				<!-- end measure drop down -->

			</div>
			<%@include file="/WEB-INF/views/include/super-district-control.jsp" %>

		</form>
	</div>
</div>
<!-- end body -->
