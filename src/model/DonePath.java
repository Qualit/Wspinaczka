package model;

import java.util.ArrayList;

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
		ArrayList<State> list = new ArrayList<State>();
		State now = goal;

		while (now != null)
		{
			list.add(now);
			now = now.getPrevious();
		}

		for (int i = list.size()-1; i >= 0; i--)
			System.out.println(list.get(i));
	}
}
