package ioedata.actuator.service;

import ioedata.actuator.model.ActuatorValue;
import ioedata.exception.factory.ActuatorDuplicateException;
import ioedata.exception.factory.ActuatorNotExistException;
import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.UserNotExistException;

import org.bson.types.ObjectId;

/**
 * This interface provides methods about actuator services, such as actuator
 * registration and getting actuator information of specific devices.
 * 
 * @author ajou
 * 
 */
public interface ActuatorService {
	/*
	 * Register new actuator to a specific device.
	 */
	public ObjectId registerActuator(String userName, String userWifiSsid,
			String deviceName, String actuatorType, String actuatorName)
			throws UserNotExistException, DeviceNotExistException,
			ActuatorDuplicateException;

	/*
	 * Get actuator serial number by actuator name and device serial number.
	 */
	public ObjectId retrieveActuatorSerialNumByActuatorNameAndDeviceSerialNum(
			String actuatorName, ObjectId deviceSerialNum);
	
	/*
	 * Retrieve actuator information by actuator serial number. 
	 */
	public ActuatorValue retrieveActuatorInfo(String actuatorSerialNum)
			throws ActuatorNotExistException;
	
	/*
	 * Retrieve actuator information by device serial number and actuator name. 
	 */
	public ActuatorValue retrieveActuatorInfo(String deviceSerialNum, String actuatorName)
			throws ActuatorNotExistException,
			DeviceNotExistException;

	/*
	 * Check whether the actuator already exists by using actuator serial
	 * number. If actuator exists, return true; else, return false.
	 */
	public boolean isActuatorExist(ObjectId actuatorSerialNum);

	/*
	 * Check whether the actuator already exists by using device serial number
	 * and actuator name. If actuator exists, return true; else, return false.
	 */
	public boolean isActuatorExist(ObjectId deviceSerialNum, String actuatorName);
}
