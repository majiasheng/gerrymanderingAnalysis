<%@include file="/WEB-INF/views/include/header.jsp" %>


<h2>Compare</h2>

<!--<div class="row">-->
    <!-- 1 -->
    <!--<div class="col-md-6">-->
        <%@include file="/WEB-INF/views/include/compare-column-upper.jsp" %>

        <!-- map -->
        <div id="mapid" style="height: 500px;">
            <script src="/resources/js/init-map.js"></script>
        </div>

        <%@include file="/WEB-INF/views/include/compare-column-lower.jsp" %>
    <!-- end 1 -->

    <!-- TODO: add a border to separate two maps -->

    <!-- 2	 -->
    <!--<div class="col-md-6"">-->
        <%@include file="/WEB-INF/views/include/compare-column-upper.jsp" %>

        <!-- map -->
        <div id="mapid2" style="height: 500px;">
            <script src="/resources/js/init-map2.js"></script>
        </div>

        <%@include file="/WEB-INF/views/include/compare-column-lower.jsp" %>

    <!--</div>-->
    <!-- end 2 -->
  </div>

<!--</div>-->
<!-- end row -->

<%@include file="/WEB-INF/views/include/footer.jsp" %>
