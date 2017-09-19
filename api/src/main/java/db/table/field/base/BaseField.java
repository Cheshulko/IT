package db.table.field.base;

import db.table.field.IField;

public class BaseField implements IField{
	protected String tableFieldName;
	protected BaseFieldType type;

	public BaseField(String tableFieldName, BaseFieldType type) {
		this.tableFieldName = tableFieldName;
		this.type = type;
	}

	public BaseFieldType getType() {
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
		if (object instanceof BaseField) {
			BaseField tmpTableField = (BaseField) object;
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
