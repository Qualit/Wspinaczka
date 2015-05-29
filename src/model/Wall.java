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
     private final int n;	// ilosc uchwytow na scianie
     private final double w; // wysokosc sciany
     private final State start; //stan poczatkowy
     private final State goal; // stan koncowy
     
     // konstruktor sciany losujacy parametry
     public Wall(Map<Integer, Grip> grips2, final int n, final double w)
     {
    	 this.n = n;
    	 this.w = w;
    	 
    	 
    	 // wszystko poniezej napisane dla celow TESTOWYCH
    	 this.grips = new HashMap<Integer, Grip>();
    	 grips.put(new Integer(1), new Grip(1.1, 2.05, 1.5));
    	 grips.put(new Integer(2), new Grip(2.1, 3.34, 1.12));
    	 grips.put(new Integer(3), new Grip(2.2, 1.24, 1.27));
    	 grips.put(new Integer(4), new Grip(1.2, 5.16, 1.7));
    	 grips.put(new Integer(5), new Grip(2.3, 4.75, 1.05));

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
    
	// konstruktor sciany czytajacy dane z pliku
    public Wall(final String fileName) 
    {
    	this.grips = null;
    	this.n = 0;
    	this.w = 0;
    	this.start = null;
    	this.goal = null;
		// TODO Auto-generated constructor stub
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
