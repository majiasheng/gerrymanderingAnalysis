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
${msg}
<table>
    <tr>
        <th>user</th>
        <th>Can Save Result</th>
        <th>Can Delete Result</th>
        <th>Can Upload</th>
    </tr>
    <c:forEach var="normalUser" items="${normalUsers}">
        <!--TODO: list all normal users, and add option for admin to edit-->
        <tr>
            <td>${normalUser.username}</td>
            <form class="adminManageForm" id="${normalUser.username}" method="GET" action="#">
                <!--TODO: check permissions based on user's rights-->
                <td><input type="checkbox" name="canSave"></td>
                <td><input type="checkbox" name="canDelete"></td>
                <td><input type="checkbox" name="canUpload"></td>
                <input type="hidden" name="username" value="${normalUser.username}">
                <!--TODO: change action on click with jquery-->
                <td><input type="submit" name="operation" value="Edit"></td>
                <td><input type="submit" name="operation" value="Delete"></td>
            </form>
        </tr>
    </c:forEach>
</table>

<script src="/resources/js/admin.js"></script>

<%@include file="/WEB-INF/views/include/footer.jsp" %>