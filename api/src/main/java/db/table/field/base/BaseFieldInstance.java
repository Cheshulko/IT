package db.table.field.base;

import java.io.Serializable;

public class BaseFieldInstance extends BaseField implements Serializable{

	private Object data;

	public BaseFieldInstance(String tableFieldName, Object data, BaseFieldType type) {
		super(tableFieldName, type);
		this.data = data;
	}

	public void setType(BaseFieldType type) {
		this.type = type;
	}

	public Object getData(){
		return data;
	}
	
	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (this == object)
			return true;
		if (object instanceof BaseFieldInstance) {
			BaseFieldInstance tmpTableFieldInstance = (BaseFieldInstance) object;
			if (!super.equals(object)) {
				return false;
			}
//			switch (this.type) {
//			case CHAR:
//				return ((Character) this.data).equals((Character) tmpTableFieldInstance.data);
//			case STRING:
//				return ((String) this.data).equals((String) tmpTableFieldInstance.data);
//			case INTEGER:
//				return ((Integer) this.data).equals((Integer) tmpTableFieldInstance.data);
//			case LONGINT:
//				return ((Long) this.data).equals((Long) tmpTableFieldInstance.data);
//			case REAL:
//				return ((Double) this.data).equals((Double) tmpTableFieldInstance.data);
//			default:
//				return false;
//			}
			return this.data.equals(tmpTableFieldInstance.data);
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
