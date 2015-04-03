package ioedata.device.repository;

import ioedata.device.model.DeviceValue;
import ioedata.mongodb.repository.BaseRepository;

import java.io.Serializable;

/**
 * This interface provides abstract methods specifically for device to access MongoDB.
 * It will be implemented by DeviceRepositoryImpl class. 
 * @author ajou
 *
 * @param <T>
 * @param <ID>
 */
public interface DeviceRepository<T, ID extends Serializable> extends BaseRepository<T, ID> {
	/*
	 * Get device information by device name and user serial number.
	 */
	public DeviceValue findByDeviceNameAndUserSerialNum(String deviceName, int userSerialNum);

	/*
	 * Overload isObjectExist method to check whether device exists by user serial number and device name.
	 */
	public boolean isObjectExist(int userSerialNum, String deviceName);
}
