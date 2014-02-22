
<c:forEach items="${restaurantData.tableInformation}" var="table">
<!-- / uc-container -->
<div class="${table.key} ${shouldShow ? ' uc-container' : ' uc-container hidden'  }">
	<div class="uc-initial-content">
		<h1>Table ${table.value}</h1>
		<div>
			<span class="notification" style="display: none">0</span> 
			<img src="/resources/img/order_icon.png" class="icon-image" />
		</div>
		<span class="icon-eye"></span>
	</div>
	<div class="uc-final-content">
		<div class="restaurant panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">Table ${table.value}</div>
			<div class="tableId" id="${table.key}"></div>
		</div>
		<span class="icon-cancel"></span>
	</div>
</div>
</c:forEach>