package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class Wall
{
     private final List<Grip> grips; // uchwyty na scianie
     private final int n;	// ilosc uchwytow na scianie
     private final double w; // wysokosc sciany
     private final State start; //stan poczatkowy
     private final State goal; // stan koncowy
     
     // konstruktor sciany losujacy parametry
     public Wall(List<Grip> grips, final int n, final double w)
     {
    	 this.grips = grips;
    	 this.n = n;
    	 this.w = w;
    	 
    	 Map<LEG, Grip> startowaMapa = new HashMap<LEG, Grip>();
    	 Map<LEG, Grip> koncowaMapa = new HashMap<LEG, Grip>();
    	 
    	 startowaMapa.put(LEG.LEFT_HAND, grips.get(1));
    	 startowaMapa.put(LEG.RIGHT_HAND, grips.get(1));
    	 startowaMapa.put(LEG.LEFT_FOOT, grips.get(0));
    	 startowaMapa.put(LEG.RIGHT_FOOT, grips.get(0));
    	 
    	 koncowaMapa.put(LEG.LEFT_HAND, grips.get(this.n+1));
    	 koncowaMapa.put(LEG.RIGHT_HAND, grips.get(this.n+1));
    	 koncowaMapa.put(LEG.LEFT_FOOT, grips.get(this.n));
    	 koncowaMapa.put(LEG.RIGHT_FOOT, grips.get(this.n));
    	 
    	 this.start = new State(startowaMapa, null);
    	 this.goal = new State(koncowaMapa, null);
     }     

	public  List<Grip> getFeasibleGrips(final State current, LEG activeLeg, double radius)
     {
    	 // dla zadanej konczyny zwraca kontener dopuszczalnych 
    	 // uchwytow do przejscia
	 
    	 List<Grip> feasibleGrips = new ArrayList<Grip>();
    	 
    	 for (Grip g : grips)
    	 {
    		 feasibleGrips.add(g);
    	 }
    	 
//    	 for (int i = 0; true ; i++)
//    	 {
//    		 
//    	 }
	 
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
	
	
	
	public final int getN() {
		return n;
	}

	public final double getW() {
		return w;
	}

	public final List<Grip> getGrips() {
		return grips;
	}

	public final State getStart() {
		return start;
	}

	public final State getGoal() {
		return goal;
	}
}
