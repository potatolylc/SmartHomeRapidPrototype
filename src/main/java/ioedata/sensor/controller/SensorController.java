package ioedata.sensor.controller;

import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.SensorDuplicateException;
import ioedata.exception.factory.SensorNotExistException;
import ioedata.exception.factory.UserNotExistException;
import ioedata.sensor.model.SensorValue;
import ioedata.sensor.service.SensorService;
import ioedata.utils.StringUtils;

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

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public void sensorRegistration(@RequestParam("userName") String userName,
			@RequestParam("userWifiSsid") String userWifiSsid,
			@RequestParam("deviceName") String deviceName,
			@RequestParam("sensorType") String sensorType,
			@RequestParam("sensorName") String sensorName) throws JSONException {
		System.out.println("sensorRegistration: " + userName + " " + " "
				+ userWifiSsid + " " + deviceName + " " + sensorType + " "
				+ sensorName);
		String msg = null;
		try {
			this.sensorService.registerSensor(userName,
					userWifiSsid, deviceName, sensorType, sensorName);
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
	}

	@RequestMapping(value = "/{sensorSerialNum}", method = RequestMethod.GET)
	@ResponseBody
	public String sensorInformation(
			@PathVariable(StringUtils.SENSOR_SERIAL_NUM) String sensorSerialNum)
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
			return new JSONObject().put(StringUtils.RESULT, flag).toString();
		}
		return new JSONObject().put(StringUtils.RESULT, flag)
				.put(StringUtils.SENSOR_SERIAL_NUM, sensorValRet.getSensorSerialNum())
				.put(StringUtils.DEVICE_SERIAL_NUM, sensorValRet.getDeviceSerialNum())
				.put(StringUtils.SENSOR_NAME, sensorValRet.getSensorName())
				.put(StringUtils.SENSOR_TIMESTAMP, sensorValRet.getSensorTimestamp())
				.put(StringUtils.SENSOR_TYPE_NUM, sensorValRet.getSensorTypeNum())
				.toString();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String sensorInformation(
			@RequestParam("deviceSerialNum") String deviceSerialNum,
			@RequestParam("sensorName") String sensorName) throws JSONException {
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
		return new JSONObject().put(StringUtils.RESULT, flag)
				.put(StringUtils.SENSOR_SERIAL_NUM, sensorValRet.getSensorSerialNum())
				.put(StringUtils.DEVICE_SERIAL_NUM, sensorValRet.getDeviceSerialNum())
				.put(StringUtils.SENSOR_NAME, sensorValRet.getSensorName())
				.put(StringUtils.SENSOR_TIMESTAMP, sensorValRet.getSensorTimestamp())
				.put(StringUtils.SENSOR_TYPE_NUM, sensorValRet.getSensorTypeNum())
				.toString();
	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public void sensorRemove(@PathVariable(StringUtils.SENSOR_SERIAL_NUM) String sensorSerialNum) {
		System.out.println("sensorRemove: " + sensorSerialNum);
	}
}