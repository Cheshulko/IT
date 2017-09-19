package db.table.field.interval;

public class IntervalFieldStringInstance {

	private String tableIntervalFieldName;
	private String stringBase;

	private char stringBaseMin;
	private char stringBaseMax;

	public IntervalFieldStringInstance(String tableIntervalFieldName, String stringBase) {
		this.tableIntervalFieldName = tableIntervalFieldName;
		this.stringBase = stringBase;
		
		char minc = stringBase.charAt(0);
		char maxc = stringBase.charAt(0);
		for(int i = 0; i < stringBase.length(); ++i){
			if(stringBase.charAt(i) < minc) minc = stringBase.charAt(i);
			if(stringBase.charAt(i) > maxc) maxc = stringBase.charAt(i);
		}
		this.stringBaseMin = minc;
		this.stringBaseMax = maxc;
	}

	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (this == object)
			return true;
		if (object instanceof IntervalFieldStringInstance) {
			IntervalFieldStringInstance tmpStringIntv = (IntervalFieldStringInstance) object;
			return this.tableIntervalFieldName.equals(tmpStringIntv.getTableIntervalFieldName())
					&& this.stringBase.equals(tmpStringIntv.getStringBase());
		}
		return false;
	}

	public int hashCode() {
		return this.stringBase.hashCode() ^ this.tableIntervalFieldName.hashCode();
	}

	public String getStringBase() {
		return stringBase;
	}

	public String getTableIntervalFieldName() {
		return tableIntervalFieldName;
	}

	public void setStringBase(String stringBase) {
		this.stringBase = stringBase;
	}

	@Override
	public String toString() {
		String res = "[-]Field type: CHARINTV\n";
		res += " Value: ";
		res += this.stringBase + "\n";
		return res;
	}

	public char getStringBaseMin() {
		return stringBaseMin;
	}

	public char getStringBaseMax() {
		return stringBaseMax;
	}

}
