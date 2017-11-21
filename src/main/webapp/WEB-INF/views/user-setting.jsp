<%-- 
    Document   : user-setting
    Created on : Nov 21, 2017, 3:27:51 PM
    Author     : majiasheng
--%>
<%@include file="/WEB-INF/views/include/header.jsp" %>

<!--send home if user is empty-->
<c:if test="${empty user}">
    <c:redirect url="/"/>
</c:if>
<h2>User Setting</h2>
<hr>
${msg}
<form name="userInfoForm" action="/confirmEdit" method="POST">
    <input type="hidden" name="username" value="${user.username}">
    First Name: <input type="text" name="firstname" value="${user.firstName}"><br>
    Last Name: <input type="text"  name="lastname" value="${user.lastName}"><br>
    <input type="submit" value="Eidt">
</form>

<%@include file="/WEB-INF/views/include/footer.jsp" %>