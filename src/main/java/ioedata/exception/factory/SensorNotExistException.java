package ioedata.exception.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SensorNotExistException extends NotExistException {
	Log log = LogFactory.getFactory().getInstance(getClass());

	public SensorNotExistException() {
		super();
		log.error("SensorNotExistException: sensor does not exist.");
	}

	public SensorNotExistException(String message) {
		super(message);
	}
}
