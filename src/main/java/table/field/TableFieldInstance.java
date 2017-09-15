package table.field;

public class TableFieldInstance extends TableField {

	private Object data;

	public TableFieldInstance(String tableFieldName, Object data, TableFieldType type) {
		super(tableFieldName, type);
		this.data = data;
	}

	public void setType(TableFieldType type) {
		this.type = type;
	}

	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (this == object)
			return true;
		if (object instanceof TableFieldInstance) {
			TableFieldInstance tmpTableFieldInstance = (TableFieldInstance) object;
			if (!super.equals(object)) {
				return false;
			}
			switch (this.type) {
			case CHAR:
				return ((Character) this.data).equals((Character) tmpTableFieldInstance.data);
			case STRING:
				// FIXME
				return false;
			case INTEGER:
				return ((Integer) this.data).equals((Integer) tmpTableFieldInstance.data);
			case LONGINT:
				return ((Long) this.data).equals((Long) tmpTableFieldInstance.data);
			case REAL:
				return ((Double) this.data).equals((Double) tmpTableFieldInstance.data);
			default:
				return false;
			}
		}
		return false;
	}

	public int hashCode() {
		return super.hashCode() ^ data.hashCode();
	}

	@Override
	public String toString() {
		String res = super.toString();
		res += " Value: ";
		switch (super.type) {
		case CHAR:
			res += ((Character) this.data) + "\n";
			break;
		case STRING:
			res += ((String) this.data) + "\n";
			break;
		case INTEGER:
			res += ((Integer) this.data) + "\n";
			break;
		case LONGINT:
			res += ((Long) this.data) + "\n";
			break;
		case REAL:
			res += ((Double) this.data) + "\n";
			break;
		}
		return res;
	}
}
