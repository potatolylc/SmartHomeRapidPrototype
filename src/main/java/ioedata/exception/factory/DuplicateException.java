package ioedata.exception.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DuplicateException extends BaseException {
	Log log = LogFactory.getFactory().getInstance(getClass());
	
	public DuplicateException() {
		super();
		log.error("DuplicateException!");
	}

	public DuplicateException(String message) {
		super(message);
	}

}
