package com.ftonline.service.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ftonline.dao.DatastoreDao;
import com.ftonline.dao.IdGenerator;
import com.ftonline.entities.TournamentsToUsers;
import com.ftonline.entities.User;
import com.ftonline.service.user.UserService;

@RestController
public class TournamentService 
{
	@Autowired
	private DatastoreDao datastoreDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IdGenerator idGenerator;
	
	@PostMapping(value="/tournament/createTournament", consumes="application/json")
	public CreateTournamentOutput createTournament(@RequestBody CreateTournamentInput createTournamentInput)
	{
		CreateTournamentOutput createTournamentOutput = new CreateTournamentOutput();
		try 
		{
			User user = userService.retrieveUser(createTournamentInput.getTokenId());
			if (!"MANAGER".equals(user.getSkills()))
			{
				throw new Exception("the user is not MANAGER.");
			}
			createTournamentInput.getTournament().setTournamentId(idGenerator.getUniqueId());
			datastoreDao.create(createTournamentInput.getTournament());
			return createTournamentOutput;
		} 
		catch (Exception e) 
		{
			createTournamentOutput.addThrowable(e);
			e.printStackTrace();
			return createTournamentOutput;
		}
	}
	
	
	@PostMapping(value="/tournament/registerTournament", consumes="application/json")
	public RegisterTournamentOutput registerTournament(@RequestBody RegisterTournamentInput registerTournamentInput)
	{
		RegisterTournamentOutput registerTournamentOutput = new RegisterTournamentOutput();
		try 
		{
			User user = userService.retrieveUser(registerTournamentInput.getTokenId());
			TournamentsToUsers tournamentsToUsers = new TournamentsToUsers();
			tournamentsToUsers.setTournamentsId(registerTournamentInput.getTournamentId());
			tournamentsToUsers.setUserId(user.getUserId());
			//TODO - need to send email to the user.
			datastoreDao.create(tournamentsToUsers);
			return registerTournamentOutput;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			registerTournamentOutput.addThrowable(e);
			return registerTournamentOutput;
		}
	}
}
