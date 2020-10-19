package stringLists;

import java.util.Arrays;


public class StringList {

	/**
	 * @invar | strings != null
	 * @invar | Arrays.stream(strings).allMatch(s -> s != null)
	 * 
	 * @representationObject
	 */
	
	private String[] strings;
	
	public int getSumOfLengths() {
		int totalLength = 0;
		for(int i = 0; i < strings.length; i++)
			totalLength += strings[i].length();
		return totalLength;
	}
	
	public StringList(String[] strings) {
		if(strings == null || Arrays.stream(strings).anyMatch(s -> s == null))
			throw new IllegalArgumentException();
		this.strings = strings;
	}
	
	public void set(int index, String value) {
		strings[index] = value;
	}
}
