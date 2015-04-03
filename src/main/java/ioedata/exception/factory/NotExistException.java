package ioedata.exception.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NotExistException extends BaseException {
	Log log = LogFactory.getFactory().getInstance(getClass());

	public NotExistException() {
		super();
		log.error("NotExistException");
	}

	public NotExistException(String message) {
		super(message);
	}

}
