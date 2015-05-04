package ioedata.geolocation.model;

import java.util.Date;

import ioedata.mongodb.repository.DBUtils;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = DBUtils.GEO_LOCATION_COLLECTION_NAME)
public class GeoLocationValue {
	@Id
	private ObjectId geoSerialNum;
	@Indexed
	private GeoCoordinate geoCoordinate;
	private Date geoTimestamp;
	private int userSerialNum;

	public GeoLocationValue() {
		super();
	}

	public GeoLocationValue(GeoCoordinate geoCoordinate, Date geoTimestamp,
			int userSerialNum) {
		super();
		this.geoCoordinate = geoCoordinate;
		this.geoTimestamp = geoTimestamp;
		this.userSerialNum = userSerialNum;
	}

	public GeoLocationValue(ObjectId geoSerialNum, GeoCoordinate geoCoordinate,
			Date geoTimestamp, int userSerialNum) {
		super();
		this.geoSerialNum = geoSerialNum;
		this.geoCoordinate = geoCoordinate;
		this.geoTimestamp = geoTimestamp;
		this.userSerialNum = userSerialNum;
	}

	public GeoCoordinate getGeoCoordinate() {
		return geoCoordinate;
	}

	public void setGeoCoordinate(GeoCoordinate geoCoordinate) {
		this.geoCoordinate = geoCoordinate;
	}

	public ObjectId getGeoSerialNum() {
		return geoSerialNum;
	}

	public void setGeoSerialNum(ObjectId geoSerialNum) {
		this.geoSerialNum = geoSerialNum;
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
				+ ", geoCoordinate=" + geoCoordinate + ", geoTimestamp="
				+ geoTimestamp + ", userSerialNum=" + userSerialNum + "]";
	}

}
