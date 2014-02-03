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
import edu.soc.models.Flight;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * We are not implementing get
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
	        out.println("Get requests are not supported. Return to home page and do a post request");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Handle the inputs from jsp, call external functions, redirect to result or error
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get inputs from form
		String flightCarrier = request.getParameter("flightCarrier"); 
		String flightNumber = request.getParameter("flightNumber"); 
		String year = request.getParameter("year"); 
		String month = request.getParameter("month"); 
		String day = request.getParameter("day"); 

		//Make call to get flight details
		Flight flight = FetchFlightInfo.getFlightInfo(flightCarrier+"/"+flightNumber, year+"/"+month+"/"+day);
		
		//Check if travel date is within 10 days from now
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DAY_OF_YEAR, 10); // <--
		Date rangeEnd = cal.getTime();
		System.out.println(dateFormat.format(rangeEnd));
		Date travelDate=null;
		try{
			travelDate = new Date(year+"/"+month+"/"+day);
		}
		catch(IllegalArgumentException e){
			//Redirect to error when the date entered is not correct
			request.setAttribute("flight", null);
	        request.getRequestDispatcher("/error.jsp").forward(request, response);
	        return;
		}
		if (travelDate.compareTo(rangeEnd) >= 0) {
			//TODO: will fetch weather here
			System.out.println("Karthika add call for weather similar to flight and populate a weather object. Do a null check and then we can print wethaer details");
		}
		
		//Set the flight attribute 
		request.setAttribute("flight", flight);
		//Display results on result.jsp
        request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

}
