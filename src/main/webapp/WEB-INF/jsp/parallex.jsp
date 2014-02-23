<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <!DOCTYPE html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    
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
/* CSS */
.hsContainer {
    display: table;
    table-layout: fixed;
    width: 100%;
    height: 100%;
    overflow: hidden;
    position: relative;
    opacity: 0;
}
.hsContent {
    max-width: 450px;
    margin: -150px auto 0 auto;
    display: table-cell;
    vertical-align: middle;
    color: #ebebeb;
    padding: 0 8%;
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
#slide-1 .bcg {background-image:url('/resources/img/bcg_slide-1.jpg')}
/* CSS - Slide 2 */
#slide-2 .bcg {
    background: none;
    background-color: #010101;
    height: 310px;
    text-align: center
}
/* CSS - Slide 3 */
#slide-3 .bcg {background-image:url('/resources/img/bcg_slide-3.jpg')}
/* Slide 4 */
.curtainContainer {
    width: 100%; height: 100%;
    position: relative;
}
.curtainContainer .curtain {
    width: 300%; height: 1px;
    background-color: #000000;
    position: absolute; top: 25%; left: 0;
    opacity: 0
}
.curtainContainer .copy {
    position: absolute;
    bottom: 30%; left: 0;
    width: 100%; text-align: center;
}
#slide-4 .bcg {background-image:url('/resources/img/bcg_slide-4.jpg')}
/* Slide 5 */
#slide-5 {position: relative;}
#slide-5 .bcg {background-image:url('/resources/img/bcg_slide-5.jpg')}
</style>
  </head>
  <body class="loading">
    <c:set var="options">Home,App,Blog</c:set>
    <c:set var="showOption" value="true"/>
    <c:set var="showOptionData" value="Contact Us"/>
    <%@ include file="/WEB-INF/jsp/common/new-navbar.jsp" %>

    <main>
  <section id="slide-1" class="homeSlide">
    <div class="bcg"
        data-center="background-position: 50% 0px;"
        data-top-bottom="background-position: 50% -100px;"
        data-anchor-target="#slide-1">
        <div class="hsContainer">
            <div class="hsContent"
                data-center="opacity: 1"
                data-106-top="opacity: 0"
                data-anchor-target="#slide-1 h2">
                <div class="row">
                        <div class="col">
                            <iframe src="http://prezi.com/embed/ue9suwajy1kl/?bgcolor=ffffff&amp;lock_to_path=0&amp;autoplay=1&amp;autohide_ctrls=0&amp;features=undefined&amp;disabled_features=undefined" width="650" height="500" frameBorder="0"></iframe>
                        </div>
                    </div>
                <h2>Fade out elements before <br>they leave viewport</h2>
            </div>
        </div>
    </div>
</section>
<section id="slide-2">
    <div class="bcg"
        data-0="background-color:rgb(1,27,59);"
        data-top="background-color:(0,0,0);" 
        data-anchor-target="#slide-2">
        <div class="hsContainer">
            <div class="hsContent">
                <h2
                    data--200-bottom="opacity: 0"                   
                    data-center="opacity: 1"
                    data-206-top="opacity: 1"
                    data-106-top="opacity: 0"
                    data-anchor-target="#slide-2 h2">
                    Fade me in and out
                </h2>
            </div>
        </div>
    </div>
</section>
<section id="slide-3" class="homeSlide">
    <div class="bcg"
        data-center="background-position: 0px 50%;"
        data-bottom-top="background-position: 0px 40%;"
        data-top-bottom="background-position: -40px 50%;"
        data-anchor-target="#slide-3">
        <div class="hsContainer">
            <div class="hsContent">
                <div class="plaxEl"
                    data-106-top="opacity: 0"
                    data-bottom="opacity: 1; position: fixed; top: 206px; width: 100%; left: 0;"
                    data--30p-top="opacity: 1;"
                    data--60p-top="opacity: 0;"
                    data-anchor-target="#slide-3"
                >
                    <h2>Fixed element fading in and out</h2>
                </div>
            </div>
        </div>   
    </div>
</section>
<section id="slide-4" class="homeSlide homeSlideTall">
    <div class="bcg"
        data-center="background-position: 50% 0px;"
        data-bottom-top="background-position: 50% 100px;"
        data-top-bottom="background-position: 50% -100px;"
        data-anchor-target="#slide-4">
        <div class="curtainContainer">
            <div class="curtain"
                data-bottom-top="opacity: 0"
                data-106-top="height: 1%; top: -10%; opacity: 0;"
                data-center="height: 100%; top: 0%; opacity: 0.5"
                data-anchor-target="#slide-4"></div>
            <div class="copy"
                data-bottom-top="opacity: 0"
                data--100-bottom="opacity: 0"
                data--280-bottom="opacity: 1;"
                data-280-top="opacity: 1;"
                data-106-top="opacity: 0;"
                data-anchor-target="#slide-4 .copy">
                <h2>Curtain effect while you scroll</h2>
            </div>
             
        </div>
    </div>
</section>
<section id="slide-5" class="homeSlide homeSlideTall2">
    <div class="bcg">&nbsp;</div>
</section>
</main>
 

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
        P.when('jQuery').execute(function($){
            loadJS("${path}/resources/js/scroller.js", function() {
                P.register('scroller');
            });  
            loadJS("${path}/resources/js/imageloaded.js", function() {
                loadJS("${path}/resources/js/main.js", function() {
                });
            });
            
        });
    </script>
  </body>
</html>