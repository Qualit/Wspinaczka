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
    	 for (int i = firstFeasibleGrip(current, activeLeg) ; i<= lastFeasibleGrip(current, activeLeg) ; i++)
//    	 for (int i = current.getLegGrip(activeLeg).getIdGrip()+1; i<=n+1 ; i++)
    	 {
    		 if(grips.get(i).isFeasible(current, activeLeg, radius))
    		 {
    			 feasibleGrips.add(grips.get(i));
    		 }
    	 }
	 
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
	
	private int firstFeasibleGrip(State current, LEG activeLeg) 
	{
		return current.getLegGrip(activeLeg).getIdGrip()+1;
	}
	
	private int lastFeasibleGrip(State current, LEG activeLeg) 
	{
		switch (activeLeg)
		{
			case LEFT_HAND:
			{
				return (this.n+1);
			}
			case RIGHT_HAND:
			{
				return (this.n+1);
			}
			case LEFT_FOOT:
			{
				if(current.getLegGrip(LEG.LEFT_HAND).getIdGrip() <= current.getLegGrip(LEG.RIGHT_HAND).getIdGrip())
				{
					return current.getLegGrip(LEG.LEFT_HAND).getIdGrip()-1;
				}
				else
				{
					return current.getLegGrip(LEG.RIGHT_HAND).getIdGrip()-1;
				}
			}
			case RIGHT_FOOT:
			{
				if(current.getLegGrip(LEG.LEFT_HAND).getIdGrip() <= current.getLegGrip(LEG.RIGHT_HAND).getIdGrip())
				{
					return current.getLegGrip(LEG.LEFT_HAND).getIdGrip()-1;
				}
				else
				{
					return current.getLegGrip(LEG.RIGHT_HAND).getIdGrip()-1;
				}
			}
			default:
			{
				System.exit(1);
				return 0;
			}
		}
	}

	public final int getN() 
	{
		return n;
	}

	public final double getW() 
	{
		return w;
	}

	public final List<Grip> getGrips() 
	{
		return grips;
	}

	public final State getStart() 
	{
		return start;
	}

	public final State getGoal() 
	{
		return goal;
	}
}
