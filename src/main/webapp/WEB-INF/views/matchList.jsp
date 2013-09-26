<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Match List</title>
</head>
<body>
<h1>Match List</h1>
<hr>
<table class="table table-condensed table-bordered table-striped" id="matchList">
<thead>
	<tr>
		<th></th>
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
		<td style="text-align: center;"><a href="match/${match.matchID}" class="btn btn-default btn-xs"><i class="icon icon-search"></i></a></td>
		<td>${match.venue}</td>
		<td>${match.date}</td>
		<td>${match.sport}</td>
		<td>${match.homeTeam}</td>
		<td>${match.visitorTeam}</td>
		<td><strong>${match.totalNumberOfAvailableTickets}</strong> (<a href="match/${match.matchID}">view by section</a>)</td>
	</tr>
</c:forEach>
</tbody>
</table>

</body>
</html>
