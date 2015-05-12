package ioedata.utils;

public interface StringUtils {
	/*
	 * REST URI request Strings
	 */
	// user module
	public final static String USER_SERIAL_NUM = "userSerialNum";
	public final static String USER_NAME = "userName";
	public final static String USER_WIFI_SSID = "userWifiSsid";
	public final static String USER_TIMESTAMP = "userTimestamp";
	
	// device module
	public final static String DEVICE_SERIAL_NUM = "deviceSerialNum";
	public final static String DEVICE_NAME = "deviceName";
	public final static String DEVICE_TIMESTAMP = "deviceTimestamp";
	public final static String SENSOR_SERIAL_NUMS = "sensorSerialNums";
	public final static String ACTUATOR_SERIAL_NUMS = "actuatorSerialNums";
	
	// sensor module
	public final static String SENSOR_SERIAL_NUM = "sensorSerialNum";
	public final static String SENSOR_NAME = "sensorName";
	public final static String SENSOR_TIMESTAMP = "sensorTimestamp";
	public final static String SENSOR_TYPE_NUM = "sensorTypeNum";
	public final static String SENSOR_TYPE = "sensorType";
	
	// sensor data module
	public final static String SENSOR_DATA_SERIAL_NUM = "sensorDataSerialNum";
	public final static String SENSOR_DATA_VALUE = "sensorDataValue";
	public final static String SENSOR_DATA_TIMESTAMP = "sensorDataTimestamp";
	public final static String AVERAGE_SENSOR_DATA_VALUE = "averageSensorDataValue";
	public final static String LATEST_SENSOR_DATA_VALUE = "latestSensorDataValue";
	
	// actuator module
	public final static String ACTUATOR_SERIAL_NUM = "actuatorSerialNum";
	public final static String ACTUATOR_NAME = "actuatorName";
	public final static String ACTUATOR_TIMESTAMP = "actuatorTimestamp";
	public final static String ACTUATOR_TYPE_NUM = "actuatorTypeNum";
	public final static String ACTUATOR_TYPE = "actuatorType";
	
	// geo location module
	public final static String GEO_COORDINATE = "geoCoordinate";
	public final static String LONGITUDE = "longitude";
	public final static String LATITUDE = "latitude";
	
	/*
	 * JSON response Strings
	 */
	public final static String RESULT = "result";
	public final static String MESSAGE = "message";
}
