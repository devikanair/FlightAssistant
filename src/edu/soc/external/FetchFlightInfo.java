package edu.soc.external;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import edu.soc.models.Flight;

public class FetchFlightInfo {
	// Sample main for testing purpose
	/*
	 * public static void main(String[] args) throws URISyntaxException,
	 * ClientProtocolException, IOException { // Sample call for testing Flight
	 * flight = getFlightInfo("WN/721", "2014/02/03"); //
	 * System.out.println(flight.getArrivalDate()); }
	 */

	/**
	 * 
	 * @param flightNum
	 * @param depDate
	 * @return Flight This function gets flight information from flightstats API
	 */
	public static Flight getFlightInfo(String flightNum, String depDate) {
		// Make a call to flight schedules to get more details of your flight
		URIBuilder uri = null;
		HttpGet httpget = null;
		try {
			uri = new URIBuilder(
					"https://api.flightstats.com/flex/schedules/rest/v1/json/flight/"
							+ flightNum + "/departing/" + depDate);
			// Provide API key
			uri.addParameter("extendedOptions", "useInlinedReferences");
			uri.addParameter("appId", "a2d81565");
			uri.addParameter("appKey", "90c0ac5457d9ac6d80ddb19545b3ca29");

			httpget = new HttpGet(uri.build());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		// Create a response handler
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		HttpClient httpclient = new DefaultHttpClient();
		String responseBody = null;
		try {
			responseBody = httpclient.execute(httpget, responseHandler);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Create object of class flight to hold the parsed values
		Flight flight = new Flight();

		// Parse the JSON response
		try {
			if (responseBody != null && responseBody.length() > 0) {
				JSONArray schedules = new JSONObject(responseBody)
				.getJSONArray("scheduledFlights");
				if (schedules.length() > 0) {
					JSONObject schedule = (JSONObject) schedules.get(0);
					// Set the flight details in the object and return to caller

					flight.setDepartureTime(schedule.getString("departureTime"));
					flight.setArrivalTime(schedule.getString("arrivalTime"));
					flight.setFlightNumber(schedule.getString("flightNumber"));
					flight.setCarrierName(((JSONObject) schedule.get("carrier"))
							.getString("name"));
					flight.setCarrier(((JSONObject) schedule.get("carrier"))
							.getString("fs"));
					flight.setCarrierPhone(((JSONObject) schedule
							.get("carrier")).getString("phoneNumber"));
					flight.setDepartureAirport(((JSONObject) schedule
							.get("departureAirport")).getString("fs"));
					flight.setDepartureAirportName(((JSONObject) schedule
							.get("departureAirport")).getString("name"));
					flight.setDepartureAirportCity(((JSONObject) schedule
							.get("departureAirport")).getString("city"));
					flight.setArrivalAirport(((JSONObject) schedule
							.get("arrivalAirport")).getString("fs"));
					flight.setArrivalAirportName(((JSONObject) schedule
							.get("arrivalAirport")).getString("name"));
					flight.setArrivalAirportCity(((JSONObject) schedule
							.get("arrivalAirport")).getString("city"));
					if (schedule.has("stops"))
						flight.setStops(schedule.getString("stops"));
					else
						flight.setStops("-");
					if (schedule.has("arrivalTerminal"))
						flight.setDepartureAirportTerminal(schedule
								.getString("departureTerminal"));
					else
						flight.setDepartureAirportTerminal("Unknown");
					if (schedule.has("arrivalTerminal"))
						flight.setArrivalAirportTerminal(schedule
								.getString("arrivalTerminal"));
					else
						flight.setArrivalAirportTerminal("Unknown");
				}
				return flight;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		// Return null in case of any error
		return null;

	}
}
