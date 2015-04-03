package ioedata.exception.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DeviceDuplicateException extends DuplicateException {
	Log log = LogFactory.getFactory().getInstance(getClass());

	public DeviceDuplicateException() {
		super();
		log.error("DeviceNameDuplicateException: check whether the device name is in use.");
	}

	public DeviceDuplicateException(String message) {
		super(message);
	}
}
