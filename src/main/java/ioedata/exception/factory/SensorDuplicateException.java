package ioedata.exception.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SensorDuplicateException extends DuplicateException {
	Log log = LogFactory.getFactory().getInstance(getClass());

	public SensorDuplicateException() {
		super();
		log.error("SensorDuplicateException: check whether the sensor name is in use.");
	}

	public SensorDuplicateException(String message) {
		super(message);
	}
}
