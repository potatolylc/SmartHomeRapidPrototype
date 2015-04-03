package ioedata.sensor.repository;

import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;

import ioedata.sensor.model.SensorValue;

@Repository
public class SensorRepositoryImpl implements SensorRepository<SensorValue, ObjectId> {
	@Resource(name = "mongoTemplate")
	private MongoTemplate template;

	@Override
	public List<SensorValue> findAllObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SensorValue findOneObject(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertObject(SensorValue sensorVal) {
		this.template.insert(sensorVal);
	}

	@Override
	public WriteResult updateObject(SensorValue sensorVal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteObject(ObjectId id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isObjectExist(ObjectId id) {
		this.template.exists(new Query(
											Criteria.where("_id").is(id)), 
									SensorValue.class);
		return false;
	}

	@Override
	public boolean isObjectExist(ObjectId deviceSerialNum, String sensorName) {
		return this.template.exists(new Query(Criteria.where("deviceSerialNum")
																			.is(deviceSerialNum)
																			.and("sensorName")
																			.is(sensorName)), 
												SensorValue.class);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SensorValue findBySensorNameAndDeviceSerialNum(String sensorName,
			ObjectId deviceSerialNum) {
		return this.template.findOne(
						new Query(Criteria.where("sensorName")
												.is(sensorName)
												.and("deviceSerialNum")
												.is(deviceSerialNum)),
						SensorValue.class);
	}
}
