<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="/WEB-INF/views/include/header.jsp" %>
<h2 id="h">Sign Me Up</h2>
<div id="regform">
    <form method="POST" action="/register">
        First Name ${err_firstname}<br>
        <input type="text" name="firstName" placeholder="First Name"><br>
        Last Name ${err_lastname}<br>
        <input type="text" name="lastName" placeholder="Last Name"><br>
        Email ${err_email}<br>
        <input type="text" name="email" placeholder="email"><br>
        Re-enter email ${err_reemail}<br>
        <input type="text" name="reemail" placeholder="Re-enter email"><br>
        Username ${err_username}<br>
        <input type="text" name="username" placeholder="Username"><br>
        Password ${err_password}<br>
        <input type="password" name="password" placeholder="password"><br>
        Re-enter password ${err_repassword}<br>
        <input type="password" name="repassword" placeholder="Re-enter password"><br>
        <input type="submit" name="submit" value="Sign Me Up">
    </form>
</div>

<%@include file="/WEB-INF/views/include/footer.jsp" %>