package ioedata.sensordata.service;

import ioedata.device.service.DeviceService;
import ioedata.exception.factory.SensorNotExistException;
import ioedata.sensor.model.SensorValue;
import ioedata.sensor.service.SensorService;
import ioedata.sensordata.model.SensorDataValue;
import ioedata.sensordata.repository.DataDao;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

/**
 * This class provides services about sensor data. It implements DataService
 * interface.
 * 
 * @author ajou
 */
@Service
public class DataServiceImpl implements DataService {
	@Resource(name = "deviceServiceImpl")
	private DeviceService deviceService;
	@Resource(name = "sensorServiceImpl")
	private SensorService sensorService;
	@Resource(name = "dataDaoImpl")
	private DataDao dataDao;

	@Override
	public boolean storeSensorData(String deviceSerialNum, String sensorName,
			double sensorDataValue) throws SensorNotExistException {
		// Check whether sensor exists
		boolean isSensorExistFlag = this.sensorService.isSensorExist(
				new ObjectId(deviceSerialNum), sensorName);
		// If sensor doesn't exist
		if (!isSensorExistFlag) {
			throw new SensorNotExistException();
		}
		// If sensor exists
		String sensorSerialNumStr = this.sensorService
				.retrieveSensorSerialNumBySensorNameAndDeviceSerialNum(
						sensorName, new ObjectId(deviceSerialNum)).toString();
		int result = this.dataDao.insertSensorData(new SensorDataValue(
				sensorDataValue, new SensorValue(sensorSerialNumStr)));
		return result > 0 ? true : false;
	}

}