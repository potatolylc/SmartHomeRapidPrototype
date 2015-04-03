package ioedata.sensor.controller;

import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.SensorDuplicateException;
import ioedata.exception.factory.SensorNotExistException;
import ioedata.exception.factory.UserNotExistException;
import ioedata.sensor.model.SensorValue;
import ioedata.sensor.service.SensorService;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This class is the controller for requests and responses about sensor
 * services, such as add or remove sensors attached to devices.
 * 
 * @author ajou
 * 
 */
@Controller
@RequestMapping(value = "/sensor")
public class SensorController {
	@Resource(name = "sensorServiceImpl")
	SensorService sensorService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public String sensorRegistration(@RequestParam("userName") String userName,
			@RequestParam("userWifiSsid") String userWifiSsid,
			@RequestParam("deviceName") String deviceName,
			@RequestParam("sensorType") String sensorType,
			@RequestParam("sensorName") String sensorName) throws JSONException {
		System.out.println("sensorRegistration: " + userName + " " + " "
				+ userWifiSsid + " " + deviceName + " " + sensorType + " "
				+ sensorName);
		ObjectId sensorSerialNum = null;
		boolean flag = false;
		String msg = null;
		try {
			sensorSerialNum = this.sensorService.registerSensor(userName,
					userWifiSsid, deviceName, sensorType, sensorName);
			flag = true;
			msg = "Sensor has been successfully registered.";
		} catch (UserNotExistException e) {
			msg = "User does not exist.";
			e.printStackTrace();
		} catch (DeviceNotExistException e) {
			msg = "Device does not exist.";
			e.printStackTrace();
		} catch (SensorDuplicateException e) {
			msg = "Sensor already exist.";
			e.printStackTrace();
		} catch (Exception e) {
			msg = "Something goes wrong with device registration. ";
			e.printStackTrace();
		}
		System.out.println("sensorRegistration: " + msg);
		return new JSONObject().put("result", flag).put("message", msg)
				.put("sensorSerialNum", sensorSerialNum).toString();
	}

	@RequestMapping(value = "/retrieve/{sensorSerialNum}", method = RequestMethod.GET)
	@ResponseBody
	public String sensorInformation(
			@PathVariable("sensorSerialNum") String sensorSerialNum)
			throws JSONException {
		System.out.println("sensorInformation: " + sensorSerialNum);
		SensorValue sensorValRet = null;
		boolean flag = false;
		try {
			sensorValRet = this.sensorService
					.retrieveSensorInfo(sensorSerialNum);
			flag = true;
		} catch (SensorNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put("result", flag).toString();
		}
		return new JSONObject().put("result", flag)
				.put("sensorSerialNum", sensorValRet.getSensorSerialNum())
				.put("deviceSerialNum", sensorValRet.getDeviceSerialNum())
				.put("sensorName", sensorValRet.getSensorName())
				.put("sensorTimestamp", sensorValRet.getSensorTimestamp())
				.put("sensorTypeNum", sensorValRet.getSensorTypeNum())
				.toString();
	}

	@RequestMapping(value = "/retrieve/{deviceSerialNum}/{sensorName}", method = RequestMethod.GET)
	@ResponseBody
	public String sensorInformation(
			@PathVariable("deviceSerialNum") String deviceSerialNum,
			@PathVariable("sensorName") String sensorName) throws JSONException {
		System.out.println("sensorInformation: " + deviceSerialNum + " "
				+ sensorName);
		SensorValue sensorValRet = null;
		boolean flag = false;
		try {
			sensorValRet = this.sensorService.retrieveSensorInfo(new ObjectId(
					deviceSerialNum), sensorName);
			flag = true;
		} catch (SensorNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put("result", flag).toString();
		} catch (DeviceNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put("result", flag).toString();
		}
		return new JSONObject().put("result", flag)
				.put("sensorSerialNum", sensorValRet.getSensorSerialNum())
				.put("deviceSerialNum", sensorValRet.getDeviceSerialNum())
				.put("sensorName", sensorValRet.getSensorName())
				.put("sensorTimestamp", sensorValRet.getSensorTimestamp())
				.put("sensorTypeNum", sensorValRet.getSensorTypeNum())
				.toString();
	}

	@RequestMapping(value = "/retrieve/{userSerialNum}/{deviceName}/{sensorName}", method = RequestMethod.GET)
	@ResponseBody
	public String sensorInformation(
			@PathVariable("userSerialNum") int userSerialNum,
			@PathVariable("deviceName") String deviceName,
			@PathVariable("sensorName") String sensorName) throws JSONException {
		System.out.println("sensorInformation: " + userSerialNum + " "
				+ deviceName + " " + sensorName);
		SensorValue sensorValRet = null;
		boolean flag = false;
		try {
			sensorValRet = this.sensorService.retrieveSensorInfo(userSerialNum,
					deviceName, sensorName);
			flag = true;
		} catch (UserNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put("result", flag).toString();
		} catch (DeviceNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put("result", flag).toString();
		} catch (SensorNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put("result", flag).toString();
		}
		return new JSONObject().put("result", flag)
				.put("sensorSerialNum", sensorValRet.getSensorSerialNum())
				.put("deviceSerialNum", sensorValRet.getDeviceSerialNum())
				.put("sensorName", sensorValRet.getSensorName())
				.put("sensorTimestamp", sensorValRet.getSensorTimestamp())
				.put("sensorTypeNum", sensorValRet.getSensorTypeNum())
				.toString();
	}

	@RequestMapping(value = "/retrieve/{userName}/{userWifiSsid}/{deviceName}/{sensorName}", method = RequestMethod.GET)
	@ResponseBody
	public String sensorInformation(@PathVariable("userName") String userName,
			@PathVariable("userWifiSsid") String userWifiSsid,
			@PathVariable("deviceName") String deviceName,
			@PathVariable("sensorName") String sensorName) throws JSONException {
		System.out.println("sensorInformation: " + userName + " "
				+ userWifiSsid + " " + deviceName + " " + sensorName);
		SensorValue sensorValRet = null;
		boolean flag = false;
		try {
			sensorValRet = this.sensorService.retrieveSensorInfo(userName, userWifiSsid, deviceName, sensorName);
			flag = true;
		} catch (UserNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put("result", flag).toString();
		} catch (DeviceNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put("result", flag).toString();
		} catch (SensorNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put("result", flag).toString();
		}
		return new JSONObject().put("result", flag)
				.put("sensorSerialNum", sensorValRet.getSensorSerialNum())
				.put("deviceSerialNum", sensorValRet.getDeviceSerialNum())
				.put("sensorName", sensorValRet.getSensorName())
				.put("sensorTimestamp", sensorValRet.getSensorTimestamp())
				.put("sensorTypeNum", sensorValRet.getSensorTypeNum())
				.toString();
	}
}