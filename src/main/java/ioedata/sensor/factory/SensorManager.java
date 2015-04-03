package ioedata.sensor.factory;



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

	/*
	 * Get sensor instances based on different sensor type numbers.
	 */
	public Sensor getSensor(int sensorTypeNum) {
		Sensor sensor = null;
		switch (sensorTypeNum) {
		case Sensor.TYPE_ANALOG_SOUND:
			sensor = new AnalogSoundSensor();
			break;
		case Sensor.TYPE_DUST:
			sensor = new DustSensor();
			break;
		case Sensor.TYPE_FLAME:
			sensor = new FlameSensor();
			break;
		case Sensor.TYPE_HUMIDITY:
			sensor = new HumiditySensor();
			break;
		case Sensor.TYPE_LIGHT_BRIGHTNESS:
			sensor = new LightSensor();
			break;
		case Sensor.TYPE_RAINDROP:
			sensor = new RaindropSensor();
			break;
		case Sensor.TYPE_TEMPERATURE_CELSIUS:
			sensor = new TemperatureCelsiusSensor();
			break;
		case Sensor.TYPE_TEMPERATURE_FAHRENHEIT:
			sensor = new TemperatureFahrenheitSensor();
			break;
		case Sensor.TYPE_ACCELEROMETER:
			sensor = new AccelerometerSensor();
			break;
		case Sensor.TYPE_DIGITAL_TILT:
			sensor = new DigitalTiltSensor();
			break;
		case Sensor.TYPE_DIGITAL_VIBRATION:
			sensor = new DigitalVibrationSensor();
			break;
		case Sensor.TYPE_INFRARED_MOTION:
			sensor = new InfraredMotionSensor();
			break;
		case Sensor.TYPE_TOUCH:
			sensor = new TouchSensor();
			break;
		default:
			break;
		}
		return sensor;
	}
	
	/*
	 * Parse the sensor type name to get sensor type number.
	 */
	public int getSensorTypeNum(String sensorType) {
		int sensorTypeNum = -1;
		if(sensorType.equals("analogSound")) {
			sensorTypeNum = Sensor.TYPE_ANALOG_SOUND;
		} else if(sensorType.equals("dust")) {
			sensorTypeNum = Sensor.TYPE_DUST;
		} else if(sensorType.equals("flame")) {
			sensorTypeNum = Sensor.TYPE_FLAME;
		} else if(sensorType.equals("humidity")) {
			sensorTypeNum = Sensor.TYPE_HUMIDITY;
		} else if(sensorType.equals("lightBrightness")) {
			sensorTypeNum = Sensor.TYPE_LIGHT_BRIGHTNESS;
		} else if(sensorType.equals("raindrop")) {
			sensorTypeNum = Sensor.TYPE_RAINDROP;
		} else if(sensorType.equals("temperatureCel")) {
			sensorTypeNum = Sensor.TYPE_TEMPERATURE_CELSIUS;
		} else if(sensorType.equals("temperatureFah")) {
			sensorTypeNum = Sensor.TYPE_TEMPERATURE_FAHRENHEIT;
		} else if(sensorType.equals("accelerometer")) {
			sensorTypeNum = Sensor.TYPE_ACCELEROMETER;
		} else if(sensorType.equals("digitalTilt")) {
			sensorTypeNum = Sensor.TYPE_DIGITAL_TILT;
		} else if(sensorType.equals("digitalVibration")) {
			sensorTypeNum = Sensor.TYPE_DIGITAL_VIBRATION;
		} else if(sensorType.equals("infraredMotion")) {
			sensorTypeNum = Sensor.TYPE_INFRARED_MOTION;
		} else if(sensorType.equals("touch")) {
			sensorTypeNum = Sensor.TYPE_TOUCH;
		} 
		return sensorTypeNum;
	}

}
