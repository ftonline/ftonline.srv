package com.minyan.karov.dao;

import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@Repository
public class DatastoreApi extends DatastoreDao
{
	private DatastoreService datastore;
	
	
	public DatastoreApi() 
	{
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	
	@Override
	protected void put(EntityObject entityObject)
	{
		Entity post = populateFields(entityObject);
		datastore.put(post);	
	}
	
	
	private Entity populateFields(EntityObject entityObject)
	{
		Entity entity = new Entity(entityObject.getEntityName());
		
		for (Entry<String, Object> entry : entityObject.getProperties().entrySet())
		{
			entity.setProperty(entry.getKey(), entry.getValue());
		}
		return entity;
	}
}
