package ioedata.sensor.factory;

/**
 * Create SensorManager instances.
 * Parse sensor type between String and number.
 * @author ajou
 *
 */
public final class SensorManager {
	/*
	 * Singleton pattern for creating SensorManager instances.
	 */
	private SensorManager() {
	}
	private static SensorManager sensorManager = new SensorManager();
	public static SensorManager getSensorManager() {
		return sensorManager;
	}

	/**
	 * Get sensor instances based on different sensor type numbers.
	 * @param sensorTypeNum
	 * @return
	 */
	public Sensor getSensor(int sensorTypeNum) {
		Sensor sensor = null;
		switch (sensorTypeNum) {
		case Sensor.SENSOR_TYPE_ANALOG_SOUND:
			sensor = new AnalogSoundSensor();
			break;
		case Sensor.SENSOR_TYPE_DUST:
			sensor = new DustSensor();
			break;
		case Sensor.SENSOR_TYPE_FLAME:
			sensor = new FlameSensor();
			break;
		case Sensor.SENSOR_TYPE_HUMIDITY:
			sensor = new HumiditySensor();
			break;
		case Sensor.SENSOR_TYPE_LIGHT_BRIGHTNESS:
			sensor = new LightSensor();
			break;
		case Sensor.SENSOR_TYPE_RAINDROP:
			sensor = new RaindropSensor();
			break;
		case Sensor.SENSOR_TYPE_TEMPERATURE_CELSIUS:
			sensor = new TemperatureCelsiusSensor();
			break;
		case Sensor.SENSOR_TYPE_TEMPERATURE_FAHRENHEIT:
			sensor = new TemperatureFahrenheitSensor();
			break;
		case Sensor.SENSOR_TYPE_ACCELEROMETER:
			sensor = new AccelerometerSensor();
			break;
		case Sensor.SENSOR_TYPE_DIGITAL_TILT:
			sensor = new DigitalTiltSensor();
			break;
		case Sensor.SENSOR_TYPE_DIGITAL_VIBRATION:
			sensor = new DigitalVibrationSensor();
			break;
		case Sensor.SENSOR_TYPE_INFRARED_MOTION:
			sensor = new InfraredMotionSensor();
			break;
		case Sensor.SENSOR_TYPE_TOUCH:
			sensor = new TouchSensor();
			break;
		case Sensor.SENSOR_TYPE_PRESSURE:
			sensor = new PressureSensor();
		default:
			break;
		}
		return sensor;
	}
	
	/**
	 * Parse the sensor type name to get sensor type number.
	 * @param sensorType
	 * @return
	 */
	public int getSensorTypeNum(String sensorType) {
		int sensorTypeNum = -1;
		if(sensorType.equals("analogSound")) {
			sensorTypeNum = Sensor.SENSOR_TYPE_ANALOG_SOUND;
		} else if(sensorType.equals("dust")) {
			sensorTypeNum = Sensor.SENSOR_TYPE_DUST;
		} else if(sensorType.equals("flame")) {
			sensorTypeNum = Sensor.SENSOR_TYPE_FLAME;
		} else if(sensorType.equals("humidity")) {
			sensorTypeNum = Sensor.SENSOR_TYPE_HUMIDITY;
		} else if(sensorType.equals("lightBrightness")) {
			sensorTypeNum = Sensor.SENSOR_TYPE_LIGHT_BRIGHTNESS;
		} else if(sensorType.equals("raindrop")) {
			sensorTypeNum = Sensor.SENSOR_TYPE_RAINDROP;
		} else if(sensorType.equals("temperatureCel")) {
			sensorTypeNum = Sensor.SENSOR_TYPE_TEMPERATURE_CELSIUS;
		} else if(sensorType.equals("temperatureFah")) {
			sensorTypeNum = Sensor.SENSOR_TYPE_TEMPERATURE_FAHRENHEIT;
		} else if(sensorType.equals("accelerometer")) {
			sensorTypeNum = Sensor.SENSOR_TYPE_ACCELEROMETER;
		} else if(sensorType.equals("digitalTilt")) {
			sensorTypeNum = Sensor.SENSOR_TYPE_DIGITAL_TILT;
		} else if(sensorType.equals("digitalVibration")) {
			sensorTypeNum = Sensor.SENSOR_TYPE_DIGITAL_VIBRATION;
		} else if(sensorType.equals("infraredMotion")) {
			sensorTypeNum = Sensor.SENSOR_TYPE_INFRARED_MOTION;
		} else if(sensorType.equals("touch")) {
			sensorTypeNum = Sensor.SENSOR_TYPE_TOUCH;
		} else if(sensorType.equals("pressure")) {
			sensorTypeNum = Sensor.SENSOR_TYPE_PRESSURE;
		}
		return sensorTypeNum;
	}
	
	/*
	 * Get sensor type by sensor type number.
	 */
	public String getSensorType(int sensorTypeNum) {
		String sensorType = null;
		switch (sensorTypeNum) {
		case Sensor.SENSOR_TYPE_ANALOG_SOUND:
			sensorType = "analogSound";
			break;
		case Sensor.SENSOR_TYPE_DUST:
			sensorType = "dust"; 
			break;
		case Sensor.SENSOR_TYPE_FLAME:
			sensorType = "flame";
			break;
		case Sensor.SENSOR_TYPE_HUMIDITY:
			sensorType = "humidity";
			break;
		case Sensor.SENSOR_TYPE_LIGHT_BRIGHTNESS:
			sensorType = "lightBrightness";
			break;
		case Sensor.SENSOR_TYPE_RAINDROP:
			sensorType = "raindrop";
			break;
		case Sensor.SENSOR_TYPE_TEMPERATURE_CELSIUS:
			sensorType = "temperatureCel";
			break;
		case Sensor.SENSOR_TYPE_TEMPERATURE_FAHRENHEIT:
			sensorType = "temperatureFah";
			break;
		case Sensor.SENSOR_TYPE_ACCELEROMETER:
			sensorType = "accelerometer";
			break;
		case Sensor.SENSOR_TYPE_DIGITAL_TILT:
			sensorType = "digitalTilt";
			break;
		case Sensor.SENSOR_TYPE_DIGITAL_VIBRATION:
			sensorType = "digitalVibration";
			break;
		case Sensor.SENSOR_TYPE_INFRARED_MOTION:
			sensorType = "infraredMotion";
			break;
		case Sensor.SENSOR_TYPE_TOUCH:
			sensorType = "touch";
			break;
		case Sensor.SENSOR_TYPE_PRESSURE:
			sensorType = "pressure";
			break;
		default:
			break;
		}
		return sensorType;
	}

}
