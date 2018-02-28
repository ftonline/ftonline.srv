package com.minyan.karov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.minyan.karov.entities.Synagogue;

@SpringBootApplication
@RestController
public class DemoApplication {
	
	@Autowired
	HttpService httpService;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
  	}
  
	@GetMapping("/")
  	public String hello()
	{
		try {
		// Instantiates a client
	    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

	    // The kind for the new entity
	    String kind = "Task";
	    // The name/ID for the new entity
	    String name = "sampletask1";
	    // The Cloud Datastore key for the new entity
	    Key taskKey = datastore.newKeyFactory().setKind(kind).newKey(name);

	    // Prepares the new entity
	    Entity task = Entity.newBuilder(taskKey)
	        .set("description", "Buy milk")
	        .build();

	    // Saves the entity
	    datastore.put(task);

	    System.out.printf("Saved %s: %s%n", task.getKey().getName(), task.getString("description"));

	    //Retrieve entity
	    Entity retrieved = datastore.get(taskKey);

	    System.out.printf("Retrieved %s: %s%n", taskKey.getName(), retrieved.getString("description"));
		}
		catch (Exception e) {
			return e.toString();
		}
		
		
		
		return "blabal";
	}
  
  
}