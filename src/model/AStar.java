package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import algorithm.Algorithm;
import algorithm.Path;
import algorithm.Point;


// TODO --> this will be algorithm implementation
public class AStar implements Algorithm 
{
    
    	private final Wall wall;
    	private final Set<State> openSet;  // zbior stanow otwartych
    	private final Set<State> closedSet; // zbior stanow zamknietych
    	private final Map<State, Double> gScore; // waga aktualnie najtanszej sciezki
    	private final Map<State, Double> fScore; // gScore + heuristic
    	private final State start; // stan poczatkowy
    	private final State goal; // stan koncowy
    	private State current;
    	

	public AStar(final State start, final State goal, final Wall wall)
	{
	    super();
	    this.wall = wall;
	    this.openSet = new HashSet<State>();
	    this.closedSet = new HashSet<State>();
	    this.gScore = new HashMap<State, Double>();
	    this.fScore = new HashMap<State, Double>();
	    this.start = start;
	    this.goal = goal;
	    this.current = start;
	}

	@Override
	public Path findPath(Point start, Point goal) {
		// TODO Auto-generated method stub
	    
	    	
		return null;
	}
	
	// testy
	public void testy()
	{
		openSet.add(start);
		openSet.add(goal);
		
		for(State s : openSet)
		{
			System.out.println(s.toString());
		}
		
		Map<LEG, Grip> jakasMapa = new HashMap<LEG, Grip>();
		
   	 jakasMapa.put(LEG.LEFT_HAND, wall.getGrips().get(1));
   	 jakasMapa.put(LEG.RIGHT_HAND, wall.getGrips().get(2));
   	 jakasMapa.put(LEG.LEFT_FOOT, wall.getGrips().get(3));
   	 jakasMapa.put(LEG.RIGHT_FOOT, wall.getGrips().get(4));
   	 
		Map<LEG, Grip> jakasMapa2 = new HashMap<LEG, Grip>();
		
	   	 jakasMapa2.put(LEG.LEFT_HAND, wall.getGrips().get(2));
	   	 jakasMapa2.put(LEG.RIGHT_HAND, wall.getGrips().get(2));
	   	 jakasMapa2.put(LEG.LEFT_FOOT, wall.getGrips().get(3));
	   	 jakasMapa2.put(LEG.RIGHT_FOOT, wall.getGrips().get(3));
	   	 
	   	 State jakisStan = new State(jakasMapa, null);
	   	State jakisStan2 = new State(jakasMapa2, null);
	   	 
	   	
			openSet.add(jakisStan);
			openSet.add(jakisStan2);
	   	 
			for(State s : openSet)
			{
				System.out.println(s.toString());
			}
			
	}

}
