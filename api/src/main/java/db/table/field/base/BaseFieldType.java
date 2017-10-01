package db.table.field.base;

public enum BaseFieldType{
	INTEGER("INTEGER"), REAL("REAL"), CHAR("CHAR"), LONGINT("LONGINT"), STRING("STRING");

	private final String tableFieldTypeName;

	private BaseFieldType(String tableFieldTypeName) {
		this.tableFieldTypeName = tableFieldTypeName;
	}

	public static boolean contains(String type) {
		for (BaseFieldType c : BaseFieldType.values()) {
			if (c.name().equals(type)) {
				return true;
			}
		}
		return false;
	}

	public static BaseFieldType getTableFieldType(String tableFieldTypeName) {
		for (BaseFieldType c : BaseFieldType.values()) {
			if (c.name().equals(tableFieldTypeName)) {
				return c;
			}
		}
		try {
			throw new Exception("No tableFieldTypeName");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return this.tableFieldTypeName;
	}
}
