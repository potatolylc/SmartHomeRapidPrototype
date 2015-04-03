package ioedata.sensor.model;

import ioedata.mongodb.repository.DBUtils;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class creates data transport objects that contain the sensors' information. 
 * @author ajou
 */
@Document(collection = DBUtils.SENSOR_COLLECTION_NAME)
public class SensorValue {
	@Id
	private ObjectId sensorSerialNum;
	private String sensorName;
	private ObjectId deviceSerialNum;
	private int sensorTypeNum;
	private Date sensorTimestamp;
	
	public SensorValue() {
		super();
	}

	public SensorValue(ObjectId sensorSerialNum, String sensorName,
			ObjectId deviceSerialNum, int sensorTypeNum, Date sensorTimestamp) {
		super();
		this.sensorSerialNum = sensorSerialNum;
		this.sensorName = sensorName;
		this.deviceSerialNum = deviceSerialNum;
		this.sensorTypeNum = sensorTypeNum;
		this.sensorTimestamp = sensorTimestamp;
	}

	public ObjectId getSensorSerialNum() {
		return sensorSerialNum;
	}

	public void setSensorSerialNum(ObjectId sensorSerialNum) {
		this.sensorSerialNum = sensorSerialNum;
	}

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public ObjectId getDeviceSerialNum() {
		return deviceSerialNum;
	}

	public void setDeviceSerialNum(ObjectId deviceSerialNum) {
		this.deviceSerialNum = deviceSerialNum;
	}

	public int getSensorTypeNum() {
		return sensorTypeNum;
	}

	public void setSensorTypeNum(int sensorTypeNum) {
		this.sensorTypeNum = sensorTypeNum;
	}

	public Date getSensorTimestamp() {
		return sensorTimestamp;
	}

	public void setSensorTimestamp(Date sensorTimestamp) {
		this.sensorTimestamp = sensorTimestamp;
	}

	@Override
	public String toString() {
		return "SensorValue [sensorSerialNum=" + sensorSerialNum
				+ ", sensorName=" + sensorName + ", deviceSerialNum="
				+ deviceSerialNum + ", sensorTypeNum=" + sensorTypeNum
				+ ", sensorTimestamp=" + sensorTimestamp + "]";
	}	
}
