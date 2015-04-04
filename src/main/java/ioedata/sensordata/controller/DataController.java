package ioedata.sensordata.controller;

import java.util.Enumeration;

import ioedata.exception.factory.SensorNotExistException;
import ioedata.sensordata.service.DataService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

	@RequestMapping(value = "/collect", method = RequestMethod.POST)
	@ResponseBody
	public String collectData(
			@RequestParam("deviceSerialNum") String deviceSerialNum,
			@RequestParam("sensorName") String sensorName,
			@RequestParam("sensorDataValue") double sensorDataValue) throws JSONException {
		System.out.println("collectData: " + deviceSerialNum + " " + sensorName + " " + sensorDataValue);
		boolean flag = false;
		String msg = null;
		try {
			flag = this.dataService.storeSensorData(deviceSerialNum, sensorName, sensorDataValue);
			if (flag)
				msg = "Sensor data has been stored.";
			else
				msg = "Something goes wrong with data collection.";
		} catch (SensorNotExistException e) {
			msg = "Sensor does not exist.";
			e.printStackTrace();
		}
		System.out.println("collectData: " + msg);
		return new JSONObject().put("result", flag).put("message", msg).toString();
	}

	@RequestMapping(value = "/collectAll", method = RequestMethod.POST)
	@ResponseBody
	public String collectData(HttpServletRequest request, 
			@RequestParam("deviceSerialNum") String deviceSerialNum) throws JSONException {
		System.out.println("collectData all: " + deviceSerialNum);
		boolean flag = false;
		String msg = null;
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			System.out.println(enu.nextElement());
		}
		System.out.println("collectAllData: " + msg);
		return new JSONObject().toString();
	}

	/*
	 * @RequestMapping(value =
	 * "/retrieve/{deviceId}/{sensorType}/{retrieveType}", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public String retrieveData(@PathVariable("deviceId") String
	 * deviceId, @PathVariable("sensorType") String sensorType,
	 * @PathVariable("retrieveType") String retrieveType) throws
	 * UnknownHostException, IOException {
	 * System.out.println("retrieveDataeee: "+deviceId+" "+retrieveType);
	 * DataValue dataVal; JSONObject json = new JSONObject(); try { dataVal =
	 * this.dataService.retrieveData(deviceId, sensorType, retrieveType);
	 * if(dataVal != null){ json = this.createJsonObjForDataValue(dataVal); } }
	 * catch (Exception e) { e.printStackTrace(); }
	 * //System.out.println(json.toString()); return json.toString(); }
	 * 
	 * @RequestMapping(value =
	 * "/retrieveList/{deviceId}/{sensorType}/{retrieveType}", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public String retrieveDataList(@PathVariable("deviceId")
	 * String deviceId, @PathVariable("sensorType") String sensorType,
	 * @PathVariable("retrieveType") String retrieveType) {
	 * System.out.println("retrieveDataList: "
	 * +deviceId+" "+sensorType+" "+retrieveType); return null; }
	 * 
	 * @RequestMapping(value =
	 * "/retrieveList/{deviceId}/{sensorType}/{startDate}/{endDate}", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public String
	 * retrieveDataListByDate(@PathVariable("deviceId") String deviceId,
	 * @PathVariable("sensorType") String sensorType, @PathVariable("startDate")
	 * String startDate, @PathVariable("endDate") String endDate) throws
	 * JSONException{
	 * System.out.println("retrieveDataListByDate: "+deviceId+" "+
	 * sensorType+" "+startDate+" "+endDate); JSONArray jsonArr = new
	 * JSONArray(); try { List<DataValue> dataList =
	 * this.dataService.retrieveDataList(deviceId, sensorType, startDate,
	 * endDate); if(dataList != null){ jsonArr =
	 * this.createJsonArrayForDataValueList(dataList);
	 * //System.out.println(jsonArr.length()+" "+jsonArr.toString()); } } catch
	 * (Exception e) { e.printStackTrace(); } return new
	 * JSONObject().put("dataList", jsonArr).toString(); }
	 * 
	 * @RequestMapping(value =
	 * "/retrieveList/{deviceId}/{sensorType}/{startDate}/{startTime}/{endDate}/{endTime}"
	 * , method = RequestMethod.GET)
	 * 
	 * @ResponseBody public String
	 * retrieveDataListByTime(@PathVariable("deviceId") String deviceId,
	 * @PathVariable("sensorType") String sensorType, @PathVariable("startDate")
	 * String startDate, @PathVariable("startTime") String startTime,
	 * @PathVariable("endDate") String endDate, @PathVariable("endTime") String
	 * endTime) throws JSONException{
	 * System.out.println("retrieveDataListByTime: "
	 * +deviceId+" "+sensorType+" "+
	 * startDate+" "+startTime+" "+endDate+" "+endTime); JSONArray jsonArr = new
	 * JSONArray(); try { List<DataValue> dataList =
	 * this.dataService.retrieveDataList(deviceId, sensorType, startDate,
	 * startTime, endDate, endTime); if(dataList != null){ jsonArr =
	 * this.createJsonArrayForDataValueList(dataList);
	 * //System.out.println(jsonArr.length()+" "+jsonArr.toString()); } } catch
	 * (Exception e) { e.printStackTrace(); } return new
	 * JSONObject().put("dataList", jsonArr).toString(); }
	 * 
	 * 
	 * private JSONObject createJsonObjForDataValue(DataValue dataVal) throws
	 * JSONException{ JSONObject json = new JSONObject(); json.put("dataValue",
	 * dataVal.getSensorDataValue()); json.put("unit",
	 * dataVal.getSensorValue().getSensorTypeValue().getUnit());
	 * json.put("timestamp", dataVal.getSensorDataTimestamp());
	 * json.put("timestampStr", dataVal.getSensorDataTimestampStr());
	 * json.put("sensorType",
	 * dataVal.getSensorValue().getSensorTypeValue().getSensorType());
	 * json.put("deviceId",
	 * dataVal.getSensorValue().getDeviceValue().getDeviceId()); return json; }
	 * 
	 * private JSONArray createJsonArrayForDataValueList(List<DataValue>
	 * dataList) throws JSONException{ JSONArray jsonArr = new JSONArray();
	 * for(DataValue dataVal : dataList){ //System.out.println(dataVal);
	 * JSONObject json = new JSONObject(); json.put("dataValue",
	 * dataVal.getSensorDataValue()); json.put("unit",
	 * dataVal.getSensorValue().getSensorTypeValue().getUnit());
	 * json.put("timestamp", dataVal.getSensorDataTimestamp());
	 * json.put("timestampStr", dataVal.getSensorDataTimestampStr());
	 * json.put("sensorType",
	 * dataVal.getSensorValue().getSensorTypeValue().getSensorType());
	 * json.put("deviceId",
	 * dataVal.getSensorValue().getDeviceValue().getDeviceId());
	 * jsonArr.put(json); } return jsonArr; }
	 */
}