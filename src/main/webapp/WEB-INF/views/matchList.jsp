<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Match List</title>
</head>
<body>
<h1>MatchList</h1>
<hr>
<table class="table table-condensed table-bordered table-striped">
<thead>
	<tr>
		<th>Venue</th>
		<th>Date</th>
		<th>Sport</th>
		<th>Host</th>
		<th>Visitor</th>
		<th>Number of Available Tickets</th>
	</tr>
</thead>
<tbody>
<c:forEach var="match" items="${matches}">
	<tr>
		<td><a href="match/${match.matchID}">${match.venue}</a></td>
		<td>${match.date}</td>
		<td>${match.sport}</td>
		<td>${match.homeTeam}</td>
		<td>${match.visitorTeam}</td>
		<td>${match.totalNumberOfAvailableTickets}</td>
	</tr>
</c:forEach>
</tbody>
</table>

</body>
</html>
