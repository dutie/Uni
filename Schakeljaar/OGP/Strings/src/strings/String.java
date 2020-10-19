package strings;

/**
 * Each instance of this class represents a sequence of text characters.
 * 
 * @immutable *
 */

public class String {
	
	/**
	 * @invar | characters != null
	 * 
	 * @representationObject
	 */
	private char[] characters;

	public int length() {
		return characters.length;
	}
	
	public char charAt(int index) {
		return characters[index];
	}
	//.clone om geen gevolgen op abstracte toestand te kunnen krijgen
	// geen lekken van representatie-objecten
	public char[] toCharArray() {
		return characters.clone();
	}
	
	public String(char[] characters) {
		this.characters = characters;
	}
	// klant geen referentie geven naar object met clone
	public static String valueOf(char[] characters) {
		return new String(characters.clone());
	}
}
