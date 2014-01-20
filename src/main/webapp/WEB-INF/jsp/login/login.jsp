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
        <form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
        
        <div id="rest-sign-in">
            <c:if test="${loginFailed }">
                <p class="login-failed-p">Login Failed Please Try Again!!</p>
            </c:if>
            <%@ include file="/WEB-INF/jsp/body/sign-in-box.jsp" %>
        </div>
        </form>
    </div>
  </body>
</html>