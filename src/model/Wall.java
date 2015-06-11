package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.TreeMap;

import mockups.GripMockup;
import mockups.WallMockup;
import configuration.*;


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

	public  List<Grip> getFeasibleGrips(final State current, LEG activeLeg)
     {
    	 // dla zadanej konczyny zwraca kontener dopuszczalnych 
    	 // uchwytow do przejscia
	 
    	 List<Grip> feasibleGrips = new ArrayList<Grip>();
    	 for (int i = firstProbableGrip(current, activeLeg) ; isStillProbable(current, activeLeg, i) ; i++)
//    	 for (int i = firstFeasibleGrip(current, activeLeg) ; lastFeasibleGrip(current, activeLeg, radius) ; i++)
//    	 for (int i = current.getLegGrip(activeLeg).getIdGrip()+1; i<=n+1 ; i++)
    	 {
    		 if(grips.get(i).isFeasible(current, activeLeg))
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
	
	private boolean isStillProbable(State current, LEG activeLeg, int i) 
	{
		switch (activeLeg)
		{
			case LEFT_HAND:
			{
				if(i > n+1)
				{
					return false;
				}
				if(grips.get(i).getY() - current.getLegGrip(activeLeg).getY() <= Configuration.radius)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			case RIGHT_HAND:
			{
				if(i > n+1)
				{
					return false;
				}
				if(grips.get(i).getY() - current.getLegGrip(activeLeg).getY() <= Configuration.radius)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			case LEFT_FOOT:	// nie wyzej niz rece
			{
				if(current.getLegGrip(LEG.LEFT_HAND).getIdGrip() <= current.getLegGrip(LEG.RIGHT_HAND).getIdGrip())
				{
					if(i < current.getLegGrip(LEG.LEFT_HAND).getIdGrip())
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				else
				{
					if(i < current.getLegGrip(LEG.RIGHT_HAND).getIdGrip())
					{
						return true;
					}
					else
					{
						return false;
					}
				}
			}
			case RIGHT_FOOT: // nie wyzej niz rece
			{
				if(current.getLegGrip(LEG.LEFT_HAND).getIdGrip() <= current.getLegGrip(LEG.RIGHT_HAND).getIdGrip())
				{
					if(i < current.getLegGrip(LEG.LEFT_HAND).getIdGrip())
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				else
				{
					if(i < current.getLegGrip(LEG.RIGHT_HAND).getIdGrip())
					{
						return true;
					}
					else
					{
						return false;
					}
				}
			}
			default:
			{
				System.exit(1);
				return false;
			}
		}
	}

	private int firstProbableGrip(State current, LEG activeLeg) 
	{
		return current.getLegGrip(activeLeg).getIdGrip()+1;
	}
//	
//	private int lastFeasibleGrip(State current, LEG activeLeg, double radius) 
//	{
//
//	}

//	private int gripLessThen2MetersAway(double currentLegY, Grip lowestGrip, Grip highestGrip, double radius) 
//	{
//
//	}

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

	public WallMockup getWallMockup(State currentState) 
	{
		double lowerBound;
		double upperBound;
		
		if(currentState.getLegState().get(LEG.LEFT_FOOT).getY() <= currentState.getLegState().get(LEG.RIGHT_FOOT).getY())
		{
			lowerBound = currentState.getLegState().get(LEG.LEFT_FOOT).getY() - 1;
			if(lowerBound < 0)
			{
				lowerBound = 0;
			}
			upperBound = lowerBound + Configuration.visibleWallHeight;
			if(upperBound > w)
			{
				upperBound = w;
				lowerBound = upperBound - Configuration.visibleWallHeight;
			}
		}
		else
		{
			lowerBound = currentState.getLegState().get(LEG.RIGHT_FOOT).getY() - 1;
			if(lowerBound < 0)
			{
				lowerBound = 0;
			}
			upperBound = lowerBound + Configuration.visibleWallHeight;
			if(upperBound > w)
			{
				upperBound = w;
				lowerBound = upperBound - Configuration.visibleWallHeight;
			}
		}
		
		List<Grip> gripsList = getGripsBetween(lowerBound, upperBound);
		
		Map<Integer, GripMockup> gripMockupsList = new TreeMap<Integer, GripMockup>();
		for (Grip g : gripsList)
		{
			gripMockupsList.put(g.getIdGrip(), new GripMockup(g));
		}
		
		Integer leftHandId = currentState.getLegState().get(LEG.LEFT_HAND).getIdGrip();
		Integer rightHandId = currentState.getLegState().get(LEG.RIGHT_HAND).getIdGrip();
		Integer leftFootId = currentState.getLegState().get(LEG.LEFT_FOOT).getIdGrip();
		Integer rightFootId = currentState.getLegState().get(LEG.RIGHT_FOOT).getIdGrip();
		
		(gripMockupsList.get(leftHandId)).setLeg(LEG.LEFT_HAND);
		(gripMockupsList.get(rightHandId)).setLeg(LEG.RIGHT_HAND);
		(gripMockupsList.get(leftFootId)).setLeg(LEG.LEFT_FOOT);
		(gripMockupsList.get(rightFootId)).setLeg(LEG.RIGHT_FOOT);
		
		WallMockup wallMochup = new WallMockup(gripMockupsList);
	
		return wallMochup;
	}

	public List<Grip> getGripsBetween(double lowerBound, double upperBound) 
	{
		List<Grip> gripsList = new ArrayList<Grip>();
		for (Grip g : grips)
		{
			if(g.getY() < lowerBound)
			{
				continue;
			}
			if(g.getY() > upperBound)
			{
				break;
			}
			gripsList.add(g);
		}
		return gripsList;
	}
}
