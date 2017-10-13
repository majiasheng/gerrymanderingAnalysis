        </div> <!-- end of col-md-12 -->
	</div> <!-- end of row -->
</div> <!-- end of class="container-fluid" -->
    <footer class="footer navbar-fixed-bottom">
    	&copy; GerrymanderingAnalysis - Aspen-CSE308 2017
    </footer>

    <!-- have to put styles and scripts in "resource folder"
    <script src="/WEB-INF/js/jquery.min.js"></script>
    <script src="/WEB-INF/js/bootstrap.min.js"></script>
    <script src="/WEB-INF/js/scripts.js"></script>
     -->
    <spring:url value= "/resources/js/jquery.min.js" var="jquery" />
    <spring:url value= "/resources/js/bootstrap.min.js" var="boot" />
    <spring:url value= "/resources/js/script.js" var="script" />
    <script src="${jquery}"></script>
    <script src="${boot}"></script>
    <script src="${script}"></script>
  </body>
</html>