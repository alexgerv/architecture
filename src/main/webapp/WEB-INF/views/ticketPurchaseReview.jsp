<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Ticket Purchase</title>
</head>
<body>
	<h2>
		Review Purchase
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
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Please enter your payment information</h3>
				</div>
				<div class="panel-body">
					<form role="form"
						action="/purchaseConfirm/${section.venue}/${section.date}/${section.name}"
						method="post">
						<br>
						<div class="ckeckbox">
							<label> <input type="radio" name="credit_type"
								value="Mistercard"> Mistercard
							</label>
							<div class="ckeckbox">
								<label><input type="radio" name="credit_type"
									value="Vasi"> Vasi </label>
							</div>
							<div class="ckeckbox">
								<label><input type="radio" name="credit_type"
									value="AmericanExpresso"> AmericanExpresso </label>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label for="creditCard_number">Card Number</label>
							<div class="row">
								<div class="col-xs-4">
								<input type=hidden name="quantity" value=${quantity}>
									<input type="text" class="form-control"
										name="creditCard_number" placeholder="credit card number">
								</div>

							</div>
						</div>
						<button type="submit" class="btn btn-default">Confirm
							payment</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
