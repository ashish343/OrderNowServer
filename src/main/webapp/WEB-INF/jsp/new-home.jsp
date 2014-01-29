<!DOCTYPE html>
<html lang="en" class="no-js">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <title>Order Now</title>
        <link rel="icon" type="image/png" href="${path}/resources/img/icon.png" />
        <link rel="stylesheet" type="text/css" href="/resources/css/demo.css" />
        <link rel="stylesheet" type="text/css" href="/resources/css/component.css" />
        <link rel="stylesheet" type="text/css" href="/resources/css/normalize.css" />
        <script src="/resources/js/modernizr.custom.js"></script>
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
        </script>
    </head>
    <body>
        <div class="container">
            <header class="clearfix">
                <h1>Order Now</h1>
                <nav>
                    <a href="#" class="bp-icon bp-icon-archive contact-us-nav" data-info="Contact Us"><span>Contact Us.</span></a>
                </nav>
            </header>   
            <div id="cbp-so-scroller" class="cbp-so-scroller">
                <section class="cbp-so-section">
                    <article class="cbp-so-side cbp-so-side-left">
                        <h2>Lemon drops</h2>
                        <p>Fruitcake toffee jujubes. Topping biscuit sesame snaps jelly caramels jujubes tiramisu fruitcake. Marzipan tart lemon drops chocolate sesame snaps jelly beans.</p>
                    </article>
                    <figure class="cbp-so-side cbp-so-side-right">
                        <img src="/resources/img/story_start1.png" alt="img01">
                    </figure>
                </section>
                <section class="cbp-so-section">
                    <figure class="cbp-so-side cbp-so-side-left">
                        <img src="/resources/img/story_start2.png" alt="img01">
                    </figure>
                    <article class="cbp-so-side cbp-so-side-right">
                        <h2>Plum caramels</h2>
                        <p>Lollipop powder danish sugar plum caramels liquorice sweet cookie. Gummi bears caramels gummi bears candy canes cheesecake sweet roll icing dragée. Gummies jelly-o tart. Cheesecake unerdwear.com candy canes apple pie halvah chocolate tiramisu.</p>
                    </article>
                </section>
                <section class="cbp-so-section">
                    <article class="cbp-so-side cbp-so-side-left">
                        <h2>Marzipan gingerbread</h2>
                        <p>Soufflé bonbon jelly cotton candy liquorice dessert jelly bear claw candy canes. Pudding halvah bonbon marzipan powder. Marzipan gingerbread sweet jelly.</p>
                    </article>
                    <figure class="cbp-so-side cbp-so-side-right">
                        <img src="/resources/img/3.png" alt="img01">
                    </figure>
                </section>
                <section class="cbp-so-section">
                    <figure class="cbp-so-side cbp-so-side-left">
                        <img src="/resources/img/4.png" alt="img01">
                    </figure>
                    <article class="cbp-so-side cbp-so-side-right">
                        <h2>Carrot cake</h2>
                        <p>Sesame snaps sweet wafer danish. Chupa chups carrot cake icing donut halvah bonbon. Chocolate cake candy marshmallow pudding dessert marzipan jujubes sugar plum.</p>
                    </article>
                </section>
            </div>
            <div class="container contact-us">
            <section id="grid" class="grid clearfix">
                <a href="#" data-path-hover="m 180,34.57627 -180,0 L 0,0 180,0 z">
                    <figure>
                        <img src="/resources/img/int_1.png" />
                        <svg viewBox="0 0 180 320" preserveAspectRatio="none"><path d="M 180,160 0,218 0,0 180,0 z"/></svg>
                        <figcaption>
                            <h2>Like the idea!!</h2>
                            <button id="like"> Like </button>
                        </figcaption>
                    </figure>
                </a>
                <a href="#" data-path-hover="m 180,34.57627 -180,0 L 0,0 180,0 z">
                    <figure>
                        <img src="/resources/img/int_3.png" />
                        <svg viewBox="0 0 180 320" preserveAspectRatio="none"><path d="M 180,160 0,218 0,0 180,0 z"/></svg>
                        <figcaption>
                            <h2>Conatct Us.</h2>
                            <button id="email-us">Email Us</button>
                        </figcaption>
                    </figure>
                </a>
                
            </section>
            </div>
            <section>
            
                <div class="tn-box tn-box-color-1">
                    <p>Your settings have been saved successfully!</p>
                    <div class="tn-progress"></div>
                </div>
                
                <div class="tn-box tn-box-color-2">
                    <p>Yummy! I just ate your settings! They were delicious!</</p>
                    <div class="tn-progress"></div>
                </div>
                
                <div class="tn-box tn-box-color-3">
                    <p>Look at me! I take much longer!<p>
                    <div class="tn-progress"></div>
                </div>
                
            </section>
        </div>
        <script src="/resources/js/classie.js"></script>
        <script src="/resources/js/cbpScroller.js"></script>
        <script src="/resources/js/snap.svg-min.js"></script>
        <script>
            new cbpScroller( document.getElementById( 'cbp-so-scroller' ) );
            (function() {
                
                function init() {
                    var speed = 250,
                        easing = mina.easeinout;

                    [].slice.call ( document.querySelectorAll( '#grid > a' ) ).forEach( function( el ) {
                        var s = Snap( el.querySelector( 'svg' ) ), path = s.select( 'path' ),
                            pathConfig = {
                                from : path.attr( 'd' ),
                                to : el.getAttribute( 'data-path-hover' )
                            };

                        el.addEventListener( 'mouseenter', function() {
                            path.animate( { 'path' : pathConfig.to }, speed, easing );
                        } );
                        el.addEventListener( 'click', function(e) {
                            e.preventDefault();
                        } );

                        el.addEventListener( 'mouseleave', function() {
                            path.animate( { 'path' : pathConfig.from }, speed, easing );
                        } );
                    } );
                }

                init();

            })();
            P.when('jQuery').execute(function($) {
                attachEvents();
            });
            var attachEvents = function() {
                jQuery(".contact-us-nav").click(function(e) {
                	jQuery('html, body').animate({
                        scrollTop: $(".contact-us").offset().top
                    }, 700);
                	e.preventDefault();
                });
                jQuery('#like').click(function(e){
                    jQuery(this).html('Thanks');
                    jQuery(this).unbind('click');;
                });
                jQuery('#email-us').click(function(e){
                    document.location.href='mailto:ordernowinfo@gmail.com';
                });
                
            };
        </script>
    </body>
</html>
