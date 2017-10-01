package db.table.field.base;

import java.io.Serializable;

public class BaseFieldInstance extends BaseField implements Serializable{

	private Object data;

	public static Object createValidFromString(String data, BaseFieldType baseFieldType){
		Object crData;
		switch (baseFieldType) {
		case CHAR:{
			if(data.length() != 1)
				try {
					throw new Exception("Invalid input");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			crData = data.charAt(0);
			break;
		}			
		case STRING:{
			crData = data;
			break;
		}
		case INTEGER:{
			crData = Integer.parseInt(data);
			break;
		}
		case LONGINT:{
			crData = Long.parseLong(data);
			break;
		}
		case REAL:{
			crData = Double.parseDouble(data);
			break;
		}
		default:
			crData = null;
		}
		return crData;
	}
	
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
