/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	Seat class will create a Seat object that will be held in a two-dimensional array 
   in the FlightSection object it belongs to.
*/


import java.io.*;
import java.lang.*;
import java.util.*;


public class Seat {
	private String seatID = null;
	private boolean bookedState = false;
	
	// constructor
	public Seat(int newRow, int newColumn, boolean newState) {
		// typecast the ascii value of the character to get its corresponding letter
		char column = (char) (newColumn + 65);
		seatID = Integer.toString(newRow + 1) + column;
		bookedState = newState;
	}
	
	// setter and getter functions for the seat's current booked state
	public void bookSeat(boolean newState) {
		bookedState = newState;
	}
   
   // check if seat is booked
	public boolean isBooked() {
		return bookedState;
	}
   
   // display seat details
   public void displaySeatDetails() {
      if (seatID.length() == 3) {
         System.out.print("[" + seatID + "]");
      }
      else {
         System.out.print("[ " + seatID + "]");
      }
   }
}