<style type="text/css">
.nav-container {
    heoght:70px;
}
.nav-element-title {
    font-size:25px;
    padding-top:20px;
}
.nav-element {
    font-size:20px;
    padding-top:20px !important;
}
</style>

<header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav" role="banner">
  <div class="container nav-container">
    <div class="navbar-header">
      <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a href="/" class="navbar-brand nav-element-title">Order Now</a>
    </div>
    <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
        <ul class="nav navbar-nav navbar-left">
            <c:forTokens items="${options}" delims="," var="category" varStatus="categoryStatus">
            <c:forTokens items="${category}" delims=" " var="event" varStatus="status">
            <c:if test="${status.first }">
                <li class="${categoryStatus.first ? 'active' :'' }"><a class="btn btn-large btn-large-navbar nav-element" href="#" id="action-${event}">${category}</a></li>
            </c:if>
            </c:forTokens>
        </c:forTokens>
        </ul>
        <c:if test="${showOption}">
            <ul class="nav navbar-nav navbar-right">
                <c:forTokens items="${showOptionData}" delims=" " var="event" varStatus="status">
                <c:if test="${status.first }">
                    <li><a href="#" class="btn btn-large btn-large-navbar nav-element" id="action-${event}">${showOptionData}</a></li>
                </c:if>
                </c:forTokens>
            </ul>
        </c:if>
    </nav>
  </div>
</header>