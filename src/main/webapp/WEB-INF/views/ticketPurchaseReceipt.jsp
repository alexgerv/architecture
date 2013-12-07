<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Receipt</title>
</head>
<body>
	<h2>Your payment has successfully completed</h2>
	<br>
	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Your purchase details</h3>
				</div>
				<div class="panel-body">
					<c:forEach var="section" items="${sections}">
						<h4>${section.sport}-${section.sex}</h4>
						<div class="row">
							<div class="col-md-6">
								<strong>Venue :</strong> ${section.venue}<br> <strong>Date
									:</strong> ${section.date}<br> <strong>Home Team :</strong>
								${section.homeTeam}<br> <strong>Visitor Team :</strong>
								${section.visitorTeam}<br>
							</div>
							<div class="col-md-6">
								<strong>Section :</strong> ${section.name}<br> <strong>Number
									of Tickets :</strong> <span id="quantity">${section.purchaseQuantity}</span><br> <strong>Individual
									Price :</strong> ${section.price}<br> <strong>Admission :</strong>
								${section.admissionType}<br>
							</div>
						</div>
						<hr>
					</c:forEach>
					<div class="text-right">
						<strong>TOTAL :</strong> ${purchaseTotal} $<br>
					</div>
				</div>
			</div>
			<div class="text-right">
				<form action="/" method="get">
					<input class="btn btn-default" type="submit"
						value="Back to home page">
				</form>
			</div>
		</div>
	</div>
</body>
</html>
