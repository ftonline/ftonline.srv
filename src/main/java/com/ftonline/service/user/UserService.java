package com.ftonline.service.user;


import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

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
	DatastoreDao datastoreDao;
	
	
	@PostMapping(value="/user/retrieveUser", consumes="application/json")
  	public PsotUserOutput psotUser(@RequestBody PsotUserInput psotUserInput)
	{
		PsotUserOutput psotUserOutput = new PsotUserOutput();
		try 
		{
			String googleTokenId = psotUserInput.getTokenId();
			User user = retrieveUser(googleTokenId);
			psotUserOutput.setUser(user);
			return psotUserOutput;
		} 
		catch (Exception e) 
		{
			psotUserOutput.addThrowable(e);
			return psotUserOutput;
		}
	}
	
	
	@PostMapping(value="/user/updateUser", consumes="application/json")
	public UpdateUserOutput updateUser(@RequestBody UpdateUserInput updateUserInput)
	{
		UpdateUserOutput updateUserOutput = new UpdateUserOutput();
		try
		{
			User user = updateUserInput.getUser();
			User userToUpdate = retrieveUser(updateUserInput.getTokenId());
			userToUpdate.setFifaUser(user.getFifaUser());
			userToUpdate.setFirstName(user.getFirstName());
			userToUpdate.setLastName(user.getLastName());
			userToUpdate.setPhone(user.getPhone());
			datastoreDao.update(userToUpdate);
		}
		catch (Exception e) 
		{
			updateUserOutput.addThrowable(e);
		}
		return updateUserOutput;
	}
	
	
	@GetMapping(value="/user/getUser/{userId}")
	public User getUser(@PathVariable("userId") String userId)
	{
		Entry<String, String> pair = new AbstractMap.SimpleEntry<String, String>("userId", userId);
		List<User> users;
		
		users = datastoreDao.getEntity(User.class, pair);
	
		if (users.size() > 0)
		{
			return users.get(0);
		}
		return null;
	}
	
	
	public User retrieveUser(String googleTokenId) throws Exception
	{
		GoogleTokenClaims googleTokenClaims = googleSignInService.retriveGoogleTokenClaims(googleTokenId);
		if(!googleTokenClaims.getAud().equals("566387118967-mj1o2jd61ra475g3ql29qkvrv6htrh7m.apps.googleusercontent.com"))
		{
			throw new Exception("the client id is wrong");
		}
		
		User user = getUser(googleTokenClaims.getSub());
		
		if (user != null)
		{
			return user;
		}
		
		user = new User();
		user.setUserId(googleTokenClaims.getSub());
		user.setFirstName(googleTokenClaims.getGiven_name());
		user.setLastName(googleTokenClaims.getFamily_name());
		user.setMail(googleTokenClaims.getEmail());
		
		datastoreDao.create(user);
		return user;
	}
	
	
	
	class  PsotUserOutput extends ServiceOutput
	{
		private User user;
		
		public void setUser(User user)
		{
			this.user = user;
		}
		
		public User getUser()
		{
			return this.user;
		}
	}
	
	
	class UpdateUserOutput extends ServiceOutput
	{
		
	}
}
