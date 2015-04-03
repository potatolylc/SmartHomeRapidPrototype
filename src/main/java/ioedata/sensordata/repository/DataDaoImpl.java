package ioedata.sensordata.repository;

import ioedata.sensor.model.SensorValue;
import ioedata.sensordata.model.DataValue;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/**
 * This class helps access to sensor data database to manipulate data storage.
 * It implements DataDao interface.
 * @author ajou
 *
 */
@Repository
public class DataDaoImpl implements DataDao {
	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public DataValue getLastDataByDeviceIdSortedByTimestamp(String deviceId) throws Exception {
		return this.template.selectOne("data.getLastDataByDeviceIdSortedByTimestamp", deviceId);
	}

	@Override
	public DataValue getLastDataByDeviceIdAndSensorTypeSortedByTimestamp(
			SensorValue sensorVal) throws Exception {
		return this.template.selectOne("data.getLastDataByDeviceIdAndSensorTypeSortedByTimestamp", sensorVal);
	}

	@Override
	public int insertSensorData(DataValue dataVal) throws Exception {
		return this.template.insert("data.insertSensorData", dataVal);
	}

	@Override
	public List<DataValue> getDataListByDeviceIdAndSensorTypeAndDateSortedByTimestamp(
			DataValue dataVal) throws Exception {
		return this.template.selectList("data.getDataListByDeviceIdAndSensorTypeAndDateSortedByTimestamp", dataVal);
	}

	@Override
	public DataValue getGreatestDataByDeviceIdAndSensorType(
			SensorValue sensorVal) throws Exception {
		return this.template.selectOne("data.getGreatestDataByDeviceIdAndSensorType", sensorVal);
	}

	@Override
	public DataValue getLeastDataByDeviceIdAndSensorType(SensorValue sensorVal)
			throws Exception {
		return this.template.selectOne("data.getLeastDataByDeviceIdAndSensorType", sensorVal);
	}
}