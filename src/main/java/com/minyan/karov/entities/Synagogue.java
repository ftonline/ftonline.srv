package com.minyan.karov.entities;

import java.util.ArrayList;
import java.util.List;

public class Synagogue 
{
	private String synagogueId;
	
	
	private String senagogName;
	
	
	private String address;
	
	
	private String coordinate;
	
	
	private String nosach;
	
	
	private List<Minyan> minyans = new ArrayList<Minyan>();
	
	
	public String getSynagogueId() 
	{
		return synagogueId;
	}
	
	
	public void setSynagogueId(String senagogId) 
	{
		this.synagogueId = senagogId;
	}
	
	
	public String getSenagogName() 
	{
		return senagogName;
	}
	
	
	public void setSenagogName(String senagogName) 
	{
		this.senagogName = senagogName;
	}
	
	
	public String getAddress() 
	{
		return address;
	}
	
	
	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	
	public String getCoordinate() 
	{
		return coordinate;
	}
	
	
	public void setCoordinate(String coordinate) 
	{
		this.coordinate = coordinate;
	}
	
	
	public String getNosach() 
	{
		return nosach;
	}
	
	
	public void setNosach(String nosach) 
	{
		this.nosach = nosach;
	}
	
	
	public List<Minyan> getMinyans() 
	{
		return minyans;
	}
	
	
	public void setMinyans(List<Minyan> minyans) 
	{
		this.minyans = minyans;
	}
}
