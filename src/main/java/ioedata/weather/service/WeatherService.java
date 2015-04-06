package ioedata.weather.service;

import java.util.Map;

import ioedata.exception.factory.UserNotExistException;

/**
 * This interface provides many methods to deal with weather dynamic data,
 * such as ambient temperature, pressure, etc.
 * @author ajou
 *
 */
public interface WeatherService {
	/*
	 * Store weather information into database.
	 * First check whether user exists.
	 * If user exists, then store data; if not, throw UserNotExistException.
	 */
	public void logWeatherInfo(String userName, String userWifiSsid, Map<String, Object> dataPairs) throws UserNotExistException;
}
