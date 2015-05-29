package ioedata.sensor.service;

import java.util.List;

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
	/**
	 * Register new sensor to a specific device. 
	 * @param userName
	 * @param userWifiSsid
	 * @param deviceName
	 * @param sensorType
	 * @param sensorName
	 * @return
	 * @throws UserNotExistException
	 * @throws DeviceNotExistException
	 * @throws SensorDuplicateException
	 */
	public ObjectId registerSensor(String userName, String userWifiSsid,
			String deviceName, String sensorType, String sensorName)
			throws UserNotExistException, DeviceNotExistException,
			SensorDuplicateException;

	/**
	 * Get sensor serial number by sensor name and device serial number.
	 * @param sensorName
	 * @param deviceSerialNum
	 * @return
	 */
	public ObjectId retrieveSensorSerialNumBySensorNameAndDeviceSerialNum(
			String sensorName, ObjectId deviceSerialNum);

	/**
	 * Get sensor basic information using sensor serial number.
	 * @param sensorSerialNum
	 * @return
	 * @throws SensorNotExistException
	 */
	public SensorValue retrieveSensorInfo(String sensorSerialNum)
			throws SensorNotExistException;

	/**
	 * Get sensor basic information using device serial number and sensor name.
	 * @param deviceSerialNum
	 * @param sensorName
	 * @return
	 * @throws SensorNotExistException
	 * @throws DeviceNotExistException
	 */
	public SensorValue retrieveSensorInfo(ObjectId deviceSerialNum,
			String sensorName) throws SensorNotExistException,
			DeviceNotExistException;

	/**
	 * Get sensor basic information using user serial number, device name and
	 * sensor name.
	 * @param userSerialNum
	 * @param deviceName
	 * @param sensorName
	 * @return
	 * @throws UserNotExistException
	 * @throws DeviceNotExistException
	 * @throws SensorNotExistException
	 */
	public SensorValue retrieveSensorInfo(int userSerialNum, String deviceName,
			String sensorName) throws UserNotExistException,
			DeviceNotExistException, SensorNotExistException;

	/**
	 * Get sensor basic information using user name, user WiFi SSID, device name
	 * and sensor name.
	 * @param userName
	 * @param userWifiSsid
	 * @param deviceName
	 * @param sensorName
	 * @return
	 * @throws UserNotExistException
	 * @throws DeviceNotExistException
	 * @throws SensorNotExistException
	 */
	public SensorValue retrieveSensorInfo(String userName, String userWifiSsid,
			String deviceName, String sensorName) throws UserNotExistException,
			DeviceNotExistException, SensorNotExistException;
	
	/**
	 * Get list of all sensors attached to a device.
	 * @param deviceSerialNum
	 * @return
	 */
	public List<SensorValue> retrieveSensorList(ObjectId deviceSerialNum);

	/**
	 * Check whether the sensor serial number already exists, which should be
	 * unique in the database. If sensor serial number already exists, return
	 * true; else, return false.
	 * @param sensorSerialNum
	 * @return
	 */
	public boolean isSensorExist(ObjectId sensorSerialNum);

	/**
	 * Check whether the sensor exists by using device serial number and sensor name. 
	 * If sensor serial number already exists, return
	 * true; else, return false.
	 * @param deviceSerialNum
	 * @param sensorName
	 * @return
	 */
	public boolean isSensorExist(ObjectId deviceSerialNum, String sensorName);
}