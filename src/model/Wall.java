package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class Wall
{
     private final Map<Integer, Grip> grips; // uchwyty na scianie
     private final State start; //stan poczatkowy
     private final State goal; // stan koncowy
     
     // konstruktor sciany losujacy parametry
     public Wall(final int n, final double w)
     {
    	 
    	 
    	 
    	 // wszystko poniezej napisane dla celow TESTOWYCH
    	 this.grips = new HashMap<Integer, Grip>();
    	 grips.put(new Integer(1), new Grip(1.1, 2.05, 1.5, 5.2));
    	 grips.put(new Integer(2), new Grip(2.1, 3.34, 1.12, 2.11));
    	 grips.put(new Integer(3), new Grip(2.2, 1.24, 1.27, 4.04));
    	 grips.put(new Integer(4), new Grip(1.2, 5.16, 1.7, 1.97));
    	 grips.put(new Integer(5), new Grip(2.3, 4.75, 1.05, 3.33));

    	 Map<LEG, Grip> startowaMapa = new HashMap<LEG, Grip>();
    	 Map<LEG, Grip> koncowaMapa = new HashMap<LEG, Grip>();
    	 
    	 startowaMapa.put(LEG.LEFT_HAND, grips.get(1));
    	 startowaMapa.put(LEG.RIGHT_HAND, grips.get(2));
    	 startowaMapa.put(LEG.LEFT_FOOT, grips.get(3));
    	 startowaMapa.put(LEG.RIGHT_FOOT, grips.get(4));
    	 
    	 koncowaMapa.put(LEG.LEFT_HAND, grips.get(2));
    	 koncowaMapa.put(LEG.RIGHT_HAND, grips.get(3));
    	 koncowaMapa.put(LEG.LEFT_FOOT, grips.get(4));
    	 koncowaMapa.put(LEG.RIGHT_FOOT, grips.get(5));
    	 
    	 this.start = new State(startowaMapa, null);
    	 this.goal = new State(koncowaMapa, null);
     }     
    
	
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
    			 return new Double(grip1.getCost()).compareTo(new Double(grip2.getCost()));
    		 }
    	 });
    	 return feasibleGrips;
     }
	
     public void getRandomGrips ()
     {
	 
     }

	public final Map<Integer, Grip> getGrips() {
		return grips;
	}

	public final State getStart() {
		return start;
	}

	public final State getGoal() {
		return goal;
	}
}
