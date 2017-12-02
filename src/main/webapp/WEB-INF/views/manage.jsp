<%-- 
    Document   : manage
    Created on : Nov 20, 2017, 10:24:47 PM
    Author     : majiasheng
--%>

<%@include file="/WEB-INF/views/include/header.jsp" %>

<!--send home if user is empty or user is not admin-->
<c:if test="${empty user || not user.isAdmin()}">
    <c:redirect url="/"/>
</c:if>

<h2>Manage User</h2>
<hr>
<div id="msg"></div>
<h2 style="color:red">TODO: ask for confirmation before deleting user</h2>
<table>
    <tr>
        <th>user</th>
<!--        <th>Can Save Result</th>
        <th>Can Delete Result</th>-->
        <th>Can Upload</th>
    </tr>
    <c:forEach var="normalUser" items="${normalUsers}">
        <!--TODO: list all normal users, and add option for admin to edit-->
        <tr id="${normalUser.username}">
            <td>${normalUser.username}</td>
            <form class="adminManageForm" method="GET" action="#">
                <td><input type="checkbox" name="allowedToUpload"<c:if test="${normalUser.isAllowedToUpload()}"> checked</c:if>></td>
                <input type="hidden" name="username" value="${normalUser.username}">
                <input type="hidden" name="firstname" value="${normalUser.firstName}">
                <input type="hidden" name="lastname" value="${normalUser.lastName}">
                <td><input type="submit" name="operation" value="Update"></td>
                <td><input type="submit" name="operation" value="Delete"></td>
            </form>
        </tr>
    </c:forEach>
</table>

<script src="/resources/js/admin.js"></script>

<%@include file="/WEB-INF/views/include/footer.jsp" %>