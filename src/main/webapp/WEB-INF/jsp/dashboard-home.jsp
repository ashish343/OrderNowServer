<%@ include file="/WEB-INF/jsp/include.jsp" %>


<html>
  <head>
    <title>Order Now</title>
    <%@ include file="/WEB-INF/jsp/common/head-resources.jsp" %>
    
<style type="text/css">
.page-layout {
    margin:150px auto 20px;
    max-width:996px;
    min-height:500px;
    border-style: solid; 
    border-width: 1px; 
    border-color: dimgrey;	
}
</style>
  </head>
  <body>
    <div class="navbar-wrapper">
      <div class="container">
        <c:set var="options">Add Restaurant,Modify Restaurant</c:set>
        <%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
      </div>
    </div>
    
    <div class="container page-layout">
        <div id="add-rest" stye="display:none;">
            <%@ include file="/WEB-INF/jsp/body/add-rest-body.jsp" %>
        </div>
        <div id="modify-rest" style="display:none;">
            <%@ include file="/WEB-INF/jsp/body/modify-rest-body.jsp" %>
        </div>
    </div>
    
    <!-- Load JS -->
    <script type="text/javascript" src="/resources/js/dashboard-js.js"></script>
  </body>
</html>