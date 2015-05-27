package model;

import java.util.Map;

/**
 * @author mariusz
 *
 */
public class State
{
    
    private final Map<LEG, Grip> legState;
    private State previous;

    public State(Map<LEG, Grip> legState)
    {
	super();
	this.legState = legState;
    }
    
    
    
    /* (non-Javadoc)
     * returns hash code of state, calculated as a weighted sum of legs in this state
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
	int hash=0;
	for(int i=1; i<=legState.values().size(); i++)
	{
	    int tmp = legState.get(i-1).hashCode()*i;
	    hash+=tmp;
	}
	return hash;
    }



    @Override
    public boolean equals(Object obj)
    {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	State other = (State) obj;
	if (legState == null)
	{
	    if (other.legState != null)
		return false;
	} else if (!legState.equals(other.legState))
	    return false;
	return true;
    }



    public State getPrevious()
    {
        return previous;
    }

    public void setPrevious(State previous)
    {
        this.previous = previous;
    }
    
    
    
    

}
