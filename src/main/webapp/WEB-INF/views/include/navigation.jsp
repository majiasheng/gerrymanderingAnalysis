<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default" role="navigation">

    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
        </button> <a class="navbar-brand" href="/"><img src="/resources/image/brand" height=100% width=auto></a> 
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <!--nav bar left-->
        <ul class="nav navbar-nav">
            <li class="active">
                <a href="/">Home</a>
            </li>
            <li>
                <a href="compare">Compare</a>
            </li>
            <c:choose>
                <c:when test="${user.isAllowedToUpload()}">
                    <li>
                    </c:when>
                    <c:otherwise>
                    <li class="disabled">
                    </c:otherwise>
                </c:choose>
                <a href="file-upload">File Upload</a>
            </li>

            <li class="disabled export">
                <a id="exportTo" href="#">Export Election Data</a>
            </li>
            
            <li>
                <a href="help">Help</a>
            </li>
            <li>
                <a href="credit">Credit</a>
            </li>
        </ul>
        <!--end nav bar left-->

        <!--nav bar right-->
        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <%-- show Register option if user is not logged in --%>
                <c:when test="${empty user}">
                    <li><a href="registration">Register</a></li>
                    <li><%@include file="/WEB-INF/views/include/login-modal.jsp" %></li>
                    </c:when>

                <c:otherwise>
                    <li class="dropdown"><%@include file="/WEB-INF/views/include/user-setting-dropdown.jsp" %></li>
                    </c:otherwise>
                </c:choose>
        </ul>
        <!--end nav bar right-->
    </div>

</nav>