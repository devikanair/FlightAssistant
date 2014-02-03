package edu.soc.external;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import edu.soc.models.Weather;

public class WeatherInfo {

	public static Weather getWeatherInfo(String place, String day_number) {
		// Construct a URI, pass API key and get forecast for next 10 days
		HttpGet httpget = null;
		URIBuilder uri = null;
		try {
			uri = new URIBuilder(
					"http://api.wunderground.com/api/cf9e052e936f13e8/forecast10day/q/"
							+ place + ".json");
			uri.addParameter("extendedOptions", "useInlinedReferences");
			uri.addParameter("appId", "a2d81565");

			uri.addParameter("appKey", "90c0ac5457d9ac6d80ddb19545b3ca29");
			httpget = new HttpGet(uri.build());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}

		// Create a response handler
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		HttpClient httpclient = new DefaultHttpClient();
		String responseBody;
		try {
			responseBody = httpclient.execute(httpget, responseHandler);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		//Parse the data and store it in a weather object
		Weather weather = new Weather();

		if (responseBody != null && responseBody.length() > 0) {
			JSONObject forecasts;
			try {
				forecasts = new JSONObject(responseBody)
						.getJSONObject("forecast");
			JSONObject txt_forecasts = forecasts.getJSONObject("txt_forecast");
			JSONArray forecastday = txt_forecasts.getJSONArray("forecastday");
			//Get the forecast of day we require
			int dayNum = Integer.parseInt(day_number);
			JSONObject forecastdayval = (JSONObject) forecastday.get(dayNum);
			JSONObject forecastnightval = (JSONObject) forecastday
					.get(dayNum + 1);

			weather.setForecastDay(forecastdayval.getString("icon"));
			weather.setForecastTextDay(forecastdayval.getString("fcttext"));
			weather.setForecastNight(forecastnightval.getString("icon"));
			weather.setForecastTextNight(forecastnightval.getString("fcttext"));
			weather.setAirportName(place);
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
		}
		return weather;
	}
}
