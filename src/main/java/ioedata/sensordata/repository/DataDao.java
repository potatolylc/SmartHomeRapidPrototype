package ioedata.sensordata.repository;

import ioedata.sensor.model.SensorValue;
import ioedata.sensordata.model.DataValue;

import java.util.List;

/**
 * This interface provides methods to access and manipulate database about data.
 * @author ajou
 *
 */
public interface DataDao {
	/*
	 * Get the latest data of a specific device despite of sensor data type.
	 */
	public DataValue getLastDataByDeviceIdSortedByTimestamp(String deviceId) throws Exception;
	/*
	 * Get the latest data of a specific device and a sensor data type.
	 */
	public DataValue getLastDataByDeviceIdAndSensorTypeSortedByTimestamp(SensorValue sensorVal) throws Exception;
	/*
	 * Get the data information list from database that is between a specific time interval.
	 */
	public List<DataValue> getDataListByDeviceIdAndSensorTypeAndDateSortedByTimestamp(DataValue dataVal) throws Exception;
	/*
	 * Insert new sensor data into database.
	 */
	public int insertSensorData(DataValue dataVal) throws Exception;
	/*
	 * Get the greatest data value of a specific sensor type.
	 */
	public DataValue getGreatestDataByDeviceIdAndSensorType(SensorValue sensorVal) throws Exception;
	/*
	 * Get the least data value of a specific sensor type.
	 */
	public DataValue getLeastDataByDeviceIdAndSensorType(SensorValue sensorVal) throws Exception;
}