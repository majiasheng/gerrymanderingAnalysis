<%@include file="/WEB-INF/views/include/header.jsp" %>
<!--TODO: check if normal user has upload permission-->
<c:if test="${empty user || not user.isAdmin()}">
    <c:redirect url="/"/>
</c:if>

<h1>File Upload</h1>
<hr>
${msg}
<h2 style="color:red">TODO: check all files are included before sending request to server
<br>show user what is the exact format for each of the csv file
</h2>
<h5>Please upload files in .csv format</h5><br>
<form action="/upload" enctype="multipart/form-data" method="POST">
    Geospatial Data<input type="file" name="geoData" required/><br>
    Demographic Data<input type="file" name="demographicData" required/><br>
    Election Data<input type="file" name="electionData" required/><br>
    <input type='submit' value="Upload">
</form>

<%@include file="/WEB-INF/views/include/footer.jsp" %>