package ioedata.sensordata.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.SensorNotExistException;
import ioedata.sensordata.model.SensorDataValue;
import ioedata.sensordata.service.DataService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This class provides client requests analysis of all services about sensor
 * data, such as triggering or pausing sensor data collection, data retrieval
 * from sensor database or sensor data analysis
 * 
 * @author ajou
 */
@Controller
@RequestMapping(value = "/sensorData")
public class DataController {
	@Resource(name = "dataServiceImpl")
	private DataService dataService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public String collectData(
			@RequestParam("deviceSerialNum") String deviceSerialNum,
			@RequestParam("sensorName") String sensorName,
			@RequestParam("sensorDataValue") double sensorDataValue)
			throws JSONException {
		System.out.println("collectData: " + deviceSerialNum + " " + sensorName
				+ " " + sensorDataValue);
		boolean flag = false;
		String msg = null;
		try {
			flag = this.dataService.storeSensorData(deviceSerialNum,
					sensorName, sensorDataValue);
			if (flag)
				msg = "Sensor data has been stored.";
			else
				msg = "Something goes wrong with data collection.";
		} catch (SensorNotExistException e) {
			msg = "Sensor does not exist.";
			e.printStackTrace();
		} catch (DeviceNotExistException e) {
			msg = "Device does not exist.";
			e.printStackTrace();
		}
		System.out.println("collectData: " + msg);
		return new JSONObject().put("result", flag).put("message", msg)
				.toString();
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST)
	@ResponseBody
	public String collectData(HttpServletRequest request,
			@RequestParam("deviceSerialNum") ObjectId deviceSerialNum)
			throws JSONException {
		System.out.println("collectData all: " + deviceSerialNum);
		boolean flag = false;
		String msg = null;
		Map<String, Object> sensorDataPairs = new HashMap<String, Object>();
		Enumeration<String> requestParams = request.getParameterNames();
		while (requestParams.hasMoreElements()) {
			String requestParam = requestParams.nextElement();
			if (requestParam.equals("deviceSerialNum"))
				continue;
			String paramVal = request.getParameter(requestParam);
			sensorDataPairs.put(requestParam, paramVal);
		}
		try {
			flag = this.dataService.storeSensorData(deviceSerialNum,
					sensorDataPairs);
		} catch (DeviceNotExistException e) {
			msg = "Device does not exist.";
			e.printStackTrace();
		} catch (SensorNotExistException e) {
			msg = e.getMessage();
			e.printStackTrace();
		}
		return new JSONObject().put("result", flag).put("message", msg)
				.toString();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String retrieveData(
			@RequestParam("sensorSerialNum") String sensorSerialNum,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime) throws JSONException {
		System.out.println("retrieveData: " + sensorSerialNum + " " + startTime
				+ " " + endTime);
		
		List<SensorDataValue> sensorDataList = null;
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		try {
			sensorDataList = this.dataService.retrieveData(sensorSerialNum, startTime, endTime);
			if(sensorDataList != null) {
				for(SensorDataValue data : sensorDataList) {
					System.out.println(data);
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("sensorDataSerialNum", data.getSensorDataSerialNum());
					jsonObj.put("sensorDataValue", data.getSensorDataValue());
					jsonObj.put("sensorDataTimestamp", data.getSensorDataTimestampStr());
					jsonList.add(jsonObj);
				}
			}
			
		} catch (SensorNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(new JSONArray(jsonList).toString());
		return new JSONArray(jsonList).toString();
	}

	@RequestMapping(value = "/average", method = RequestMethod.GET)
	public String retrieveAverageData(
			@RequestParam("sensorSerialNum") String sensorSerialNum) {
		System.out.println("retrieveAverageData: " + sensorSerialNum);
		return new JSONObject().toString();
	}
}