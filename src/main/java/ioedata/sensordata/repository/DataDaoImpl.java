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
	public double getAverageDataBySensorSerialNum(String sensorSerialNum) {
		return this.template.selectOne("sensordata.getAverageDataBySensorSerialNum", sensorSerialNum);
	}

	@Override
	public double getLatestDataBySensorSerialNum(String sensorSerialNum) {
		return this.template.selectOne("sensordata.getLatestDataBySensorSerialNum", sensorSerialNum);
	}


}