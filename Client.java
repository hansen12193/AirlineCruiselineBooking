/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	Client.java 
   
   Client class is called when the user presses "c" from the system manager. It handles anything
   a customer would need to do from booking a flight to finding what's available. Each method 
   also handles basic error handling for the input format.
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Character;

public class Client {
   private SystemManager callback = SystemManager.getInstance();

   public void start(Scanner input) {
      
      System.out.println("//---- WELCOME TO BOOKING SYSTEM ----\\ ");
      printCommands();
      
         while (true) {
            String line = input.nextLine();
            
            switch (line) {
               case "book seat":             bookSeat(input);
                                             break;
               case "book cabin":            bookCabin(input);
                                             break;
               case "find available flights":findAvailableFlights(input);
                                             break;                                                      
               case "find available trips":  findAvailableTrips(input);
                                             break;
               case "display system details":displaySystemDetails();
                                             break;
               case "help":                  printCommands();
                                             break;
               case "exit":                  return;
               default:                      printCommands();
            }
            
            System.out.print("Input command: ");
         }
      }
   
   // print available commands
   private void printCommands() {
      System.out.println("Valid commands: ");
      System.out.println("[book seat] [book cabin] [find available flights] [find available trips] ");
      System.out.println("[display system details] [help] [exit] ");
      
      return;
   }
   
   // book a seat
   private void bookSeat(Scanner input) {
      SeatClass sectionClass = null;
      String sectionClassString = null;
      int row = 0;
      char column = 0;
      boolean validClass = false;
      String flightID = null,
             airlineID = null;
      
      System.out.println("Input airline ID for desired flight, or type \"CANCEL\": ");
      airlineID = input.nextLine().toUpperCase();
      
      if (airlineID.equalsIgnoreCase("CANCEL")) {
         return;
      }
      
      else {
         System.out.println("Input flight ID associated with desired airline: ");
         flightID = input.nextLine().toUpperCase();
         
         System.out.println("Input desired class: ");
         sectionClassString = input.nextLine().toUpperCase().replaceAll("\\s","");
         
         System.out.println("Input row number: "); 
         row = Integer.parseInt(input.nextLine());
         
         System.out.println("Input column character: ");
         column = input.next().trim().charAt(0);
         column = Character.toUpperCase(column);
      }
      
      for (SeatClass seatClass : SeatClass.values()) {
         if (seatClass.toString().equals(sectionClassString)) {
            validClass = true;
            sectionClass = SeatClass.valueOf(sectionClassString);
            break;
         }
      }
      if (!validClass) {
         System.out.println("Invalid class section. ");
         return;
      }
      
      if (row < 0 || row > 100) {
         System.out.println("Invalid row number. ");
         return;
      }
      
      if (!(Character.isLetter(column))) {
         System.out.println("Invalid column. ");
         return;
      }
         
      try {
         callback.bookSeat(airlineID, flightID, sectionClass, row, column);
         System.out.println("Seat booked. ");
      }
      catch (Exception e) {
	      System.out.println(e);
	   }
      return;
   }
   
   // book a cabin
   private void bookCabin(Scanner input) {
      CabinClass sectionClass = null;
      String sectionClassString = null;
      int passengers = 0,
          tripIndex = 0;
      boolean validClass = false;
      String cruiselineName = null;
      
      System.out.println("Input cruiseline name, or type \"CANCEL\": ");
      cruiselineName = input.nextLine().toUpperCase();
      
      if (cruiselineName.equalsIgnoreCase("CANCEL")) {
         return;
      }
      
      else {
         System.out.println("Input trip index: ");
         tripIndex = Integer.parseInt(input.nextLine());
         
         System.out.println("Input desired class: ");
         sectionClassString = input.nextLine().toUpperCase().replaceAll("\\s","");
         
         System.out.println("Input number of passengers: "); 
         passengers = Integer.parseInt(input.nextLine());
      }
      
      for (CabinClass cabinClass : CabinClass.values()) {
         if (cabinClass.toString().equals(sectionClassString)) {
            validClass = true;
            sectionClass = CabinClass.valueOf(sectionClassString);
            break;
         }
      }
      if (!validClass) {
         System.out.println("Invalid class section. ");
         return;
      }
         
      try {
         callback.bookCabin(cruiselineName, tripIndex, sectionClass, passengers);
         System.out.println("Cabin booked. ");
      }
      catch (Exception e) {
	      System.out.println(e);
	   }
      return;
   }
   
   // find available flights
   private void findAvailableFlights(Scanner input) {
      String originCode = null,
             destinationCode = null;
             
      System.out.println("Input origin code, or type \"CANCEL\": ");
      originCode = input.nextLine().toUpperCase();
      
      if (originCode.equalsIgnoreCase("CANCEL")) {
         return;
      }
      
      System.out.println("Input destination code, or type \"CANCEL\": ");
      destinationCode = input.nextLine().toUpperCase();
      
      if (destinationCode.equalsIgnoreCase("CANCEL")) {
         return;
      }
      
      if (originCode.equals(destinationCode)) {
         System.out.println("Airport codes were the same, please try again. ");
         return;
      }
      
      else {
         try {
            callback.findAvailableFlights(originCode, destinationCode);
         }
         catch (Exception e) {
		      System.out.println(e);
		   }
      }
   }
   
   // find available trips
   private void findAvailableTrips(Scanner input) {
      String[] tripPorts;
      String line = null;
      
      System.out.println("Input ports. Multiple ports must be seperated by a comma: ");
      line = input.nextLine().toUpperCase();
      line = line.replaceAll("\\s","");
      tripPorts = line.split(",");
      
      if (tripPorts.toString().equalsIgnoreCase("CANCEL")) {
         return;
      }
      
      else {
         try {
            callback.findAvailableTrips(tripPorts);
         }
         catch (Exception e) {
		      System.out.println(e);
		   }
      }
   }
   
   // display system details
   private void displaySystemDetails() {
      callback.displaySystemDetails();
   }   
}