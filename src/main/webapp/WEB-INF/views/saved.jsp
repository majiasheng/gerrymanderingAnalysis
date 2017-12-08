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
                    <th></th>
                    <th>Time Saved</th>
                    <th>State</th>
                    <th>Year</th>
                    <th>Test</th>
                    <th></th>
                </tr>

                <tr>
                <form class="savedWorkForm" action="#"> 
                    <td>${loop.index+1}</td>
                    <td>${snapshot.timeSaved}</td>
                    <td><input type="text" name="state" value="${snapshot.selectedState}" readonly></td>
                    <td><input type="text" name="year" value="${snapshot.selectedYear}" readonly></td>
                    <td><input type="text" name="test" value="${snapshot.selectedTest}" readonly></td>
                    <!--<td><input class="savedWorkFormBtn" type="submit" value='View'/></td>-->
                    <td><button class="savedWorkFormBtn" data-toggle="modal" data-target="#testResultModal">View</button>
                </form>
            </tr>
        </table>

    </c:forEach>
        <%@include file="/WEB-INF/views/include/test-result-modal.jsp" %>
</c:otherwise>
</c:choose>
<%@include file="/WEB-INF/views/include/footer.jsp" %>