package ioedata.actuator.repository;

import java.util.List;

import ioedata.actuator.model.ActuatorValue;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
}
