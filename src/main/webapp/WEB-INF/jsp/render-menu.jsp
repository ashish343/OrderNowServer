<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>

<html>
  <head>
    <title>Menu</title>
    <link type="text/css" href="${path}/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link type="text/css" href="${path}/resources/css/pageCss.css" rel="stylesheet"/>
    <link type="text/css" href="${path}/resources/css/CSSreset.min.css" rel="stylesheet"/>
    <%@ include file="/WEB-INF/jsp/js/pageJS.jsp" %>


<style type="text/css">
.page-layout {
    margin:0 auto;
    margin-top:20px;
    margin-bottom:20px;
    max-width:996px;
    border-style: solid; 
    border-width: 1px; 
    border-color: dimgrey; 
}
.shovler-basic {
    width:90%;
    margin-right:5%;
    margin-left:5%;
    height:150px;
}
.dish-container {
    width:90%;
    margin-right:5%;
    margin-left:5%;
}
.dish-container a {
    cursor:default;
}
.dish-col {
    height:inherit;
}
.dish-item {
    height:150px;
    margin-bottom:10px;
}
.dish-item-img {
    padding:0px;
}
.dish-item-img img {
    height:100%;
    width:100%;
} 
a.list-group-item.active.dish-item {
    background-color:gainsboro;
}
.maintain-height {
    height:100%;
}
.maintain-height-width {
    height:100%;
    width:100%;
}
.custom-margin-left {
    margin-left:10px;
}
.custom-margin-right {
    margin-right:10px;
}
.custom-margin-top {
    margin-top:10px;
}
.custom-margin-bottom {
    margin-bottom:10px;
}
.dish-name-row {
    height:30%;
}
.dish-desc-row {
    height:40%;
    display:table;
    color:black;
}
.dish-desc {
    display:table-cell;
    vertical-align:middle;
}
.dish-price-row {
    height:30%;
    white-space: nowrap; 
}
.dish-name-col {
    padding-left:0px;
    display:table;
}
.dish-name {
    display:table-cell;
    vertical-align:middle;
    font-size:24px;
    color:chocolate;
}
.dish-review-col {
    padding-left:0px;
}

.dish-description-col {
    padding-left:0px;
}
.dish-add-col {
    padding:0px;
    text-align:center;
}
.dish-price-col {
    padding:0px;
    display:table;
}
/*************************************
 * generic styling for ALS elements
 ************************************/

.als-container {
    position: relative;
    width: 100%;
    height: 100%;
    margin: 0px auto;
    z-index: 0;
}

.als-viewport {
    position: relative;
    overflow: hidden;
    margin: 0px auto;
}

.als-wrapper {
    position: relative;
    list-style: none;
}

.als-item {
    position: relative;
    display: block;
    text-align: center;
    cursor: pointer;
    float: left;
}

.als-prev, .als-next {
    position: absolute;
    cursor: pointer;
    clear: both;
}

#demo1 {
    margin: 0px auto;
}

#demo1 .als-item {
    margin: 0px 5px;
    padding: 4px 0px;
    min-height: 160px;
    min-width: 100px;
    text-align: center;
}

#demo1 .als-item img {
    display: block;
    margin: 0 auto;
    vertical-align: middle;
}

#demo1 .als-prev, #demo1 .als-next {
    top: 40px;
}

#demo1 .als-prev {
    left: 0px;
}

#demo1 .als-next {
    right: 0px;
}
</style>



</head>
  <body>
    
     <!-- Basic page layout -->
    <!-- Page Container Begin-->
    <div class="page-layout">
        <div>
            
            
            
            
            <!-- Category Container Begin -->
            <div class="row shovler-basic">
                <div class="als-container" id="demo1">
                    <span class="als-prev"><img src="${path}/resources/img/left_arrow.png" alt="prev" title="previous" /></span>
                    <div class="als-viewport">
                        <ul class="als-wrapper">
                            <li class="als-item"><div class="well carousal-item parentDiv"><div class="childDiv">Appetizers</div></div></li>
                            <li class="als-item"><div class="well carousal-item parentDiv"><div class="childDiv">Beverages</div></div></li>
                            <li class="als-item"><div class="well carousal-item parentDiv"><div class="childDiv">Entries</div></div></li>
                            <li class="als-item"><div class="well carousal-item parentDiv"><div class="childDiv">Desserts</div></div></li>
                        </ul>
                    </div>
                    <span class="als-next"><img src="${path}/resources/img/right_arrow.png" alt="next" title="next" /></span>
                </div>
            </div>
            <!-- Category Container End -->

            <!-- Dishes Container Begin -->
            <div class="row dish-container">
                <div class="col-xs-12 col-lg-6 dish-col">
                    <div class="list-group">
                        <a href="#" class="list-group-item active dish-item">
                            <div class="row maintain-height">
                                <div class="col-xs-3 col-lg-3 dish-item-img maintain-height">
                                    <img src="${path}/resources/img/dish.jpg" alt="dish"/>
                                </div>
                                <div class="col-xs-9 col-lg-9 dish-item-content maintain-height">
                                        <!-- Right Column for Dish Start-->
                                        <div class="row dish-name-row">
                                            <div class="col-xs-12 col-lg-12 col-md-12 dish-name-col maintain-height">
                                                <div class="dish-name">
                                                    <i> Chocolate Cake </i>
                                                </div>
                                            </div>
                                            <!-- 
                                            <div class="col-xs-5 col-lg-5 dish-review-col maintain-height">
                                                <p> Reviews </p>
                                            </div> -->
                                        </div>
                                        <div class="row dish-desc-row">
                                            <div class="dish-desc">
                                                <p>Made of chocolate, what did you think.</p>
                                            </div>
                                        </div>
                                        <div class="row dish-price-row">
                                            <div class="col-xs-6 col-lg-6 dish-price-col maintain-height">
                                                <div class="price">100 Rs.</div>
                                            </div>
                                            <div class="col-xs-6 col-lg-6 dish-add-col maintain-height">
                                                <button type="button" class="btn btn-default btn-lg maintain-height-width">Add</button>
                                            </div>
                                        </div>
                                        <!-- Right Column for Dish Ends-->
                                </div>
                            </div>
                        </a>    
                        <a href="#"  class="list-group-item dish-item">Dapibus ac facilisis in</a>
                        <a href="#"  class="list-group-item dish-item">Morbi leo risus</a>
                        <a href="#"  class="list-group-item dish-item">Porta ac consectetur ac</a>
                        <a href="#"  class="list-group-item dish-item">Vestibulum at eros</a>
                    </div>
                </div>
                <div class="col-xs-12 col-lg-6 dish-col">
                    <div class="list-group">
                        <a href="#" class="list-group-item dish-item">Cras justo odio</a>    
                        <a href="#" class="list-group-item dish-item">Dapibus ac facilisis in</a>
                        <a href="#" class="list-group-item dish-item">Morbi leo risus</a>
                        <a href="#" class="list-group-item dish-item">Porta ac consectetur ac</a>
                    </div>
                </div>
            </div>
            <!-- Dishes Container End -->
        </div>
    <div>
    <!-- Page Container Ends-->
    
    
    
    
<script>
P.when('jQuery').execute(function($)  {
	funct(this.$);
	});

var funct = function($) {};
</script>
    
    <!-- Load JS -->
    <%@ include file="/WEB-INF/jsp/js/loadJS.jsp" %>
    <script type="text/javascript" src="${path}/resources/js/bodyJS.js"></script>
  </body>
</html>