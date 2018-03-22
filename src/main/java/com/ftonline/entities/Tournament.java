package com.ftonline.entities;

import javax.persistence.Column;
import javax.persistence.Id;

public class Tournament 
{
	@Id
	@Column
	private String tournamentId;
	
	@Column
	private String numOfParticipant;
	
	@Column
	private String price;
	
	@Column
	private String date;
	
	@Column
	private String consoleType;
	
	@Column
	private String headLine;
	
	@Column
	private String content;

	public String getTournamentId() 
	{
		return tournamentId;
	}

	public void setTournamentId(String tournamentId) 
	{
		this.tournamentId = tournamentId;
	}

	public String getNumOfParticipant() 
	{
		return numOfParticipant;
	}

	public void setNumOfParticipant(String numOfParticipant) 
	{
		this.numOfParticipant = numOfParticipant;
	}

	public String getPrice() 
	{
		return price;
	}

	public void setPrice(String price) 
	{
		this.price = price;
	}

	public String getDate() 
	{
		return date;
	}

	public void setDate(String date) 
	{
		this.date = date;
	}

	public String getConsoleType() 
	{
		return consoleType;
	}

	public void setConsoleType(String consoleType) 
	{
		this.consoleType = consoleType;
	}

	public String getHeadLine() 
	{
		return headLine;
	}

	public void setHeadLine(String headLine) 
	{
		this.headLine = headLine;
	}

	public String getContent() 
	{
		return content;
	}

	public void setContent(String content) 
	{
		this.content = content;
	}
}
