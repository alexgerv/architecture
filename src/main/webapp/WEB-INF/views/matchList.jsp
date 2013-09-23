<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Match List</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"  type="text/css" />    
</head>
<body>
	<div class="container">

      <div class="navbar navbar-default">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Tickets</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="/">Home</a></li>
            <li class="active"><a href="#">Matches</a></li>
          </ul>
        </div>
      </div>
      
      <div class="row">
      	<div class="col-md-12">
      		<h1>Match List</h1>
      		<hr>
      	</div>
      </div>
      <div class="row">
      	<div class="col-md-12">
      		<c:forEach var="match" items="${matches}">
				<p>Match: ${match.sport}</p>
			</c:forEach>
      	</div>
      </div>
    </div>
</body>
</html>