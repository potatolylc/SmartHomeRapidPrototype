package ioedata.actuator.repository;

import java.util.List;

import ioedata.actuator.model.ActuatorValue;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;

@Repository
public class ActuatorRepositoryImpl implements
		ActuatorRepository<ActuatorValue, ObjectId> {
	@Resource(name = "mongoTemplate")
	private MongoTemplate template;

	@Override
	public List<ActuatorValue> findAllObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActuatorValue findOneObject(ObjectId id) {
		return this.template.findOne(new Query(Criteria.where("_id").is(id)),
				ActuatorValue.class);
	}

	@Override
	public void insertObject(ActuatorValue actuatorVal) {
		this.template.insert(actuatorVal);
	}

	@Override
	public WriteResult updateObject(ActuatorValue actuatorVal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteObject(ObjectId id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isObjectExist(ObjectId id) {
		return this.template.exists(new Query(Criteria.where("_id").is(id)),
				ActuatorValue.class);
	}

	@Override
	public boolean isObjectExist(ObjectId deviceSerialNum, String actuatorName) {
		return this.template.exists(new Query(Criteria.where("deviceSerialNum")
				.is(deviceSerialNum).and("actuatorName").is(actuatorName)),
				ActuatorValue.class);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ActuatorValue findByActuatorNameAndDeviceSerialNum(
			String actuatorName, ObjectId deviceSerialNum) {
		return this.template.findOne(new Query(Criteria.where("actuatorName")
				.is(actuatorName).and("deviceSerialNum").is(deviceSerialNum)),
				ActuatorValue.class);
	}

	@Override
	public List<ActuatorValue> findByDeviceSerialNum(ObjectId deviceSerialNum) {
		return this.template.find(new Query(Criteria.where("deviceSerialNum")
				.is(deviceSerialNum)), ActuatorValue.class);
	}

}
