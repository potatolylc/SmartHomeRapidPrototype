package ioedata.sensordata.repository;

import ioedata.sensor.model.SensorValue;
import ioedata.sensordata.model.SensorDataValue;

import java.util.List;

/**
 * This interface provides methods to access and manipulate database about data.
 * 
 * @author ajou
 * 
 */
public interface DataDao {
	/**
	 * Insert new sensor data into database.
	 * @param dataVal
	 * @return
	 */
	public int insertSensorData(SensorDataValue dataVal);
	
	/**
	 * Get data list of a specific sensor from the start time to the end time.
	 * @param sensorVal
	 * @return
	 */
	public List<SensorDataValue> getDataListBySensorSerialNumAndStartTimeAndEndTime(SensorDataValue sensorVal);

	/**
	 * Get average data value using sensor serial number.
	 * @param sensorSerialNum
	 * @return
	 */
	public double getAverageDataBySensorSerialNum(String sensorSerialNum);
	
	/**
	 * Get average data value using sensor serial number.
	 * @param sensorSerialNum
	 * @return
	 */
	public double getLatestDataBySensorSerialNum(String sensorSerialNum);	
}