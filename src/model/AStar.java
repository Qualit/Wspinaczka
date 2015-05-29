package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import algorithm.AbstractState;
import algorithm.Algorithm;
import algorithm.Path;


// TODO --> this will be algorithm implementation
public class AStar implements Algorithm 
{
    
    	private final Model model;
    	private final Set<State> openSet;  // zbior stanow otwartych
    	private final Set<State> closedSet; // zbior stanow zamknietych
    	private final Map<State, Double> gScore; // waga aktualnie najtanszej sciezki
    	private final Map<State, Double> fScore; // gScore + heuristic
    	private final State start; // stan poczatkowy
    	private final State goal; // stan koncowy
    	private State current;
    	

	public AStar(Model model) 
	{
	    super();
	    this.model = model;
	    this.openSet = new HashSet<State>();
	    this.closedSet = new HashSet<State>();
	    this.gScore = new HashMap<State, Double>();
	    this.fScore = new HashMap<State, Double>();
	    this.start = model.getWall().getStart();
	    this.goal = model.getWall().getGoal();
	    this.current = start;
	}

	@Override
	public Path findPath(AbstractState start, AbstractState goal) {
		// TODO Auto-generated method stub
	    
	    	
		return null;
	}
	
	// testy
	public void testy()
	{
			for (Grip g : model.getWall().getGrips().values())
			{
				System.out.print(g.toString());
			}
			
	}

}
