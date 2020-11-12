package company;

import java.util.Arrays;

public class CompanyB extends Company {
	// called method of the call in printLocations
	/**
	 * @post | result != null
	 * @post | result.length == 2
	 * @post | Arrays.stream(result).allMatch(e -> e != null)
	 */

	@Override
	public String[] getLocations() {
		String [] locations = new String[] {
				"Brussels",
				"Antwerp"
			};
			
		return locations;
	}}
