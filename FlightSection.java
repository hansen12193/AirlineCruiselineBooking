/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	The FlightSection class will create a FlightSection object that contains a 
   two-dimensional array of Seat objects, as well as information about its own flight section. 
*/


import java.io.*;
import java.lang.*;
import java.util.*;


public class FlightSection {
	private SeatClass sectionClass;
	private Seat[][] seats;
	private int rows = 0,
				   columns = 0,
				   availableSeats = 0;
	
	// constructor
	public FlightSection(int newRows, int newColumns, SeatClass newSectionClass) throws Exception {
		if ((newRows > 0) && (newRows <= 100)) {
			if ((newColumns > 0) && (newColumns <= 10)) {
				rows = newRows;
				columns = newColumns;
				sectionClass = newSectionClass;
				seats = new Seat[rows][columns];
				
				// initialize every seat in the flight
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < columns; j++) {
						seats[i][j] = new Seat(i, j, false);
						availableSeats++;
					}
				}
			}
         
			else {
				throw new Exception("Invalid column amount for section. ");
			}
		}
      
		else {
			throw new Exception("Invalid row amount for section. ");
		}
	}
	
	// book a seat
	public void bookSeat(int newRow, char newColumn) throws Exception {
		int row = newRow - 1;
		int column = ((int) newColumn) - 65;
		
		if (row >= 0 && row <= rows) {
			if (columns >= 0 && column <= columns) {
				if (!seats[row][column].isBooked()) {
					// if the seat is available, book it
					seats[row][column].bookSeat(true);
					availableSeats--;
				}
            
				else {
					throw new Exception("Seat is already booked. ");
				}
			}
         
			else {
				throw new Exception("Column is out of bounds for this flight section. ");
			}
		}
      
		else {
			throw new Exception("Row is out of bounds for this flight section. ");
		}
	}
	
	
	// get section class
	public SeatClass getSectionClass() {
		return sectionClass;
	}
	
   // display section details
	public void displaySectionDetails() {
		System.out.println("\t\t" + sectionClass + " class with " + availableSeats + " of " + (rows * columns) + " seats available. ");
      
      for (int i = 0; i < rows; i ++) {
         for (int j = 0; j < columns; j++) {
            if (j == (columns / 2)) {
               System.out.print("  ");
            }
            if (!seats[i][j].isBooked()) {
               seats[i][j].displaySeatDetails();
            }
            else {
               System.out.print("[   ]");
            }
         }
         
         System.out.println();
      }
	}
}