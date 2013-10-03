<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Tickets</title>
</head>
<body>
	<h1>Signup</h1>
<hr>

<c:if test="${not empty message}"><div class="alert alert-info">${message}</div></c:if>
<form:form modelAttribute="userDAO">
      <label for="usernameInput">Username: </label>
      <form:input path="username" id="usernameInput" />
      <br/>
<input type="submit" value="Submit" />
</form:form>
</body>
</html>  