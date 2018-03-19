package com.ftonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.ftonline.entities.User;

@SpringBootApplication
@RestController
public class FtonlineApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(FtonlineApplication.class, args);
  	}
  
	@GetMapping(value="/")
  	public String hello( )
	{
		return "blabal";
	}
  
  
}