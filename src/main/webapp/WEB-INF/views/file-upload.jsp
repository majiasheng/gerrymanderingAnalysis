<%@include file="/WEB-INF/views/include/header.jsp" %>
<!--TODO: check if normal user has upload permission-->
<c:if test="${empty user || not user.isAllowedToUpload()}">
    <c:redirect url="/"/>
</c:if>

<h1>File Upload</h1>
<hr>
${msg}
<%-- <h2 style="color:red">TODO: show user what is the exact format for each of the csv file --%>
</h2>
<h5>Please upload files in .csv format</h5><br>
<form action="/upload" enctype="multipart/form-data" method="POST">
    Geospatial Data<input type="file" name="geoData" required/><br>
    Demographic Data<input type="file" name="demographicData" required/><br>
    Election Data<input type="file" name="electionData" required/><br>
    <input type='submit' value="Upload">
</form>
<hr>
<h3>File Formats and Samples:</h3>
<div>
    <h5>Geospatial .CSV File</h5>
    <table>
        <tr>
            <th>District Number</th>
            <th>State(Short Name)</th>
            <th>Congress</th>
            <th>geojson</th>
        </tr>
        <tr>
            <td>1</td>
            <td>NY</td>
            <td>116</td>
            <td>{ "type": "Feature", "properties": { "STATENAME": "New York", "DISTRICT": "11" }, "geometry": { "type": "Polygon", "coordinates": [ [ [ -73.983525, 40.67203 ], [ -73.983024, 40.672671 ], [ -73.982501, 40.673278 ], [ -73.981958, 40.673804 ], [ -73.981108, 40.675003 ] ] ] } }</td>
        </tr>

    </table>
</div>
<br>
<div>
    <h5>Demographic .CSV File</h5>(*Note: Demographic files can be obtained from Census Bureau, and can be used as it is)
    <table>
        <tr>
            <th>Geography</th>
            <th>Total population</th>
            <th>One race</th>
            <th>White</th>
            <th>Black or African American</th>
            <th>American Indian and Alaska Native</th>
            <th>Asian</th>
            <th>Native Hawaiian and Other Pacific Islander</th>
            <th>Some other race</th>
            <th>Two or more races</th>
        </tr>
        <tr>
            <td>Congressional District 1 (115th Congress) Alabama</td>
            <td>704457</td>
            <td>690895</td>
            <td>474871</td>
            <td>192647</td>
            <td>7670</td>
            <td>9224</td>
            <td>192</td>
            <td>6291</td>
            <td>13562</td>
        </tr>

    </table>
</div>
<br>
<br>
<%@include file="/WEB-INF/views/include/footer.jsp" %>
