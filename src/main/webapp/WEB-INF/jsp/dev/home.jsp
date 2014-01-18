<%@ include file="/WEB-INF/jsp/include.jsp" %>


<html>
  <head>
    <title>Order Now</title>
    <link type="text/css" href="${path}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link type="text/css" href="${path}/resources/css/pageCss.css" rel="stylesheet"/>
    <%@ include file="/WEB-INF/jsp/js/pageJS.jsp" %>
    
<style type="text/css">
.page-layout {
    margin:150px auto 20px;
    max-width:996px;
    min-height:500px;
}
</style>
  </head>
  <body>
    <div class="navbar-wrapper">
      <div class="container">
        <c:set var="options">Add Restaurant,Modify Restaurant</c:set>
        <%@ include file="/WEB-INF/jsp/NavigationBar/navbar.jsp" %>
      </div>
    </div>
    
    <div class="container page-layout">
        <div id="add-rest" stye="display:none;">
            <%@ include file="/WEB-INF/jsp/body/add-rest-helper.jsp" %>
        </div>
        <div id="modify-rest">
            <%@ include file="/WEB-INF/jsp/body/modify-rest-helper.jsp" %>
        </div>
    </div>
    
    <!-- Load JS -->
    <%@ include file="/WEB-INF/jsp/js/loadJS.jsp" %>
    <script type="text/javascript" src="${path}/resources/js/addRest-js.js"></script>
  </body>
</html>