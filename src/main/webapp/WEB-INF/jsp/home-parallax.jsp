<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <title>Order Now</title>
        <meta name="description" content="Parallax Content Slider with CSS3 and jQuery" />
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
#slide-1 .bcg {background-image:url('/resources/new-layout/img/bg-1.jpg')}
/* CSS - Slide 2 */
#slide-2 .bcg {
    background: none;
    background-color: #270d15;
    height: 210px;
    text-align: center
}
/*Slide 3*/
#slide-3 .bcg {background-image:url('/resources/img/bcg_slide-1.jpg')}
</style>
    </head>
    <body class="loading">
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
                         <div class="hsContent" data-center="opacity: 1" data--5p-top="opacity: 0.1" data-anchor-target="#slide-1 .slider-container">
                             <%@ include file="/WEB-INF/jsp/common/image-slider-parallax.jsp" %>
                         </div>
                     </div> 
                 </div>
             </section>
            <section id="slide-2">
                <div class="bcg" data-0="background-color:#1a090c;" data-top="background-color:#270d15;"  data-anchor-target="#slide-2">
                    <div class="hsContainer">
                        <div class="hsContent">
                            <h2 data-10p-top="opacity:1" data-40p-top="opacity:0" data-anchor-target="#slide-2 h2" style="font-size:180px">
                                Contact Us!
                            </h2>
                        </div>
                    </div>
                </div>
            </section>
            <section id="slide-3" class="homeSlide">
                <div class="bcg" data-center="background-position: 0px 50%;" data-bottom-top="background-position: 0px 40%;"
                data-top-bottom="background-position: -40px 50%;"
                data-anchor-target="#slide-3">
                    <div class="hsContainer">
                        <div class="hsContent">
                            <div class="plaxEl" data-106-top="opacity: 0" data-bottom="opacity: 1; position: fixed; top: 206px; width: 100%; left: 0;" 
                                data--30p-top="opacity: 1;" data--60p-top="opacity: 0;" data-anchor-target="#slide-3" >
                                <h2>Fixed element fading in and out</h2>
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
        </script>   
    </body>
</html>