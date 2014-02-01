<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Travel Assistant</title>
</head>
<body>

<% String flightNumber = request.getParameter("flightNumber"); %>
<% String date = request.getParameter("date"); %>
<% String fromAirport = request.getParameter("fromAirport"); %>
You are flying in <%= flightNumber %> on <%= date %> from <%= fromAirport %>
</body>
</html>