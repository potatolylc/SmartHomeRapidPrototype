package ioedata.device.service;

import java.util.List;

import org.bson.types.ObjectId;

import ioedata.actuator.model.ActuatorValue;
import ioedata.device.model.DeviceValue;
import ioedata.exception.factory.DeviceDuplicateException;
import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.UserNotExistException;
import ioedata.geolocation.model.GeoCoordinate;
import ioedata.sensor.model.SensorValue;

/**
 * This interface provides many methods to deal with end devices,
 * such as device registration, check whether the device exists, etc.
 * @author ajou
 *
 */
public interface DeviceService {
	/*
	 * Store new device information into database.
	 * Before storing new device into database, it first checks whether the device serial number has already been stored in the database.
	 * If the device does not exist and has been inserted into database successfully, return true value; else, return false.
	 */
	public ObjectId registerDevice(String userName, String userWifiSsid, String deviceName) throws UserNotExistException, DeviceDuplicateException;

	/*
	 * Store new device information into database.
	 * Before storing new device into database, it first checks whether the device serial number has already been stored in the database.
	 * If the device does not exist and has been inserted into database successfully, return true value; else, return false.
	 */
	public ObjectId registerDevice(String userName, String userWifiSsid, String deviceName, GeoCoordinate coordinate) throws UserNotExistException, DeviceDuplicateException;
	
	/*
	 * Get device serial number by device name and user serial number
	 */
	public ObjectId retrieveDeviceSerialNumByDeviceNameAndUserSerialNum(String deviceName, int userSerialNum);
	
	/*
	 * Get device basic information using device serial number.
	 */
	public DeviceValue retrieveDeviceInfo(String deviceSerialNum) throws DeviceNotExistException;
	
	/*
	 * Get device basic information using user serial number and device name.
	 */
	public DeviceValue retrieveDeviceInfo(int userSerialNum, String deviceName) throws DeviceNotExistException, UserNotExistException;
	
	/*
	 * Get device basic information using user name, user WiFi SSID and device name.
	 */
	public DeviceValue retrieveDeviceInfo(String userName, String userWifiSsid, String deviceName) throws DeviceNotExistException, UserNotExistException;
	
	/*
	 * Check whether the device serial number already exists, which should be unique in the database.
	 * If device serial number already exists, return true; else, return false.
	 */
	public boolean isDeviceExist(String deviceSerialNum);
	
	/*
	 * Check whether the device serial number already exists, which should be unique in the database.
	 * If device serial number already exists, return true; else, return false.
	 */
	public boolean isDeviceExist(int userSerialNum, String deviceName);
	
	/*
	 * Upsert device sensor information.
	 */
	public void updateDeviceSensorsInfo(DeviceValue deviceValue);
	
	/*
	 * Upsert device actuator information.
	 */
	public void updateDeviceActuatorsInfo(DeviceValue deviceValue);
	
	/*
	 * Get device list within a circle geospatial area using a geolocation coordinate
	 */
	public List<DeviceValue> retrieveDeviceListWithinCircle(GeoCoordinate geoCoordinate, int distance);
	
	/*
	 * Get sensor list of a device.
	 */
	public List<SensorValue> retrieveDeviceSensorList(ObjectId deviceSerialNum) throws DeviceNotExistException;
	
	/*
	 * Get actuator list of a device.
	 */
	public List<ActuatorValue> retrieveDeviceActuatorList(ObjectId deviceSerialNum) throws DeviceNotExistException;
	
}