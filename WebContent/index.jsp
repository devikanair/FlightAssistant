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
<td width="30%"><img src="images/flight.png"  width="100" height="110" /></td>
<td width="70%"><h1>Bon Voyage</h1></td>
</tr>
</table>
</div>
<div class="mainbody">
Enter your itinerary details
<form method="POST" action="flightinfo">
Flight Number: <input type="text" name="flightNumber"/>
Origin Airport: <input type="text" name="fromAirport"/>
Date: <input type="text" name="date"/>
<input type="submit" value="GO"/>
</form>
</div>
</body>
</html>