package ioedata.sensor.repository;

import ioedata.mongodb.repository.BaseRepository;
import ioedata.sensor.model.SensorValue;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
/**
 * This interface provides abstract methods specifically for sensor to access MongoDB.
 * It will be implemented by SensorRepositoryImpl class. 
 * @author ajou
 *
 * @param <T>
 * @param <ID>
 */
public interface SensorRepository<T, ID extends Serializable> extends BaseRepository<T, ID> {
	/**
	 * Get sensor information by sensor name and device serial number.
	 * @param sensorName
	 * @param deviceSerialNum
	 * @return
	 */
	public SensorValue findBySensorNameAndDeviceSerialNum(String sensorName, ObjectId deviceSerialNum); 
	
	/**
	 * Get list of all sensors by device serial number.
	 * @param deviceSerialNum
	 * @return
	 */
	public List<SensorValue> findByDeviceSerialNum(ObjectId deviceSerialNum);
	
	/**
	 * Overloading isObjectExist method to check whether sensor exists by device serial number and sensor name.
	 * @param deviceSerialNum
	 * @param sensorName
	 * @return
	 */
	public boolean isObjectExist(ObjectId deviceSerialNum, String sensorName);
}
