<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Shopping Cart</title>
</head>
<body>
	<h2>
		Shopping Cart
	</h2><br>
	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Your purchase details</h3>
				</div>
				<div class="panel-body">
					<h4>${section.sport} - ${section.sex}</h4>
					<strong>Venue :</strong>
					${section.venue}<br>
					<strong>Date :</strong>
					${section.date}<br>
					<strong>Home Team :</strong>
					${section.homeTeam}<br>
					<strong>Visitor Team :</strong>
					${section.visitorTeam}<br>
					<hr>
					<strong>Section :</strong> ${section.name}<br>
					<strong>Number of Tickets :</strong>
					<span id="quantity">${quantity}</span><br>
					<strong>Individual Price :</strong>
					${section.price}<br>
					<strong>Admission :</strong>
					${section.admissionType}<br>
					<hr>
					<div class="text-right">
					<strong>TOTAL :</strong>
					${purchaseTotal} $<br>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
