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
    
    	private Wall wall;
    	private Set<State> openSet;  // zbior stanow otwartych
    	private Set<State> closedSet; // zbior stanow zamknietych
    	private Map<State, Double> gScore; // waga aktualnie najtanszej sciezki
    	private Map<State, Double> fScore; // gScore + heuristic
    	private State current;
    	

    	
    	
	public AStar()
	{
	    super();
	    this.openSet = new HashSet<State>();
	    this.closedSet = new HashSet<State>();
	    this.gScore = new HashMap<State, Double>();
	    this.fScore = new HashMap<State, Double>();
	}




	@Override
	public Path findPath(Point start, Point goal) {
		// TODO Auto-generated method stub
	    
	    	
		return null;
	}

}
