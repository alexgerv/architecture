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
							<th/>
							<th>Venue</th>
							<th>Date</th>
							<th>Sport</th>
							<th>Host</th>
							<th>Visitor</th>
							<th>Sex</th>
							<th>Section</th>
							<th>Admission Type</th>
							<th>Quantity</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="section" items="${cartContent}">
							<tr>
								<td style="text-align: center;">
								    <a href="/match/${section.venue}/${section.date}/${section.name}" class="btn btn-default btn-xs">
								        <i class="icon icon-search"></i>
								    </a>
								</td>
								<td>${section.venue}</td>
								<td>${section.date}</td>
								<td>${section.sport}</td>
								<td>${section.homeTeam}</td>
								<td>${section.visitorTeam}</td>
								<td>${section.sex}</td>
								<td>${section.name}</td>
								<td>${section.admissionType}</td>
								<td><strong>${section.availableTickets}</strong></td>
                                <td>
                                    <form
                                        action="/cart/${section.venue}/remove/"
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
