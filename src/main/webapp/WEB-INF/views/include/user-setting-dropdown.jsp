
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="#" class="dropdown-toggle" data-toggle="dropdown">${user.firstName} ${user.lastName}<strong class="caret"></strong></a>

<ul class="dropdown-menu">

    <c:if test="${user.isAdmin()}">
        <li><a href="/manage">Manager Users</a></li>
    </c:if>

    <li><a href="/account">Account</a></li>
    <li><a href="/saved">Saved</a></li>

    <li class="divider"></li>

    <li><a href="/logout">Log out</a></li>
</ul>

