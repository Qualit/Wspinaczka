package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.Collections;



public class Wall
{
     public Wall(final int n, final double w)
     {
	 // konstruktor klasy Wall
     }
	
    
     Set<Grip> grips; // uchwyty na scianie
	
     
	
     private  List<Grip> getFeasibleGrips(final State current, LEG activeLeg, double radius)
     {
	 // dla zadanej konczyny zwraca kontener dopuszczalnych 
	 // uchwytow do przejscia
	 
	 List<Grip> feasibleGrips = new ArrayList<Grip>();
	 
	 // tutaj logika znajdowania dozwolonych wierzcholkow
	 Collections.sort(feasibleGrips, new Comparator<Grip>() {

	    @Override
	    public int compare(Grip grip1, Grip grip2)
	    {
		return new Double(grip1.getY()).compareTo(new Double(grip2.getY()));
	    }
	});
	 return feasibleGrips;
     }
	
	
     
     public void getRandomGrips ()
     {
	 
     }
}
