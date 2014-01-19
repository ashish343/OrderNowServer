<%@ include file="/WEB-INF/jsp/include.jsp" %>

<nav class="navbar navbar-default navbar-inverse" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">Order Now</a>
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse navbar-ex1-collapse">
    <ul class="nav navbar-nav">
      <c:forTokens items="${options}" delims="," var="category">
        <c:forTokens items="${category}" delims=" " var="event" varStatus="status">
          <c:if test="${status.first }">
            <li><a href="#" id="action-${event}">${category}</a></li>
          </c:if>
        </c:forTokens>
      </c:forTokens>
    </ul>
    <c:if test="${not showOption}">
        <ul class="nav navbar-nav navbar-right">
        <li><a href="/dev/analytics" id="action-sign-in">Analytics</a></li>
        </ul>
    </c:if>
  </div><!-- /.navbar-collapse -->
</nav>