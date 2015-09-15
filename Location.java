/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	Location.java 
   
   Location abstract base class
*/

public abstract class Location {
	private String locationName;
	
   // constructor
	public Location(String locationName) {
		this.locationName = locationName;
	}
	
   // get location name
	public String getLocationName() {
		return locationName;
	}
}