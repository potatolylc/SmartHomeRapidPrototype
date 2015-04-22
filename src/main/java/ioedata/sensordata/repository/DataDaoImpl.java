package ioedata.sensordata.repository;

import ioedata.sensor.model.SensorValue;
import ioedata.sensordata.model.SensorDataValue;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/**
 * This class helps access to sensor data database to manipulate data storage.
 * It implements DataDao interface.
 * 
 * @author ajou
 * 
 */
@Repository
public class DataDaoImpl implements DataDao {
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate template;

	@Override
	public int insertSensorData(SensorDataValue dataVal) {
		return this.template.insert("sensordata.insertSensorData", dataVal);
	}

	@Override
	public List<SensorDataValue> getDataListBySensorSerialNumAndStartTimeAndEndTime(
			SensorDataValue sensorVal) {
		return this.template
				.selectList(
						"sensordata.getDataListBySensorSerialNumAndStartTimeAndEndTime",
						sensorVal);
	}

	@Override
	public SensorDataValue getLastDataByDeviceIdSortedByTimestamp(
			String deviceId) throws Exception {
		return this.template.selectOne(
				"data.getLastDataByDeviceIdSortedByTimestamp", deviceId);
	}

	@Override
	public SensorDataValue getLastDataByDeviceIdAndSensorTypeSortedByTimestamp(
			SensorValue sensorVal) throws Exception {
		return this.template.selectOne(
				"data.getLastDataByDeviceIdAndSensorTypeSortedByTimestamp",
				sensorVal);
	}

	@Override
	public List<SensorDataValue> getDataListByDeviceIdAndSensorTypeAndDateSortedByTimestamp(
			SensorDataValue dataVal) throws Exception {
		return this.template
				.selectList(
						"data.getDataListByDeviceIdAndSensorTypeAndDateSortedByTimestamp",
						dataVal);
	}

	@Override
	public SensorDataValue getGreatestDataByDeviceIdAndSensorType(
			SensorValue sensorVal) throws Exception {
		return this.template.selectOne(
				"data.getGreatestDataByDeviceIdAndSensorType", sensorVal);
	}

	@Override
	public SensorDataValue getLeastDataByDeviceIdAndSensorType(
			SensorValue sensorVal) throws Exception {
		return this.template.selectOne(
				"data.getLeastDataByDeviceIdAndSensorType", sensorVal);
	}

}