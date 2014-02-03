<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Travel Assistant</title>
</head>
<body>
	<div class="pagebanner">
		<table>
			<tr>
				<td width="30%"><img src="images/flight.png" width="100"
					height="110" /></td>
				<td width="70%"><h1>Bon Voyage</h1></td>
			</tr>
		</table>
	</div>
	<div class="mainbody">
		<h3>Enter your itinerary details:</h3>
		<br />
		<form method="POST" action="flightinfo">
			<b>Flight details</b><br /> Flight Carrier Code: <input type="text"
				name="flightCarrier" /> Flight Number: <input type="text"
				name="flightNumber" /><br /> <b>Travel date</b><br /> Year(YYYY): <input
				type="text" name="year" /> Month(MM): <input type="text"
				name="month" /> Day(DD): <input type="text" name="day" /> <br /> <input
				type="submit" value="GET DETAILS" />
		</form>
	</div>
</body>
</html>