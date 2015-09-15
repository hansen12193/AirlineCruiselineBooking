/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	Trip.java 
   
   Trip class creates a Trip object that has a list of ports, the ship name,
   and the dates of the trip.
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Arrays;

public class Trip {
	private List<ShipSection> sections = new ArrayList<ShipSection>();
	private String[] ports;
   private String shipName;
   private int sy = 0, ey = 0,
               sm = 0, em = 0,
               sd = 0, ed = 0;
	
	// constructor
	public Trip(String[] ports, String shipName, int sy, int sm ,int sd, int ey, int em, int ed) throws Exception {
		this.ports = ports;
      this.shipName = shipName;
      this.sy = sy;
      this.sm = sm;
      this.sd = sd;
      this.ey = ey;
      this.em = em;
      this.ed = ed;
      
      if (getEndDate().before(getStartDate())) {
         throw new Exception("End trip date must be after the start date. ");
      }
      else if ((getEndDate().getTimeInMillis() - (getStartDate().getTimeInMillis())) > 1814400000) {
         throw new Exception("Trip must be less than 21 days. ");
      }
	}
	
	// create section
	public void createSection(int cabins, CabinClass sectionClass) throws Exception {
		if (!checkSectionClass(sectionClass)) {
			// the flight section does not yet exist, so create it
			sections.add(new ShipSection(cabins, sectionClass));
		}
		else {
			throw new Exception("Ship section already exists. ");
		}
	}
   
	// book a cabin
	public void bookCabin(CabinClass sectionClass, int passengers) throws Exception {
		boolean foundSection = false;
	
		for (ShipSection section : sections) {
			if ((foundSection == false) && (section.getSectionClass() == sectionClass)) {
				foundSection = true;
				section.bookCabin(passengers);
			}
		}
      
		if (!foundSection) {
			throw new Exception("Ship section does not exist on ship. ");
		}
	}
	
	// get ship name
	public String getShipName() {
		return shipName;
	}
   
   // get start date
   public Calendar getStartDate() {
      Calendar startDate = Calendar.getInstance();
      startDate.set(sy, sm, sd);
      return startDate;
   }
   
   // get end date
   public Calendar getEndDate() {
      Calendar endDate = Calendar.getInstance();
      endDate.set(ey, em, ed);
      return endDate;
   }
   
   // check section class
	public boolean checkSectionClass(CabinClass sectionClass) {
		for (ShipSection section : sections) {
			if (section.getSectionClass() == sectionClass) {
				return true;
			}
		}
		return false;
	}
	
	// check if trip visits specified port(s)
	public boolean visitsPort(String tripPort) {
		for (String port : ports) {
			if (tripPort.equals(port)) {
				return true;
			}
		}
		return false;
	}
	
   //display trip details
	public void displayTripDetails() {
		System.out.println(" visits " + Arrays.toString(ports) + " departing " + sm + "/" + sd + "/" + sy + " and arriving " + + em + "/" + ed + "/" + ey);
		
		for (ShipSection section : sections) {
			section.displaySectionDetails();
		}
	}
}