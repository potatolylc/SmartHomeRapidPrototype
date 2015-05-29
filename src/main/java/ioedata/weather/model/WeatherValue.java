package ioedata.weather.model;

import java.util.Date;

import ioedata.mongodb.repository.DBUtils;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class creates data transport objects that contain the user's ambient environment information. 
 * @author ajou
 *
 */
@Document(collection = DBUtils.WEATHER_COLLECTION_NAME)
public class WeatherValue {
	@Id
	private ObjectId weatherSerialNum;
	private String weatherDataType;
	private int weatherDataTypeNum;
	private double dataValue;
	private Date weatherTimestamp;
	private int userSerialNum;
	
	public WeatherValue() {
		super();
	}

	public WeatherValue(String weatherDataType, int weatherDataTypeNum,
			double dataValue, Date weatherTimestamp, int userSerialNum) {
		super();
		this.weatherDataType = weatherDataType;
		this.weatherDataTypeNum = weatherDataTypeNum;
		this.dataValue = dataValue;
		this.weatherTimestamp = weatherTimestamp;
		this.userSerialNum = userSerialNum;
	}

	public WeatherValue(ObjectId weatherSerialNum, String weatherDataType,
			double dataValue, Date weatherTimestamp, int userSerialNum) {
		super();
		this.weatherSerialNum = weatherSerialNum;
		this.weatherDataType = weatherDataType;
		this.dataValue = dataValue;
		this.weatherTimestamp = weatherTimestamp;
		this.userSerialNum = userSerialNum;
	}

	public ObjectId getWeatherSerialNum() {
		return weatherSerialNum;
	}

	public void setWeatherSerialNum(ObjectId weatherSerialNum) {
		this.weatherSerialNum = weatherSerialNum;
	}

	public String getWeatherDataType() {
		return weatherDataType;
	}

	public void setWeatherDataType(String weatherDataType) {
		this.weatherDataType = weatherDataType;
	}

	public int getWeatherDataTypeNum() {
		return weatherDataTypeNum;
	}

	public void setWeatherDataTypeNum(int weatherDataTypeNum) {
		this.weatherDataTypeNum = weatherDataTypeNum;
	}

	public double getDataValue() {
		return dataValue;
	}

	public void setDataValue(double dataValue) {
		this.dataValue = dataValue;
	}

	public Date getWeatherTimestamp() {
		return weatherTimestamp;
	}

	public void setWeatherTimestamp(Date weatherTimestamp) {
		this.weatherTimestamp = weatherTimestamp;
	}

	public int getUserSerialNum() {
		return userSerialNum;
	}

	public void setUserSerialNum(int userSerialNum) {
		this.userSerialNum = userSerialNum;
	}

	@Override
	public String toString() {
		return "WeatherValue [weatherSerialNum=" + weatherSerialNum
				+ ", weatherDataType=" + weatherDataType + ", dataValue="
				+ dataValue + ", weatherTimestamp=" + weatherTimestamp
				+ ", userSerialNum=" + userSerialNum + "]";
	}
}
