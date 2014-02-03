package edu.soc;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.soc.external.FetchFlightInfo;
import edu.soc.external.WeatherInfo;
import edu.soc.models.Flight;
import edu.soc.models.Weather;

/**
 * Servlet implementation class FlightInfoServlet
 */
@WebServlet("/FlightInfoServlet")
public class FlightInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlightInfoServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) We are not implementing get
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("Get requests are not supported. Return to home page and do a post request");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) Handle the inputs from jsp, call external functions,
	 *      redirect to result or error
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Get inputs from form
		String flightCarrier = request.getParameter("flightCarrier");
		String flightNumber = request.getParameter("flightNumber");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");

		// Make call to get flight details
		Flight flight = FetchFlightInfo.getFlightInfo(flightCarrier + "/"
				+ flightNumber, year + "/" + month + "/" + day);

		// Check if travel date is within 10 days from now
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DAY_OF_YEAR, 10); // <--
		Date rangeEnd = cal.getTime();

		Date travelDate = null, arrivalDate = null;
		try {
			// Get the dep and arrival dates
			travelDate = new Date(year + "/" + month + "/" + day);
			//If arrival date is not available, default it to travel Date
			if(flight.getArrivalTime() != null  && flight.getArrivalTime().length()>10)
			arrivalDate = new Date(
					(flight.getArrivalTime().substring(0, 10))
							.replace("-", "/"));
			else
				arrivalDate=travelDate;
		} catch (IllegalArgumentException e) {
			// Redirect to error when the date entered is not correct
			request.setAttribute("flight", null);
			request.getRequestDispatcher("/error.jsp").forward(request,
					response);
			return;
		}

		// Find number of days until departure and arrival
		long diffDep = (travelDate.getTime() - now.getTime())/(24*60*60*1000);
		long diffArrival = (arrivalDate.getTime() - now.getTime())/(24*60*60*1000);
		
		if (diffDep >= 0 && diffDep < 10) {
			String arrival_airport = flight.getArrivalAirport();
			String departure_airport = flight.getDepartureAirport();

			Weather weatherDeparture = WeatherInfo.getWeatherInfo(
					departure_airport, String.valueOf(diffDep));

			Weather weatherArrival = WeatherInfo.getWeatherInfo(
					arrival_airport, String.valueOf(diffArrival));
		
			if (weatherDeparture == null ) {
				weatherDeparture = new Weather();
				weatherDeparture.setForecastTextDay("UNKNOWN");
				weatherDeparture.setForecastTextNight("UNKNOWN");
			}
			if (weatherArrival == null) {
				weatherArrival = new Weather();
				weatherArrival.setForecastTextDay("UNKNOWN");
				weatherArrival.setForecastTextNight("UNKNOWN");
			}
			request.setAttribute("weatherD", weatherDeparture);
			request.setAttribute("weatherA", weatherArrival);
		}
		else{
			Weather weatherDeparture = new Weather();
			weatherDeparture.setForecastTextDay("Beyond prediction limit of 10 days");
			weatherDeparture.setForecastTextNight("Beyond prediction limit of 10 days");
		
			Weather weatherArrival = new Weather();
			weatherArrival.setForecastTextDay("Beyond prediction limit of 10 days");
			weatherArrival.setForecastTextNight("Beyond prediction limit of 10 days");
			request.setAttribute("weatherD", weatherDeparture);
			request.setAttribute("weatherA", weatherArrival);
		}

		// Set the flight attribute
		request.setAttribute("flight", flight);
		// Display results on result.jsp
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

}
