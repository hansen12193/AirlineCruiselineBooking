/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	Port.java 
   
   Port concrete class derived from Location abstract base class
*/

public class Port extends Location {

   //constructor
	public Port(String portName) {
		super(portName);
	}
	
   // get port name
	public String getPortName() {
		return super.getLocationName();
	}
}