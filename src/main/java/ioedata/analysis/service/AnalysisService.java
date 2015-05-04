package ioedata.analysis.service;

import ioedata.geolocation.model.GeoCoordinate;

public interface AnalysisService {
	public boolean isUserNearHome(GeoCoordinate geoCoordinate, int userSerialNum);
}
