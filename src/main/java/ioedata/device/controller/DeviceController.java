package ioedata.device.controller;

import java.util.ArrayList;
import java.util.List;

import ioedata.actuator.model.ActuatorValue;
import ioedata.device.model.DeviceValue;
import ioedata.device.service.DeviceService;
import ioedata.exception.factory.DeviceDuplicateException;
import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.UserNotExistException;
import ioedata.geolocation.model.GeoCoordinate;
import ioedata.sensor.model.SensorValue;
import ioedata.utils.DateFormatConverter;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This class is the controller for requests and responses from client end side
 * about device services, such as register new devices or authenticate existing
 * devices with device ID and password.
 * 
 * @author ajou
 * 
 */
@Controller
@RequestMapping(value = "/device")
public class DeviceController {
	@Resource(name = "deviceServiceImpl")
	private DeviceService deviceService;

	/*
	 * Register a new device.
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public void deviceRegistration(@RequestParam("userName") String userName,
			@RequestParam("userWifiSsid") String userWifiSsid,
			@RequestParam("deviceName") String deviceName) throws JSONException {
		System.out.println("deviceRegistration: " + userName + " "
				+ userWifiSsid + " " + deviceName);
		ObjectId deviceSerialNum = null;
		String msg = null;
		try {
			deviceSerialNum = this.deviceService.registerDevice(userName,
					userWifiSsid, deviceName);
			if (deviceSerialNum != null) {
				msg = "Device has been successfully registerd.";
			}
		} catch (DeviceDuplicateException e) {
			msg = "Device already exists.";
			e.printStackTrace();
		} catch (UserNotExistException e) {
			msg = "User does not exist.";
			e.printStackTrace();
		} catch (Exception e) {
			msg = "Something goes wrong with device registration. ";
			e.printStackTrace();
		}
		System.out.println("deviceRegistration: " + msg);
	}

	/*
	 * Register a new device with geospatial information.
	 */
	@RequestMapping(value = "/geo", method = RequestMethod.POST)
	@ResponseBody
	public String deviceRegistration(@RequestParam("userName") String userName,
			@RequestParam("userWifiSsid") String userWifiSsid,
			@RequestParam("deviceName") String deviceName,
			@RequestParam("longitude") double longitude,
			@RequestParam("latitude") double latitude) throws JSONException {
		System.out.println("deviceRegistration: " + userName + " "
				+ userWifiSsid + " " + deviceName + " (" + longitude + ", "
				+ latitude + ")");
		ObjectId deviceSerialNum = null;
		boolean flag = false;
		String msg = null;
		try {
			deviceSerialNum = this.deviceService.registerDevice(userName,
					userWifiSsid, deviceName, new GeoCoordinate(longitude,
							latitude));
			if (deviceSerialNum != null) {
				flag = true;
				msg = "Device has been successfully registerd.";
			}
		} catch (DeviceDuplicateException e) {
			msg = "Device already exists.";
			e.printStackTrace();
		} catch (UserNotExistException e) {
			msg = "User does not exist.";
			e.printStackTrace();
		} catch (Exception e) {
			msg = "Something goes wrong with device registration. ";
			e.printStackTrace();
		}
		System.out.println("deviceRegistration: " + msg);
		return new JSONObject().put("result", flag).put("message", msg)
				.put("deviceSerialNum", deviceSerialNum).toString();
	}

