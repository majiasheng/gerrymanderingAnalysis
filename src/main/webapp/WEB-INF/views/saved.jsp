<%-- 
    Document   : saved
    Created on : Dec 8, 2017, 12:42:32 AM
    Author     : majiasheng
--%>

<%@include file="/WEB-INF/views/include/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!--send home if user is empty-->
<c:if test="${empty user}">
    <c:redirect url="/"/>
</c:if>
<h2>Saved Works</h2>
<hr>
${msg}
<c:choose>
    <c:when test="${fn:length(snapshots) <= 0}">
        <h3>You haven't saved anything yet :)</h3>
    </c:when>
    <c:otherwise>
        <c:forEach var="snapshot" items="${snapshots}" varStatus="loop">
            <table>
                <tr>

                    <th>Time Saved</th>
                    <th>State</th>
                    <th>Year</th>
                    <th>Test</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>

                <tr>
                <form>
                    <td>${loop.index+1}</td>
                    <td>${snapshot.timeSaved}</td>
                    <td>${snapshot.selectedState}</td>
                    <td>${snapshot.selectedYear}</td>
                    <td>${snapshot.selectedTest}</td>
                    <td></td>
                    <input type="submit" value='view'/>
                </form>
            </tr>
        </table>

    </c:forEach>
</c:otherwise>
</c:choose>
<%@include file="/WEB-INF/views/include/footer.jsp" %>