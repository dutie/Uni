package company;

public class Program {

	// resolved method of the call in main = called method of the call in main
	/**@pre | company != null */
	public static void printLocations(Company company) {
		String[] locations = company.getLocations(); // dynamic call
		for (int i = 0; i < locations.length; i++) {
			System.out.println(locations[i]);	
		}
		
	}
	

	public static void main(String[] args) {
		printLocations(new CompanyA()); // static call
	}
	
}
