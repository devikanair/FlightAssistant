package edu.soc.models;

public class Weather {
	String forecastDay;
	String forecastNight;
	String forecastTextDay;
	String forecastTextNight;
	String airport_name;

	public void setForecastDay(String dayForecast) {
		forecastDay = dayForecast;
	}

	public void setForecastNight(String nightForecast) {
		forecastTextDay = nightForecast;
	}

	public void setForecastTextNight(String nightForecastText) {
		forecastTextNight = nightForecastText;
	}

	public void setForecastTextDay(String dayForecastText) {
		forecastNight = dayForecastText;
	}

	public void setAirportName(String name) {
		airport_name = name;
	}

	public String getForecastDay() {
		return forecastDay;
	}

	public String getForecastNight() {
		return forecastTextDay;
	}

	public String getForecastTextDay() {
		return forecastNight;
	}

	public String getForecastTextNight() {
		return forecastTextNight;
	}

	public String airportName() {
		return airport_name;
	}

}
