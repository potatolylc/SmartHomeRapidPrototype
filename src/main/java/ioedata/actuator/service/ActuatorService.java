package ioedata.actuator.service;

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
			String deviceName, String actuatorType, String actuatorName);
}
