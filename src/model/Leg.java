package model;

import java.util.LinkedList;

public class Leg
{
    private LinkedList<Grip> visitedGrips;
    private double currentCost;
    
    
    public Grip getLastGrip()
    {
	return new Grip();
    }
    
    public Boolean addGrip(Grip grip)
    {
	return false;
    }
    
    public void updateCost(Grip grip)
    {
		currentCost+=grip.getCost();
    }

}
