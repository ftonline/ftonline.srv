package com.ftonline.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Game")
public class Game
{
	@Id
	@Column
	private String gameId;
	
	@Column
	private String tournamentId;
	
	@Column
	private String player1;
	
	@Column
	private String player2;
	
	@Column
	private String cycle;
	
	@Column
	private String type;
	
	@Column
	private String group;
	
	@Column
	private String score;
	
	@Column
	private String player1checkin;
	
	@Column
	private String player2checkin;
	
	@Column
	private String p1score;
	
	@Column
	private String p2score;
	
	@Column
	private String p1confirm;
	
	@Column
	private String p2confirm;

	public String getGameId() 
	{
		return gameId;
	}

	public void setGameId(String gameId) 
	{
		this.gameId = gameId;
	}

	public String getTournamentId() 
	{
		return tournamentId;
	}

	public void setTournamentId(String tournamentId) 
	{
		this.tournamentId = tournamentId;
	}

	public String getPlayer1() 
	{
		return player1;
	}

	public void setPlayer1(String player1) 
	{
		this.player1 = player1;
	}

	public String getPlayer2() 
	{
		return player2;
	}

	public void setPlayer2(String player2) 
	{
		this.player2 = player2;
	}

	public String getCycle() 
	{
		return cycle;
	}

	public void setCycle(String cycle) 
	{
		this.cycle = cycle;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public String getGroup() 
	{
		return group;
	}

	public void setGroup(String group)
	{
		this.group = group;
	}

	public String getScore()
	{
		return score;
	}

	public void setScore(String score)
	{
		this.score = score;
	}

	public String getPlayer1checkin()
	{
		return player1checkin;
	}

	public void setPlayer1checkin(String player1checkin)
	{
		this.player1checkin = player1checkin;
	}

	public String getPlayer2checkin() 
	{
		return player2checkin;
	}

	public void setPlayer2checkin(String player2checkin) 
	{
		this.player2checkin = player2checkin;
	}

	public String getP1score() 
	{
		return p1score;
	}

	public void setP1score(String p1score) 
	{
		this.p1score = p1score;
	}

	public String getP2score()
	{
		return p2score;
	}

	public void setP2score(String p2score)
	{
		this.p2score = p2score;
	}

	public String getP1confirm()
	{
		return p1confirm;
	}

	public void setP1confirm(String p1confirm)
	{
		this.p1confirm = p1confirm;
	}

	public String getP2confirm()
	{
		return p2confirm;
	}

	public void setP2confirm(String p2confirm)
	{
		this.p2confirm = p2confirm;
	}
}
