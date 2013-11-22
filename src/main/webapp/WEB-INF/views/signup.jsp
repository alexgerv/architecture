<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Sign Up</title>
  <link href="<c:url value="/resources/css/forms.css" />" rel="stylesheet"  type="text/css" />
</head>
<body>
	<h1>Sign Up</h1>
<hr>

<c:if test="${not empty message}">
	<div class="alert alert-info">${message}</div>
</c:if>
<form:form modelAttribute="userDAO" class="form-signin">
	<h2 class="form-signin-heading">Please register</h2>
      <label class="control-label" for="login">Username:</label>
      <div class="control-group">
	      <div class="controls">
	      	<form:input path="emailAddress" size="50" id="usernameInput" class="form-control" placeholder="Enter desired email address"/>
	      </div>
	  </div>
	  <div class="control-group">
            <label class="control-label" for="password">Password:</label>
            <div class="controls">
                <input size="50" name="password" id="password" value="" type="password" class="form-control" placeholder="Password">
            </div>
       </div>
       <button name="submit" id="submit" value="" type="submit" class="btn btn-large btn-primary btn-block">Sign Up</button>
</form:form>
</body>
</html>  

