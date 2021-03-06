package ioedata.device.repository;

import ioedata.device.model.DeviceValue;
import ioedata.geolocation.model.GeoCoordinate;
import ioedata.mongodb.repository.BaseRepository;

import java.io.Serializable;
import java.util.List;

import com.mongodb.WriteResult;

/**
 * This interface provides abstract methods specifically for device to access MongoDB.
 * It will be implemented by DeviceRepositoryImpl class. 
 * @author ajou
 *
 * @param <T>
 * @param <ID>
 */
public interface DeviceRepository<T, ID extends Serializable> extends BaseRepository<T, ID> {
	/**
	 * Get device information by device name and user serial number.
	 * @param deviceName
	 * @param userSerialNum
	 * @return
	 */
	public DeviceValue findByDeviceNameAndUserSerialNum(String deviceName, int userSerialNum);

	/**
	 * Find all devices with a circle geospatial area using a coordinate.
	 * @param geoCoordinate
	 * @param distance
	 * @return
	 */
	public List<DeviceValue> findByGeoCoordinate(GeoCoordinate geoCoordinate, int distance);

	/**
	 * Overload isObjectExist method to check whether device exists by user serial number and device name.
	 * @param userSerialNum
	 * @param deviceName
	 * @return
	 */
	public boolean isObjectExist(int userSerialNum, String deviceName);

	/**
	 * Update sensor information of a device.
	 * @param deviceVal
	 * @return
	 */
	public WriteResult updateDeviceSensors(DeviceValue deviceVal);

	/**
	 * Update actuator information of a device.
	 * @param deviceVal
	 * @return
	 */
	public WriteResult updateDeviceActuators(DeviceValue deviceVal);
	
}
