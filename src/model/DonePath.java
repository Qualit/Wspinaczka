package model;

import java.util.Map;

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
		assignNumberOfStateVariable();
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
		} while (current.getPrevious() != null);
	}

	private void assignNumberOfStateVariable() 
	{
		State current = start;
		int number = 0;

		do 
		{

			current.setNumberOfState(number);
			number++;
			current = current.getNext();
		} while (current != null);
	}

	public final State getStart() 
	{
		return start;
	}

	public void setCosts(Map<State, Double> costs) 
	{
		State current = start;

		do 
		{
			current.setOwnCost(costs.get(current));
			current = current.getNext();
		} while (current != null);
	}
}
