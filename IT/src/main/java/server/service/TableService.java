package server.service;

import java.awt.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import db.DB;
import db.table.Storage;
import db.table.Table;
import db.table.TableInstance;
import service.ITableService;

@Service
public class TableService implements ITableService {

	@Autowired
	private Storage storage;

	public Boolean addTable(DB db, Table table) {
		System.out.println("Add table " + table.getTableName() + " to " + db.getDbName());
		return storage.getDbs().stream().filter(x -> x.equals(db)).findAny().get().createTable(table);
		// return db.createTable(table);
	}

	public Boolean addTableInstance(Table table, TableInstance tableInstance) {
		storage.getDbs().stream().filter(x -> x.contains(table)).findAny().get().getTableByName(table.getTableName())
				.addTableInstance(tableInstance);
		return table.addTableInstance(tableInstance);
	}

	public Boolean delteTableInstance(Table table, TableInstance tableInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Table> getTables(DB db) {
		return new ArrayList<Table>(storage.getDbs().stream().filter(x -> x.equals(db)).findAny().get().getTables());
	}

}
