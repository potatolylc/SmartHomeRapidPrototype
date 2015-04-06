package ioedata.weather.repository;

import java.util.List;

import javax.annotation.Resource;

import ioedata.weather.model.WeatherValue;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository<WeatherValue, ObjectId> {
	@Resource(name = "mongoTemplate")
	private MongoTemplate template;

	@Override
	public List<WeatherValue> findAllObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WeatherValue findOneObject(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertObject(WeatherValue weatherVal) {
		this.template.insert(weatherVal);
	}

	@Override
	public WriteResult updateObject(WeatherValue weatherVal) {
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
