/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	SystemManager.java 
   
   SystemManager class is in charge of running the entire booking program.
   It will prompt the user to either enter the admin interface (and then send them
   to the admin class to handle the rest) or the client interface (and then
   send them to the client class to handle the rest). Once the user enters the
   client or admin class and chooses a command, that command is sent back here 
   to the system manager, processed, and then directed to wherever it needs to go.
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class SystemManager implements AdminFunctions, ClientFunctions {
   private static SystemManager instance = new SystemManager();
   private static Scanner input = new Scanner(System.in);
   private static Admin adminInterface = new Admin();
   private static Client clientInterface = new Client();
   private List<Airport> airports = new ArrayList<Airport>();
   private List<Port> ports = new ArrayList<Port>();
   private List<Airline> airlines = new ArrayList<Airline>();
   private List<Cruiseline> cruiselines = new ArrayList<Cruiseline>();
   
   // initiate Singleton of system manager
   private SystemManager() {
   
   }
   
   // only access point back to system manager
   public static SystemManager getInstance() {
      return instance;
   }

   public static void main (String[] args) {      
      System.out.println("//----------- WELCOME TO SYSTEM MANAGER -----------\\ ");
      System.out.println("Please type A and hit enter for the admin interface, ");
      System.out.println("type C and hit enter for the client interface, or ");
      System.out.println("type EXIT and hit enter to terminate the program. ");
      System.out.println();
      
      while (true) {
         String line = input.nextLine();
      
         if (line.equalsIgnoreCase("EXIT")) {
            System.out.println("Exiting the system manager. ");
            return;
         }
         else if (line.equalsIgnoreCase("A")) {
            adminInterface.start(input);
         }
         else if (line.equalsIgnoreCase("C")) {
            clientInterface.start(input);
         }
         else {
            System.out.println("Invalid command. ");
         }
         
         System.out.println("\n[A] admin interface - [C] client interface - [EXIT] terminate ");
      }
   }
   
   // create airport (callback)
   public void createAirport(String airportCode) throws Exception {
      for (Airport airport : airports) {
         if (airportCode.equals(airport.getAirportCode())) {
            throw new Exception("Airport code already exists. ");
         }
      }
      
      airports.add(new Airport(airportCode));
      return;
   }
   
   // create port (callback)
   public void createPort(String portName) throws Exception {
      for (Port port : ports) {
         if (portName.equals(port.getPortName())) {
            throw new Exception("Port name already exists. ");
         }
      }
      
      ports.add(new Port(portName));
      return;
   }
   
   // create airline (callback)
   public void createAirline(String airlineID) throws Exception {
      for (Airline airline : airlines) {
         if (airlineID.equals(airline.getAirlineID())) {
            throw new Exception("Airline ID already exists. ");
         }
      }
      
      airlines.add(new Airline(airlineID));
      return;
   }
   
   // create cruiseline (callback)
   public void createCruiseline(String cruiselineName) throws Exception {
      for (Cruiseline cruiseline : cruiselines) {
         if (cruiselineName.equals(cruiseline.getCruiselineName())) {
            throw new Exception("Cruiseline name already exists. ");
         }
      }
      
      cruiselines.add(new Cruiseline(cruiselineName));
      return;
   }
   
   // create flight (callback)
   public void createFlight(String airlineID, String originCode, String destinationCode, int y, int m, int d, String flightID) throws Exception {
      boolean foundAirline = false,
              foundOrigin = false,
              foundDestination = false;
      Airline airlineForFlight = null;
   
      for (Airline airline : airlines) {
         if (!foundAirline && airlineID.equals(airline.getAirlineID())) {
            foundAirline = true;
            airlineForFlight = airline;
         }
      }
      
      if (!foundAirline) {
         throw new Exception("Airline ID not found. ");
      }
      else {
         for (Airport airport : airports) {
            if (!foundOrigin && originCode.equals(airport.getAirportCode())) {
               foundOrigin = true;
            }
         }
         
         for (Airport airport : airports) {
            if (!foundDestination && destinationCode.equals(airport.getAirportCode())) {
               foundDestination = true;
            }
         }
         
         if (!foundOrigin || !foundDestination) {
            throw new Exception("One or both airport codes not found. ");
         }
         else {
            airlineForFlight.createFlight(originCode, destinationCode, y, m, d, flightID);
         }
      }
   }
   
   // create trip (callback)
   public void createTrip(String cruiselineName, String[] tripPorts, String shipName, int sy, int sm , int sd, int ey, int em, int ed) throws Exception {
      boolean foundCruiseline = false;
      Cruiseline cruiselineForTrip = null;
      
      for (Cruiseline cruiseline : cruiselines) {
         if (!foundCruiseline && cruiselineName.equals(cruiseline.getCruiselineName())) {
            foundCruiseline = true;
            cruiselineForTrip = cruiseline;
         }
      }
      
      if (!foundCruiseline) {
         throw new Exception("Cruiseline name not found. ");
      }
      
      else {
         for (String tripPort : tripPorts) {
            boolean foundPort = false;
            
            for (Port port : ports) {
               if (!foundPort && tripPort.equals(port.getPortName())) {
                  foundPort = true;
               }
            }
            
            if (!foundPort) {
               throw new Exception("Port not found. ");
            }
         }
         
         cruiselineForTrip.createTrip(tripPorts, shipName, sy, sm , sd, ey, em, ed);
      }   
   }
   
   // create flight section (callback)
   public void createFlightSection(String airlineID, String flightID, int rows, int columns, SeatClass sectionClass) throws Exception  {
      boolean foundAirline = false;
      Airline airlineForSection = null;
   
      for (Airline airline : airlines) {
         if (!foundAirline && airlineID.equals(airline.getAirlineID())) {
            foundAirline = true;
            airlineForSection = airline;
         }
      }
      
      if (!foundAirline) {
         throw new Exception("Airline ID not found. ");
      }
      
      else {
         airlineForSection.createSection(flightID, rows, columns, sectionClass);
      }
   }
   
   // create cabin section (callback)
   public void createCabinSection(String cruiselineName, int tripIndex, int cabins, CabinClass sectionClass) throws Exception {
      boolean foundCruiseline = false;
      Cruiseline cruiselineForSection = null;
   
      for (Cruiseline cruiseline : cruiselines) {
         if (!foundCruiseline && cruiselineName.equals(cruiseline.getCruiselineName())) {
            foundCruiseline = true;
            cruiselineForSection = cruiseline;
         }
      }
      
      if (!foundCruiseline) {
         throw new Exception("Cruiseline name not found. ");
      }
      
      else {
         cruiselineForSection.createSection(tripIndex, cabins, sectionClass);
      }
   }
	
	// book a seat (callback)
	public void bookSeat(String airlineID, String flightID, SeatClass sectionClass, int row, char column) throws Exception {
		boolean foundAirline = false;
      Airline airlineForSeat = null;
		
		for (Airline airline : airlines) {
         if (!foundAirline && airlineID.equals(airline.getAirlineID())) {
            foundAirline = true;
            airlineForSeat = airline;
         }
      }
      
      if (!foundAirline) {
         throw new Exception("Airline ID not found. ");
      }
      
      else {
         airlineForSeat.bookSeat(flightID, sectionClass, row, column);
      }
	}
	
   //book a cabin (callback)
	public void bookCabin(String cruiselineName, int tripIndex, CabinClass sectionClass, int passengers) throws Exception {
		boolean foundCruiseline = false;
      Cruiseline cruiselineForCabin = null;
		
		for (Cruiseline cruiseline : cruiselines) {
         if (!foundCruiseline && cruiselineName.equalsIgnoreCase(cruiseline.getCruiselineName())) {
            foundCruiseline = true;
            cruiselineForCabin = cruiseline;
         }
      }
      
      if (!foundCruiseline) {
         throw new Exception("Cruiseline name not found. ");
      }
      
      else {
         cruiselineForCabin.bookCabin(tripIndex, sectionClass, passengers);
      }
	}
	
	// find flights (callback)
	public void findAvailableFlights(String originCode, String destinationCode) throws Exception {
		System.out.println("Flights from " + originCode + " to " + destinationCode + ": ");
		
		for (Airline airline : airlines) {
         System.out.println(airline.getAirlineID());
			airline.findAvailableFlights(originCode, destinationCode);
		}
      
      System.out.println();
	}
	
	// find trips (callback)
	public void findAvailableTrips(String[] tripPorts) throws Exception {
		System.out.println("Trips including " + Arrays.toString(tripPorts) + ": ");
		
		for (Cruiseline cruiseline : cruiselines) {
			cruiseline.findAvailableTrips(tripPorts);
		}
      
      System.out.println();
	}
	
	// display system details (callback)
	public void displaySystemDetails() {
		for (Airport airport : airports) {
			System.out.println("Airport code: " + airport.getAirportCode());
		}
      
      System.out.println();
      
		for (Port port : ports) {
			System.out.println("Port name: " + port.getPortName());
		}
      
      System.out.println();
      
		for (Airline airline : airlines) {
			airline.displayAirlineDetails();
		}
      
      System.out.println();
      
		for (Cruiseline cruiseline : cruiselines) {
			cruiseline.displayCruiselineDetails();
		}
      
      System.out.println();
	}
}