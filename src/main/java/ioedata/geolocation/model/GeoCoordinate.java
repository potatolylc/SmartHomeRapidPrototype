package ioedata.geolocation.model;

/**
 * This class creates data transport objects that contain the geographic coordinates' information. 
 * @author ajou
 *
 */
public class GeoCoordinate {
	private double longitude;
	private double latitude;
	public GeoCoordinate() {
		super();
	}
	public GeoCoordinate(double longitude, double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "GeoCoordinate [longitude=" + longitude + ", latitude="
				+ latitude + "]";
	}
	
}
