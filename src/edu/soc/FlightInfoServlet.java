package edu.soc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
	        out.println("Hello World");
	        String data = "DO A POST!";
	        request.setAttribute("data", data);
	        request.getRequestDispatcher("/page.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flightNumber = request.getParameter("flightNumber"); 
		String date = request.getParameter("date"); 
		String fromAirport = request.getParameter("fromAirport"); 
		
		Flight flight = new Flight();
		flight.setDepDate(date);
		flight.setFlightNum(flightNumber);
		flight.setOrigin(fromAirport);

		request.setAttribute("flight", flight);
        request.setAttribute("date", date);
        request.setAttribute("flightNumber", flightNumber);
        request.setAttribute("fromAirport", fromAirport);
        
        request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

}
