package db;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import db.table.Table;
import db.table.field.base.BaseFieldInstance;

public class DB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		Table tmpTable = null;
		try {
			tmpTable = Table.tableBuilder().setTableName(tableName).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.contains(tmpTable);
	}

	public Boolean contains(Table table) {
		return this.tables.contains(table);
	}

	/*
	 * Create table to a base
	 */
	public Boolean createTable(Table table) {
		// Check if table with that name already exist
		Boolean haveTable = this.tables.stream().filter(x -> x.getTableName().equals(table.getTableName())).findAny()
				.isPresent();

		if (!haveTable) {
			this.tables.add(table);
			return true;
		} else
			return false;
		// for (Table t : tables) {
		// if (t.getTableName() == table.getTableName()) {
		// return false;
		// }
		// }

	}

	/*
	 * Remove table from a base
	 */
	public Boolean removeTable(Table table) {
		// FIXME Need to check for relation
		return tables.remove(table);
	}

	public int hashCode() {
		return this.getDbName().hashCode();
	}

	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (this == object)
			return true;
		if (object instanceof DB) {
			DB tmpTable = (DB) object;
			if (this.getDbName().equals(tmpTable.getDbName()))
				return true;
			return false;
		}
		return false;
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("DB name: " + this.getDbName() + "\n");
		this.tables.stream().forEach(x -> stringBuilder.append(x.toString()));
		return stringBuilder.toString();
	}

}
