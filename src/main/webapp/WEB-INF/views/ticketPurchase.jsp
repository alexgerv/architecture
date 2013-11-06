<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Ticket Purchase</title>
</head>
<body>
	<h1>
		You are buying <strong>${quantity}</strong> tickets for the match <small>${section.homeTeam} VS
			${section.visitorTeam}</small> at ${section.venue}!
	</h1>	
</body>
</html>
