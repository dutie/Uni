package company;

public abstract class Company {

	// resolved method of the call in printLocations
	/**
	 * @post | result != null
	 */
	public abstract String[] getLocations();
	
}
// Basic strategy:
// - Reason about calls based on specifications called methods

// Better strategy:
// - Reason about calls based on specifications of solved methods
// - Overriding method needs to fulfill the specifications of the 
//   override method