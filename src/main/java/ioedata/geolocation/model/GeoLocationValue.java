package ioedata.geolocation.model;

import java.util.Date;

import ioedata.mongodb.repository.DBUtils;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = DBUtils.GEO_LOCATION_NAME)
public class GeoLocationValue {
	@Id
	private ObjectId geoSerialNum;
	private double longitude;
	private double latitude;
	private Date geoTimestamp;
	private int userSerialNum;
	
	public GeoLocationValue() {
		super();
	}
	
	public GeoLocationValue(ObjectId geoSerialNum, double longitude,
			double latitude, Date geoTimestamp, int userSerialNum) {
		super();
		this.geoSerialNum = geoSerialNum;
		this.longitude = longitude;
		this.latitude = latitude;
		this.geoTimestamp = geoTimestamp;
		this.userSerialNum = userSerialNum;
	}

	public ObjectId getGeoSerialNum() {
		return geoSerialNum;
	}

	public void setGeoSerialNum(ObjectId geoSerialNum) {
		this.geoSerialNum = geoSerialNum;
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

	public Date getGeoTimestamp() {
		return geoTimestamp;
	}

	public void setGeoTimestamp(Date geoTimestamp) {
		this.geoTimestamp = geoTimestamp;
	}

	public int getUserSerialNum() {
		return userSerialNum;
	}

	public void setUserSerialNum(int userSerialNum) {
		this.userSerialNum = userSerialNum;
	}

	@Override
	public String toString() {
		return "GeoLocationValue [geoSerialNum=" + geoSerialNum
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", geoTimestamp=" + geoTimestamp + ", userSerialNum="
				+ userSerialNum + "]";
	}
}
