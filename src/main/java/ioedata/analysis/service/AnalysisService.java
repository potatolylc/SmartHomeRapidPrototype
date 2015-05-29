package ioedata.analysis.service;

import ioedata.geolocation.model.GeoCoordinate;

/**
 * This interface provides service-oriented data analysis service based on real-time information collected from home environment or users.
 * @author ajou
 *
 */
public interface AnalysisService {
	/**
	 * Check whether the user is in vicinity of his/her home.
	 * @param geoCoordinate
	 * @param userSerialNum
	 * @return
	 */
	public boolean isUserNearHome(GeoCoordinate geoCoordinate, int userSerialNum);
}
