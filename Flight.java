/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	Flight.java 
   
   Flight class creates a Flight object with an origin and destination, as well
   as the dates and the flight id.
*/

import java.util.List;
import java.util.ArrayList;

public class Flight {
	private List<FlightSection> sections = new ArrayList<FlightSection>();
	private String originCode = null,
					   destinationCode = null,
					   flightID = null;
   private int year = 0,
               month = 0,
               day = 0;
	
	// constructor
	public Flight(String newOriginCode, String newDestinationCode, int newYear, int newMonth, int newDay, String newFlightID) throws Exception {
		if (((newYear < 2015) || (newYear > 2020)) || ((newMonth < 1) || (newMonth > 12)) || ((newDay < 1) || (newDay > 31))) {
			throw new Exception("Flight date is invalid. ");
		}
      
		else {
			originCode = newOriginCode;
			destinationCode = newDestinationCode;
			flightID = newFlightID;
			year = newYear;
			month = newMonth;
			day = newDay;
		}
	}
	
	// create section
	public void createSection(int rows, int columns, SeatClass sectionClass) throws Exception {
		if (!checkSectionClass(sectionClass)) {
			sections.add(new FlightSection(rows, columns, sectionClass));
		}
      
		else {
			throw new Exception("Flight section already exists. ");
		}
	}
	
   // book a seat
	public void bookSeat(SeatClass sectionClass, int row, char column) throws Exception {
		boolean foundSection = false;
	
		for (FlightSection section : sections) {
			if ((foundSection == false) && (section.getSectionClass() == sectionClass)) {
				foundSection = true;
				section.bookSeat(row, column);
			}
		}
		if (!foundSection) {
			throw new Exception("Flight section does not exist on flight. ");
		}
	}
	
	// get flight id
	public String getFlightID() {
		return flightID;
	}
	
   // check if section class already exists
	public boolean checkSectionClass(SeatClass sectionClass) {
		for (FlightSection section : sections) {
			if (section.getSectionClass() == sectionClass) {
				return true;
			}
		}
		return false;
	}
	
   // display flight details
	public void displayFlightDetails() {
		System.out.println("\tFlight ID: " + flightID + " from " + originCode + " to " + destinationCode + " on " + getDate());
		
		for (FlightSection section : sections) {
			section.displaySectionDetails();
		}
	}
	
   // find matching flights
	public void findMatchingFlight(String originCode, String destinationCode) {
		if ((originCode.equals(this.originCode)) && (destinationCode.equals(this.destinationCode))) {
			System.out.println("flight ID: " + flightID + " from " + originCode + " to " + destinationCode + " on " + getDate());
			
			for (FlightSection section : sections) {
				section.displaySectionDetails();
			}
		}
	}
	
   // get the date
	private String getDate() {
		StringBuilder returnString = new StringBuilder();
		returnString.append(month);
		returnString.append("/");
		returnString.append(day);
		returnString.append("/");
		returnString.append(year);
		
		return returnString.toString();
	}
	
   // get origin airport code
	public String getOriginCode() {
		return originCode;
	}
   
   // get destination airport code
	public String getDestinationCode() {
		return destinationCode;
	}
}