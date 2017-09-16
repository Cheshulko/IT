package table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import table.field.TableField;

public class Table {
	// Table name field
	private String tableName = null;
	// Fix structure of a table
	private List<TableField> tableFields = null;
	// Set of foreign key tables
	private Set<Table> foreignKeyTables = null;
	// List of table instances
	private List<TableInstance> tableInstances = null;

	private Table() {
	}

	public String getTableName() {
		return tableName;
	}

	public List<TableField> getTableFields() {
		return tableFields;
	}

	public List<TableInstance> getTableInstances() {
		return tableInstances;
	}

	public Set<Table> getForeignKeyTables() {
		return foreignKeyTables;
	}

	public TableInstance getTableInstanceByIndex(String index) {
		for (int i = 0; i < tableInstances.size(); ++i) {
			if (tableInstances.get(i).getIndex().equals(index))
				return tableInstances.get(i);
		}
		return null;
	}

	/*
	 * Check input data and add table instance
	 */
	public Boolean addTableInstance(TableInstance tableInstance) {
		// Check input data
		if (tableInstance.getFields() == null)
			return false;

		if (tableInstance.getFields().size() != this.tableFields.size())
			return false;

		for (int iType = 0; iType < tableInstance.getFields().size(); ++iType) {
			if (tableInstance.getFields().get(iType).getType() != this.tableFields.get(iType).getType())
				return false;
		}

		// for(int i = 0; i < tableInstances.size(); ++i){
		// if(tableInstances.get(i).equals(tableInstance)){
		// return false;
		// }
		// }

		// OK. Add new table instance
		tableInstances.add(tableInstance);
		return true;
	}

	public void addForeignKeyTable(Table primaryTable) {
		this.foreignKeyTables.add(primaryTable);
	}

	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (this == object)
			return true;
		if (object instanceof Table) {
			Table tmpTable = (Table) object;
			if (this.getTableName() != tmpTable.getTableName())
				return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		return tableName.hashCode();
	}

	// Unnecessary function just for lab :)
	public void deleteDuplicates() {
		Set<TableInstance> tmpTi = new HashSet<TableInstance>();
		for (int i = 0; i < tableInstances.size(); ++i) {
			TableInstance tmpTb = tableInstances.get(i);
			if (tmpTi.contains(tmpTb)) {
				tableInstances.remove(i);
			} else {
				tmpTi.add(tmpTb);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Table name: " + this.tableName + "\n");
		stringBuilder.append("Fields : \n");
		for (int i = 0; i < tableFields.size(); ++i) {
			stringBuilder.append((i + 1) + ". " + tableFields.get(i));
		}
		stringBuilder.append("Connected tables: ");
		for (Table table : foreignKeyTables) {
			stringBuilder.append(table.getTableName() + " ");
		}
		stringBuilder.append("\n");
		stringBuilder.append("Instances: \n");
		for (TableInstance tableInstance : tableInstances) {
			stringBuilder.append(tableInstance);
		}
		return stringBuilder.toString();

	}

	public static TableBuilder tableBuilder() {
		return new Table().new TableBuilder();
	}

	/*
	 * Create TableBuilder to build a table
	 */
	public class TableBuilder {

		private TableBuilder() {
			tableFields = new ArrayList<TableField>();
			foreignKeyTables = new HashSet<Table>();
			tableInstances = new ArrayList<TableInstance>();
		}

		public TableBuilder setTableName(String tableName) {
			Table.this.tableName = tableName;
			return this;
		}

		public TableBuilder addTableField(TableField tableField) {
			Table.this.tableFields.add(tableField);
			return this;
		}

		public TableBuilder addForeignKeyTable(Table primaryTable) {
			Table.this.addForeignKeyTable(primaryTable);
			return this;
		}

		public Table build() throws Exception {
			// Check if indexField is set
			if (tableName == null)
				throw new Exception("indexTableFieldType or tableName doesn't set");
			return Table.this;
		}
	}
}
