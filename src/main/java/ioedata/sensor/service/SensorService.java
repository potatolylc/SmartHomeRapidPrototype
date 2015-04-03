package ioedata.sensor.service;

import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.SensorDuplicateException;
import ioedata.exception.factory.SensorNotExistException;
import ioedata.exception.factory.UserNotExistException;
import ioedata.sensor.model.SensorValue;

import org.bson.types.ObjectId;

/**
 * This interface provides methods about sensor services, such as sensor
 * registration and getting sensor information of specific devices.
 * 
 * @author ajou
 * 
 */
public interface SensorService {
	/*
	 * Register new sensor to a specific device. If registration succeeded,
	 * return true value;else, return false.
	 */
	public ObjectId registerSensor(String userName, String userWifiSsid,
			String deviceName, String sensorType, String sensorName) throws UserNotExistException,
			DeviceNotExistException, SensorDuplicateException;
	
	/*
	 * Get sensor serial number by sensor name and device serial number.
	 */
	public ObjectId retrieveSensorSerialNumBySensorNameAndDeviceSerialNum(String sensorName, ObjectId deviceSerialNum);
	
	/*
	 * Get sensor basic information using sensor serial number.
	 */
	public SensorValue retrieveSensorInfo(String sensorSerialNum) throws SensorNotExistException;
	
	/*
	 * Get sensor basic information using device serial number and sensor name.
	 */
	public SensorValue retrieveSensorInfo(ObjectId deviceSerialNum, String sensorName) throws SensorNotExistException, DeviceNotExistException;
	
	/*
	 * Get sensor basic information using user serial number, device name and sensor name.
	 */
	public SensorValue retrieveSensorInfo(int userSerialNum, String deviceName, String sensorName) throws UserNotExistException, DeviceNotExistException, SensorNotExistException;

	/*
	 * Get sensor basic information using user name, user WiFi SSID, device name and sensor name.
	 */
	public SensorValue retrieveSensorInfo(String userName, String userWifiSsid, String deviceName, String sensorName) throws UserNotExistException, DeviceNotExistException, SensorNotExistException;
	
	/*
	 * Check whether the sensor serial number already exists, which should be unique in the database.
	 * If sensor serial number already exists, return true; else, return false.
	 */
	public boolean isSensorExist(ObjectId sensorSerialNum);
	
	/*
	 * Check whether the sensor serial number already exists, which should be unique in the database.
	 * If sensor serial number already exists, return true; else, return false.
	 */
	public boolean isSensorExist(ObjectId deviceSerialNum, String sensorName);
}