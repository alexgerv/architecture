<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Match Details</title>
</head>
<body>
<h1>Match Details</h1>
<hr>
<table class="table table-condensed table-bordered table-striped">
<thead>
	<tr>
		<th>Section</th>
		<th>Number Of Available Tickets</th>
	</tr>
</thead>
<tbody>
<c:forEach var="availableTicketsBySections" items="${availableTicketsBySection}">
	<tr>
		<td>${availableTicketsBySections.key}</td>
		<td>${availableTicketsBySections.value}</td>
	</tr>
</c:forEach>
</tbody>
</table>

</body>
</html>
