package ioedata.device.repository;

import ioedata.device.model.DeviceValue;

import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;

@Repository
public class DeviceRepositoryImpl implements DeviceRepository<DeviceValue, ObjectId> {
	@Resource(name = "mongoTemplate")
	private MongoTemplate template;

	@Override
	public List<DeviceValue> findAllObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeviceValue findOneObject(ObjectId id) {
		return this.template.findOne(new Query(Criteria.where("_id").is(id)), DeviceValue.class);
	}

	@Override
	public void insertObject(DeviceValue deviceVal) {
		this.template.insert(deviceVal);
	}

	@Override
	public WriteResult updateObject(DeviceValue deviceVal) {
		Update update = new Update();
		update.addToSet("sensors", deviceVal.getSensors().get(0));
		return this.template.upsert(
				new Query(
						Criteria.where("_id").is(deviceVal.getDeviceSerialNum())), update, DeviceValue.class);
						
	}

	@Override
	public void deleteObject(ObjectId id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isObjectExist(ObjectId id) {
		return this.template.exists(new Query(Criteria.where("_id").is(id)), 
												DeviceValue.class);
	}

	@Override
	public boolean isObjectExist(int userSerialNum, String deviceName) {
		return this.template.exists(new Query(Criteria.where("userSerialNum")
																			.is(userSerialNum)
																			.and("deviceName")
																			.is(deviceName)), 
												DeviceValue.class);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DeviceValue findByDeviceNameAndUserSerialNum(String deviceName, int userSerialNum) {
		return (DeviceValue) this.template.findOne(
				new Query(Criteria.where("deviceName").is(deviceName)
											.and("userSerialNum").is(userSerialNum)), 
								DeviceValue.class);
	}

}
