package db.table.field.interval;

import db.table.field.IField;

public class IntervalField implements IField{

	private char startIntvFrom;
	private char endIntvTo;
	
	protected String tableIntervalFieldName;
	public static String type = "CHARINTV"; 
	
	public IntervalField(char startIntvFrom, char endIntvTo, String tableIntervalFieldName){
		
		if (startIntvFrom > endIntvTo)
			try {
				throw new Exception("EndIntvTo is less than startIntvFrom");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else {
			this.setStartIntvFrom(startIntvFrom);
			this.setEndIntvTo(endIntvTo);
		}
		
		this.setStartIntvFrom(startIntvFrom);
		this.setEndIntvTo(endIntvTo);
		this.tableIntervalFieldName = tableIntervalFieldName;
	}
	
	public Boolean checkValidIntervalFieldStringInstance(IntervalFieldStringInstance intervalFieldStringInstance){
		Boolean result = true;
		result &= (this.startIntvFrom <= intervalFieldStringInstance.getStringBaseMin());
		result &= (this.endIntvTo >= intervalFieldStringInstance.getStringBaseMax());
		return result;
	}
	
	public String getTableFieldName() {
		return tableIntervalFieldName;
	}

	public void setTableFieldName(String tableIntervalFieldName) {
		this.tableIntervalFieldName = tableIntervalFieldName;		
	}

	@Override
	public String toString() {
		String res = tableIntervalFieldName + "\n";
		res += "[-]Field type: " + type + "\n";
		res += "[-]Start interval:'" + startIntvFrom + "'\n";
		res += "[-]End   interval:'" + endIntvTo + "'\n";
		return res;
	}

	public char getStartIntvFrom() {
		return startIntvFrom;
	}

	public void setStartIntvFrom(char startIntvFrom) {
		this.startIntvFrom = startIntvFrom;
	}

	public char getEndIntvTo() {
		return endIntvTo;
	}

	public void setEndIntvTo(char endIntvTo) {
		this.endIntvTo = endIntvTo;
	}
	
}
