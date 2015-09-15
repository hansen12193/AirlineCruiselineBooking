/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	Company.java 
   
   Company abstract base class
*/

public abstract class Company {
	private String companyName;
	
   // constructor
	public Company(String companyName) {
		this.companyName = companyName;
	}
	
   // get company name
	public String getCompanyName() {
		return companyName;
	}
}