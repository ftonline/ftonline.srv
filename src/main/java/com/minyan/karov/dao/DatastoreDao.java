package com.minyan.karov.dao;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Id;



public abstract class DatastoreDao 
{	
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
		String id = (String) fieldId.get(obj);
		
		EntityObject entityObject = new EntityObject();
		entityObject.setEntityName(entityName);
		entityObject.setId(id);
		
		populateFields(obj, entityObject);
		put(entityObject);     
	}
	
	
	protected abstract void put(EntityObject entityObject);
	
	
	private void populateFields(Object obj, EntityObject entityObject) throws IllegalArgumentException, IllegalAccessException
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
					entityObject.setProperty(columnName, value);
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
