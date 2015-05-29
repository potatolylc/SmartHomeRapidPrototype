package ioedata.geolocation.service;

import ioedata.exception.factory.UserNotExistException;

/**
 * This interface provides many methods to deal with geospatial location,
 * such as geospatial storage, finding people around, etc.
 * @author ajou
 *
 */
public interface GeoLocationService {
	/**
	 * Store location information into database.
	 * First check whether user exists.
	 * If user exists, then store data; if not, throw UserNotExistException.
	 * @param userName
	 * @param userWifiSsid
	 * @param longitude
	 * @param latitude
	 * @throws UserNotExistException
	 */
	public void logLocationInfo(String userName, String userWifiSsid, double longitude, double latitude) throws UserNotExistException;
}
