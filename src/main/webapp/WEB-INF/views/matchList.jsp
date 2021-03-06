<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Match List</title>
</head>
<body>
	<div class="row">
		<div class="col-sm-3">
			<div class="well"><%@include file="searchBar.jsp"%></div>
		</div>
		<div class="col-sm-9">
			<div style="display: inline-block; float: clear">
				<h1>Match List</h1>
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
							<th>Number of Available Tickets</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="match" items="${matches}">
							<tr>
								<td style="text-align: center;">
								    <a href="match/${match.venue}" class="btn btn-default btn-xs">
								        <i class="icon icon-search"></i>
								    </a>
								</td>
								<td>${match.venue}</td>
								<td>${match.date}</td>
								<td>${match.sport}</td>
								<td>${match.homeTeam}</td>
								<td>${match.visitorTeam}</td>
								<td>${match.sex}</td>
								<td><strong>${match.totalNumberOfAvailableTickets}</strong>
									(<a href="match/${match.venue}/${match.date}">view by section</a>)
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
