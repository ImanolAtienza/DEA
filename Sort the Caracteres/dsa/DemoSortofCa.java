package dsa;

public class DemoSortofCa {

	public static void main(String[] args) {
		String myString = "a fantaastic fantastic day";
		
		DoubleOrderedList<CharWithFrequency> listChar = createListOfCharsWithFreq(myString);
		writeListOfCharsFreq(listChar);
	}	
	
	private static DoubleOrderedList<CharWithFrequency> createListOfCharsWithFreq(String s) {
		
		DoubleOrderedList<CharWithFrequency> charFreq = new DoubleOrderedList<CharWithFrequency>();
		
		for (int i=0; i<s.length(); i++) {
			CharWithFrequency car = new CharWithFrequency(s.charAt(i));
			if (charFreq.contains(car)) {
				car = charFreq.remove(car);
				if (car.getChar() == s.charAt(i)) {
					car.setFrequency(car.getFrequency()+1);
				}
				charFreq.add(car);
			} else 
				charFreq.add(car);
		}
		
		return charFreq;
	}
	
	private static void writeListOfCharsFreq(DoubleOrderedList<CharWithFrequency> theList) {
		System.out.println(theList.toString());
	}
}
