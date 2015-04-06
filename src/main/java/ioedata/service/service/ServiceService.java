package ioedata.service.service;

import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.UserNotExistException;

public interface ServiceService {
	/*
	 * Provide recommend services
	 */
	public Object recommendService(String userName, String userWifiSsid, String deviceName, String serviceType) throws DeviceNotExistException, UserNotExistException;
}
