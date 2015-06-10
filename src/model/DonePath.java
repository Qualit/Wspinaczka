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
		assignNextPointers();
	}

	@Override
	public void showPath()
	{
		State now = start;

		while (now != null)
		{
			System.out.println(now.toString());
			now = now.getNext();
		}
			
	}
	
	private void assignNextPointers()
	{
		State current = null;
		State nextState = goal;
		
		do
		{
			current = nextState.getPrevious();
			current.setNext(nextState);
			nextState = current;
		}while(current.getPrevious() != null);
	}

	public final State getStart() {
		return start;
	}
}
