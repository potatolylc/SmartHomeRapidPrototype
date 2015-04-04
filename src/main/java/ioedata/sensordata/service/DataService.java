package ioedata.sensordata.service;

import java.util.Map;

import org.bson.types.ObjectId;

import ioedata.exception.factory.DeviceNotExistException;
import ioedata.exception.factory.SensorNotExistException;


/**
 * This interface provides methods to both access and manipulate data in the sensor database and collect and control sensor end devices.
 * @author ajou
 */
public interface DataService {
	/*
	 * Insert sensor data into cloud server.
	 * First check whether the sensor with a typical sensor name exists.
	 * If it does, return true; if not, return false.
	 */
	public boolean storeSensorData(String deviceSerialNum, String sensorName, double sensorDataValue) throws SensorNotExistException, DeviceNotExistException;
	
	public boolean storeSensorData(ObjectId deviceSerialNum, Map<String , Object> sensorDataPairs) throws DeviceNotExistException, SensorNotExistException;
}