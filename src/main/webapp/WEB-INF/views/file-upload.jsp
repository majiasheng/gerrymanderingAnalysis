<%@include file="/WEB-INF/views/include/header.jsp" %>
<!--TODO: check if normal user has upload permission-->
<c:if test="${empty user || not user.isAdmin()}">
    <c:redirect url="/"/>
</c:if>

<h1>File Upload</h1>
<hr>
${msg}
<h5>Please upload files in .csv format</h5><br>
<form action="/upload" method="POST">
    Geospatial Data<input type="file" name="geoData"/><br>
    Dempgraphic Data<input type="file" name="demoData"/><br>
    Election Data<input type="file" name="electionData"/><br>
    <input type='submit' value="Upload">
</form>

<%@include file="/WEB-INF/views/include/footer.jsp" %>