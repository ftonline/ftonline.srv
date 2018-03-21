package com.ftonline.service.user;

import com.ftonline.entities.User;

public class UpdateUserInput
{
	private String tokenId;
	
	private User user;

	public String getTokenId() 
	{
		return tokenId;
	}

	public void setTokenId(String tokenId) 
	{
		this.tokenId = tokenId;
	}

	public User getUser() 
	{
		return user;
	}

	public void setUser(User user) 
	{
		this.user = user;
	}
}
