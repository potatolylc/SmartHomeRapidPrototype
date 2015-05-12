package ioedata.actuator.controller;

import ioedata.actuator.model.ActuatorValue;
import ioedata.actuator.service.ActuatorService;
import ioedata.exception.factory.ActuatorDuplicateException;
import ioedata.exception.factory.ActuatorNotExistException;
import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.UserNotExistException;
import ioedata.utils.StringUtils;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/actuator")
public class ActuatorController {
	@Resource(name = "actuatorServiceImpl")
	private ActuatorService actuatorService;

	@RequestMapping(value = "", method = RequestMethod.POST)
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

	@RequestMapping(value = "/{actuatorSerialNum}", method = RequestMethod.GET)
	@ResponseBody
	public String actuatorInformation(
			@PathVariable("actuatorSerialNum") String actuatorSerialNum)
			throws JSONException {
		System.out.println("actuatorInformation: " + actuatorSerialNum);
		ActuatorValue actuatorValRet = null;
		boolean flag = false;
		try {
			actuatorValRet = this.actuatorService
					.retrieveActuatorInfo(actuatorSerialNum);
			flag = true;
		} catch (ActuatorNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put(StringUtils.RESULT, flag).toString();
		}
		return new JSONObject()
				.put(StringUtils.RESULT, flag)
				.put(StringUtils.ACTUATOR_SERIAL_NUM,
						actuatorValRet.getActuatorSerialNum())
				.put(StringUtils.DEVICE_SERIAL_NUM,
						actuatorValRet.getDeviceSerialNum())
				.put(StringUtils.ACTUATOR_NAME,
						actuatorValRet.getActuatorName())
				.put(StringUtils.ACTUATOR_TYPE_NUM,
						actuatorValRet.getActuatorTypeNum())
				.put(StringUtils.ACTUATOR_TIMESTAMP,
						actuatorValRet.getActuatorTimestamp()).toString();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String actuatorInformation(
			@RequestParam("deviceSerialNum") String deviceSerialNum,
			@RequestParam("actuatorName") String actuatorName) throws JSONException {
		System.out.println("actuatorInformation: " + deviceSerialNum + " "
				+ actuatorName);
		ActuatorValue actuatorValRet = null;
		boolean flag = false;
		try {
			actuatorValRet = this.actuatorService.retrieveActuatorInfo(
					deviceSerialNum, actuatorName);
			flag = true;
		} catch (ActuatorNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put(StringUtils.RESULT, flag).toString();
		} catch (DeviceNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put(StringUtils.RESULT, flag).toString();
		}
		return new JSONObject().put(StringUtils.RESULT, flag)
				.put(StringUtils.ACTUATOR_SERIAL_NUM,
						actuatorValRet.getActuatorSerialNum())
				.put(StringUtils.DEVICE_SERIAL_NUM,
						actuatorValRet.getDeviceSerialNum())
				.put(StringUtils.ACTUATOR_NAME,
						actuatorValRet.getActuatorName())
				.put(StringUtils.ACTUATOR_TYPE_NUM,
						actuatorValRet.getActuatorTypeNum())
				.put(StringUtils.ACTUATOR_TIMESTAMP,
						actuatorValRet.getActuatorTimestamp()).toString();
	}

}
