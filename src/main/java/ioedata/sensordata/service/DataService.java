package ioedata.sensordata.service;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.SensorNotExistException;
import ioedata.sensordata.model.SensorDataValue;

/**
 * This interface provides methods to both access and manipulate data in the
 * sensor database and collect and control sensor end devices.
 * 
 * @author ajou
 */
public interface DataService {
	/**
	 * Insert sensor data into cloud server. First check whether the sensor with
	 * a typical sensor name exists. If it does, return true; if not, return
	 * false.
	 * @param deviceSerialNum
	 * @param sensorName
	 * @param sensorDataValue
	 * @return
	 * @throws SensorNotExistException
	 * @throws DeviceNotExistException
	 */
	public boolean storeSensorData(String deviceSerialNum, String sensorName,
			double sensorDataValue) throws SensorNotExistException,
			DeviceNotExistException;

	/**
	 * Insert sensor data of a specific device into database based on
	 * sensor-sensor data key-value pairs.
	 * @param deviceSerialNum
	 * @param sensorDataPairs
	 * @return
	 * @throws DeviceNotExistException
	 * @throws SensorNotExistException
	 */
	public boolean storeSensorData(ObjectId deviceSerialNum,
			Map<String, Double> sensorDataPairs)
			throws DeviceNotExistException, SensorNotExistException;

	/**
	 * Retrieve data list of a specific sensor with the time period ranging from
	 * startTime and endTime.
	 * @param sensorSerialNum
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws SensorNotExistException
	 * @throws JSONException
	 */
	public List<SensorDataValue> retrieveData(String sensorSerialNum,
			String startTime, String endTime) throws SensorNotExistException, JSONException;
	
	/**
	 * Retrieve the average data value of a specific sensor.
	 * @param sensorSerialNum
	 * @return
	 */
	public double retrieveAverageData(String sensorSerialNum);
	
	/**
	 * Retrieve the latest data value of a specific sensor.
	 * @param sensorSerialNum
	 * @return
	 */
	public double retrieveLatestData(String sensorSerialNum);
	
	/**
	 * Retrieve the list of data value and sensor info bundles  of a specific device.
	 * @param deviceSerialNum
	 * @return
	 * @throws DeviceNotExistException
	 * @throws JSONException
	 */
	public List<JSONObject> retrieveLatestDataSet(String deviceSerialNum) throws DeviceNotExistException, JSONException;
}