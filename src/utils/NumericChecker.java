package utils;

public class NumericChecker
{
    public static boolean isNumericDouble(String str)  
    {  
      try  
      {  
        double d = Double.parseDouble(str); 
        if(d<0.0)
        {
            return false;
        }
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }
    
    public static boolean isNumericInteger(String s) 
    {
	    try { 
	        Integer res = Integer.parseInt(s); 
	        if(res<0)
	        {
	            return false;
	        }
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
}
