<%@include file="/WEB-INF/views/include/header.jsp" %>
<h2>Analytics</h2>
<script>
(function(w,d,s,g,js,fs){
  g=w.gapi||(w.gapi={});g.analytics={q:[],ready:function(f){this.q.push(f);}};
  js=d.createElement(s);fs=d.getElementsByTagName(s)[0];
  js.src='https://apis.google.com/js/platform.js';
  fs.parentNode.insertBefore(js,fs);js.onload=function(){g.load('analytics');};
}(window,document,'script'));
</script>

<script src="/resources/js/analytics/auth.js"></script>
<script src="/resources/js/analytics/dashboard.js"></script>

<div id="embed-api-auth-container"></div>
<div id="view-selector-container"></div>
<div id="chart-container"></div>

<%@include file="/WEB-INF/views/include/footer.jsp" %>