	/*
	 * Get device information by device serial number.
	 */
	@RequestMapping(value = "/{deviceSerialNum}", method = RequestMethod.GET)
	@ResponseBody
	public String deviceInformation(
			@PathVariable("deviceSerialNum") String deviceSerialNum)
			throws JSONException {
		System.out.println("deviceInformation: " + deviceSerialNum);
		DeviceValue deviceValRet = null;
		boolean flag = false;
		try {
			deviceValRet = this.deviceService
					.retrieveDeviceInfo(deviceSerialNum);
			flag = true;
		} catch (DeviceNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put("result", flag).toString();
		}
		
		List<String> sensorSerialNums = null;
		List<String> actuatorSerialNums = null;
		if(deviceValRet != null) {
			List<SensorValue> sensors = null;
			List<ActuatorValue> actuators = null;
			if((sensors = deviceValRet.getSensors()) != null) {
				sensorSerialNums = new ArrayList<String>();
				for(SensorValue sensor : sensors) {
					sensorSerialNums.add(sensor.getSensorSerialNum().toString());
				}
			}
			if((actuators = deviceValRet.getActuators()) != null) {
				actuatorSerialNums = new ArrayList<String>();
				for(ActuatorValue actuator : actuators) {
					actuatorSerialNums.add(actuator.getActuatorSerialNum().toString());
				}
			}
		}
		if (deviceValRet.getGeoCoordinate() == null) {
			return new JSONObject()
					.put("result", flag)
					.put("deviceSerialNum", deviceValRet.getDeviceSerialNum())
					.put("userSerialNum", deviceValRet.getUserSerialNum())
					.put("deviceName", deviceValRet.getDeviceName())
					.put("deviceTimestamp",
							DateFormatConverter.getConvert()
									.englishLocaleToStandard(
											deviceValRet.getDeviceTimestamp()))
					.put("sensorSerialNums",
							new JSONArray(sensorSerialNums))
					.put("actuatorSerialNums",
							new JSONArray(actuatorSerialNums))
					.toString();
		} else {
			return new JSONObject()
					.put("result", flag)
					.put("deviceSerialNum", deviceValRet.getDeviceSerialNum())
					.put("userSerialNum", deviceValRet.getUserSerialNum())
					.put("deviceName", deviceValRet.getDeviceName())
					.put("deviceTimestamp",
							DateFormatConverter.getConvert()
									.englishLocaleToStandard(
											deviceValRet.getDeviceTimestamp()))
					.put("geoCoordinate",
							new JSONObject().put(
									"longitude",
									deviceValRet.getGeoCoordinate()
											.getLongitude()).put(
									"latitude",
									deviceValRet.getGeoCoordinate()
											.getLatitude()))
					.put("sensorSerialNums",
							new JSONArray(sensorSerialNums))
					.put("actuatorSerialNums",
							new JSONArray(actuatorSerialNums))
					.toString();
		}
	}

