package ioedata.geolocation.repository;

import java.io.Serializable;

import ioedata.mongodb.repository.BaseRepository;

/**
 * This interface provides abstract methods specifially for geospatial location to access MongoDB.
 * It will be implemented by GeoRepositoryImpl class.
 * @author ajou
 *
 */
public interface GeoLocationRepository<T, ID extends Serializable> extends BaseRepository<T, ID> {

}
