package com.ftonline.service.user;

import java.util.ArrayList;
import java.util.List;

public class ServiceOutput 
{
	List <Throwable> throwables = new ArrayList<>();
	
	public List<Throwable> getThrowables() 
	{
		return throwables;
	}

	public void setThrowables(List<Throwable> throwables) 
	{
		this.throwables = throwables;
	}

	public void addThrowable(Throwable throwable)
	{
		throwables.add(throwable);
	}
}
