<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Ticket Purchase</title>
</head>
<body>
	<h1>
		You are buying <strong>${quantity}</strong> tickets for the match:
	</h1> 
	<h2>
	   ${section.homeTeam} VS ${section.visitorTeam}
	   <br>
	   at ${section.venue}
	   <br>
	   on the ${section.date}
	</h2>
	<form action="/purchaseConfirm/${section.venue}/${section.date}/${section.name}" method="post">
        <h3>
            Payment method:<br>
            <input type="radio" name="credit_type" value="Mistercard">Mistercard<br>
            <input type="radio" name="credit_type" value="Vasi">Vasi<br>
            <input type="radio" name="credit_type" value="AmericanExpresso">AmericanExpresso<br>
            Card Number:<br>
            <input type="text" name="creditCard_number"><br>
            <input type="hidden" name="quantity" value="${quantity}">
            <input  type="submit" value="Confirm"/>
        </h3>
    </form>	
</body>
</html>
