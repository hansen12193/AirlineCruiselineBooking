/*
	Nick Nestor and Nathan Hansen
	CS 345
	
	CabinClass.java
   
   CabinClass enum
*/


public enum CabinClass { 

	FAMILY, DELUXEFAMILY, COUPLES, DELUXECOUPLES;

	public String toString() {
		switch(this) {
			case FAMILY:			return "FAMILY";
			case DELUXEFAMILY:   return "DELUXEFAMILY";
			case COUPLES:		   return "COUPLES";
         case DELUXECOUPLES:  return "DELUXECOUPLES";
			default:				   return "NULL";
		}
	}
}