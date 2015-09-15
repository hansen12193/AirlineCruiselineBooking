/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	Admin.java 
   
   Admin class is called when the user presses "a" from the system manager. It handles creating 
   everything needed to fly or cruise. Each method also handles basic error handling for
   the input format.
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Admin {
   private SystemManager callback = SystemManager.getInstance();

   public void start(Scanner input) {
      String password = "password";
      
      System.out.println("//---- WELCOME TO ADMIN INTERFACE ----\\ ");
      System.out.println("Please verify password. ");
      System.out.println(" *hint* it's password ");
      
      if (!input.next().equals(password)) {
         System.out.println("Incorrect password - it wasn't \"password\". ");
         return;
      }
      
      else {
         System.out.println("Password verified. \n");
      
         while (true) {
            String line = input.nextLine();
            
            switch (line) {
               case "create airport":        createAirport(input);
                                             break;
               case "create port":           createPort(input);
                                             break;
               case "create airline":        createAirline(input);
                                             break;                                                      
               case "create cruiseline":     createCruiseline(input);
                                             break;
               case "create flight":         createFlight(input);
                                             break;
               case "create trip":           createTrip(input);
                                             break; 
               case "create flight section": createFlightSection(input);
                                             break;
               case "create cabin section":  createCabinSection(input);
                                             break;
               case "help":                  printCommands();
                                             break;
               case "exit":                  return;
               default:                      printCommands();
            }
            
            System.out.print("Input command: ");
         }
      }
   }
   
   // print available commands
   private void printCommands() {
      System.out.println("Valid commands: ");
      System.out.println("[create airport] [create port] [create airline] [create cruiseline] ");
      System.out.println("[create flight] [create trip] [create flight section] [create cabin section] ");
      System.out.println("[help] [exit] ");
      
      return;
   }
   
   // create airport
   private void createAirport(Scanner input) {
      String airportCode = null;
      
      System.out.println("Input new airport code (must be 3 alphanumeric characters), or type \"CANCEL\": ");
      airportCode = input.nextLine().toUpperCase();
      
      while (airportCode.length() != 3 && !airportCode.equalsIgnoreCase("CANCEL")) {
         System.out.println("Airport code must be 3 alphanumeric characters. Type \"CANCEL\" to return. ");
         airportCode = input.nextLine().toUpperCase();
      }
      
      if (airportCode.equalsIgnoreCase("CANCEL")) {
         return;
      }
      
      else {
         try {
            callback.createAirport(airportCode);
            System.out.println("Airport created: " + airportCode);
         }
         catch (Exception e) {
		      System.out.println(e);
		   }
      }
      return;
   }
   
   // create port
   private void createPort(Scanner input) {
      String portName = null;
      
      System.out.println("Input new port name, or type \"CANCEL\": ");
      portName = input.nextLine().toUpperCase();
      portName = portName.replaceAll("\\s","");
      
      if (portName.equalsIgnoreCase("CANCEL")) {
         return;
      }
      
      else {
         try {
            callback.createPort(portName);
            System.out.println("Port created: " + portName);
         }
         catch (Exception e) {
		      System.out.println(e);
		   }   
      }
      return;
   }
   
   // create airline
   private void createAirline(Scanner input) {
      String airlineID = null;
      
      System.out.println("Input new airline ID (must be between 1 and 5 alphanumeric characters), or type \"CANCEL\": ");
      airlineID = input.nextLine().toUpperCase();
      
      while (((airlineID.length() < 1) || (airlineID.length() > 5)) && !airlineID.equalsIgnoreCase("CANCEL")) {
         System.out.println("Airline ID must be between 1 and 5 alphanumeric characters. Type \"CANCEL\" to return. ");
         airlineID = input.nextLine().toUpperCase();
      }
      
      if (airlineID.equalsIgnoreCase("CANCEL")) {
         return;
      }
      
      else {
         try {
            callback.createAirline(airlineID);
            System.out.println("Airline created: " + airlineID);
         }
         catch (Exception e) {
		      System.out.println(e);
		   }
      }
      return;
   }
   
   // create cruiseline
   private void createCruiseline(Scanner input) {
      String cruiselineName = null;
      
      System.out.println("Input new cruiseline name, or type \"CANCEL\": ");
      cruiselineName = input.nextLine().toUpperCase();
      
      if (cruiselineName.equalsIgnoreCase("CANCEL")) {
         return;
      }
      
      else {
         try {
            callback.createCruiseline(cruiselineName);
            System.out.println("Cruiseline created: " + cruiselineName);
         }
         catch (Exception e) {
		      System.out.println(e);
		   }
      }
      return;
   }
   
   // create flight
   private void createFlight(Scanner input) {
      String airlineID = null,
             originCode = null,
			    destinationCode = null,
				 flightID = null;
      int y = 0,
          m = 0,
          d = 0;
                  
      System.out.println("Input airline ID, or type \"CANCEL\": ");
      airlineID = input.nextLine().toUpperCase();
      
      if (airlineID.equalsIgnoreCase("CANCEL")) {
         return;
      }
      
      else {
         System.out.println("Input origin code. ");
         originCode = input.nextLine().toUpperCase();
         System.out.println("Input destination code. ");
         destinationCode = input.nextLine().toUpperCase();
         System.out.println("Input flight ID ");
         flightID = input.nextLine().toUpperCase();
         System.out.println("Input departure year. ");
         y = Integer.parseInt(input.nextLine());
         System.out.println("Input departure month. ");
         m = Integer.parseInt(input.nextLine());
         System.out.println("Input departure day. ");
         d = Integer.parseInt(input.nextLine());  
      }
      
      if (originCode.equals(destinationCode)) {
         System.out.println("Airport codes were the same, please try again. ");
         return;
      }
      
      if (((y < 2015) || (y > 2020)) || ((m < 1) || (m > 12)) || ((d < 1) || (d > 31))) {
         System.out.println("Date format was invalid, please try again. ");
         return;
      }
      
      try {
         callback.createFlight(airlineID, originCode, destinationCode, y, m, d, flightID);
         System.out.println("Flight created: " + flightID);
      }
      catch (Exception e) {
		   System.out.println(e);
		}
      return;
   }
   
   // create trip
   private void createTrip(Scanner input) {
      String cruiselineName = null,
             shipName = null,
             line = null;
      String[] tripPorts;
      int sy, ey,
          sm, em,
          sd, ed;
      
      System.out.println("Input cruiseline name, or type \"CANCEL\": ");
      cruiselineName = input.nextLine().toUpperCase();
      if (cruiselineName.equalsIgnoreCase("CANCEL")) {
         return;
      }
      
      System.out.println("Input ports. Multiple ports must be seperated by a comma: ");
      line = input.nextLine().toUpperCase();
      line = line.replaceAll("\\s","");
      tripPorts = line.split(",");
      
      System.out.println("Input ship name: ");
      shipName = input.nextLine().toUpperCase();
      shipName = shipName.replaceAll("\\s","");
      
      
      System.out.println("Input departure year: ");
      sy = Integer.parseInt(input.nextLine());
      if (!((sy > 2014) && (sy <= 2020))) {
         System.out.println("Departure year must be greater than 2014 and less than 2020. ");
         return;
      }
      
      System.out.println("Input departure month: ");
      sm = Integer.parseInt(input.nextLine());
      if (!((sm > 0) && (sm <= 12))) {
         System.out.println("Departure month must be greater than 0 and less than 13. ");
         return;
      }
      
      System.out.println("Input departure day: ");
      sd = Integer.parseInt(input.nextLine());
      if (!((sd > 0) && (sd <= 31))) {
         System.out.println("Departure day must be greater than 0 and less than 32. ");
         return;
      }
      
      System.out.println("Input arrival year: ");
      ey = Integer.parseInt(input.nextLine());
      if (!((ey > 2014) && (ey <= 2020))) {
         System.out.println("Arrival year must be greater than 2014 and less than 2020. ");
         return;
      }
      
      System.out.println("Input arrival month: ");
      em = Integer.parseInt(input.nextLine());
      if (!((em > 0) && (em <= 13))) {
         System.out.println("Arrival month must be greater than 0 and less than 13. ");
         return;
      }
      
      System.out.println("Input arrival day: ");
      ed = Integer.parseInt(input.nextLine());
      if (!((ed > 0) && (ed <= 31))) {
         System.out.println("Arrival day must be greater than 0 and less than 32. ");
         return;
      }
      
      try {
         callback.createTrip(cruiselineName, tripPorts, shipName, sy, sm, sd, ey, em, ed);
         System.out.println("Trip created: " + Arrays.toString(tripPorts));
      }
      catch (Exception e) {
		   System.out.println(e);
		}
      return;
   }
   
   // create flight section
   private void createFlightSection(Scanner input) {
      String airlineID = null,
             flightID = null;
      String sectionClassString = null;
      int rows = -1,
          columns = -1;
      boolean validClass = false;
      SeatClass sectionClass = null;
      
      System.out.println("Input airline ID, or type \"CANCEL\": ");
      airlineID = input.nextLine().toUpperCase();
      if (airlineID.equalsIgnoreCase("CANCEL")) {
         return;
      }
      
      System.out.println("Input flight ID: ");
      flightID = input.nextLine().toUpperCase(); 
      
      System.out.println("Input number of rows: ");
      rows = Integer.parseInt(input.nextLine());
      if (!((rows > 0) && (rows <= 100))) {
         System.out.println("Number of rows must be greater than 0 and less than 101. ");
         return;
      }

      System.out.println("Input number of columns: ");
      columns = Integer.parseInt(input.nextLine());
      if (!((columns > 0) && (columns <= 10))) {
         System.out.println("Number of columns must be greater than 0 and less than 11. ");
         return;
      }
      
      System.out.println("Input section class: ");
      sectionClassString = input.nextLine().toUpperCase();
      
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
      
      try {
         callback.createFlightSection(airlineID, flightID, rows, columns, sectionClass);
         System.out.println(sectionClass + " class section created on " + airlineID + " flight " + flightID);
      }
      catch (Exception e) {
		   System.out.println(e);
		}
      return;
   }
   
   // create cabin section
   private void createCabinSection(Scanner input) {
      String cruiselineName = null;
      String sectionClassString = null;
      int cabins = -1, tripIndex = -1;
      CabinClass sectionClass = null;
      boolean validClass = false;
      
      System.out.println("Input cruiseline name, or type \"CANCEL\": ");
      cruiselineName = input.nextLine().toUpperCase();
      if (cruiselineName.equalsIgnoreCase("CANCEL")) {
         return;
      }

      System.out.println("Input trip index: ");
      tripIndex = Integer.parseInt(input.nextLine());
      
      System.out.println("Input number of cabins: ");
      cabins = Integer.parseInt(input.nextLine());

      System.out.println("Input section class: ");
      sectionClassString = input.nextLine().toUpperCase().replaceAll("\\s","");
      
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
         callback.createCabinSection(cruiselineName, tripIndex, cabins, sectionClass);
         System.out.println("Cabin section created: " + sectionClass + " on " + cruiselineName + " trip " + tripIndex);
      }
      catch (Exception e) {
		   System.out.println(e);
		}
      return;
   }
}