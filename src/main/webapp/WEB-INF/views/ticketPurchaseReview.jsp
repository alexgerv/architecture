<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Ticket Purchase</title>
</head>
<body>
	<h2>Review Purchase</h2>
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
									of Tickets :</strong> <span id="quantity">${quantity}</span><br> <strong>Individual
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
		</div>
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Please enter your payment information</h3>
				</div>
				<div class="panel-body">
					<form:form action="${purchaseURL}" commandName="creditCardForm">
						<br>
						<div class="ckeckbox">

							<label> <form:radiobutton path="type"
								value="MISTERCARD" checked="checked" /> Mistercard

							</label>
							<div class="ckeckbox">
								<label><form:radiobutton path="type" value="VASI" />
									Vasi </label>
							</div>
							<div class="ckeckbox">
								<label><form:radiobutton path="type"
										value="AMERICANEXPRESSO" /> AmericanExpresso </label>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label for="creditCard_number">Card Number</label>
							<div class="row">
								<div class="col-xs-4">
									<input type=hidden name="quantity" value=${quantity } />
									<form:input type="text" class="form-control" path="number"
										placeholder="credit card number" />
								</div>

							</div>
						</div>
						<button type="submit" class="btn btn-default">Confirm
							payment</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
