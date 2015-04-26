package ioedata.sensordata.service;

import ioedata.device.service.DeviceService;
import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.SensorNotExistException;
import ioedata.sensor.factory.SensorManager;
import ioedata.sensor.model.SensorValue;
import ioedata.sensor.service.SensorService;
import ioedata.sensordata.model.SensorDataValue;
import ioedata.sensordata.model.TimeValue;
import ioedata.sensordata.repository.DataDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * This class provides services about sensor data. It implements DataService
 * interface.
 * 
 * @author ajou
 */
@Service
public class DataServiceImpl implements DataService {
	@Resource(name = "deviceServiceImpl")
	private DeviceService deviceService;
	@Resource(name = "sensorServiceImpl")
	private SensorService sensorService;
	@Resource(name = "dataDaoImpl")
	private DataDao dataDao;

	@Override
	public boolean storeSensorData(String deviceSerialNum, String sensorName,
			double sensorDataValue) throws SensorNotExistException,
			DeviceNotExistException {
		// Check whether device exists
		boolean isDeviceExistFlag = this.deviceService
				.isDeviceExist(deviceSerialNum);
		if (!isDeviceExistFlag) {
			throw new DeviceNotExistException();
		}
		// Check whether sensor exists
		boolean isSensorExistFlag = this.sensorService.isSensorExist(
				new ObjectId(deviceSerialNum), sensorName);
		// If sensor doesn't exist
		if (!isSensorExistFlag) {
			throw new SensorNotExistException();
		}
		// If sensor exists
		String sensorSerialNumStr = this.sensorService
				.retrieveSensorSerialNumBySensorNameAndDeviceSerialNum(
						sensorName, new ObjectId(deviceSerialNum)).toString();
		int result = this.dataDao.insertSensorData(new SensorDataValue(
				sensorDataValue, new SensorValue(sensorSerialNumStr)));
		return result > 0 ? true : false;
	}

	@Override
	public boolean storeSensorData(ObjectId deviceSerialNum,
			Map<String, Double> sensorDataPairs)
			throws DeviceNotExistException, SensorNotExistException {
		// Check whether device exists
		boolean isDeviceExistFlag = this.deviceService
				.isDeviceExist(deviceSerialNum.toString());
		if (!isDeviceExistFlag) {
			throw new DeviceNotExistException();
		}
		// Get sensor type list
		Iterator<String> sensorNames = sensorDataPairs.keySet().iterator();
		int result = 0;
		while (sensorNames.hasNext()) {
			String sensorName = sensorNames.next();
			boolean isSensorExistFlag = this.sensorService.isSensorExist(
					deviceSerialNum, sensorName);
			if (!isSensorExistFlag)
				throw new SensorNotExistException("Sensor " + sensorName
						+ " does not exist.");
			// If the sensor exists, get sensor serial number and store data
			String sensorSerialNumStr = this.sensorService
					.retrieveSensorSerialNumBySensorNameAndDeviceSerialNum(
							sensorName, deviceSerialNum).toString();
			this.dataDao.insertSensorData(new SensorDataValue(sensorDataPairs
					.get(sensorName), new SensorValue(sensorSerialNumStr)));
			result++;
		}
		return result == sensorDataPairs.size() ? true : false;
	}

	@Override
	public List<SensorDataValue> retrieveData(String sensorSerialNum,
			String startTime, String endTime) throws SensorNotExistException {
		List<SensorDataValue> sensorDataList = null;
		// check whether sensor exists
		/*
		 * boolean isSensorExistFlag = this.sensorService .isSensorExist(new
		 * ObjectId(sensorSerialNum)); if (!isSensorExistFlag) throw new
		 * SensorNotExistException(); SensorValue sensor =
		 * this.sensorService.retrieveSensorInfo(sensorSerialNum);
		 */
		sensorDataList = this.dataDao
				.getDataListBySensorSerialNumAndStartTimeAndEndTime(new SensorDataValue(
						new SensorValue(sensorSerialNum), new TimeValue(
								startTime, endTime)));
		/*
		 * if(sensorDataList != null) { for(SensorDataValue data :
		 * sensorDataList) { data.setSensorValue(sensor); } }
		 */
		return sensorDataList;
	}

	@Override
	public double retrieveAverageData(String sensorSerialNum) {
		return this.dataDao.getAverageDataBySensorSerialNum(sensorSerialNum);
	}

	@Override
	public double retrieveLatestData(String sensorSerialNum) {
		return this.dataDao.getLatestDataBySensorSerialNum(sensorSerialNum);
	}

	@Override
	public List<JSONObject> retrieveLatestDataSet(String deviceSerialNum)
			throws DeviceNotExistException, JSONException {
		List<JSONObject> jsonObjList = new ArrayList<JSONObject>();
		// check whether device exists
		boolean isDeviceExistFlag = this.deviceService
				.isDeviceExist(deviceSerialNum);
		if (!isDeviceExistFlag)
			throw new DeviceNotExistException();

		// get all sensors attached to the device
		List<SensorValue> sensorList = this.sensorService
				.retrieveSensorList(new ObjectId(deviceSerialNum));
		if (sensorList != null) {
			// for each sensor, get the latest sensor data
			for (SensorValue sensor : sensorList) {
				JSONObject jsonObj = new JSONObject();
				double sensorVal = this.dataDao
						.getLatestDataBySensorSerialNum(sensor
								.getSensorSerialNum().toString());
				jsonObj.put("sensorSerialNum", sensor.getSensorSerialNum());
				jsonObj.put("sensorName", sensor.getSensorName());
				jsonObj.put("sensorType", SensorManager.getSensorManager()
						.getSensorType(sensor.getSensorTypeNum()));
				jsonObj.put("sensorDataValue", sensorVal);
				jsonObjList.add(jsonObj);
			}
		}
		return jsonObjList;
	}
}