package ioedata.sensordata.model;

import ioedata.sensor.model.SensorValue;

import java.util.Date;
/**
 * This class creates data transport objects that contain the sensor data timestamp,
 * the raw data from sensors and the respective sensor information.
 * Also, it can contain an array data information to represent a list of the past raw sensor data.
 * @author ajou
 */
public final class DataValue {
	private int sensorDataSerialNum;
	private Object sensorDataValue;
	private Date sensorDataTimestamp;
	private String sensorDataTimestampStr;
	private SensorValue sensorValue;
	private TimeValue timeValue;
	
	public DataValue() {
		super();
	}
	public DataValue(SensorValue sensorValue, TimeValue timeValue) {
		super();
		this.sensorValue = sensorValue;
		this.timeValue = timeValue;
	}
	public DataValue(Object sensorDataValue, String sensorDataTimestampStr,
			SensorValue sensorValue) {
		super();
		this.sensorDataValue = sensorDataValue;
		this.sensorDataTimestampStr = sensorDataTimestampStr;
		this.sensorValue = sensorValue;
	}
	public DataValue(Object sensorDataValue, Date sensorDataTimestamp,
			String sensorDataTimestampStr, SensorValue sensorValue) {
		super();
		this.sensorDataValue = sensorDataValue;
		this.sensorDataTimestamp = sensorDataTimestamp;
		this.sensorDataTimestampStr = sensorDataTimestampStr;
		this.sensorValue = sensorValue;
	}
	public DataValue(int sensorDataSerialNum, Object sensorDataValue,
			SensorValue sensorValue) {
		super();
		this.sensorDataSerialNum = sensorDataSerialNum;
		this.sensorDataValue = sensorDataValue;
		this.sensorValue = sensorValue;
	}
	public DataValue(int sensorDataSerialNum, Object sensorDataValue,
			Date sensorDataTimestamp, String sensorDataTimestampStr,
			SensorValue sensorValue) {
		super();
		this.sensorDataSerialNum = sensorDataSerialNum;
		this.sensorDataValue = sensorDataValue;
		this.sensorDataTimestamp = sensorDataTimestamp;
		this.sensorDataTimestampStr = sensorDataTimestampStr;
		this.sensorValue = sensorValue;
	}
	public DataValue(int sensorDataSerialNum, Object sensorDataValue,
			Date sensorDataTimestamp, String sensorDataTimestampStr,
			SensorValue sensorValue, TimeValue timeValue) {
		super();
		this.sensorDataSerialNum = sensorDataSerialNum;
		this.sensorDataValue = sensorDataValue;
		this.sensorDataTimestamp = sensorDataTimestamp;
		this.sensorDataTimestampStr = sensorDataTimestampStr;
		this.sensorValue = sensorValue;
		this.timeValue = timeValue;
	}
	
	public int getSensorDataSerialNum() {
		return sensorDataSerialNum;
	}
	public void setSensorDataSerialNum(int sensorDataSerialNum) {
		this.sensorDataSerialNum = sensorDataSerialNum;
	}
	public Object getSensorDataValue() {
		return sensorDataValue;
	}
	public void setSensorDataValue(Object sensorDataValue) {
		this.sensorDataValue = sensorDataValue;
	}
	public Date getSensorDataTimestamp() {
		return sensorDataTimestamp;
	}
	public void setSensorDataTimestamp(Date sensorDataTimestamp) {
		this.sensorDataTimestamp = sensorDataTimestamp;
	}
	public String getSensorDataTimestampStr() {
		return sensorDataTimestampStr;
	}
	public void setSensorDataTimestampStr(String sensorDataTimestampStr) {
		this.sensorDataTimestampStr = sensorDataTimestampStr;
	}
	public SensorValue getSensorValue() {
		return sensorValue;
	}
	public void setSensorValue(SensorValue sensorValue) {
		this.sensorValue = sensorValue;
	}
	public TimeValue getTimeValue() {
		return timeValue;
	}
	public void setTimeValue(TimeValue timeValue) {
		this.timeValue = timeValue;
	}

	@Override
	public String toString() {
		return "DataValue [sensorDataSerialNum=" + sensorDataSerialNum
				+ ", sensorDataValue=" + sensorDataValue
				+ ", sensorDataTimestamp=" + sensorDataTimestamp
				+ ", sensorDataTimestampStr=" + sensorDataTimestampStr
				+ ", sensorValue=" + sensorValue + ", timeValue=" + timeValue
				+ "]";
	}
}