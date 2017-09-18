package db.table.field;

public enum TableFieldType {
	INTEGER("INTEGER"), REAL("REAL"), CHAR("CHAR"), LONGINT("LONGINT"), STRING("STRING");

	private final String tableFieldTypeName;

	private TableFieldType(String tableFieldTypeName) {
		this.tableFieldTypeName = tableFieldTypeName;
	}

	public static boolean contains(String type) {
		for (TableFieldType c : TableFieldType.values()) {
			if (c.name().equals(type)) {
				return true;
			}
		}
		return false;
	}

	public static TableFieldType getTableFieldType(String tableFieldTypeName) {
		for (TableFieldType c : TableFieldType.values()) {
			if (c.name().equals(tableFieldTypeName)) {
				return c;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return this.tableFieldTypeName;
	}
}
