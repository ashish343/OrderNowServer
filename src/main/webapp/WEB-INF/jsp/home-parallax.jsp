<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <title>Order Now</title>
        <meta name="author" content="Ashish" />
        <link rel="icon" type="image/png" href="${path}/resources/img/icon.png" />
        <link rel="stylesheet" type="text/css" href="/resources/new-layout/css/demo.css" />
        <link type="text/css" href="${path}/resources/css/bootstrap.min.css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="/resources/new-layout/css/style2.css" />
        <script type="text/javascript" src="/resources/new-layout/js/modernizr.custom.28468.js"></script>
        <link href='http://fonts.googleapis.com/css?family=Economica:700,400italic' rel='stylesheet' type='text/css'>
        <noscript>
            <link rel="stylesheet" type="text/css" href="/resources/new-layout/css/nojs.css" />
        </noscript>
<style type="text/css">
/* CSS */
.hsContainer {
    display: table;
    table-layout: fixed;
    width: 100%;
    height: 100%;
    overflow: hidden;
    position: relative;
    opacity: 1;
}
.hsContent {
    margin: 30px auto;
    vertical-align: middle;
    color: #ebebeb;
    text-align: center
}
.bcg {
    background-position: center center;
    background-repeat: no-repeat;
    background-attachment: fixed;
    background-size: cover;
    height: 100%;
    width: 100%;
}
/* Slide 1 */
#slide-1 .bcg {background-image:url('/resources/new-layout/img/bus.jpg')}
/* CSS - Slide 2 */
#slide-2 .bcg {
    height: 350px;
    text-align: center
}
#slide-2 .bcg {background-image:url('/resources/new-layout/img/bg3.jpg')}
/*Slide 3*/
#slide-3 .bcg {
    height: 400px;
}
#slide-3 .bcg {
    background-image:url('/resources/new-layout/img/contact.png');
    background-repeat:repeat;
}
.text {
    margin:10% auto;
}
.contact-us-row {
    margin-left:20%;
    margin-right: 20%;
    margin-bottom:40px;
    margin-top:40px;
    font-size:40px;
}
.contact-info {
    text-align:center;
    font-size:30px;
}
.description {
    color:black !important;
    font-size:x-large !important;
    margin-top:30px !important;
}
</style>
    </head>
    <body class="loading">
    <div id="fb-root"></div>
    <script>(function(d, s, id) {
    	  var js, fjs = d.getElementsByTagName(s)[0];
    	  if (d.getElementById(id)) return;
    	  js = d.createElement(s); js.id = id;
    	  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=728085460544650";
    	  fjs.parentNode.insertBefore(js, fjs);
    	  }(document, 'script', 'facebook-jssdk'));</script>
        <div>
            <c:set var="options">Home</c:set>
            <c:set var="showOption" value="true"/>
            <c:set var="showOptionData" value="Contact Us"/>
            <%@ include file="/WEB-INF/jsp/common/new-navbar.jsp" %>
        </div>
        
        
        
        
        
         <main>
             <section id="slide-1" class="homeSlide">
                 <div class="bcg" data-center="background-position: 50% 0px;" data-top-bottom="background-position: 50% -150px;" data-anchor-target="#slide-1">
                     <div class="hsContainer">
                         <div class="hsContent" data-center="opacity: 1" data--40p-top="opacity: 0.1" data-anchor-target="#slide-1 .slider-container">
                             <%@ include file="/WEB-INF/jsp/common/image-slider-parallax.jsp" %>
                         </div>
                     </div> 
                 </div>
             </section>
            <section id="slide-2">
                <div class="bcg" data-10p-bottom="background-position: 50% 0px;" data-top-bottom="background-position: 50% -150px;" data-anchor-target="#slide-2">
                    <div class="hsContainer">
                        <div class="hsContent">
                            <div class="text">
                                <h2 data-40p-top="opacity:1" data-100p-top="opacity:0" data-anchor-target="#slide-2 h2">
                                    IN ORDER TO SUCCEED, YOUR DESIRE FOR SUCCESS SHOULD BE GREATER THAN YOUR FEAR OF FAILURE.
                                </h2>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section id="slide-3">
                <div class="bcg" data-center="background-position: 0px 50%;" data-bottom-top="background-position: 0px 40%;"
                data-top-bottom="background-position: -40px 50%;"
                data-anchor-target="#slide-3">
                    <div class="hsContainer">
                        <div class="hsContent">
                            <div>
                               <%@ include file="/WEB-INF/jsp/common/contact-us.jsp" %>
                            </div>
                        </div>
                    </div>   
                </div>
            </section>
        </main>
        
        
        
        
        <!-- Scripts -->
        <script type="text/javascript" src="/resources/new-layout/js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="/resources/new-layout/js/jquery.cslider.js"></script>
        <script type="text/javascript" src="/resources/js/scroller.js"></script>
        <script type="text/javascript" src="/resources/js/imageloaded.js"></script>
        <script type="text/javascript" src="/resources/js/main.js"></script>
        
        <script type="text/javascript">
            $(function() {
            
                $('#da-slider').cslider({
                    autoplay    : false,
                    bgincrement : 450
                });
            
            });
            ( function( $ ) {
                // Init Skrollr
                var s = skrollr.init({
                    render: function(data) {
                        //Debugging - Log the current scroll position.
                        //console.log(data.curTop);
                    }
                });
            } )( jQuery );
            ( function( $ ) {
            	$("#action-Contact").click(function(e) {
                    $('html, body').animate({
                        scrollTop: $("#slide-3").offset().top
                    }, 700);
                    e.preventDefault();
                });
            	$('.navbar-brand.nav-element-title').click(function(e){
                    showActiveList($, $("#action-Home"));
                   e.preventDefault();
               });
            	$("#action-Home").click(function(e) {
                    showActiveList($, this);
                    e.preventDefault();
                });
            } )( jQuery );
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
        </script>   
    </body>
</html>