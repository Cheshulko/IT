package db.table.field.usertype;

public class StringIntv {
	private char startIntvFrom;
	private char endIntvTo;
	
	private String stringBase;

	public StringIntv(char startIntvFrom, char endIntvTo) {
		if (startIntvFrom > endIntvTo)
			try {
				throw new Exception("EndIntvTo i less than startIntvFrom");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else {
			this.startIntvFrom = startIntvFrom;
			this.endIntvTo = endIntvTo;
		}
	}

	public StringIntv(String stringBase){
		char minc = stringBase.charAt(0);
		char maxc = stringBase.charAt(0);
		for(int i = 0; i < stringBase.length(); ++i){
			if(minc > stringBase.charAt(i)) minc = stringBase.charAt(i);
			if(maxc < stringBase.charAt(i)) maxc = stringBase.charAt(i);
		}
		this.startIntvFrom = minc;
		this.endIntvTo = maxc;
		this.stringBase = stringBase;
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

	public Boolean checkValid(String str) {
		for (int i = 0; i < str.length(); ++i) {
			if (!(startIntvFrom <= str.charAt(i) && str.charAt(i) <= endIntvTo)) {
				return false;
			}
		}
		return true;
	}

	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (this == object)
			return true;
		if (object instanceof StringIntv) {
			StringIntv tmpStringIntv = (StringIntv) object;
			return (this.startIntvFrom <= tmpStringIntv.getStartIntvFrom())
					&& (tmpStringIntv.getEndIntvTo() <= this.endIntvTo);
		}
		return false;
	}

	public int hashCode() {
		return this.startIntvFrom ^ this.endIntvTo;
	}

	public String getStringBase() {
		return stringBase;
	}

	public void setStringBase(String stringBase) {
		this.stringBase = stringBase;
	}
}
