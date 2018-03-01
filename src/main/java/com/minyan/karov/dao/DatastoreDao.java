package com.minyan.karov.dao;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;


@Repository
public class DatastoreDao 
{
	private DatastoreService datastore;
	
	
	public DatastoreDao() 
	{
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	
	public void create(Object obj) throws IllegalArgumentException, IllegalAccessException
	{
		javax.persistence.Entity entity = obj.getClass().getAnnotation(javax.persistence.Entity.class);
		if (entity == null)
		{
			return;
		}
		String entityName = entity.name();
		
		Field fieldId = getFieldId(obj);
		fieldId.setAccessible(true);		
		
		Entity post = new Entity(entityName);
		
		populateFields(obj, post);
		
		datastore.put(post);			        
	}
	
	
	private void populateFields(Object obj, Entity entity) throws IllegalArgumentException, IllegalAccessException
	{
		for (Field f : obj.getClass().getDeclaredFields())
		{
			Column column = f.getAnnotation(Column.class);
			if (column != null)
			{
				String columnName = column.name() != null && !column.name().trim().isEmpty() ? column.name() : f.getName();
				f.setAccessible(true);
				String value = (String) f.get(obj);
				if (value != null)
				{
					entity.setProperty(columnName, value);
				}
			}
		}
	}
	
	
	private Field getFieldId(Object obj)
	{
		for (Field f : obj.getClass().getDeclaredFields()) 
		{
			Id id = f.getAnnotation(Id.class);
			if (id != null)
			{
				return f;
			}
		}
		return null;
	}	
}
