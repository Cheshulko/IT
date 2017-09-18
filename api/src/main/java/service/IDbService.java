package service;

import db.DB;

public interface IDbService {
	public DB createDB(String dbName);
}
