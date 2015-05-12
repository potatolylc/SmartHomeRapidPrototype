package ioedata.exception.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ActuatorNotExistException extends NotExistException {
	Log log = LogFactory.getFactory().getInstance(getClass());

	public ActuatorNotExistException() {
		super();
		log.error("ActuatorNotExistException: actuator does not exist.");
	}

	public ActuatorNotExistException(String message) {
		super(message);
	}
}
