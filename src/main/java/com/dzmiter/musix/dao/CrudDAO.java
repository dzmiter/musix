package com.dzmiter.musix.dao;

import java.io.Serializable;
import java.util.List;

/**
 * CRUD interface
 * 
 * @author Dzmiter
 */
public interface CrudDAO {

	/**
	 * Creates/updates a new object for the given type.
	 * 
	 * @param <T>
	 *            Entity class
	 * @param t
	 *            entity
	 * @return persisted Object
	 */
	<T> T merge(T t);

	/**
	 * Delete object by id
	 * 
	 * @param <T>
	 * @param <PK>
	 * @param type
	 *            , entity class type
	 * @param id
	 */
	<T, PK extends Serializable> void delete(Class<T> type, PK id);

	/**
	 * Find object by id
	 * 
	 * @param <T>
	 * @param <PK>
	 * @param type
	 * @param id
	 * @return the object
	 */
	<T, PK extends Serializable> T find(Class<T> type, PK id);

	/**
	 * List of objects
	 * 
	 * @param type
	 * @param <T>
	 * @return
	 */
	<T> List<T> list(Class<T> type);
	
	/**
	 * Top list of objects
	 * 
	 * @param type
	 * @param amount
	 * @param field
	 * @return
	 */
	<T> List<T> topList(Class<T> type, int amount, String field);
	
}