<%@include file="/WEB-INF/views/include/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2 id="h">Sign Me Up</h2>
<div id="regform">
   <form method="POST" action="/register" id="registrationForm">
        First Name <br>
        <input type="text" name="firstName" placeholder="First Name" required>
       <span class="error"><form:errors path="user.firstName"/></span><br>

        Last Name <br>
        <input type="text" name="lastName" placeholder="Last Name" required>
       <span class="error"><form:errors path="user.firstName"/></span><br>

        Email <br>
        <input type="text" name="email" placeholder="email" required>
       <span class="error"><form:errors path="user.email"/></span><br>

        Re-enter email <br>
        <input type="text" name="reemail" placeholder="Re-enter email" required><br>
        <div id="emailMisMatch"></div>
        Username <br>
        <input type="text" name="username" placeholder="Username" required>
       <span class="error"><form:errors path="user.username"/></span><br>

        Password <br>
        <input type="password" name="password" placeholder="password" required>
       <span class="error"><form:errors path="user.password"/></span><br>

        Re-enter password ${err_repassword}<br>
        <input type="password" name="repassword" placeholder="Re-enter password" required><br>
        <div id="pwMisMatch"></div>

        <!--TODO: check double password and email-->

        <input type="submit" name="submit" value="Sign Me Up">
    </form>
</div>

<%@include file="/WEB-INF/views/include/footer.jsp" %>
