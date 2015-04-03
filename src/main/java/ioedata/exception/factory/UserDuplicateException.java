package ioedata.exception.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserDuplicateException extends DuplicateException {
	Log log = LogFactory.getFactory().getInstance(getClass());

	public UserDuplicateException() {
		super();
		log.error("UserDuplicateException: check whether the user has been registered.");
	}

	public UserDuplicateException(String message) {
		super(message);
	}
}
