<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="edu.soc.models.Flight"%>
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
		Details for your flight from ${flight.departureAirportCity } to
		${flight.arrivalAirportCity }:<br /> <br /> Departure Airport:
		${flight.departureAirportName } (${flight.departureAirport }) <br />
		Departure Time: ${flight.departureTime } <br /> Departure Terminal:
		${flight.departureAirportTerminal}<br /> <br /> Arrival Airport:
		${flight.arrivalAirportName } (${flight.arrivalAirport }) <br />
		Arrival Time: ${flight.arrivalTime } <br /> Arrival Terminal:
		${flight.arrivalAirportTerminal}<br /> <br /> Flight ${flight.carrier }
		${flight.flightNumber } has ${flight.stops } stops between the 2
		airports <br />
		<br /> Contact ${flight.carrierName } at ${flight.carrierPhone } for
		any queries <br /> <a href="index.jsp">Go Back</a> <br /> Thank you
		for using Bon Voyage travel assistant by Karthika P and Devika Nair. <br />
	</div>
</body>
</html>