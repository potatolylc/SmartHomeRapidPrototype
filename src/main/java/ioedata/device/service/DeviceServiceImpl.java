package ioedata.device.service;

import ioedata.actuator.model.ActuatorValue;
import ioedata.device.model.DeviceValue;
import ioedata.device.repository.DeviceRepository;
import ioedata.exception.factory.DeviceDuplicateException;
import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.UserNotExistException;
import ioedata.geolocation.model.GeoCoordinate;
import ioedata.sensor.model.SensorValue;
import ioedata.user.model.UserValue;
import ioedata.user.service.UserService;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class implements DeviceService interface for dealing with services about
 * devices.
 * 
 * @author ajou
 * 
 */
@Service
public class DeviceServiceImpl implements DeviceService {
	@Resource(name = "userServiceImpl")
	private UserService userService;
	@Resource(name = "deviceRepositoryImpl")
	private DeviceRepository<DeviceValue, ObjectId> deviceRepository;

	@Override
	@Transactional
	public ObjectId registerDevice(String userName, String userWifiSsid,
			String deviceName) throws DeviceDuplicateException,
			UserNotExistException {
		// Check whether user exists
		boolean isUserExistFlag = this.userService.isUserExist(userName,
				userWifiSsid);
		if (!isUserExistFlag)
			throw new UserNotExistException();

		// If user exists, check whether device already exists
		int userSerialNum = this.userService
				.retrieveUserSerialNumByUserNameAndSsid(new UserValue(userName,
						userWifiSsid));
		DeviceValue isDeviceExistObj = this.deviceRepository
				.findByDeviceNameAndUserSerialNum(deviceName, userSerialNum);
		if (isDeviceExistObj != null)
			throw new DeviceDuplicateException();

		DeviceValue deviceVal = new DeviceValue(deviceName, userSerialNum);
		deviceVal.setDeviceTimestamp(new Date()); // Set device time stamp
		// Insert device info into database
		this.deviceRepository.insertObject(deviceVal);

		// Get device unique ID back
		return this.retrieveDeviceSerialNumByDeviceNameAndUserSerialNum(
				deviceVal.getDeviceName(), deviceVal.getUserSerialNum());
	}

	@Override
	public ObjectId registerDevice(String userName, String userWifiSsid,
			String deviceName, GeoCoordinate coordinate)
			throws UserNotExistException, DeviceDuplicateException {
		// Check whether user exists
		boolean isUserExistFlag = this.userService.isUserExist(userName,
				userWifiSsid);
		if (!isUserExistFlag)
			throw new UserNotExistException();

		// If user exists, check whether device already exists
		int userSerialNum = this.userService
				.retrieveUserSerialNumByUserNameAndSsid(new UserValue(userName,
						userWifiSsid));
		DeviceValue isDeviceExistObj = this.deviceRepository
				.findByDeviceNameAndUserSerialNum(deviceName, userSerialNum);
		if (isDeviceExistObj != null)
			throw new DeviceDuplicateException();
		
		DeviceValue deviceVal = new DeviceValue(deviceName, userSerialNum);
		deviceVal.setDeviceTimestamp(new Date()); // Set device time stamp
		deviceVal.setGeoCoordinate(coordinate);
		// Insert device info into database
		this.deviceRepository.insertObject(deviceVal);

		// Get device unique ID back
		return this.retrieveDeviceSerialNumByDeviceNameAndUserSerialNum(
				deviceVal.getDeviceName(), deviceVal.getUserSerialNum());
	}

	@Override
	public ObjectId retrieveDeviceSerialNumByDeviceNameAndUserSerialNum(
			String deviceName, int userSerialNum) {
		return this.deviceRepository.findByDeviceNameAndUserSerialNum(
				deviceName, userSerialNum).getDeviceSerialNum();
	}

	@Override
	public DeviceValue retrieveDeviceInfo(String deviceSerialNum)
			throws DeviceNotExistException {
		DeviceValue deviceVal = null;
		deviceVal = this.deviceRepository.findOneObject(new ObjectId(
				deviceSerialNum));
		if (deviceVal == null)
			throw new DeviceNotExistException();
		return deviceVal;
	}

	@Override
	public DeviceValue retrieveDeviceInfo(int userSerialNum, String deviceName)
			throws DeviceNotExistException, UserNotExistException {
		boolean isUserExistFlag = this.userService.isUserExist(userSerialNum);
		if (!isUserExistFlag)
			throw new UserNotExistException();
		DeviceValue deviceVal = null;
		deviceVal = this.deviceRepository.findByDeviceNameAndUserSerialNum(
				deviceName, userSerialNum);
		if (deviceVal == null)
			throw new DeviceNotExistException();
		return deviceVal;
	}

	@Override
	public DeviceValue retrieveDeviceInfo(String userName, String userWifiSsid,
			String deviceName) throws DeviceNotExistException,
			UserNotExistException {
		// Check whether user exists
		boolean isUserExistFlag = this.userService.isUserExist(userName,
				userWifiSsid);
		if (!isUserExistFlag)
			throw new UserNotExistException();

		// If user exists, get device information
		int userSerialNum = this.userService
				.retrieveUserSerialNumByUserNameAndSsid(new UserValue(userName,
						userWifiSsid));
		DeviceValue deviceVal = null;
		deviceVal = this.deviceRepository.findByDeviceNameAndUserSerialNum(
				deviceName, userSerialNum);
		if (deviceVal == null)
			throw new DeviceNotExistException();
		return deviceVal;
	}

	@Override
	public boolean isDeviceExist(String deviceSerialNum) {
		return this.deviceRepository
				.isObjectExist(new ObjectId(deviceSerialNum));
	}

	@Override
	public boolean isDeviceExist(int userSerialNum, String deviceName) {
		return this.deviceRepository.isObjectExist(userSerialNum, deviceName);
	}

	@Override
	public void updateDeviceSensorsInfo(DeviceValue deviceValue) {
		this.deviceRepository.updateDeviceSensors(deviceValue);
	}
	
	@Override
	public void updateDeviceActuatorsInfo(DeviceValue deviceValue) {
		this.deviceRepository.updateDeviceActuators(deviceValue);
	}

	@Override
	public List<DeviceValue> retrieveDeviceListWithinCircle(
			GeoCoordinate geoCoordinate, int distance) {
		return this.deviceRepository.findByGeoCoordinate(geoCoordinate, distance);
	}

	@Override
	public List<SensorValue> retrieveDeviceSensorList(ObjectId deviceSerialNum) throws DeviceNotExistException {
		return this.retrieveDeviceInfo(deviceSerialNum.toString()).getSensors();
	}

	@Override
	public List<ActuatorValue> retrieveDeviceActuatorList(ObjectId deviceSerialNum) throws DeviceNotExistException {
		return this.retrieveDeviceInfo(deviceSerialNum.toString()).getActuators();
	}

}