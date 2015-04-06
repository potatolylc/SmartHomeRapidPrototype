package ioedata.geolocation.repository;

import java.util.List;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;

import ioedata.geolocation.model.GeoLocationValue;

@Repository
public class GeoLocationRepositoryImpl implements GeoLocationRepository<GeoLocationValue, ObjectId> {
	@Resource(name = "mongoTemplate")
	private MongoTemplate template;
	
	@Override
	public List<GeoLocationValue> findAllObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeoLocationValue findOneObject(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertObject(GeoLocationValue geoLocationVal) {
		this.template.insert(geoLocationVal);
	}

	@Override
	public WriteResult updateObject(GeoLocationValue geoLocationVal) {
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
