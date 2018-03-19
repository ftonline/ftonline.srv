package com.ftonline.service;


import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ftonline.dao.DatastoreDao;
import com.ftonline.entities.GoogleTokenClaims;
import com.ftonline.entities.User;

@RestController
public class UserService 
{
	@Autowired
	private GoogleSignInService googleSignInService;
	
	
	@Autowired
	@Qualifier("datastoreCloud")
	DatastoreDao datastoreDao;
	
	
	@PostMapping(value="/user/retrieveUser", consumes="application/json")
  	public User psotUser(@RequestBody UserServiceInput userServiceInput)
	{
		String googleTokenId = userServiceInput.getTokenId();
		User user = retrieveUser(googleTokenId);
		return user;
	}
	
	
	@GetMapping(value="/user/getUser/{userId}")
	public User getUser(@PathVariable("userId") String userId)
	{
		Entry<String, String> pair = new AbstractMap.SimpleEntry<String, String>("userId", userId);
		List <User> users = datastoreDao.getEntity(User.class, pair);
		if (users.size() > 0)
		{
			return users.get(0);
		}
		return null;
	}
	
	
	private User retrieveUser(String googleTokenId)
	{
		try 
		{
			GoogleTokenClaims googleTokenClaims = googleSignInService.retriveGoogleTokenClaims(googleTokenId);
			if(!googleTokenClaims.getAud().equals("566387118967-mj1o2jd61ra475g3ql29qkvrv6htrh7m.apps.googleusercontent.com"))
			{
				throw new Exception("the client id is wrong");
			}
			User user = new User();
			user.setUserId(googleTokenClaims.getSub());
			user.setFirstName(googleTokenClaims.getGiven_name());
			user.setLastName(googleTokenClaims.getFamily_name());
			user.setMail(googleTokenClaims.getEmail());
			
			datastoreDao.create(user);
			return user;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		
	}
}
