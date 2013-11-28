<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Shopping Cart</title>
</head>
<body>
	<div class="row">
		<div class="col-sm-9">
			<div style="display: inline-block; float: clear">
				<h1>Shopping Cart</h1>
				<hr>
				<div id="searchMessage" class="alert alert-info" hidden="hidden">
					Your search produced no results.</div>
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
							<th>ID</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="ticket" items="${tickets}">
							<tr>
								<td style="text-align: center;">
								    <a href="match/${match.venue}" class="btn btn-default btn-xs">
								        <i class="icon icon-search"></i>
								    </a>
								</td>
								<td>${ticket.venue}</td>
								<td>${ticket.date}</td>
								<td>${ticket.sport}</td>
								<td>${ticket.homeTeam}</td>
								<td>${ticket.visitorTeam}</td>
								<td>${ticket.sex}</td>
								<td><strong>${ticket.ID}</strong></td>
                                <td>
                                    <form
                                        action="/cart/${section.venue}/remove/${ticket.ID}"
                                        method="post">
                                        <button type="submit" class="btn btn-default"> Remove from cart</button>

                                    </form>
                                </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
