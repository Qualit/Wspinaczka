package model;

import java.util.*;
import algorithm.*;
import wspinaczka.Configuration;

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
	    	
	    	if (current.equals(goal))
	    	{
	    		return new DonePath((State) start, (State) current);
	    		//return new Path; 	// do zrobienia
	    	}
	    	
	    	openSet.remove(current);
	    	fScore.remove(current);
	    	closedSet.add(current);
	    	
	    	Map<State, Double> neighbourNodesAndCosts = new HashMap<State, Double>();
	    	neighbourNodesAndCosts = neighbourNodes(current);
	    	
	    	for (State neightbour : neighbourNodesAndCosts.keySet())
	    	{
	    		//System.out.println(neightbour.toString());
	    		
	    		if(closedSet.contains(neightbour))
	    		{
	    			continue;
	    		}
	    		
	    		Double tentativeGScore = gScore.get(current) + neighbourNodesAndCosts.get(neightbour);
	    		
	    		if(!openSet.contains(neightbour))
	    		{
	    			neightbour.setPrevious(current);
	    			gScore.put(neightbour, tentativeGScore);
	    			fScore.put(neightbour, gScore.get(neightbour) + calculateHeuristicCost(neightbour, (State)goal));
	    			openSet.add(neightbour);
	    		}
	    		else
	    		{
	    			if(tentativeGScore < gScore.get(neightbour))
	    			{
		    			neightbour.setPrevious(current);
		    			gScore.put(neightbour, tentativeGScore);
		    			fScore.put(neightbour, gScore.get(neightbour) + calculateHeuristicCost(neightbour, (State)goal));
	    			}
	    		}
	    		
	    	}
	    }
	    	
		return null;
	}

	private Double costBetween(State currentState, State neightbour) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	private Map<State, Double> neighbourNodes(State current) 
	{
		final Map<State, Double> neighbourNodesAndCosts = new HashMap<State, Double>();
		if(current.areHandsOnTheSameGrip())
		{
			
			List<Grip> feasibleGrips = model.getFeasibleGrips(current, LEG.LEFT_HAND, Configuration.radius);
			creatingNeighbouringStates(neighbourNodesAndCosts, feasibleGrips, current, LEG.LEFT_HAND);
			
		}
		else
		{
			
			List<Grip> feasibleGrips = model.getFeasibleGrips(current, LEG.LEFT_HAND, Configuration.radius);
			creatingNeighbouringStates(neighbourNodesAndCosts, feasibleGrips, current, LEG.LEFT_HAND);
			
			List<Grip> feasibleGrips2 = model.getFeasibleGrips(current, LEG.RIGHT_HAND, Configuration.radius);
			creatingNeighbouringStates(neighbourNodesAndCosts, feasibleGrips2, current, LEG.RIGHT_HAND);
		}
		if(current.areFeetOnTheSameGrip())
		{
			List<Grip> feasibleGrips = model.getFeasibleGrips(current, LEG.LEFT_FOOT, Configuration.radius);
			creatingNeighbouringStates(neighbourNodesAndCosts, feasibleGrips, current, LEG.LEFT_FOOT);
			
		}
		else
		{
			List<Grip> feasibleGrips = model.getFeasibleGrips(current, LEG.LEFT_FOOT, Configuration.radius);
			creatingNeighbouringStates(neighbourNodesAndCosts, feasibleGrips, current, LEG.LEFT_FOOT);
			
			List<Grip> feasibleGrips2 = model.getFeasibleGrips(current, LEG.RIGHT_FOOT, Configuration.radius);
			creatingNeighbouringStates(neighbourNodesAndCosts, feasibleGrips2, current, LEG.RIGHT_FOOT);
			
		}
		return neighbourNodesAndCosts;
	}

	private void creatingNeighbouringStates(Map<State, Double> neighbourNodes, List<Grip> feasibleGrips, State currentState, LEG activeLeg) 
	{
		for (Grip g : feasibleGrips)
		{
			Map<LEG, Grip> newLegState = new HashMap<LEG, Grip>();
//			newLegState.putAll(currentState.getLegState());
			newLegState.put(LEG.LEFT_HAND, currentState.getLegGrip(LEG.LEFT_HAND));
			newLegState.put(LEG.RIGHT_HAND, currentState.getLegGrip(LEG.RIGHT_HAND));
			newLegState.put(LEG.LEFT_FOOT, currentState.getLegGrip(LEG.LEFT_FOOT));
			newLegState.put(LEG.RIGHT_FOOT, currentState.getLegGrip(LEG.RIGHT_FOOT));
			newLegState.put(activeLeg, g);
			neighbourNodes.put(new State(newLegState, null), g.getCost());
		}
		
	}

	private Double calculateHeuristicCost(State start, State goal) 
	{
		return (model.getWall().getW()- start.getLegState().get(LEG.LEFT_FOOT).getY())*2; // ZLE TYMCZASOWE
	}

	// testy
	@Override
	public void printGrips()
	{
		for (Grip g : model.getWall().getGrips())
			System.out.print(g.toString());
	}
	
	public void printGrips2()
	{
		System.out.println(model.getWall().getN() + " " + model.getWall().getW());
		for (Grip g : model.getWall().getGrips())
			System.out.print(g.toString2());
	}
}
