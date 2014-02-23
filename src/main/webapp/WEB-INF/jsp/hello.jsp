<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head>
	<title>Order Now</title>
	<link type="text/css" href="${path}/resources/css/pageCss.css" rel="stylesheet"/>	
	<%@ include file="/WEB-INF/jsp/common/head-resources.jsp" %>
<style type="text/css">
.page-layout {
    margin:110px auto 20px;
    max-width:996px;
    min-height:600px;
    border-style: solid; 
    border-width: 1px; 
    border-color: dimgrey;  
}
.card {
    heitgh:300px;
    background-color:lightgray;
    margin:10px;
}
.contact-details {
    margin:0px auto 0px;
    max-width:996px;
    color:black;
}
</style>
  </head>
  <body>
    <c:set var="options">Home,App,Blog</c:set>
    <c:set var="showOption" value="true"/>
    <c:set var="showOptionData" value="Contact Us"/>
    <%@ include file="/WEB-INF/jsp/common/new-navbar.jsp" %>

    <div class="container page-layout">
       <%@ include file="/WEB-INF/jsp/body/home-body.jsp" %>
    </div>
    <script type="text/javascript">
        P.when('jQuery').execute(function($) {
            myFunc(this.$);
        });

        var showActiveList = function($, element) {
        	var listElement = $(element).parent();
            var unorderedList = $(element).parents().find('ul');

            var rightUnorderedList = $('.navbar-right');
            
            jQuery(rightUnorderedList).find('li').each(function(index, value){
                jQuery(value).attr('class', '');
            });
            
            jQuery(unorderedList).find('li').each(function(index, value){
                jQuery(value).attr('class', '');
            });
            jQuery(listElement).attr('class', 'active');
        }
        
        var myFunc = function($) {
            $("#action-Home").click(function(e) {
                $('.carousel').carousel(0);
                $('.carousel').carousel('pause');
                showActiveList($, this);
                e.preventDefault();
            });

            $("#action-App").click(function(e) {
                $('.carousel').carousel(1);
                $('.carousel').carousel('pause');
                showActiveList($, this);
                e.preventDefault();
            });

            $("#action-Blog").click(function(e) {
                $('.carousel').carousel(2);
                $('.carousel').carousel('pause');
                showActiveList($, this);
                e.preventDefault();
            });

            $("#action-Contact").click(function(e) {
                $('.carousel').carousel(3);
                $('.carousel').carousel('pause');
                var unorderedList = $('.navbar-left');
                
                jQuery(unorderedList).find('li').each(function(index, value){
                    jQuery(value).attr('class', '');
                });
                var listElement = $(this).parent();
                jQuery(listElement).attr('class', 'active');
                
                e.preventDefault();
            });
            $('.navbar-brand.nav-element-title').click(function(e){
            	 $('.carousel').carousel(0);
                 $('.carousel').carousel('pause');
                 showActiveList($, $("#action-Home"));
            	e.preventDefault();
            });
        }
    </script>
  </body>
</html>