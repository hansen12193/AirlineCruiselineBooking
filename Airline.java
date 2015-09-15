/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	Airline.java 
   
   Airline concrete class derived from Company abstract base class.
*/

import java.util.List;
import java.util.ArrayList;

public class Airline extends Company {
   private List<Flight> flights = new ArrayList<Flight>();

   // create a airline
	public Airline(String airlineID) {
      super(airlineID);
	}
   
   // create a flight
   public void createFlight(String originCode, String destinationCode, int year, int month, int day, String flightID) throws Exception {
		for (Flight flight : flights) {
			if (flight.getFlightID().equals(flightID)) {
				throw new Exception("flightID already exists. ");
			}
		}
		flights.add(new Flight(originCode, destinationCode, year, month, day, flightID));
	}
   
   // create a section
   public void createSection(String flightID, int rows, int columns, SeatClass sectionClass) throws Exception {
		boolean foundFlight = false;
	
		for (Flight flight : flights) {
			if ((foundFlight == false) && (flightID.equals(flight.getFlightID()))) {
				foundFlight = true;
				flight.createSection(rows, columns, sectionClass);
			}
		}
		if (!foundFlight) {
			throw new Exception("flightID does not exist. ");
		}
	}
	
	// find available flights
	public void findAvailableFlights(String originCode, String destinationCode) {
		for (Flight flight : flights) {
			if (originCode.equals(flight.getOriginCode()) && destinationCode.equals(flight.getDestinationCode())) {
				flight.displayFlightDetails();
			}
		}
	}
   
   // book a seat
   public void bookSeat(String flightID, SeatClass sectionClass, int row, char column) throws Exception {
		boolean foundFlight = false;
	
		for (Flight flight : flights) {
			if ((foundFlight == false) && (flightID.equals(flight.getFlightID()))) {
				foundFlight = true;
				flight.bookSeat(sectionClass, row, column);
			}
		}
      
		if (!foundFlight) {
			throw new Exception("flightID does not exist. ");
		}
	}
   
	// get airline id
   public String getAirlineID() {
      return super.getCompanyName();
   }
	
   // display airline details
	public void displayAirlineDetails() {
		System.out.println("Airline ID " + getAirlineID() + " servicing: ");
		
		for (Flight flight : flights) {
			flight.displayFlightDetails();
		}
	}
}