/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	ClientFunctions.java 
   
	ClientFunctions interface forces Client to initiate these methods.
*/

public interface ClientFunctions {
   public void bookSeat(String airlineID, String flightID, SeatClass sectionClass, int row, char column) throws Exception;
	public void bookCabin(String cruiselineName, int tripIndex, CabinClass sectionClass, int passengers) throws Exception;
	public void findAvailableFlights(String originCode, String destinationCode) throws Exception;
	public void findAvailableTrips(String[] tripPorts) throws Exception;
	public void displaySystemDetails();
}