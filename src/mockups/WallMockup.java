package mockups;

import java.util.Map;
import java.util.TreeMap;

public class WallMockup 
{
	private Map<Integer, GripMockup> gripMockupsMap;
	private double currentCost;
	private int numberOfState;

	public WallMockup()
	{
	    this.gripMockupsMap = new TreeMap<Integer, GripMockup>();
	    this.currentCost = 0;
	    this.numberOfState = 0;
	}
	public WallMockup(final Map<Integer, GripMockup> gripMockupsMap, double currentCost, int numberOfState)
	{
		this.gripMockupsMap = gripMockupsMap;
		this.currentCost = currentCost;
		this.numberOfState = numberOfState;
	}
	
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i=0 ; i < gripMockupsMap.size() ; i++)
		{
			sb.append(gripMockupsMap.get(i).toString());
		}
		return sb.toString();
	}

	public Map<Integer, GripMockup> getGripMockupsMap()
	{
	    return gripMockupsMap;
	}
	public final double getCurrentCost() 
	{
		return currentCost;
	}
	public final int getNumberOfState() 
	{
		return numberOfState;
	}
	public final void setGripMockupsMap(Map<Integer, GripMockup> gripMockupsMap) 
	{
		this.gripMockupsMap = gripMockupsMap;
	}
	public final void setCurrentCost(double currentCost) 
	{
		this.currentCost = currentCost;
	}
	public final void setNumberOfState(int numberOfState) 
	{
		this.numberOfState = numberOfState;
	}
	
	
}
