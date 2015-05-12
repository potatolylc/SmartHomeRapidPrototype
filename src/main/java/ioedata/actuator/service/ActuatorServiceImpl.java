package ioedata.actuator.service;

import ioedata.actuator.factory.ActuatorManager;
import ioedata.actuator.model.ActuatorValue;
import ioedata.actuator.repository.ActuatorRepository;
import ioedata.device.model.DeviceValue;
import ioedata.device.service.DeviceService;
import ioedata.exception.factory.ActuatorDuplicateException;
import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.UserNotExistException;
import ioedata.user.model.UserValue;
import ioedata.user.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class ActuatorServiceImpl implements ActuatorService {
	@Resource(name = "userServiceImpl")
	private UserService userService;
	@Resource(name = "deviceServiceImpl")
	private DeviceService deviceService;
	@Resource(name = "actuatorRepositoryImpl")
	private ActuatorRepository<ActuatorValue, ObjectId> actuatorRepository;

	@Override
	public ObjectId registerActuator(String userName, String userWifiSsid,
			String deviceName, String actuatorType, String actuatorName)
			throws UserNotExistException, DeviceNotExistException,
			ActuatorDuplicateException {
		// Check whether user exists
		boolean isUserExistFlag = this.userService.isUserExist(userName,
				userWifiSsid);
		if (!isUserExistFlag)
			throw new UserNotExistException();
		int userSerialNum = this.userService
				.retrieveUserSerialNumByUserNameAndSsid(new UserValue(userName,
						userWifiSsid));
		boolean isDeviceExistFlag = this.deviceService.isDeviceExist(
				userSerialNum, deviceName);
		if (!isDeviceExistFlag)
			throw new DeviceNotExistException();
		// If user and device exist, check whether actuator name is in use
		ObjectId deviceSerialNum = this.deviceService
				.retrieveDeviceSerialNumByDeviceNameAndUserSerialNum(
						deviceName, userSerialNum);
		boolean isActuatorExistFlag = this.isActuatorExist(deviceSerialNum,
				actuatorName);
		if (isActuatorExistFlag)
			throw new ActuatorDuplicateException();
		// If actuator name is not in use, insert actuator
		ActuatorValue actuatorVal = new ActuatorValue();
		actuatorVal.setActuatorName(actuatorName);
		actuatorVal.setDeviceSerialNum(deviceSerialNum);
		actuatorVal.setActuatorTypeNum(ActuatorManager.getActuatorManager()
				.getActuatorTypeNum(actuatorType));
		actuatorVal.setActuatorTimestamp(new Date());
		this.actuatorRepository.insertObject(actuatorVal);
		ObjectId actuatorSerialNum = this
				.retrieveActuatorSerialNumByActuatorNameAndDeviceSerialNum(
						actuatorName, deviceSerialNum);
		ArrayList<ActuatorValue> actuators = new ArrayList<ActuatorValue>();
		actuators.add(actuatorVal);
		this.deviceService.updateDeviceActuatorsInfo(new DeviceValue(
				deviceSerialNum, actuators));
		return actuatorSerialNum;
	}

	@Override
	public ObjectId retrieveActuatorSerialNumByActuatorNameAndDeviceSerialNum(
			String actuatorName, ObjectId deviceSerialNum) {
		return this.actuatorRepository.findByActuatorNameAndDeviceSerialNum(
				actuatorName, deviceSerialNum).getActuatorSerialNum();
	}

	@Override
	public boolean isActuatorExist(ObjectId actuatorSerialNum) {
		return this.actuatorRepository.isObjectExist(actuatorSerialNum);
	}

	@Override
	public boolean isActuatorExist(ObjectId deviceSerialNum, String actuatorName) {
		return this.actuatorRepository.isObjectExist(deviceSerialNum,
				actuatorName);
	}

}
