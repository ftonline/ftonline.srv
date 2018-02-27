package com.minyan.karov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

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
  	public String hello() throws Exception {
		Synagogue s = new Synagogue();
		s.setAddress("sdfdfgdfg");
		return httpService.sendGet();
	}
  
  
}