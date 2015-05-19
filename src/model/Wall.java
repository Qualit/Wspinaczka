package model;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Wall
{
     public Wall(final int n, final double w)
     {
	 // konstruktor klasy Wall
     }
	
     private HashMap<Enum, Leg> legs; // konczyny pajaka
     TreeSet<Grip> grips; // uchwyty na scianie
	
	// dla zadanej konczyny zwraca kontener dopuszczalnych 
	// uchwytow do przejscia
	
     private Set getFeasibleGrips(Leg currentLeg, double radius)
     {
	 return null;
     }
	
	
     public Boolean move(Leg leg, Grip nextGrip)
     {
		leg.addGrip(nextGrip);
		leg.updateCost(nextGrip);
		
		return false;
		// plus metoda do pamietania sladu 
	}
}
