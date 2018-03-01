package com.minyan.karov.dao;

import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.Entity.Builder;

@Repository
public class DatastoreCloud extends DatastoreDao
{
	private Datastore datastore;
	
	
	public DatastoreCloud() 
	{
		datastore = DatastoreOptions.getDefaultInstance().getService();
	}
	
	
	@Override
	protected void put(EntityObject entityObject)
	{
		Key taskKey = datastore.newKeyFactory().setKind(entityObject.getEntityName()).newKey(entityObject.getId());
		Builder builder = Entity.newBuilder(taskKey);
		
		Entity task = populateFields(entityObject, builder);
		datastore.put(task);	
	}
	
	
	private Entity populateFields(EntityObject entityObject, Builder builder)
	{
		for (Entry<String, Object> entry : entityObject.getProperties().entrySet())
		{
			builder.set(entry.getKey(), (String) entry.getValue());
		}
		return builder.build();
	}
}
