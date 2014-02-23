<%@ include file="/WEB-INF/jsp/include.jsp" %>


<html>
  <head>
    <title>Order Now</title>
    <link type="text/css" href="${path}/resources/css/pageCss.css" rel="stylesheet"/>
    <%@ include file="/WEB-INF/jsp/common/head-resources.jsp" %>
    
<style type="text/css">
.page-layout {
    margin:150px auto 20px;
    max-width:996px;
    min-height:500px;
    border-style: solid; 
    border-width: 1px; 
    border-color: dimgrey;  
    background-color: lightslategrey;
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
    </div>
    
    <!-- Load JS -->
    <script type="text/javascript">
        P.when('jQuery').execute(function($){
            loadJS('https://d3dy5gmtp8yhk7.cloudfront.net/2.1/pusher.min.js', function() { 
                myPusherFunc();
            });
        });
        var myPusherFunc = function() {
            Pusher.log = function(message) {
                  if (window.console && window.console.log) {
                    window.console.log(message);
                  }
                };

                var pusher = new Pusher('1f7298f8e64c81a0d7de');
                var channel = pusher.subscribe('R1');
                channel.bind('notify_order', function(data) {
                  alert(data.message);
                });
        } 
    </script>
  </body>
</html>