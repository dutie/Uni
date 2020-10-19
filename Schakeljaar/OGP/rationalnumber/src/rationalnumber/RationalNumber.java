package rationalnumber;

/**
 * 
 * Deze klasse zal een breuk herleiden tot een geheel getal of minimale noemer.
 * 
 * @author matsf
 * @invar noemer mag niet 0 zijn
*     | nom > 0 || 0 > nom
 */

public class RationalNumber {

	/**
	 * @invar noemer mag niet 0 zijn
	 *     | nom > 0 || 0 > nom
	 */
	
	
	private int denom;
	private int nom;
	
	
	public int getDenom() {
		return denom;
	}
	
	public int getNom() {
		return nom;
	}
	/**
	 * 
	 * @throws IllegalArgumentException als de noemer gelijk is aan 0
	 *     | n == 0
	 * @param d
	 * @param n
	 */
	public RationalNumber(int d, int n) {
		if(n == 0)
			throw new IllegalArgumentException("Number under the fraction line can not be 0");
		this.denom = d;
		this.nom = n;		
	}
	
	/**
	 * 
	 * @return
	 */
	public String lowest() {
		String phrase = "";
		if(nom%denom == 0 ) {
			int result = nom/denom;
			phrase = ""+result+"";
		}else {
			if(nom%denom != 0) {
				int rem = nom%denom;
				nom = nom/rem;
				denom = denom/rem;
				phrase = ""+nom+"/"+denom+"";
			}
				else {
					phrase = "This already is the smallest we can represent this.";
				}
		}
		return phrase;
	}
	
	
	
	
}
