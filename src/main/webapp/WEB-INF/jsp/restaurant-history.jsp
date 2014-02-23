<%@ include file="/WEB-INF/jsp/include.jsp" %>

 <!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <title>Order Now</title>
        <link rel="icon" type="image/png" href="/resources/img/icon.png" />
        <link rel="shortcut icon" href="../favicon.ico">
        <link rel="stylesheet" type="text/css" href="/resources/css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" type="text/css" href="/resources/restaurant/css/normalize.css" />
        <link rel="stylesheet" type="text/css" href="/resources/restaurant/css/demo.css" />
        <link rel="stylesheet" type="text/css" href="/resources/restaurant/css/icons.css" />
        <link rel="stylesheet" type="text/css" href="/resources/restaurant/css/style5.css" />
        <!-- Loading effect -->
        <link rel="stylesheet" type="text/css" href="/resources/loading/css/normalize.css" />
        <link rel="stylesheet" type="text/css" href="/resources/loading/css/demo.css" />
        <link rel="stylesheet" type="text/css" href="/resources/loading/css/component.css" />
        <!-- Loading effect End-->
        
        <script>
        (function(){(function(){(window.P={}).error=function(h,g,m){throw Error("[aui] "+h+" @ "+(g||"N/A")+":"+(m||"N/A"));}})();(function(){function h(b){o.schedule(function(){var a=[];if(b.dependencies&&b.dependencies.length!==0)for(var c=0;c<b.dependencies.length;c++)a.push(p[b.dependencies[c]]);if(b.fn&&typeof b.fn==="function")try{p[b.name]=b.fn.apply(window,a),f[b.name]=!0,d.notify(b)}catch(i){e.error("["+b.name+"] had an error: "+i.message,"P","initComponent")}else f[b.name]=!0,d.notify(b)})}function g(b,
a,c){typeof f[a]!=="undefined"&&e.error("A component named "+a+" has already been registered.","P","register");f[a]=!1;a={name:a,dependencies:b,fn:c};if(!b||b.length===0)h(a);else{for(var c=!0,i=0;i<b.length;i++)f[b[i]]||(c=!1);c?h(a):d.wait(a)}}function m(b,a){if(j[b])return!0;j[b]=!0;if(a instanceof Array){for(var c=0;c<a.length;c++)k[a[c]]&&e.error("An asset that contains "+a[c]+" has already been loaded.","P","alreadyLoaded");for(c=0;c<a.length;c++)k[a[c]]=!0}return!1}var j={},k={},p={},f={},
l=0,n,e=window.P;e.AUI_BUILD_DATE="07252013";var o=function(){function b(){for(var e=setTimeout(b,0),f=Date.now();;){if(i.length===0){clearTimeout(e);d=!1;break}i.shift().call();if(Date.now()-f>=a){clearTimeout(e);setTimeout(b,c);break}}}if(!Date.now)Date.now=function(){return(new Date).getTime()};var a=50,c=50,i=[],d=!1;typeof window.addEventListener==="function"&&window.addEventListener("scroll",function(){setTimeout(b,0)},!1);return{schedule:function(a){i.push(a);d||(setTimeout(b,0),d=!0)}}}(),
d=function(){var b={},a={};return{wait:function(c){for(var d=0;d<c.dependencies.length;d++){var e=c.dependencies[d];f[e]||(b[e]=b[e]||[],a[c.name]=a[c.name]||0,b[e].push(c),a[c.name]++)}},notify:function(c){var d=b[c.name],e;if(d){for(var f=0;f<d.length;f++)e=d[f],a[e.name]--,a[e.name]===0&&h(e);delete b[c.name]}}}}();e.when=function(){var b=arguments;return{register:function(a,c){g(b,a,c)},execute:function(a,c){typeof a==="function"&&(c=a,a="anon"+l++);g(b,a,c)}}};e.execute=function(b,a){typeof b===
"function"&&(a=b,b="anon"+l++);g(null,b,a)};e.register=function(b,a){g(null,b,a)};e.trigger=function(b,a){var c=Date.now(),d={data:a,pageElapsedTime:window.aPageStart?c-window.aPageStart:NaN,triggerTime:c};g(null,b,function(){return d});typeof n==="function"&&n(b,d)};e.handleTriggers=function(b){typeof n==="function"&&e.error("Trigger handler already registered","P","handleTriggers");n=b};e.load={js:function(b,a){if(m(b,a))return!1;var c=document.createElement("script");c.type="text/javascript";c.src=
b;c.async=!0;document.getElementsByTagName("head")[0].appendChild(c);return!0},css:function(b,a){if(m(b,a))return!1;var c=document.createElement("link");c.type="text/css";c.rel="stylesheet";c.href=b;document.getElementsByTagName("head")[0].appendChild(c)}}})();P.register("p-detect",function(){function h(d,b){var a=d.className;a+=" "+b;d.className=a}function g(d,b){for(var a=d.className.split(" "),c=0;c<a.length;c++)a[c]===b&&a.splice(c,1);d.className=a.join(" ")}function m(){var d,b;window.innerWidth?
(d=window.innerWidth,b=window.innerHeight):(d=document.documentElement.clientWidth,b=document.documentElement.clientHeight);var a=!1,a=l.orientation?d>b:d>=1250;g(j,"a-ws");a&&h(j,"a-ws")}var j=document.getElementsByTagName("html")[0],k;try{k=navigator.userAgent}catch(p){k=""}var f=function(){var d="Webkit,Moz,O,ms,Khtml".split(","),b=document.createElement("div");return{testGradients:function(){b.style.cssText=("background-image:"+"-webkit- ".split(" ").join("gradient(linear,left top,right bottom,from(#9f9),to(white));background-image:")+
d.join("linear-gradient(left top,#9f9, white);background-image:")).slice(0,-17);return b.style.backgroundImage.indexOf("gradient")!==-1},test:function(a){for(var c=a.charAt(0).toUpperCase()+a.substr(1),a=(a+" "+d.join(c+" ")+c).split(" "),c=0;c<a.length;c++)if(b.style[a[c]]==="")return!0;return!1},testTransform3d:function(){var a=!1;if(window.matchMedia)a=window.matchMedia("(-webkit-transform-3d)").matches;return a},testTouchScrolling:function(){var a=k.match(/android [3-9]/i);return k.match(/OS [5-8](_\d)+ like Mac OS X/i)||
a}}}(),l={audio:!!document.createElement("audio").canPlayType,video:!!document.createElement("video").canPlayType,canvas:!!document.createElement("canvas").getContext,offline:navigator.hasOwnProperty&&navigator.hasOwnProperty("onLine")&&navigator.onLine,dragDrop:"draggable"in document.createElement("span"),geolocation:!!navigator.geolocation,history:!(!window.history||!window.history.pushState),autofocus:"autofocus"in document.createElement("input"),inputPlaceholder:"placeholder"in document.createElement("input"),
textareaPlaceholder:"placeholder"in document.createElement("textarea"),localStorage:"localStorage"in window&&window.localStorage!==null,orientation:"orientation"in window,touch:"ontouchend"in document,touchScrolling:f.testTouchScrolling(),gradients:f.testGradients(),hires:window.devicePixelRatio&&window.devicePixelRatio>=1.5,transform3d:f.testTransform3d(),ios:!!k.match(/OS [1-9](_\d)+ like Mac OS X/i),android:!!k.match(/android [1-9]/i)},n="textShadow textStroke boxShadow borderRadius borderImage opacity transform transition".split(" "),
e=0;for(;e<n.length;e++)l[n[e]]=f.test(n[e]);m();typeof window.addEventListener==="function"?window.addEventListener("resize",m,!1):window.attachEvent("onresize",m);g(j,"a-no-js");h(j,"a-js");for(var o in l)l.hasOwnProperty(o)&&l[o]&&h(j,"a-"+o.replace(/([A-Z])/g,function(d){return"-"+d.toLowerCase()}));j.setAttribute("data-aui-build-date",P.BUILD_DATE);return{capabilities:l}})})();
function loadJS(src, callback) {var s = document.createElement('script');s.src = src;s.async = true;s.onreadystatechange = s.onload = function() {var state = s.readyState;if (!callback.done && (!state || /loaded|complete/.test(state))) {callback.done = true;callback();}};document.getElementsByTagName('head')[0].appendChild(s);}
loadJS('http://code.jquery.com/jquery-2.0.3.min.js', function() {P.register('jQuery');});
P.when('jQuery').execute(function($){
	P.load.js("/resources/js/bootstrap.min.js");
    P.load.js("/resources/restaurant/js/modernizr.custom.js");
    P.load.js("/resources/restaurant/js/classie.js");
    P.load.js("/resources/restaurant/js/borderMenu.js");
});
</script>
    </head>
    <body>
        <div class="la-anim-1"></div>
        <div class="container">
            <header class="codrops-header">
                <h1>Menus <span>Inspired by CreativeDash's <a href="http://creativeda.sh/sandbox/bounce_menu/">bounce menu</a></h1>
            </header>
            <div class="main">
                <div id="la-buttons" class="column">
                    <button data-anim="la-anim-1" style="display:none" id="loading"></button>
                </div>
            </div>
                    
            <nav id="bt-menu" class="bt-menu">
                <a href="#" class="bt-menu-trigger"><span>Menu</span></a>
                <ul>
                    <li><a href="#" id="restInfo">Restaurant Info</a></li>
                    <li><a href="#" id="orderHistory">Order History</a></li>
                    <li><a href="#" id="analysis">Analysis</a></li>
                    <li><a href="#" id="contact">Contact</a></li>
                </ul>
            </nav>
        </div><!-- /container -->
        <script src="/resources/loading/js/modernizr.custom.js"></script>
        <script src="/resources/loading/js/classie.js"></script>
        <script>

        P.when('jQuery').execute(function($) {
            attachEvents(this.$);
        });
        var attachEvents = function($) {
            $("#restInfo").click(function(e) {
                $('#loading').trigger("click");
                $('#bt-menu').removeClass('bt-menu-open').addClass('bt-menu-close');
                e.preventDefault();
            });
            $("#orderHistory").click(function(e) {
                $('#loading').trigger("click");
                $('#bt-menu').removeClass('bt-menu-open').addClass('bt-menu-close');
                e.preventDefault();
            });
            $("#analysis").click(function(e) {
                $('#loading').trigger("click");
                $('#bt-menu').removeClass('bt-menu-open').addClass('bt-menu-close');
                e.preventDefault();
            });
            $("#contact").click(function(e) {
                $('#loading').trigger("click");
                $('#bt-menu').removeClass('bt-menu-open').addClass('bt-menu-close');
                e.preventDefault();
            });
        }
        var inProgress = false;

        Array.prototype.slice.call( document.querySelectorAll( '#la-buttons > button' ) ).forEach( function( el, i ) {
            var anim = el.getAttribute( 'data-anim' ),
                animEl = document.querySelector( '.' + anim );

            el.addEventListener( 'click', function() {
                if( inProgress ) return false;
                inProgress = true;
                classie.add( animEl, 'la-animate' );

                setTimeout( function() {
                    classie.remove( animEl, 'la-animate' );
                    inProgress = false;
                }, 6000 );
            } );
        } );
        </script>
        
    </body>
</html>

