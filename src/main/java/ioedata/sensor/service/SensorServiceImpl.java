package ioedata.sensor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ioedata.device.model.DeviceValue;
import ioedata.device.service.DeviceService;
import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.SensorDuplicateException;
import ioedata.exception.factory.SensorNotExistException;
import ioedata.exception.factory.UserNotExistException;
import ioedata.sensor.factory.SensorManager;
import ioedata.sensor.model.SensorValue;
import ioedata.sensor.repository.SensorRepository;
import ioedata.user.model.UserValue;
import ioedata.user.service.UserService;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

/**
 * This class provides services about sensors. It implements SensorService
 * interface.
 
 * @author ajou
 * 
 */
@Service
public class SensorServiceImpl implements SensorService {
	@Resource(name = "userServiceImpl")
	private UserService userService;
	@Resource(name = "deviceServiceImpl")
	private DeviceService deviceService;
	@Resource(name = "sensorRepositoryImpl")
	private SensorRepository<SensorValue, ObjectId> sensorRepository;

	@Override
	public ObjectId registerSensor(String userName, String userWifiSsid,
			String deviceName, String sensorType, String sensorName)
			throws UserNotExistException, DeviceNotExistException,
			SensorDuplicateException {
		// Check whether user exists
		boolean isUserExistFlag = this.userService.isUserExist(userName,
				userWifiSsid);
		if (!isUserExistFlag)
			throw new UserNotExistException();
		// If user exists, check whether device exists
		int userSerialNum = userService
				.retrieveUserSerialNumByUserNameAndSsid(new UserValue(userName,
						userWifiSsid));
		boolean isDeviceExistFlag = this.deviceService.isDeviceExist(
				userSerialNum, deviceName);
		if (!isDeviceExistFlag)
			throw new DeviceNotExistException();
		// If user and device both exist, check whether sensor name is in use
		ObjectId deviceSerialNum = deviceService
				.retrieveDeviceSerialNumByDeviceNameAndUserSerialNum(
						deviceName, userSerialNum);
		boolean isSensorExistFlag = this.isSensorExist(deviceSerialNum,
				sensorName);
		if (isSensorExistFlag)
			throw new SensorDuplicateException();
		// If sensor name is not in use, insert sensor
		SensorValue sensorVal = new SensorValue();
		sensorVal.setSensorName(sensorName);
		sensorVal.setDeviceSerialNum(deviceSerialNum);
		sensorVal.setSensorTypeNum(SensorManager.getSensorManager()
				.getSensorTypeNum(sensorType));
		sensorVal.setSensorTimestamp(new Date());
		this.sensorRepository.insertObject(sensorVal);
		ObjectId sensorSerialNum = this
				.retrieveSensorSerialNumBySensorNameAndDeviceSerialNum(
						sensorName, deviceSerialNum);
		ArrayList<SensorValue> sensors = new ArrayList<SensorValue>();
		sensors.add(sensorVal);
		this.deviceService.updateDeviceInfo(new DeviceValue(deviceSerialNum,
				sensors));
		return sensorSerialNum;
	}

	@Override
	public ObjectId retrieveSensorSerialNumBySensorNameAndDeviceSerialNum(
			String sensorName, ObjectId deviceSerialNum) {
		return this.sensorRepository.findBySensorNameAndDeviceSerialNum(
				sensorName, deviceSerialNum).getSensorSerialNum();
	}

	@Override
	public SensorValue retrieveSensorInfo(String sensorSerialNum)
			throws SensorNotExistException {
		SensorValue sensorVal = null;
		sensorVal = this.sensorRepository.findOneObject(new ObjectId(
				sensorSerialNum));
		if (sensorVal == null)
			throw new SensorNotExistException();
		return sensorVal;
	}

	@Override
	public SensorValue retrieveSensorInfo(ObjectId deviceSerialNum,
			String sensorName) throws SensorNotExistException,
			DeviceNotExistException {
		// Check whether device exists
		boolean isDeviceExistFlag = this.deviceService
				.isDeviceExist(deviceSerialNum.toString());
		if (!isDeviceExistFlag)
			throw new DeviceNotExistException();

		// If device exists, get sensor information
		SensorValue sensorVal = null;
		sensorVal = this.sensorRepository.findBySensorNameAndDeviceSerialNum(
				sensorName, deviceSerialNum);
		if (sensorVal == null)
			throw new SensorNotExistException();
		return sensorVal;
	}

	@Override
	public SensorValue retrieveSensorInfo(int userSerialNum, String deviceName,
			String sensorName) throws UserNotExistException,
			DeviceNotExistException, SensorNotExistException {
		boolean isUserExistFlag = this.userService.isUserExist(userSerialNum);
		if (!isUserExistFlag)
			throw new UserNotExistException();

		boolean isDeviceExistFlag = this.deviceService.isDeviceExist(
				userSerialNum, deviceName);
		if (!isDeviceExistFlag)
			throw new DeviceNotExistException();

		ObjectId deviceSerialNum = this.deviceService
				.retrieveDeviceSerialNumByDeviceNameAndUserSerialNum(
						deviceName, userSerialNum);
		return this.retrieveSensorInfo(deviceSerialNum, sensorName);
	}

	@Override
	public SensorValue retrieveSensorInfo(String userName, String userWifiSsid,
			String deviceName, String sensorName) throws UserNotExistException,
			DeviceNotExistException, SensorNotExistException {
		boolean isUserExistFlag = this.userService.isUserExist(userName,
				userWifiSsid);
		if (!isUserExistFlag)
			throw new UserNotExistException();
		int userSerialNum = this.userService
				.retrieveUserSerialNumByUserNameAndSsid(new UserValue(userName,
						userWifiSsid));
		return this.retrieveSensorInfo(userSerialNum, deviceName, sensorName);
	}

	@Override
	public List<SensorValue> retrieveSensorList(ObjectId deviceSerialNum) {
		return this.sensorRepository.findByDeviceSerialNum(deviceSerialNum);
	}

	@Override
	public boolean isSensorExist(ObjectId sensorSerialNum) {
		return this.sensorRepository.isObjectExist(sensorSerialNum);
	}

	@Override
	public boolean isSensorExist(ObjectId deviceSerialNum, String sensorName) {
		return this.sensorRepository.isObjectExist(deviceSerialNum, sensorName);
	}

}