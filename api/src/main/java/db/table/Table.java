package db.table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import db.table.field.base.BaseField;
import db.table.field.interval.IntervalField;

public class Table {
	// Table name field
	private String tableName = null;
	// Fix structure of a table
	private List<BaseField> tableBaseFields = null;
	//
	private List<IntervalField> tableIntervalFields = null;
	// Set of foreign key tables
	private Set<Table> foreignKeyTables = null;
	// List of table instances
	private List<TableInstance> tableInstances = null;

	private Table() {
	}

	public String getTableName() {
		return tableName;
	}

	public List<BaseField> getTableBaseFields() {
		return tableBaseFields;
	}
	
	public List<IntervalField> getTableIntervalFields() {
		return tableIntervalFields;
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
		if (tableInstance.getBaseFields() == null)
			return false;

		if (tableInstance.getBaseFields().size() != this.tableBaseFields.size())
			return false;

		if (tableInstance.getIntervalFields().size() != this.getTableIntervalFields().size())
			return false;

		
		for (int iType = 0; iType < tableInstance.getBaseFields().size(); ++iType) {
			if (tableInstance.getBaseFields().get(iType).getType() != this.tableBaseFields.get(iType).getType())
				return false;
		}
		
		for (int iType = 0; iType < tableInstance.getIntervalFields().size(); ++iType) {
			if (!this.getTableIntervalFields().get(iType).checkValidIntervalFieldStringInstance(tableInstance.getIntervalFields().get(iType)))
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
		stringBuilder.append("BaseFields : \n");
		for (int i = 0; i < tableBaseFields.size(); ++i) {
			stringBuilder.append((i + 1) + ". " + tableBaseFields.get(i));
		}
		stringBuilder.append("IntervalFields : \n");
		for (int i = 0; i < tableIntervalFields.size(); ++i) {
			stringBuilder.append((i + 1) + ". " + tableIntervalFields.get(i));
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
			tableBaseFields = new ArrayList<BaseField>();
			tableIntervalFields = new ArrayList<IntervalField>();
			foreignKeyTables = new HashSet<Table>();
			tableInstances = new ArrayList<TableInstance>();
		}

		public TableBuilder setTableName(String tableName) {
			Table.this.tableName = tableName;
			return this;
		}

		public TableBuilder addTableBaseField(BaseField baseField) {
			Table.this.tableBaseFields.add(baseField);
			return this;
		}
		
		public TableBuilder addTableIntervalField(IntervalField intetvalField) {
			Table.this.tableIntervalFields.add(intetvalField);
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
