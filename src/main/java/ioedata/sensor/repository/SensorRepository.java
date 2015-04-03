package ioedata.sensor.repository;

import ioedata.mongodb.repository.BaseRepository;
import ioedata.sensor.model.SensorValue;

import java.io.Serializable;

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
	/*
	 * Get sensor information by sensor name and device serial number.
	 */
	public SensorValue findBySensorNameAndDeviceSerialNum(String sensorName, ObjectId deviceSerialNum); 
	
	/*
	 * Overloading isObjectExist method to check whether sensor exists by device serial number and sensor name.
	 */
	public boolean isObjectExist(ObjectId deviceSerialNum, String sensorName);
}
