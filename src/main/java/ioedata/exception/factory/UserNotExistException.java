package ioedata.exception.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserNotExistException extends NotExistException {
	Log log = LogFactory.getFactory().getInstance(getClass());

	public UserNotExistException() {
		super();
		log.error("UserNotExistException: user may have not been registered yet.");
	}

	public UserNotExistException(String message) {
		super(message);
	}
}
