package mockups;

import java.util.Map;
import java.util.TreeMap;

public class WallMockup 
{
	private final Map<Integer, GripMockup> gripMockupsMap;

	public WallMockup()
	{
	    // TODO Auto-generated constructor stub
	    this.gripMockupsMap = new TreeMap<Integer, GripMockup>();
	}
	public WallMockup(final Map<Integer, GripMockup> gripMockupsMap)
	{
		this.gripMockupsMap = gripMockupsMap;
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
	
	
}
