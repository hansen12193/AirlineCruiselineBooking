/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	ShipSection class creates a ShipSection object that contains a specific
   number of cabins.
*/


import java.io.*;
import java.lang.*;
import java.util.*;


public class ShipSection {
	private CabinClass sectionClass;
   private List<Cabin> cabins = new ArrayList<Cabin>();
	private int totalCabins = 0,
               availableCabins = 0,
               passengersPerCabin = 0;
	
	// constructor
	public ShipSection(int cabins, CabinClass sectionClass) {
		this.sectionClass = sectionClass;
      totalCabins = cabins;
      availableCabins = cabins;
      
      switch (this.sectionClass) {
         case FAMILY:         passengersPerCabin = 4;
                              break;
         case DELUXEFAMILY:   passengersPerCabin = 6;
                              break;
         case COUPLES:        passengersPerCabin = 2;
                              break;
         case DELUXECOUPLES:  passengersPerCabin = 2;
                              break;
         default:             passengersPerCabin = -1;
                              break;
      }
      
      for (int i = 0; i < cabins; i++) {
         this.cabins.add(new Cabin());
      }
	}
	
	// book a cabin
	public void bookCabin(int passengers) throws Exception {
      if (passengers > passengersPerCabin) {
         throw new Exception("Too many passengers for cabin type. ");
      }
      
      else {
         boolean bookedCabin = false;
      
         for (Cabin cabin : cabins) {
            if (bookedCabin == false && !cabin.isBooked()) {
               bookedCabin = true;
               cabin.bookCabin();
               availableCabins--;
            }
         }
         
         if (!bookedCabin) {
            throw new Exception("No room on the ship in the class section. ");
         }
      }
	}
	
	// get section class
	public CabinClass getSectionClass() {
		return sectionClass;
	}
	
   // display section details
	public void displaySectionDetails() {
		System.out.print("\t\t" + sectionClass.toString() + " section holds " + passengersPerCabin);
		System.out.println(" passengers per cabin, and has " + availableCabins + " of " + totalCabins + " available. ");
	}
}