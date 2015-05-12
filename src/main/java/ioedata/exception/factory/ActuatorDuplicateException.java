package ioedata.exception.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ActuatorDuplicateException extends DuplicateException {
	Log log = LogFactory.getFactory().getInstance(getClass());

	public ActuatorDuplicateException() {
		super();
		log.error("ActuatorDuplicateException: check whether the actuator name is in use.");
	}

	public ActuatorDuplicateException(String message) {
		super(message);
	}
}
