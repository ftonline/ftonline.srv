package com.minyan.karov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.minyan.karov.dao.DatastoreDao;
import com.minyan.karov.entities.Synagogue;

@SpringBootApplication
@RestController
public class DemoApplication {
	
	@Autowired
	HttpService httpService;
	
	
	@Autowired
	DatastoreDao datastoreDao;
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
  	}
  
	@GetMapping("/")
  	public String hello()
	{
		try 
		{
			Synagogue s = new Synagogue();
			s.setSynagogueId("tttttt");
			s.setSynagogueName("אוהל בלהה");
			datastoreDao.create(s);
		}
		catch (Exception e) {
			String str = e.toString() + "<br/>";
			for (StackTraceElement stackTraceElement : e.getStackTrace())
			{
				str = str + stackTraceElement.toString() + "<br/>";
			}
			return str;
		}
		
		
		
		return "blabal";
	}
  
  
}