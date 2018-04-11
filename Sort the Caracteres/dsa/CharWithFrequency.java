package dsa;

public class CharWithFrequency implements Comparable {
	private char thechar;
	private int count;
	
	public CharWithFrequency(char thechar) {
		this.thechar = thechar;
		count = 1;	}
	
	public char getChar() {return thechar;	}
	public void setChar(char thechar) {}
	public int getFrequency() {return count;	}
	public void setFrequency(int count) {
		this.count = count;
	}
	public boolean equals(Object anobject)
	{
		CharWithFrequency otherCharWithFrequencyObject = (CharWithFrequency)anobject;
		if (otherCharWithFrequencyObject.thechar == this.thechar){
			return true;}
		else
			return false;
	}

	@Override
	public int compareTo(Object anobject) {
		CharWithFrequency otherCharWithFrequencyObject = (CharWithFrequency)anobject;
		if (this.equals(otherCharWithFrequencyObject))
			return 0;
		else if (this.thechar < otherCharWithFrequencyObject.thechar)
				return -1;
			else
				return 1;
	}
	
	public String toString()
	{
		String s = String.valueOf(this.thechar) + String.valueOf(this.count);
		return s;
	}
	
}
