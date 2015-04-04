package ioedata.sensordata.repository;

import ioedata.sensor.model.SensorValue;
import ioedata.sensordata.model.SensorDataValue;

import java.util.List;

/**
 * This interface provides methods to access and manipulate database about data.
 * @author ajou
 *
 */
public interface DataDao {
	/*
	 * Insert new sensor data into database.
	 */
	public int insertSensorData(SensorDataValue dataVal);
	/*
	 * Get the latest data of a specific device despite of sensor data type.
	 */
	public SensorDataValue getLastDataByDeviceIdSortedByTimestamp(String deviceId) throws Exception;
	/*
	 * Get the latest data of a specific device and a sensor data type.
	 */
	public SensorDataValue getLastDataByDeviceIdAndSensorTypeSortedByTimestamp(SensorValue sensorVal) throws Exception;
	/*
	 * Get the data information list from database that is between a specific time interval.
	 */
	public List<SensorDataValue> getDataListByDeviceIdAndSensorTypeAndDateSortedByTimestamp(SensorDataValue dataVal) throws Exception;
	/*
	 * Get the greatest data value of a specific sensor type.
	 */
	public SensorDataValue getGreatestDataByDeviceIdAndSensorType(SensorValue sensorVal) throws Exception;
	/*
	 * Get the least data value of a specific sensor type.
	 */
	public SensorDataValue getLeastDataByDeviceIdAndSensorType(SensorValue sensorVal) throws Exception;
}