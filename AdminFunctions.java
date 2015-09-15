/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	AdminFunctions.java 
   
   AdminFunctions interface forces Admin to initiate these methods.
*/

public interface AdminFunctions {
   public void createAirport(String airportCode) throws Exception;
   public void createPort(String portName) throws Exception;
   public void createAirline(String airlineID) throws Exception;
   public void createCruiseline(String cruiselineName) throws Exception;
   public void createFlight(String airlineID, String originCode, String destinationCode, int y, int m, int d, String flightID) throws Exception;
   public void createTrip(String cruiselineName, String[] tripPorts, String shipName, int sy, int sm ,int sd, int ey, int em, int ed) throws Exception;
   public void createFlightSection(String airlineID, String flightID, int rows, int columns, SeatClass sectionClass) throws Exception;
   public void createCabinSection(String cruiselineName, int tripIndex, int cabins, CabinClass sectionClass) throws Exception;
}