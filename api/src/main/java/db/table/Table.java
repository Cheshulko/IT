package db.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import db.table.field.base.BaseField;
import db.table.field.interval.IntervalField;

public class Table implements Serializable {

	private String tableName = null;
	private List<BaseField> tableBaseFields = null;
	private static final long serialVersionUID = 1L;
	private List<TableInstance> tableInstances = null;
	private List<IntervalField> tableIntervalFields = null;

	private Table() {
	}

	public String getTableName() {
		return tableName;
	}

	public void getTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<BaseField> getTableBaseFields() {
		return tableBaseFields;
	}

	public void setTableBaseFields(List<BaseField> tableBaseFields) {
		this.tableBaseFields = tableBaseFields;
	}

	public List<IntervalField> getTableIntervalFields() {
		return tableIntervalFields;
	}

	public void setTableIntervalFields(List<IntervalField> tableIntervalFields) {
		this.tableIntervalFields = tableIntervalFields;
	}

	public List<TableInstance> getTableInstances() {
		return tableInstances;
	}

	public void setTableInstances(List<TableInstance> tableInstances) {
		this.tableInstances = tableInstances;
	}

	public TableInstance getTableInstanceByIndex(String index) {
		return this.tableInstances.stream().filter(x -> x.getIndex().equals(index)).findAny().get();
	}

	public Boolean isTableInstance(TableInstance tableInstance) {
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
			if (!this.getTableIntervalFields().get(iType)
					.checkValidIntervalFieldStringInstance(tableInstance.getIntervalFields().get(iType)))
				return false;
		}
		return true;
	}

	public Boolean delteTableInstance(TableInstance tableInstance) {
		if (!isTableInstance(tableInstance))
			return false;
		return tableInstances.remove(tableInstance);
	}

	public Boolean addTableInstance(TableInstance tableInstance) {
		if (!isTableInstance(tableInstance))
			return false;
		tableInstances.add(tableInstance);
		return true;
	}

	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (this == object)
			return true;
		if (object instanceof Table) {
			Table tmpTable = (Table) object;
			if (!this.getTableName().equals(tmpTable.getTableName()))
				return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		return tableName.hashCode();
	}

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

	public class TableBuilder {

		private TableBuilder() {
			tableBaseFields = new ArrayList<BaseField>();
			tableIntervalFields = new ArrayList<IntervalField>();
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

		public Table build() throws Exception {
			if (tableName == null)
				throw new Exception("indexTableFieldType or tableName doesn't set");
			return Table.this;
		}
	}
}
