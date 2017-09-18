package service;

import db.DB;

public class DbService implements IDbService{

	public DB createDB(String dbName) {
		DB db = new DB(dbName + "_suff");
		System.out.println("Got!!");
		return db;
	}

}
