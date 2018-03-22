package com.ftonline.service.tournament;

import com.ftonline.entities.Tournament;

public class CreateTournamentInput 
{
	private Tournament tournament;

	private String tokenId;
	
	public Tournament getTournament() 
	{
		return tournament;
	}

	public void setTournament(Tournament tournament)
	{
		this.tournament = tournament;
	}

	public String getTokenId() 
	{
		return tokenId;
	}

	public void setTokenId(String tokenId) 
	{
		this.tokenId = tokenId;
	}
}
