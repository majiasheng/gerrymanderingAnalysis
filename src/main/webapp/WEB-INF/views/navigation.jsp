<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-header">
		 
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
			 <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
		</button> <a class="navbar-brand" href="/"><img src="/resources/image/coin.gif" height=100% width=auto></a> 
	</div>
	
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

		<ul class="nav navbar-nav">
			<li class="active">
				<a href="#">Home</a>
			</li>
			<li>
				<a href="#">Compare</a>
			</li>
			<li class="disabled">
				<a href="#">File Upload</a>
			</li>
			<li>
				<a href="#">Help</a>
			</li>
			<li>
				<a href="#">Credit</a>
			</li>
		</ul>

		<ul class="nav navbar-nav navbar-right">
			<c:choose>
				<%-- show Register option if user is not logged in --%>
				<c:when test="${empty user}">
					<li><a href="/registration.jsp">Register</a></li>
					<li><%@include file="/WEB-INF/views/login-modal.jsp" %></li>
				</c:when>
				
				<c:otherwise>
					<li class="dropdown"><%@include file="/WEB-INF/views/user-setting-dropdown.jsp" %></li>
  				</c:otherwise>
			</c:choose>
		</ul> <!-- end nav navbar-nav navbar-right -->
		
	</div>
	
</nav>