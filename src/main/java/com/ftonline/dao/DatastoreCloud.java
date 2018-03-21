package com.ftonline.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.Entity.Builder;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.GqlQuery;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;

@Repository
public class DatastoreCloud extends DatastoreDao
{
	private Datastore datastore;
	
	
	public DatastoreCloud() 
	{
		datastore = DatastoreOptions.getDefaultInstance().getService();
	}
	
	
	@Override
	protected void put(List<EntityObject> entityObjects)
	{
		List <FullEntity<?>> tasks = getTasksFromEntityObjects(entityObjects);
		datastore.put(tasks.toArray(new FullEntity[tasks.size()]));	
	}
	
	
	List <FullEntity<?>> getTasksFromEntityObjects(List<EntityObject> entityObjects)
	{
		List <FullEntity<?>> tasks = new ArrayList<FullEntity<?>>();
		for (EntityObject entityObject : entityObjects)
		{
			Key taskKey = datastore.newKeyFactory().setKind(entityObject.getEntityName()).newKey(entityObject.getId());
			Builder builder = Entity.newBuilder(taskKey);
			
			Entity task = populateFields(entityObject, builder);
			tasks.add(task);
		}
		return tasks;
	}
	
	
	private Entity populateFields(EntityObject entityObject, Builder builder)
	{
		for (Entry<String, Object> entry : entityObject.getProperties().entrySet())
		{
			builder.set(entry.getKey(), (String) entry.getValue());
		}
		return builder.build();
	}
	
	
	@Override
	protected List<EntityObject> getEntityObject(String entityName, Entry<String,String> ... pair)
	{
		List<EntityObject> entityObjects = new ArrayList<>();
		
		String q = "SELECT * FROM " + entityName;
		
		if (pair.length > 0)
		{
			q = q + " WHERE ";
		}
		
		boolean startQ = true;
		int i = 0;
		for (Entry<String,String> p : pair)
		{
			i++;
			if (!startQ)
			{
				q = q + " AND ";
			}
			startQ = false;
			q = q + p.getKey() + "=@par" + i;
		}
		
		i = 0;
		GqlQuery.Builder <?> builder = Query.newGqlQueryBuilder(q);
		
		for (Entry<String,String> p : pair)
		{
			i++;
			builder.setBinding("par" + i, p.getValue());
		}
		
		Query<?> query = builder.build(); 
		
		QueryResults <?> queryResults = datastore.run(query);
		
		while (queryResults.hasNext())
		{
			EntityObject entityObject = new EntityObject();
			entityObject.setEntityName(entityName);
			Entity entity = (Entity) queryResults.next();
			for (String fieldName : entity.getNames())
			{
				entityObject.setProperty(fieldName, entity.getString(fieldName));
			}
			entityObjects.add(entityObject);
		}
		
		return entityObjects;
	}
	
	@Override
	public void update(List<EntityObject> entityObjects)
	{
		List <FullEntity<?>> tasks = getTasksFromEntityObjects(entityObjects);
		
		List<Entity> entities = new ArrayList<>();
		
		for (FullEntity<?> fullEntity : tasks) 
		{
			entities.add((Entity) fullEntity);
		}
		
		datastore.put(tasks.toArray(new FullEntity[tasks.size()]));
	}
}
