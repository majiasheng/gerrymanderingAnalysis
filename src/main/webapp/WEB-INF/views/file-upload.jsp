<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--TODO: check if normal user has upload permission-->
<c:if test="${empty user || not user.isAdmin()}">
    <c:redirect url="/"/>
</c:if>

<h1>File Upload</h1>
<hr>



<%@include file="/WEB-INF/views/include/footer.jsp" %>