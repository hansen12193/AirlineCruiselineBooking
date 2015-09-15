/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	Cabin class in charge of creating a and booking a cabin.
*/

public class Cabin {
	private boolean bookedState = false;
   
   // constructor
   public Cabin() {
      bookedState = false;
   }
	
	// book cabin
	public void bookCabin() {
		bookedState = true;
	}
   
   // check cabin
	public boolean isBooked() {
		return bookedState;
	}
}