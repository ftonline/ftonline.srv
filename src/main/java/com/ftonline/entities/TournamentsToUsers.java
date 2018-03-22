package com.ftonline.entities;

import javax.persistence.Column;
import javax.persistence.Id;

public class TournamentsToUsers
{
	@Column
	private String tournamentsId;

	@Column
	private String userId;
	
	public String getTournamentsId() 
	{
		return tournamentsId;
	}

	public void setTournamentsId(String tournamentsId) 
	{
		this.tournamentsId = tournamentsId;
	}

	public String getUserId() 
	{
		return userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	
	@Id
	public String getId() 
	{
		return getUserId() + "__" + getTournamentsId();
	}
	
	
}
