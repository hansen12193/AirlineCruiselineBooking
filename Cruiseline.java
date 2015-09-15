/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	Cruiseline.java 
   
   Cruiseline concrete class derived from Company abstract base class.
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

public class Cruiseline extends Company {
   private List<Trip> trips = new ArrayList<Trip>();
   private int tripIndex = 0;

   // create a cruiseline
	public Cruiseline(String cruiselineName) {
		super(cruiselineName);
	}
 
   // create a trip
   public void createTrip(String[] tripPorts, String shipName, int sy, int sm ,int sd, int ey, int em, int ed) throws Exception {
		for (Trip trip : trips) {
         Calendar startDate = Calendar.getInstance();
         Calendar endDate = Calendar.getInstance();
         startDate.set(sy, sm, sd);
         endDate.set(ey, em, ed);
         
			if (shipName.equalsIgnoreCase(trip.getShipName())) {
            // check all overlapping possibilities
            if (startDate.before(trip.getStartDate()) && endDate.after(trip.getStartDate())) {
               throw new Exception("Ship is already scheduled during this time. ");
            }
            
            else if (endDate.after(trip.getEndDate()) && startDate.before(trip.getEndDate())) {
               throw new Exception("Ship is already scheduled during this time. ");
            }
            
            else if (startDate.equals(trip.getStartDate()) || endDate.equals(trip.getEndDate())) {
               throw new Exception("Ship is already scheduled during this time. ");
            }
            
            else if (startDate.after(trip.getStartDate()) && endDate.before(trip.getEndDate())) {
               throw new Exception("Ship is already scheduled during this time. ");
            }
			}
		}
		trips.add(tripIndex, new Trip(tripPorts, shipName, sy, sm, sd, ey, em, ed));
      System.out.println("Trip added into " + getCompanyName() + " trip list at index " + tripIndex);
      tripIndex++;
	}
   
   // create a section
   public void createSection(int tripIndex, int cabins, CabinClass sectionClass) throws Exception {
      trips.get(tripIndex).createSection(cabins, sectionClass);
	}
	
	// find available trips
	public void findAvailableTrips(String[] tripPorts) {
		for (Trip trip : trips) {
			boolean matchingTrip = true;
		
			for (String tripPort : tripPorts) {
				if (!trip.visitsPort(tripPort)) {
					matchingTrip = false;
					break;
				}
			}
			
			if (matchingTrip) {
				System.out.println("Trip index: " + trips.indexOf(trip));
				trip.displayTripDetails();
			}
		}
	}
   
   // book a cabin
   public void bookCabin(int tripIndex, CabinClass sectionClass, int passengers) throws Exception {
		trips.get(tripIndex).bookCabin(sectionClass, passengers);
	}
   
	// get cruiseline name
   public String getCruiselineName() {
      return super.getCompanyName();
   }
	
   //display cruiseline details
	public void displayCruiselineDetails() {
		System.out.println("Cruiseline " + getCruiselineName() + " servicing: ");
		
		for (Trip trip : trips) {
         System.out.print("\tTrip index " + trips.indexOf(trip));
			trip.displayTripDetails();
		}
	}
}