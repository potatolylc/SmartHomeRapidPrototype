package ioedata.actuator.factory;

/**
 * Define various actuators. 
 * @author ajou
 *
 */
public interface Actuator {
	/**
	 * Define actuator types.
	 */
	public final static int ACTUATOR_TYPE_ALL = 0;
	public final static int ACTUATOR_TYPE_LIGHT_BRIGHTNESS = 1;
	public final static int ACTUATOR_TYPE_TEMPERATURE_CELSIUS = 2;
	public final static int ACTUATOR_TYPE_TEMPERATURE_FAHRENHEIT = 3;
	public final static int ACTUATOR_TYPE_HUMIDITY = 4;
	public final static int ACTUATOR_TYPE_TV_VOLUME = 5;
	public final static int ACTUATOR_TYPE_TV_CHANNEL = 6;
}
