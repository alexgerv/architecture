<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title><decorator:title default="Tickets"/></title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"  type="text/css" />
  <link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet"  type="text/css" />
  <decorator:head/>
</head>
<body>
	<div class="navbar navbar-default navbar-static-top">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/">Tickets</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="/">Home</a></li>
            <li><a href="/matchList">Matches</a></li>
          </ul>
   	 	</div>
    </div>
	<div class="container">
      <decorator:body/>
      <footer>
      	<hr> <p><small class="text-muted">&copy; By the B7 team.</small></p>
      </footer>
    </div>
</body>

</html>  