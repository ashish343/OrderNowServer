<%@ include file="/WEB-INF/jsp/include.jsp" %>

<nav class="navbar navbar-default" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="#">Deals..</a>
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse navbar-ex1-collapse">
    <ul class="nav navbar-nav">
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Categories <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a href="#">Restaurants</a></li>
          <li><a href="#">Spa and Saloon</a></li>
          <li><a href="#">Personal Care and Wellness</a></li>
          <li><a href="#">Jewelry</a></li>
          <li><a href="#">Men and Women's Clothing</a></li>
		  <li><a href="#">Footwear</a></li>
		  <li><a href="#">Mobile and Tablets</a></li>
        </ul>
      </li>
    </ul>
    <form class="navbar-form navbar-left" role="search">
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Search">
      </div>
      <button type="submit" class="btn btn-default">Submit</button>
    </form>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#">Sign In</a></li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Account <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a href="#">Change Password</a></li>
          <li><a href="#">Past Deals</a></li>
          <li><a href="#">Wallet</a></li>
        </ul>
      </li>
    </ul>
  </div><!-- /.navbar-collapse -->
</nav>