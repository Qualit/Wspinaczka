package model;

import java.util.List;
import java.util.Map;

public class Spider
{
    public Spider()
    {
	
    }
    
    /* Spider's legs */
   private Map<LEG, Leg> legs; // 
   
   public void initializeLegs()
   {
       legs.put(LEG.LEFT_HAND, new Leg());
       legs.put(LEG.RIGHT_HAND, new Leg());
       legs.put(LEG.LEFT_FOOT, new Leg());
       legs.put(LEG.RIGHT_FOOT, new Leg());
       //zmiana krystiana
   }
    

}
