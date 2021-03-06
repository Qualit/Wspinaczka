package model;

import java.util.*;
import algorithm.*;

public class AStar implements Algorithm 
{
    	private final Model model;
    	private final Set<State> openSet;  // zbior stanow otwartych
    	private final Set<State> closedSet; // zbior stanow zamknietych
    	private final Map<State, Double> gScore; // waga aktualnie najtanszej sciezki
    	private final Map<State, Double> fScore; // gScore + heuristic
    	

	public AStar(Model model) 
	{
	    this.model = model;
	    this.openSet = new HashSet<State>();
	    this.closedSet = new HashSet<State>();
	    this.gScore = new HashMap<State, Double>();
	    this.fScore = new HashMap<State, Double>();
	    
	}

	@Override
	public Path findPath(AbstractState start, AbstractState goal) 
	{
		State current= null;
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
	    	
	    	if (current.equals(goal))
	    	{
	    		DonePath path = new DonePath((State) start, (State) current);
	    		path.setCosts(gScore);
	    		return path;
	    	}
	    	
	    	openSet.remove(current);
	    	fScore.remove(current);
	    	closedSet.add(current);
	    	
	    	Map<State, Double> neighbourStatesAndCosts = new HashMap<State, Double>();
	    	neighbourStatesAndCosts = createNeighbourStates(current);
	
	    	for (State neighbour : neighbourStatesAndCosts.keySet())
	    	{
	    		if(closedSet.contains(neighbour))
	    		{
	    			continue;
	    		}
	    		
	    		Double tentativeGScore = gScore.get(current) + neighbourStatesAndCosts.get(neighbour);
	    		
	    		if(!openSet.contains(neighbour))
	    		{
	    			neighbour.setPrevious(current);
	    			gScore.put(neighbour, tentativeGScore);
	    			fScore.put(neighbour, gScore.get(neighbour) + calculateHeuristicCost(neighbour, (State)goal));
	    			openSet.add(neighbour);
	    		}
	    		else
	    		{
	    			if(tentativeGScore < gScore.get(neighbour))
	    			{
		    			neighbour.setPrevious(current);
		    			gScore.put(neighbour, tentativeGScore);
		    			fScore.put(neighbour, gScore.get(neighbour) + calculateHeuristicCost(neighbour, (State)goal));
	    			}
	    		}
	    	}
	    }
	    	
		return null;
	}

	private Map<State, Double> createNeighbourStates(State current) 
	{
		final Map<State, Double> neighbourNodesAndCosts = new HashMap<State, Double>();
		if(current.areHandsOnTheSameGrip())
		{

			List<Grip> feasibleGrips = model.getFeasibleGrips(current, LEG.LEFT_HAND);
			createNeighbouringStatesForOneLeg(neighbourNodesAndCosts, feasibleGrips, current, LEG.LEFT_HAND);

		}
		else
		{

			List<Grip> feasibleGrips = model.getFeasibleGrips(current, LEG.LEFT_HAND);
			createNeighbouringStatesForOneLeg(neighbourNodesAndCosts, feasibleGrips, current, LEG.LEFT_HAND);

			List<Grip> feasibleGrips2 = model.getFeasibleGrips(current, LEG.RIGHT_HAND);
			createNeighbouringStatesForOneLeg(neighbourNodesAndCosts, feasibleGrips2, current, LEG.RIGHT_HAND);
		}
		if(current.areFeetOnTheSameGrip())
		{
			List<Grip> feasibleGrips = model.getFeasibleGrips(current, LEG.LEFT_FOOT);
			createNeighbouringStatesForOneLeg(neighbourNodesAndCosts, feasibleGrips, current, LEG.LEFT_FOOT);

		}
		else
		{
			List<Grip> feasibleGrips = model.getFeasibleGrips(current, LEG.LEFT_FOOT);
			createNeighbouringStatesForOneLeg(neighbourNodesAndCosts, feasibleGrips, current, LEG.LEFT_FOOT);

			List<Grip> feasibleGrips2 = model.getFeasibleGrips(current, LEG.RIGHT_FOOT);
			createNeighbouringStatesForOneLeg(neighbourNodesAndCosts, feasibleGrips2, current, LEG.RIGHT_FOOT);

		}
		return neighbourNodesAndCosts;
	}

	private void createNeighbouringStatesForOneLeg(Map<State, Double> neighbourStates, List<Grip> feasibleGrips, State currentState, LEG activeLeg) 
	{
		for (Grip g : feasibleGrips)
		{
			Map<LEG, Grip> newLegState = new HashMap<LEG, Grip>();
			newLegState.put(LEG.LEFT_HAND, currentState.getLegGrip(LEG.LEFT_HAND));
			newLegState.put(LEG.RIGHT_HAND, currentState.getLegGrip(LEG.RIGHT_HAND));
			newLegState.put(LEG.LEFT_FOOT, currentState.getLegGrip(LEG.LEFT_FOOT));
			newLegState.put(LEG.RIGHT_FOOT, currentState.getLegGrip(LEG.RIGHT_FOOT));
			newLegState.put(activeLeg, g);
			neighbourStates.put(new State(newLegState, null), g.getCost());
		}
		
	}

	private Double calculateHeuristicCost(State start, State goal) 
	{
		Double lh = (goal.getLegGrip(LEG.LEFT_HAND).distance(start.getLegGrip(LEG.LEFT_HAND))) /2;
		Double rh = (goal.getLegGrip(LEG.RIGHT_HAND).distance(start.getLegGrip(LEG.RIGHT_HAND))) /2;
		Double lf = (goal.getLegGrip(LEG.LEFT_FOOT).distance(start.getLegGrip(LEG.LEFT_FOOT))) /2;
		Double rf = (goal.getLegGrip(LEG.RIGHT_FOOT).distance(start.getLegGrip(LEG.RIGHT_FOOT))) /2;
		
		return lh+rh+lf+rf;
	}

	// testy
	@Override
	public void printGrips()
	{
		System.out.println(model.getWall().getN() + " " + model.getWall().getW());
		for (Grip g : model.getWall().getGrips())
			System.out.print(g.toString());
	}
}
