/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	SeatClass.java
   
   SeatClass enum
*/


public enum SeatClass { 

	FIRST, BUSINESS, ECONOMY;

	public String toString() {
		switch(this) {
			case FIRST:			return "FIRST";
			case BUSINESS:		return "BUSINESS";
			case ECONOMY:		return "ECONOMY";
			default:				return "NULL";
		}
	}
}