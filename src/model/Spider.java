package model;

import java.util.Map;

public class Spider
{
	/* Spider's legs */
	private Map<LegsList, Leg> legs; // nogiMariusza

    public Spider()
    {
	
    }

   public void initializeLegs()
   {
       legs.put(LegsList.LEFT_HAND, new Leg());
       legs.put(LegsList.RIGHT_HAND, new Leg());
       legs.put(LegsList.LEFT_FOOT, new Leg());
       legs.put(LegsList.RIGHT_FOOT, new Leg());
       //dsadsadasdsa
   }
}
