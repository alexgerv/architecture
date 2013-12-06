<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Shopping Cart</title>
</head>
<body>
	<div class="row">
		<div class="col-sm-12">
			<div style="display: inline-block; float: clear">
				<h1>Shopping Cart</h1>
				<hr>
				<div id="warningMessage" class="alert alert-danger hidden">
					There are not enough tickets, please select less tickets.</div>
				<table id="dataTable"
					class="table table-bordered table-condensed table-striped">
					<thead>
						<tr>
							<th></th>
							<th>Venue</th>
							<th>Date</th>
							<th>Sport</th>
							<th>Host</th>
							<th>Visitor</th>
							<th>Sex</th>
							<th>Section</th>
							<th>Admission Type</th>
							<th>Quantity</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="ticket" items="${cartContent}">
							<tr>
								<td style="text-align: center;">
								    <a href="/match/${ticket.venue}/${ticket.date}/${ticket.name}" class="btn btn-default btn-xs">
								        <i class="icon icon-search"></i>
								    </a>
								</td>
								<td>${ticket.venue}</td>
								<td>${ticket.date}</td>
								<td>${ticket.sport}</td>
								<td>${ticket.homeTeam}</td>
								<td>${ticket.visitorTeam}</td>
								<td>${ticket.sex}</td>
								<td>${ticket.name}</td>
								<td>${ticket.admissionType}</td>
								<td>
									<form class="form-inline">
										<div class="form-group">
											<input class="form-control numberOfTickets" type="number" value="${ticket.availableTickets}">
										</div>
										<a class="btn btn-primary updateCart" data-url="/cart/changeQuantity/${ticket.venue}/${ticket.date}/${ticket.name}"><i class="icon-refresh"></i></a>	
									</form>
								</td>
                                <td>
                                	<button class="btn btn-default removeFromCart" data-url="/cart/remove/${ticket.venue}/${ticket.date}/${ticket.name}"><i class="icon-remove-sign"></i> Remove from cart</button>
                                </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

<script>
	$(document).ready(function() {
		$(".updateCart").click(function() {
			var numberOfTicketsInput = $(this).parent().find('.numberOfTickets');
			var updateCartButton = $(this);
			var numberOfTickets = numberOfTicketsInput.val();
			updateCartButton.addClass("disabled");
			updateCartButton.html('<i class="icon-refresh icon-spin"></i>');
			$.ajax({
				url: $(this).attr('data-url'),
				data: {
					quantity: numberOfTickets
				},
				type: "POST"
			}).done(function(data) {
				$('#warningMessage').addClass("hidden");
				updateCartButton.removeClass("btn-primary").addClass("btn-success");
				updateCartButton.html('<i class="icon-ok-sign "></i>')
				setTimeout(function() {
					updateCartButton.addClass("btn-primary").removeClass("btn-success disabled");
					updateCartButton.html('<i class="icon-refresh"></i>');
				}, 1000);
				
			}).error(function(data) {
				$('#warningMessage').removeClass("hidden").html('There are not enough tickets to purchase ' + numberOfTickets + ', please select a smaller quantity.');
				numberofTicketsInput.val(numberOfTickets);
				updateCartButton.removeClass("btn-primary").addClass("btn-danger");
				updateCartButton.html('<i class="icon-remove-sign "></i>')
				setTimeout(function() {
					updateCartButton.addClass("btn-primary").removeClass("btn-danger disabled");
					updateCartButton.html('<i class="icon-refresh"></i>');
				}, 2000);
			});
		}); 


		$(".removeFromCart").click(function() {
			var numberOfTicketsInput = $(this).parent().find('.numberOfTickets');
			var removeFromCartButton = $(this);
			var numberOfTickets = numberOfTicketsInput.val();
			removeFromCartButton.addClass("disabled");
			removeFromCartButton.html('<i class="icon-refresh icon-spin"></i> Removing...');
			$.ajax({
				url: $(this).attr('data-url'),
				type: "POST"
			}).done(function(data) {
				$('#warningMessage').addClass("hidden");
				removeFromCartButton.parents('tr').remove();
			}).error(function(data) {
			});
		}); 
	});
</script>
</body>
</html>

