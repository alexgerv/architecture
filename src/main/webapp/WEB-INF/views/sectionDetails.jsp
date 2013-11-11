<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Section Details</title>
</head>
<body>
	<h1>
		Section Details: <small>${section.homeTeam} VS
			${section.visitorTeam}</small>
	<hr>
	</h1>
	<c:if test="${not empty message}">
		<div class="alert alert-info">${message}</div>
	</c:if>
	<div class="row">
		<div class="col-md-6">
			<table class="table">
				<tbody>
					<tr>
						<td><strong>Sport</strong></td>
						<td>${section.sport}</td>
					</tr>
					<tr>
						<td><strong>Home team</strong></td>
						<td>${section.homeTeam}</td>
					</tr>
					<tr>
						<td><strong>Visitor team</strong></td>
						<td>${section.visitorTeam}</td>
					</tr>
					<tr>
						<td><strong>Admission Type</strong></td>
						<td>${section.admissionType}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="col-md-6">
			<table class="table">
				<tbody>
					<tr>
						<td><strong>Date</strong></td>
						<td>${section.date}</td>
					</tr>
					<tr>
						<td><strong>Section</strong></td>
						<td>${section.name}</td>
					</tr>
					<tr>
						<td><strong>Sex</strong></td>
						<td>${section.sex}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<c:choose>
		<c:when test="${section.availableTickets < 1}">
			<div class="alert alert-warning">No more tickets available!</div>
		</c:when>
		<c:otherwise>
			<form action="/purchaseReview/${section.venue}/${section.date}/${section.name}" method="get">
				<h4>
					Price: ${section.price}$ x 
					<input type="number" name="quantity" value="1" min="1" max="${section.availableTickets}" />
					<input  type="submit" value="Buy"/>
				</h4>
			</form>
		</c:otherwise>
	</c:choose>	
</body>
</html>
