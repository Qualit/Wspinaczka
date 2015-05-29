package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import wspinaczka.Configuration;

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
//    	private final State start; // stan poczatkowy
//    	private final State goal; // stan koncowy
    	private State current;
    	

	public AStar(Model model) 
	{
	    super();
	    this.model = model;
	    this.openSet = new HashSet<State>();
	    this.closedSet = new HashSet<State>();
	    this.gScore = new HashMap<State, Double>();
	    this.fScore = new HashMap<State, Double>();
//	    this.start = model.getWall().getStart();
//	    this.goal = model.getWall().getGoal();
	    this.current = null;	// UWAGA
	}

	@Override
	public Path findPath(AbstractState start, AbstractState goal) {
	    openSet.add((State)start);
	    gScore.put((State)start, 0.0);
	    fScore.put((State)start, gScore.get((State)start) + calculateHeuristicCost((State) start, (State) goal ));
	    
	    while(!openSet.isEmpty())
	    {
	    	TreeMap<Double, State> sortedFScore = new TreeMap<Double, State>();
	    	for (State s : fScore.keySet())
	    	{
	    		sortedFScore.put(fScore.get(s), s);
	    	}
	    	current = sortedFScore.get(sortedFScore.firstKey()); // stan z namniejszą wartoscia fScore
	    	
	    	if (current == goal)
	    	{
	    		//return new Path; 	// do zrobienia
	    	}
	    	
	    	openSet.remove(current);
	    	closedSet.add(current);
	    	
	    	Set<State> neighbourNodes = new HashSet<State>();
	    	neighbourNodes = neighbourNodes(current);
	    }
	    	
		return null;
	}
	

	private Set<State> neighbourNodes(State current) 
	{
		if(current.areHandsOnTheSameGrip())
		{
			System.out.println("Rece na tym samym uchwycie");
			List<Grip> feasibleGrips = model.getFeasibleGrips(current, LEG.LEFT_HAND, Configuration.radius);
			
			for (Grip g : feasibleGrips)
			{
				System.out.print(g.toString());
			}
		}
		else
		{
			System.out.println("Rece na roznych uchwytach");
		}
		if(current.areFeetOnTheSameGrip())
		{
			System.out.println("Nogi na tym samym uchwycie");
		}
		else
		{
			System.out.println("Nogi na roznych uchwytach");
		}
		return null;
	}

	private Double calculateHeuristicCost(State start, State goal) 
	{
		return model.getWall().getW()- start.getLegState().get(LEG.LEFT_FOOT).getY(); // ZLE TYMCZASOWE
	}

	// testy
	public void testy()
	{
			for (Grip g : model.getWall().getGrips())
			{
				System.out.print(g.toString());
			}
			
	}

}
