package server.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import db.DB;
import db.table.Storage;
import service.IDbService;

@Service
public class DbService implements IDbService {

	@Autowired
	private Storage storage;

	@Override
	public Boolean createDB(DB db) {
		System.out.println("createDB");
		return storage.getDbs().add(db);
	}

	@Override
	public ArrayList<DB> getDbs() {
		System.out.println("getDbs");
		return new ArrayList<DB>(storage.getDbs());
	}

}
