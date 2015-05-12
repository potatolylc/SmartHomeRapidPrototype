package ioedata.sensor.factory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Define various sensors. Define sensor data collection time intervals. Perform
 * data collection actions for both single collection and continuous collection.
 * 
 * @author ajou
 * 
 */
public interface Sensor {
	/*
	 * Define sensor types
	 */
	public static final int SENSOR_TYPE_ALL = 0;
	public static final int SENSOR_TYPE_ANALOG_SOUND = 1;
	public static final int SENSOR_TYPE_DUST = 2;
	public static final int SENSOR_TYPE_FLAME = 3;
	public static final int SENSOR_TYPE_HUMIDITY = 4;
	public static final int SENSOR_TYPE_LIGHT_BRIGHTNESS = 5;
	public static final int SENSOR_TYPE_RAINDROP = 6;
	public static final int SENSOR_TYPE_TEMPERATURE_CELSIUS = 7;
	public static final int SENSOR_TYPE_TEMPERATURE_FAHRENHEIT = 8;
	public static final int SENSOR_TYPE_ACCELEROMETER = 9;
	public static final int SENSOR_TYPE_DIGITAL_TILT = 10;
	public static final int SENSOR_TYPE_DIGITAL_VIBRATION = 11;
	public static final int SENSOR_TYPE_INFRARED_MOTION = 12;
	public static final int SENSOR_TYPE_TOUCH = 13;
	public static final int SENSOR_TYPE_PRESSURE = 14;

	/*
	 * Define sensor data collection time intervals
	 */
	public static final int DATA_COLLECTION_INTERVAL_REAL_TIME = 2;
	public static final int DATA_COLLECTION_INTERVAL_RAPID = 180;
	public static final int DATA_COLLECTION_INTERVAL_MEDIUM = 600;
	public static final int DATA_COLLECTION_INTERVAL_SLOW = 1800;
	public static final int DATA_COLLECTION_INTERVAL_SINGLE = 0;

	/*
	 * Get sensor data with return type of Json String
	 */
	public JSONObject subscribeSingleJsonData(String deviceId, 
																		String deviceIp,
																		int deviceIpPort, 
																		String sensorType);

	public JSONArray subscribeContinuousData(String deviceId, 
																		String deviceIp, 
																		int deviceIpPort, 
																		String sensorType, 
																		int dataCollectionInterval) throws JSONException;
}