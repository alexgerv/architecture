<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Match List</title>
</head>
<body>
<h1>MatchList</h1>

<table>

<tr>
	<td>Venue</td><td>Date</td><td>Sport</td><td>Host</td><td>Visitor</td><td>Number of Available Tickets</td>
</tr>

<c:forEach var="match" items="${matches}">
<tr>
	<td><a href="${match.matchID}">${match.venue}</td><td>${match.date}</td><td>${match.sport}</td><td>${match.homeTeam}</td><td>${match.visitorTeam}</td><td>${match.totalNumberOfAvailableTickets}</td></a></td>
</tr>
</c:forEach>
</table>

</body>
</html>
