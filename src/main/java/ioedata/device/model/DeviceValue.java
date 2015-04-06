package ioedata.device.model;

import ioedata.actuator.model.ActuatorValue;
import ioedata.geolocation.model.GeoCoordinate;
import ioedata.mongodb.repository.DBUtils;
import ioedata.sensor.model.SensorValue;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class creates data transport objects that contain the devices'
 * information, such as the IP address, device ID and password of various
 * devices.
 * 
 * @author ajou
 */
@Document(collection = DBUtils.DEVICE_COLLECTION_NAME)
public class DeviceValue {
	@Id
	private ObjectId deviceSerialNum;
	private String deviceName;
	private int userSerialNum;
	private Date deviceTimestamp;
	@Indexed
	private GeoCoordinate geoCoordinate;
	private List<SensorValue> sensors;
	private List<ActuatorValue> actuators;

	public DeviceValue() {
		super();
	}

	public DeviceValue(String deviceName, int userSerialNum) {
		super();
		this.deviceName = deviceName;
		this.userSerialNum = userSerialNum;
	}

	public DeviceValue(ObjectId deviceSerialNum, List<SensorValue> sensors) {
		super();
		this.deviceSerialNum = deviceSerialNum;
		this.sensors = sensors;
	}

	public DeviceValue(ObjectId deviceSerialNum, String deviceName,
			int userSerialNum, Date deviceTimestamp, List<SensorValue> sensors,
			List<ActuatorValue> actuators) {
		super();
		this.deviceSerialNum = deviceSerialNum;
		this.deviceName = deviceName;
		this.userSerialNum = userSerialNum;
		this.deviceTimestamp = deviceTimestamp;
		this.sensors = sensors;
		this.actuators = actuators;
	}

	public DeviceValue(ObjectId deviceSerialNum, String deviceName,
			int userSerialNum, Date deviceTimestamp,
			GeoCoordinate geoCoordinate, List<SensorValue> sensors,
			List<ActuatorValue> actuators) {
		super();
		this.deviceSerialNum = deviceSerialNum;
		this.deviceName = deviceName;
		this.userSerialNum = userSerialNum;
		this.deviceTimestamp = deviceTimestamp;
		this.geoCoordinate = geoCoordinate;
		this.sensors = sensors;
		this.actuators = actuators;
	}

	public ObjectId getDeviceSerialNum() {
		return deviceSerialNum;
	}

	public void setDeviceSerialNum(ObjectId deviceSerialNum) {
		this.deviceSerialNum = deviceSerialNum;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public int getUserSerialNum() {
		return userSerialNum;
	}

	public void setUserSerialNum(int userSerialNum) {
		this.userSerialNum = userSerialNum;
	}

	public Date getDeviceTimestamp() {
		return deviceTimestamp;
	}

	public void setDeviceTimestamp(Date deviceTimestamp) {
		this.deviceTimestamp = deviceTimestamp;
	}

	public GeoCoordinate getGeoCoordinate() {
		return geoCoordinate;
	}

	public void setGeoCoordinate(GeoCoordinate geoCoordinate) {
		this.geoCoordinate = geoCoordinate;
	}

	public List<SensorValue> getSensors() {
		return sensors;
	}

	public void setSensors(List<SensorValue> sensors) {
		this.sensors = sensors;
	}

	public List<ActuatorValue> getActuators() {
		return actuators;
	}

	public void setActuators(List<ActuatorValue> actuators) {
		this.actuators = actuators;
	}

	@Override
	public String toString() {
		return "DeviceValue [deviceSerialNum=" + deviceSerialNum
				+ ", deviceName=" + deviceName + ", userSerialNum="
				+ userSerialNum + ", deviceTimestamp=" + deviceTimestamp
				+ ", geoCoordinate=" + geoCoordinate + ", sensors=" + sensors
				+ ", actuators=" + actuators + "]";
	}

}
