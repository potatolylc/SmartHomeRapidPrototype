package ioedata.device.service;

import org.bson.types.ObjectId;

import ioedata.device.model.DeviceValue;
import ioedata.exception.factory.DeviceDuplicateException;
import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.UserNotExistException;

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
	
}