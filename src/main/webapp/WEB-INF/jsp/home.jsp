<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head>
	<title>Order Now</title>
	<link type="text/css" href="${path}/resources/css/bootstrap.min.css" rel="stylesheet"/>
	<link type="text/css" href="${path}/resources/css/pageCss.css" rel="stylesheet"/>
	<%@ include file="/WEB-INF/jsp/js/pageJS.jsp" %>
  </head>
  <body>
    <div class="navbar-wrapper">
      <div class="container">
        <c:set var="options">Home,App,Blog</c:set>
        <c:set var="showOption" value="true"/>
        <%@ include file="/WEB-INF/jsp/NavigationBar/navbar.jsp" %>
	  </div>
	</div>
	<%@ include file="/WEB-INF/jsp/body/orderData.jsp" %>
    
    <!-- Load JS -->
	<%@ include file="/WEB-INF/jsp/js/loadJS.jsp" %>
	<script type="text/javascript" src="${path}/resources/js/bodyJS.js"></script>
  </body>
</html>