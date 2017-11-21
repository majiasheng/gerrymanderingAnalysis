<!-- body -->
<div id="bodyContainer" class="row">
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
                <select autocomplete="off" name="stateSelection" id="stateSelection">
                    <option value="">State</option>
                    <c:forEach var="state" items="${config.states}">
                        <option value ="${state.key}">${state.value}</option>
                    </c:forEach>
                </select>
                <!-- end state drop down -->

                <!-- data drop down -->
                <select autocomplete="off" name="dataSelection" id="dataSelection" disabled>
                    <option value="${config.defaultYear}">Data</option> <!-- default -->
                </select>
                <!-- end data drop down -->

                <!-- measure drop down -->
                <select autocomplete="off" name="gerrymanderingMeasure" id="gerrymanderingMeasure" disabled>
                    <option value="">Gerrymandering Measure</option>
                    <c:forEach var="measure" items="${config.measures}">
                        <option value ="${measure}">${measure}</option>
                    </c:forEach>
                </select>
                <!-- end measure drop down -->
            </div>

            <%@include file="/WEB-INF/views/include/super-district-control.jsp" %>

            <!-- Info window -->
            <div class="row" id="infowindow">
                <div class="col-md-12">
                    <span class="label label-default">Info</span>
                    <div class="info" id="infoText">

                    </div>
                </div>
            </div>
            <!-- End Info window -->

        </form>
    </div>
</div>
<hr>
<div id="testResultContainer" class="row">
    <div id="testResult"></div>
</div>
<!-- end body -->
