package ioedata.actuator.repository;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;

import ioedata.actuator.model.ActuatorValue;
import ioedata.mongodb.repository.BaseRepository;
/**
 * This interface provides abstract methods specifically for actuator to access MongoDB.
 * It will be implemented by ActuatorRepositoryImpl class.
 * @author ajou
 *
 * @param <T>
 * @param <ID>
 */
public interface ActuatorRepository<T, ID extends Serializable> extends BaseRepository<T, ID> {
	/*
	 * Get actuator information by actuator name and device serial number.
	 */
	public ActuatorValue findByActuatorNameAndDeviceSerialNum(String actuatorName, ObjectId deviceSerialNum);
	
	/*
	 * Get list of all actuators by device serial number.
	 */
	public List<ActuatorValue> findByDeviceSerialNum(ObjectId deviceSerialNum);
	
	/*
	 * Overloading isObjectExist method to check whether actuator exists by device serial number and actuator name.
	 */
	public boolean isObjectExist(ObjectId deviceSerialNum, String actuatorName);
}
