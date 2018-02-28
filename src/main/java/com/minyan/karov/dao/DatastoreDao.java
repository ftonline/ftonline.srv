package com.minyan.karov.dao;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.Entity.Builder;

public class DatastoreDao 
{
	private Datastore datastore;
	
	
	public DatastoreDao() 
	{
		datastore = DatastoreOptions.getDefaultInstance().getService();
	}
	
	
	public void create(Object obj)
	{
		try
		{
			Entity entity = obj.getClass().getAnnotation(Entity.class);
			if (entity == null)
			{
				return;
			}
			String entityName = entity.name();
			
			Field fieldId = getFieldId(obj);
			fieldId.setAccessible(true);
			String id = (String) fieldId.get(obj);
			
			Key taskKey = datastore.newKeyFactory().setKind(entityName).newKey(id);
			
			Builder builder = com.google.cloud.datastore.Entity.newBuilder(taskKey);
			
			populateFields(obj, builder);
			
			com.google.cloud.datastore.Entity task = builder.build();
			datastore.put(task);			        
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void populateFields(Object obj, Builder builder)
	{
		try
		{
			for (Field f : obj.getClass().getDeclaredFields())
			{
				Column column = f.getAnnotation(Column.class);
				if (column != null)
				{
					String columnName = column.name() != null && !column.name().trim().isEmpty() ? column.name() : f.getName();
					f.setAccessible(true);
					String value = (String) f.get(obj);
					builder.set(columnName, value);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
