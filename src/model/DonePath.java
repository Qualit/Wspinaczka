package model;

import algorithm.Path;

public class DonePath extends Path 
{
	private final State start;
	private final State goal;

	public DonePath(State start, State goal) 
	{
		super();
		this.start = start;
		this.goal = goal;
	}
	
	@Override
	public void showPath()
	{
		State now = goal;
		while(now != null)
		{
			System.out.println(now.toString());
			now = now.getPrevious();
		}
	}

}
