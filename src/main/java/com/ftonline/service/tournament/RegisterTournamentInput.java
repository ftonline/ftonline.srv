package com.ftonline.service.tournament;

public class RegisterTournamentInput 
{
	private String tokenId;
	
	private String tournamentId;
	
	public String getTokenId() 
	{
		return tokenId;
	}

	public void setTokenId(String tokenId) 
	{
		this.tokenId = tokenId;
	}

	public String getTournamentId() 
	{
		return tournamentId;
	}

	public void setTournamentId(String tournamentId) 
	{
		this.tournamentId = tournamentId;
	}

}
