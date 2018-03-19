package com.ftonline.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "User")
public class User 
{
	@Column
	private String userId;
	
	
	@Column
	private String mail;
	

	@Column
	private String firstName;
	

	@Column
	private String lastName;
	

	@Column
	private String phone;
	

	@Column
	private String fifaUser;
	

	@Column
	private String skills;


	public String getUserId() 
	{
		return userId;
	}


	public void setUserId(String userId) 
	{
		this.userId = userId;
	}


	public String getMail() 
	{
		return mail;
	}


	public void setMail(String mail) 
	{
		this.mail = mail;
	}


	public String getFirstName() 
	{
		return firstName;
	}


	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}


	public String getLastName() 
	{
		return lastName;
	}


	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}


	public String getPhone() 
	{
		return phone;
	}


	public void setPhone(String phone) 
	{
		this.phone = phone;
	}


	public String getFifaUser() 
	{
		return fifaUser;
	}


	public void setFifaUser(String fifaUser) 
	{
		this.fifaUser = fifaUser;
	}


	public String getSkills() 
	{
		return skills;
	}


	public void setSkills(String skills) 
	{
		this.skills = skills;
	}
	
	
}
