package ioedata.actuator.factory;

/**
 * Create ActuatorManager instances.
 * Parse actuator type between String and number.
 * @author ajou
 *
 */
public class ActuatorManager {
	/*
	 * Singleton pattern for creating ActuatorManager instances.
	 */
	private static ActuatorManager actuatorManager = new ActuatorManager();
	public static ActuatorManager getActuatorManager() {
		return actuatorManager;
	}
	
	/**
	 * Parse the actuator type name to get actuator type number.
	 * @param actuatorType
	 * @return
	 */
	public int getActuatorTypeNum(String actuatorType) {
		int actuatorTypeNum = -1;
		if(actuatorType.equals("lightBrightness")) {
			actuatorTypeNum = Actuator.ACTUATOR_TYPE_LIGHT_BRIGHTNESS;
		} else if(actuatorType.equals("temperatureCel")) {
			actuatorTypeNum = Actuator.ACTUATOR_TYPE_TEMPERATURE_CELSIUS;
		} else if(actuatorType.equals("temperatureFah")) {
			actuatorTypeNum = Actuator.ACTUATOR_TYPE_TEMPERATURE_FAHRENHEIT;
		} else if(actuatorType.equals("humidity")) {
			actuatorTypeNum = Actuator.ACTUATOR_TYPE_HUMIDITY;
		} else if(actuatorType.equals("tvVolume")) {
			actuatorTypeNum = Actuator.ACTUATOR_TYPE_TV_VOLUME;
		} else if(actuatorType.equals("tvChannel")) {
			actuatorTypeNum = Actuator.ACTUATOR_TYPE_TV_CHANNEL;
		}
		return actuatorTypeNum;
	}
	
	/**
	 * Get actuator type by actuator type number.
	 * @param actuatorTypeNum
	 * @return
	 */
	public String getActuatorType(int actuatorTypeNum) {
		String actuatorType = null;
		switch (actuatorTypeNum) {
		case Actuator.ACTUATOR_TYPE_LIGHT_BRIGHTNESS:
			actuatorType = "lightBrightness";
			break;
		case Actuator.ACTUATOR_TYPE_TEMPERATURE_CELSIUS:
			actuatorType = "temperatureCel";
			break;
		case Actuator.ACTUATOR_TYPE_TEMPERATURE_FAHRENHEIT:
			actuatorType = "temperatureFah";
			break;
		case Actuator.ACTUATOR_TYPE_HUMIDITY:
			actuatorType = "humidity";
			break;
		case Actuator.ACTUATOR_TYPE_TV_VOLUME:
			actuatorType = "tvVolume";
			break;
		case Actuator.ACTUATOR_TYPE_TV_CHANNEL:
			actuatorType = "tvChannel";
			break;
		default:
			break;
		}
		return actuatorType;
	}
}