	/*
	 * Get device information by user serial number and device name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String deviceInformation(
			@RequestParam("userSerialNum") int userSerialNum,
			@RequestParam("deviceName") String deviceName) throws JSONException {
		System.out.println("deviceInformation: " + userSerialNum + " "
				+ deviceName);
		DeviceValue deviceValRet = null;
		boolean flag = false;
		try {
			deviceValRet = this.deviceService.retrieveDeviceInfo(userSerialNum,
					deviceName);
			flag = true;
		} catch (DeviceNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put("result", flag).toString();
		} catch (UserNotExistException e) {
			e.printStackTrace();
			return new JSONObject().put("result", flag).toString();
		}
		
		List<String> sensorSerialNums = null;
		List<String> actuatorSerialNums = null;
		if(deviceValRet != null) {
			List<SensorValue> sensors = null;
			List<ActuatorValue> actuators = null;
			if((sensors = deviceValRet.getSensors()) != null) {
				sensorSerialNums = new ArrayList<String>();
				for(SensorValue sensor : sensors) {
					sensorSerialNums.add(sensor.getSensorSerialNum().toString());
				}
			}
			if((actuators = deviceValRet.getActuators()) != null) {
				actuatorSerialNums = new ArrayList<String>();
				for(ActuatorValue actuator : actuators) {
					actuatorSerialNums.add(actuator.getActuatorSerialNum().toString());
				}
			}
		}
		if (deviceValRet.getGeoCoordinate() == null) {
			return new JSONObject()
					.put("result", flag)
					.put("deviceSerialNum", deviceValRet.getDeviceSerialNum())
					.put("userSerialNum", deviceValRet.getUserSerialNum())
					.put("deviceName", deviceValRet.getDeviceName())
					.put("deviceTimestamp",
							DateFormatConverter.getConvert()
									.englishLocaleToStandard(
											deviceValRet.getDeviceTimestamp()))
					.put("sensorSerialNums",
							new JSONArray(sensorSerialNums))
					.put("actuatorSerialNums",
							new JSONArray(actuatorSerialNums))
					.toString();
		} else {
			return new JSONObject()
					.put("result", flag)
					.put("deviceSerialNum", deviceValRet.getDeviceSerialNum())
					.put("userSerialNum", deviceValRet.getUserSerialNum())
					.put("deviceName", deviceValRet.getDeviceName())
					.put("deviceTimestamp",
							DateFormatConverter.getConvert()
									.englishLocaleToStandard(
											deviceValRet.getDeviceTimestamp()))
					.put("geoCoordinate",
							new JSONObject().put(
									"longitude",
									deviceValRet.getGeoCoordinate()
											.getLongitude()).put(
									"latitude",
									deviceValRet.getGeoCoordinate()
											.getLatitude()))
					.put("sensorSerialNums",
							new JSONArray(sensorSerialNums))
					.put("actuatorSerialNums",
							new JSONArray(actuatorSerialNums))
					.toString();
		}
	}
	
	@RequestMapping(value = "/{deviceSerialNum}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deviceRemove(@PathVariable("deviceSerialNum") String deviceSerialNum) {
		return new JSONObject().toString();
	}

	/*
	 * Get device information by user name, user WiFi SSID and device name.
	 */
	/*
	 * @RequestMapping(value = "", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public String deviceInformation(@RequestParam("userName")
	 * String userName,
	 * 
	 * @RequestParam("userWifiSsid") String userWifiSsid,
	 * 
	 * @RequestParam("deviceName") String deviceName) throws JSONException {
	 * System.out.println("deviceInformation: " + userName + " " + userWifiSsid
	 * + " " + deviceName); DeviceValue deviceValRet = null; boolean flag =
	 * false; try { deviceValRet =
	 * this.deviceService.retrieveDeviceInfo(userName, userWifiSsid,
	 * deviceName); flag = true; } catch (DeviceNotExistException e) {
	 * e.printStackTrace(); return new JSONObject().put("result",
	 * flag).toString(); } catch (UserNotExistException e) {
	 * e.printStackTrace(); return new JSONObject().put("result",
	 * flag).toString(); } if(deviceValRet.getGeoCoordinate() == null) return
	 * new JSONObject() .put("result", flag) .put("deviceSerialNum",
	 * deviceValRet.getDeviceSerialNum()) .put("userSerialNum",
	 * deviceValRet.getUserSerialNum()) .put("deviceName",
	 * deviceValRet.getDeviceName()) .put("deviceTimestamp",
	 * deviceValRet.getDeviceTimestamp()) .put("sensorSerialNums", new
	 * JSONArray(deviceValRet.getSensors())) .put("actuatorSerialNums", new
	 * JSONArray(deviceValRet.getActuators())).toString(); else return new
	 * JSONObject() .put("result", flag) .put("deviceSerialNum",
	 * deviceValRet.getDeviceSerialNum()) .put("userSerialNum",
	 * deviceValRet.getUserSerialNum()) .put("deviceName",
	 * deviceValRet.getDeviceName()) .put("deviceTimestamp",
	 * deviceValRet.getDeviceTimestamp()) .put("geoCoordinate", new JSONObject()
	 * .put("longitude", deviceValRet.getGeoCoordinate().getLongitude())
	 * .put("latitude", deviceValRet.getGeoCoordinate().getLatitude()))
	 * .put("sensorSerialNums", new JSONArray(deviceValRet.getSensors()))
	 * .put("actuatorSerialNums", new
	 * JSONArray(deviceValRet.getActuators())).toString(); }
	 */
}