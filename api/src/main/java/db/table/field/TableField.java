package db.table.field;

public class TableField {
	protected String tableFieldName;
	protected TableFieldType type;

	public TableField(String tableFieldName, TableFieldType type) {
		this.tableFieldName = tableFieldName;
		this.type = type;
	}

	public TableFieldType getType() {
		return type;
	}

	public String getTableFieldName() {
		return tableFieldName;
	}

	public void setTableFieldName(String tableFieldName) {
		this.tableFieldName = tableFieldName;
	}

	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (this == object)
			return true;
		if (object instanceof TableField) {
			TableField tmpTableField = (TableField) object;
			if (!this.tableFieldName.equals(tmpTableField.tableFieldName)) {
				return false;
			}
			if (this.type != tmpTableField.type) {
				return false;
			}
			return true;
		}
		return false;
	}

	public int hashCode() {
		return tableFieldName.hashCode() ^ type.hashCode();
	}

	@Override
	public String toString() {
		String res = tableFieldName + "\n";
		res += "[-]Field type: " + this.type + "\n";
		return res;
	}
}
