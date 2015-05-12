package ioedata.actuator.controller;

import ioedata.actuator.service.ActuatorService;
import ioedata.exception.factory.ActuatorDuplicateException;
import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.UserNotExistException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ActuatorController {
	@Resource(name = "actuatorServiceImpl")
	private ActuatorService actuatorService;

	@RequestMapping
	@ResponseBody
	public void actuatorRegistration(@RequestParam("userName") String userName,
			@RequestParam("userWifiSsid") String userWifiSsid,
			@RequestParam("deviceName") String deviceName,
			@RequestParam("actuatorType") String actuatorType,
			@RequestParam("actuatorName") String actuatorName) {
		System.out.println("actuatorRegistration: " + userName + " " + " "
				+ userWifiSsid + " " + deviceName + " " + actuatorType + " "
				+ actuatorName);
		String msg = null;
		try {
			this.actuatorService.registerActuator(userName, userWifiSsid,
					deviceName, actuatorType, actuatorName);
			msg = "Actuator has been successfully registered.";
		} catch (UserNotExistException e) {
			msg = "User does not exist.";
			e.printStackTrace();
		} catch (DeviceNotExistException e) {
			msg = "Device does not exist";
			e.printStackTrace();
		} catch (ActuatorDuplicateException e) {
			msg = "Actuator already exists.";
			e.printStackTrace();
		} catch (Exception e) {
			msg = "Something goes wrong with actuator registration. ";
			e.printStackTrace();
		}
		System.out.println("actuatorRegistration: " + msg);
	}
}
