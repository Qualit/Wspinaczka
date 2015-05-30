package model;

import java.util.List;

public class Model 
{
	private final Wall wall;

	public Model(List<Grip> grips, int numberOfGrips, double wallHeight) 
	{
		this.wall = new Wall(grips, numberOfGrips, wallHeight);
		
	}
	
	public List<Grip> getFeasibleGrips(final State current, LEG activeLeg, double radius)
    {
		return wall.getFeasibleGrips(current, activeLeg, radius);
    }

	public final Wall getWall() 
	{
		return wall;
	}
}
