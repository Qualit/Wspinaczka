package mockups;

import java.util.Map;

public class WallMockup 
{
	private final Map<Integer, GripMockup> gripMockupsList;

	public WallMockup(final Map<Integer, GripMockup> gripMockupsList)
	{
		this.gripMockupsList = gripMockupsList;
	}
	
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i=0 ; i < gripMockupsList.size() ; i++)
		{
			sb.append(gripMockupsList.get(i).toString());
		}
		return sb.toString();
	}
}
