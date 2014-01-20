<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head>
	<title>Order Now</title>	
	<%@ include file="/WEB-INF/jsp/common/head-resources.jsp" %>
  </head>
  <body>
    <div class="navbar-wrapper">
      <div class="container">
        <c:set var="options">Home,App,Blog</c:set>
        <c:set var="showOption" value="true"/>
        <%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
	  </div>
	</div>
	<%@ include file="/WEB-INF/jsp/body/home-body.jsp" %>
    
    <script type="text/javascript">
        P.when('jQuery').execute(function($) {
            myFunc(this.$);
        });

        var myFunc = function($) {
            $("#action-Home").click(function(e) {
            $('.carousel').carousel(0);
            $('.carousel').carousel('pause');
            e.preventDefault();
        });

        $("#action-App").click(function(e) {
            $('.carousel').carousel(1);
            $('.carousel').carousel('pause');
            e.preventDefault();
        });

        $("#action-Blog").click(function(e) {
            $('.carousel').carousel(2);
            $('.carousel').carousel('pause');
            e.preventDefault();
        });

        $("#action-sign-in").click(function(e) {
            $('.carousel').carousel(3);
            $('.carousel').carousel('pause');
            e.preventDefault();
        });
        }
    </script>
  </body>
</html>