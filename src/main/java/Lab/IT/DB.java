package Lab.IT;

import java.util.HashSet;
import java.util.Set;

import table.Table;

public class DB {
	private String dbName;
	private Set<Table> tables = null;

	public DB(String dbName) {
		this.dbName = dbName;
		tables = new HashSet<Table>();
	}

	public Set<Table> getTables() {
		return tables;
	}

	public Table getTableByName(String tableName) {
		for (Table table : tables) {
			if (table.getTableName().equals(tableName)) {
				return table;
			}
		}
		return null;
	}

	public String getDbName() {
		return dbName;
	}

	public Boolean contains(String tableName) {
		try {
			if (this.tables.contains(Table.tableBuilder().setTableName(tableName).build()))
				return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * Create table to a base
	 */
	public Boolean createTable(Table table) {
		// Check if table with that name already exist
		for (Table t : tables) {
			if (t.getTableName() == table.getTableName()) {
				return false;
			}
		}
		this.tables.add(table);
		return true;
	}

	/*
	 * Remove table from a base
	 */
	public Boolean removeTable(Table table) {
		// FIXME Need to check for relation
		return tables.remove(table);
	}

}
