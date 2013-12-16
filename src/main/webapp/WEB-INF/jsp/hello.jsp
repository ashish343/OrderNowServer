<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head>
	<title>Hello :: Spring Application</title>
	<link type="text/css" href="${path}/resources/css/bootstrap.min.css" rel="stylesheet"/>
	<%@ include file="/WEB-INF/jsp/js/pageJS.jsp" %>
  </head>
  <body>
    <%@ include file="/WEB-INF/jsp/NavigationBar/navbar.jsp" %>
	
    <h1>Hello - Spring Application</h1>
    <p>Greetings, it is now <c:out value="${now}"/></p>
	
	<%@ include file="/WEB-INF/jsp/js/loadJS.jsp" %>
  </body>
</html>