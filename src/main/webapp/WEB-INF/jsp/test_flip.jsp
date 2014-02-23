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
.item { 
    width: 220px; 
    height:220px; 
    padding:0px 10px 15px 10px;
}
.flipbox-container .flipbox {
    height:100%;
    background-color: red;
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

    <div id="container" class="container page-layout">
        <div class="item">
            <div class="flipbox-container">
                <div class="flipbox">
                    <svg class="pie" width="148" height="148">
                    <g transform="translate(74,74)">
                        <text dy=".35em" style="text-anchor: middle;">AK</text>
                    </g>
                    </svg>
                </div>
            </div>
        </div>
        <div class="item">
            <div class="flipbox-container">
                <div class="flipbox">
                    <span>Hello !</span>
                </div>
            </div>
        </div>
        <div class="item">
            <div class="flipbox-container">
                <div class="flipbox">
                    <span>Hello !</span>
                </div>
            </div>
        </div>
        <div class="item">
            <div class="flipbox-container">
                <div class="flipbox">
                    <span>Hello !</span>
                </div>
            </div>
        </div>
        <div class="item">
            <div class="flipbox-container">
                <div class="flipbox">
                    <span>Hello !</span>
                </div>
            </div>
        </div>
        <div class="item">
            <div class="flipbox-container">
                <div class="flipbox">
                    <span>Hello !</span>
                </div>
            </div>
        </div>
        <div class="item">
            <div class="flipbox-container">
                <div class="flipbox">
                    <span>Hello !</span>
                </div>
            </div>
        </div>
        <div class="item">
            <div class="flipbox-container">
                <div class="flipbox">
                    <span>Hello !</span>
                </div>
            </div>
        </div>
        <div class="item">
            <div class="flipbox-container">
                <div class="flipbox">
                    <span>Hello !</span>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Load JS -->
    <script type="text/javascript">
        P.when('jQuery').execute(function($){
            loadJS('/resources/js/flippy.min.js', function() { 
            	//flipy();
            });
            loadJS('/resources/js/masonry.js', function() { 
            	masonry();
            });
        });
        var flipy = function() {
        	jQuery(".flipbox").flippy({
        	    color_target: "red",
        	    duration: "1000",
        	    direction:"TOP",
        	    verso: "woohoo"
        	 });
        } 
        P.when('jQuery').execute(function($){
            myFlipEffect(this.$);
        });
        var myFlipEffect = function($) {
            $(".flipbox-container").mouseenter(function() {
         	    jQuery(this).flippy({
         	    	color_target: "white",
                     duration: "500",
                     direction:"LEFT",
                     verso: "woohoo"
                 });
            }); 
        }
        
        var masonry = function() {
        	var container = document.querySelector('#container');
        	var msnry = new Masonry( container, {
        	  // options
        	  columnWidth: 220,
        	  itemSelector: '.item'
        	});
        }
    </script>
  </body>
</html> 