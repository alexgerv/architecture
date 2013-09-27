<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Match Details</title>
</head>
<body>
<h1>Match Details: <small>${match.homeTeam} VS ${match.visitorTeam}</small></h1>
<hr>
<div class="row">
	<div class="col-md-6">
		<table class="table">
		<tbody>
			<tr>
				<td><strong>Sport</strong></td>
				<td>${match.sport}</td>
			</tr>
			<tr>
				<td><strong>Home team</strong></td>
				<td>${match.homeTeam}</td>
			</tr>
			<tr>
				<td><strong>Visitor team</strong></td>
				<td>${match.visitorTeam}</td>
			</tr>
		</tbody>
		</table>
	</div>
	<div class="col-md-6">
		<table class="table">
		<tbody>
			<tr>
				<td><strong>Date</strong></td>
				<td>${match.date}</td>
			</tr>
			<tr>
				<td><strong>Venue</strong></td>
				<td>${match.venue}</td>
			</tr>
			<tr>
				<td><strong>Available tickets</strong></td>
				<td>${match.totalNumberOfAvailableTickets}</td>
			</tr>
		</tbody>
		</table>
	</div>
</div>
<h3>Tickets by sections:</h3>
<table class="table table-condensed table-bordered table-striped" id="matchDetails">
<thead>
	<tr>
		<th>Section</th>
		<th>Number Of Available Tickets</th>
	</tr>
</thead>
<tbody>
<c:forEach var="availableTicketsBySection" items="${match.availableTicketsBySection}">
	<tr>
		<td>${availableTicketsBySection.key}</td>
		<td class="ticketsBySection">${availableTicketsBySection.value}</td>
	</tr>
</c:forEach>
</tbody>
</table>

</body>
</html>
