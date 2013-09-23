<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Match List</title>
</head>
<body>
<h1>MatchList</h1>

<c:forEach var="match" items="${matches}">
	<p>Match: ${match.sport}</p>
</c:forEach>

</body>
</html>
