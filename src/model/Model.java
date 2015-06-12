package model;

import java.util.List;

import mockups.WallMockup;

public class Model 
{
	private  Wall wall;
	
	public Model()
	{
	    this.wall = new Wall();
	}
	
	public Model(List<Grip> grips, int numberOfGrips, double wallHeight) 
	{
		this.wall = new Wall(grips, numberOfGrips, wallHeight);
		
	}
	
	public List<Grip> getFeasibleGrips(final State current, LEG activeLeg, double radius)
    {
		return wall.getFeasibleGrips(current, activeLeg);
    }

	public final Wall getWall() 
	{
		return wall;
	}
	
	public final WallMockup getWallMockup(State currentState)
	{
		return wall.getWallMockup(currentState);
	}
	
	public List<Grip> getGripsBetween(final double lowerBoud, final double upperBound)
	{
		return wall.getGripsBetween(lowerBoud, upperBound);
	}

	public void setWall(Wall wall)
	{
	    this.wall = wall;
	}
	
	
}
