package ioedata.actuator.repository;

import java.io.Serializable;

import ioedata.mongodb.repository.BaseRepository;
/**
 * This interface provides abstract methods specifically for actuator to access MongoDB.
 * It will be implemented by ActuatorRepositoryImpl class.
 * @author ajou
 *
 * @param <T>
 * @param <ID>
 */
public interface ActuatorRepository<T, ID extends Serializable> extends BaseRepository<T, ID> {

}
