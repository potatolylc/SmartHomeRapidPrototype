package ioedata.exception.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DeviceNotExistException extends NotExistException {
	Log log = LogFactory.getFactory().getInstance(getClass());

	public DeviceNotExistException() {
		super();
		log.error("DeviceNotExistException: device does not exist.");
	}

	public DeviceNotExistException(String message) {
		super(message);
	}
}
