<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<link type="text/css" href="${path}/resources/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<form class="form-horizontal" role="form">
  <div class="form-group">
    <label for="restaurant_name" class="col-sm-2 control-label">Restaurant Name</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="restaurant_name" placeholder="Restaurant Name">
    </div>
  </div>
  <div class="form-group">
    <label for="address" class="col-sm-2 control-label">Address</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="address" placeholder="Address">
    </div>
  </div>
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Sign in</button>
    </div>
  </div>
</form>
</body>
</html>