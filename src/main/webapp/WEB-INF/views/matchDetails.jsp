<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Match Details</title>
</head>
<body>
<h1>Match Details</h1>

<table>

<tr>
	<td>Section</td><td>Number Of Available Tickets</td>
</tr>

<c:forEach var="availableTicketsBySections" items="${availableTicketsBySection}">

<tr>
	<td>${availableTicketsBySections.key}</td><td>${availableTicketsBySections.value}</td>
</tr>

</c:forEach>

</table>

</body>
</html>
