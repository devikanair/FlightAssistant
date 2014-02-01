package org.cmu.soc;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class FlightInfo {
	public static void main(String [ ] args) throws IOException, ParseException{
		FlightInfo.fetch("UA502","2014-01-31","SFO");
	}
	public FlightInfo() throws IOException{
		
	}
	public static void fetch(String flightID,String date, String dep_para){
		//AA/1673/departing/2013/11/17?=&appKey=&extendedOptions=useInlinedReferences
		HttpClient httpclient = new DefaultHttpClient();
		try {
			String uc = flightID.substring(0, 2);
			String num = flightID.substring(2);
			String res = uc+"/"+num;
			 String newdate = date.replace('-', '/');
			//List<NameValuePair> qparams = new ArrayList<NameValuePair>();
			//qparams.add(new BasicNameValuePair("q", airport.getLatitude()+","+airport.getLongitude()));
			URIBuilder uri = new URIBuilder("https://api.flightstats.com/flex/schedules/rest/v1/json/flight/"+res+"/departing/"+newdate);
			uri.addParameter("extendedOptions", "useInlinedReferences");
			uri.addParameter("appId", "a2d81565");
			
			uri.addParameter("appKey", "90c0ac5457d9ac6d80ddb19545b3ca29");
		
//http://api.worldweatheronline.com/free/v1/weather.ashx?q=sunnyvale&format=json&num_of_days=5&key=xekxkssj32w832j6bkkxets7
			HttpGet httpget = new HttpGet(uri.build());

			System.out.println("executing request " + httpget.getURI());

			// Create a response handler
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);
			//System.out.println(responseBody);
			JSONArray schedules = new JSONObject(responseBody).getJSONArray("scheduledFlights");
			for(int i=0;i<schedules.length();i++){
				JSONObject schedule=  (JSONObject) schedules.get(0);
				JSONObject departureAirport =(JSONObject) schedule.getJSONObject("departureAirport");
				if(departureAirport.getString("iata").equals(dep_para)){
					JSONObject carrie = (JSONObject) schedule.getJSONObject("carrier");
					
					JSONObject arrivalAirport = (JSONObject) schedule.getJSONObject("arrivalAirport");
					System.out.println("acrrie:  -"+carrie.toString());
					System.out.println("departureAirport:  -"+departureAirport.toString());
					System.out.println("arrivalAirport:  -"+arrivalAirport.toString());
					/*
					 * departureTerminal: "3",
		arrivalTerminal: "7",
		departureTime: "2013-11-18T16:35:00.000",
		arrivalTime: "2013-11-19T00:54:00.000",
					 */
					String departureTime = schedule.getString("departureTime");
					String arrivalTime = schedule.getString("arrivalTime");
					System.out.println(departureTime+" ;"+arrivalTime);
					SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss.SSS");
					/*JSONObject current = myjson.getJSONArray("time_zone").getJSONObject(0);
					System.out.println("***********"+current.getString("localtime"));
					*/
					Date depDate = format.parse(departureTime);
					Date arrivalDate = format.parse(arrivalTime);
					//int depTZ = departureAirport.getInt("utcOffsetHours");
					//int arrTZ = arrivalAirport.getInt("utcOffsetHours");
					
					String dep = depDate.getHours()+""+depDate.getMinutes();
					String arr = arrivalDate.getHours()+""+arrivalDate.getMinutes();
					String departCity=departureAirport.getString("fs");
					String arrivalCity= arrivalAirport.getString("fs");
					String carrier_name = carrie.getString("name");
					System.out.println(flightID+" "+departCity+" "+" "+arrivalCity+ 
					" carrier_name "+carrier_name+" ;  time:"+dep+" "+arr);
					
					
				}
				
			}
			
			
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		catch(Exception e){
			e.printStackTrace();

		}
		
		
	}
}
