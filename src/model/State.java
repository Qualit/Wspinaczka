package model;

import java.util.Map;

import algorithm.AbstractState;

/**
 * @author mariusz
 *
 */
public class State implements AbstractState
{
    // mapa wskazujaca ktora konczyna jest na ktorym uchwycie, kolejnosc LEFT_HAND, RIGHT_HAND, LEFT_FOOT, RIGHT_FOOT
    private final Map<LEG, Grip> legState;
    // stan z ktorego przyszlismy do biezacego stanu
    private State previous;

    public State(Map<LEG, Grip> legState, State previous)
    {
	    super();
	    this.legState = legState;
	    this.previous = previous;
    }
    
    /* (non-Javadoc)
     * returns hash code of state, calculated as a weighted sum of legs in this state
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
    	int hash=0;
    	int i=1;
	
    	for (Grip g : legState.values())
    	{
    		hash+=g.hashCode()/i;		// zamiana *i na /i => mnozenie powodowalo wypadanie poza zakres inta co dalej powodowalo ze rozne stany mialy takie same hashe
    		i++;
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
    
    public String toString()
    {
    	StringBuilder sb = new StringBuilder();
    	for(Grip g : legState.values())
    	{
    		sb.append(g.getX() + " " + g.getY() + " " + g.getCost() + " \n");
    	}
    	return sb.toString();
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
