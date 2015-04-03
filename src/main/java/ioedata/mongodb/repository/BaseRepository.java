package ioedata.mongodb.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.mongodb.WriteResult;

/**
 * This interface is the base repository for accessing MongoDB.
 * @author ajou
 *
 * @param <T>
 * @param <ID>
 */
public interface BaseRepository<T, ID extends Serializable> 
		extends Repository<T, ID>{
	/*
	 * Find all objects of T type.
	 */
	public List<T> findAllObjects();
	/*
	 * Find object by a uniquely identifiable field.
	 */
	public T findOneObject(ID id);
	/*
	 * Save new object to database.
	 */
	public void insertObject(T object);
	/*
	 * Update an object.
	 */
	public WriteResult updateObject(T object);
	/*
	 * Delete an object.
	 */
	public void deleteObject(ID id);
	/*
	 * Check whether an object exists.
	 */
	public boolean isObjectExist(ID id);
	/*
	 * Get the number count of one collection.
	 */
	public long count();
}
