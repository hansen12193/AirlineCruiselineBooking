/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	Airport.java 
   
   Airport concerte class derived from Location abstract base class.
*/

public class Airport extends Location {
	
   // constructor
   public Airport(String airportCode) {
      super(airportCode);
	}
	
   // get airport code
	public String getAirportCode() {
		return super.getLocationName();
	}
}