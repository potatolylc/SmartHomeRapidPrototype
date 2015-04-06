package ioedata.service.service;

import java.util.List;

import ioedata.device.model.DeviceValue;
import ioedata.device.service.DeviceService;
import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.UserNotExistException;
import ioedata.user.model.UserValue;
import ioedata.user.service.UserService;
import ioedata.utils.Utils;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl implements ServiceService {
	@Resource(name = "userServiceImpl")
	private UserService userService;
	@Resource(name = "deviceServiceImpl")
	private DeviceService deviceService;

	@Override
	public Object recommendService(String userName, String userWifiSsid,
			String deviceName, String serviceType)
			throws DeviceNotExistException, UserNotExistException {
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
		// If device exists, get device information
		DeviceValue deviceVal = this.deviceService.retrieveDeviceInfo(
				userSerialNum, deviceName);

		// Parse service type
		if (serviceType.equals("temperatureCel"))
			return this.recommendTemperature(deviceVal);
		else if (serviceType.equals("humidity"))
			return this.recommendHumidity(deviceVal);
		else if (serviceType.equals("lightBrightness"))
			return this.recommendLightBrightness(deviceVal);
		else if (serviceType.equals("TVChannel"))
			return this.recommendTVChannel(deviceVal);
		return null;
	}

	private double recommendTemperature(DeviceValue deviceVal) {
		List<DeviceValue> deviceList = deviceService
				.retrieveDeviceListWithinCircle(deviceVal.getGeoCoordinate(),
						Utils.RECOMMEND_SERVICE_GEO_DISTANCE);
		for(DeviceValue device : deviceList) {
			System.out.println(device);
		}
		return 0.0;
	}

	private int recommendHumidity(DeviceValue deviceVal) {
		return 0;
	}

	private int recommendLightBrightness(DeviceValue deviceVal) {
		return 0;
	}

	private int recommendTVChannel(DeviceValue deviceVal) {
		return 0;
	}

}
