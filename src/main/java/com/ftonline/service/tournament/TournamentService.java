package com.ftonline.service.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ftonline.dao.DatastoreDao;

@RestController
public class TournamentService 
{
	@Autowired
	DatastoreDao datastoreDao;
}
