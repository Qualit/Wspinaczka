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
    private State next;

    public State(Map<LEG, Grip> legState, State previous)
    {	// TODO Auto-generated method stub
	    super();
	    this.legState = legState;
	    this.previous = previous;
    }
    
	public boolean areHandsOnTheSameGrip() 
	{
		return (legState.get(LEG.LEFT_HAND) == legState.get(LEG.RIGHT_HAND));
	}
	
	public boolean areFeetOnTheSameGrip() 
	{
		return (legState.get(LEG.LEFT_FOOT) == legState.get(LEG.RIGHT_FOOT));
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
	
    	for (LEG l : legState.keySet())
    	{
    		switch (l)
    		{
    		case LEFT_HAND:
    			i=1;
    			break;
    		case RIGHT_HAND:
    			i=2;
    			break;
    		case LEFT_FOOT:
    			i=3;
    			break;
    		case RIGHT_FOOT:
    			i=4;
    			break;
    		default:
    			i=1;
    			break;
    			
    		}
    		hash+=legState.get(l).hashCode()/i;		// zamiana *i na /i => mnozenie powodowalo wypadanie poza zakres inta co dalej powodowalo ze rozne stany mialy takie same hashe
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
	    
	} else if (legState.get(LEG.LEFT_HAND).hashCode() != other.legState.get(LEG.LEFT_HAND).hashCode() |
				legState.get(LEG.RIGHT_HAND).hashCode() != other.legState.get(LEG.RIGHT_HAND).hashCode() |
				legState.get(LEG.LEFT_FOOT).hashCode() != other.legState.get(LEG.LEFT_FOOT).hashCode() |
				legState.get(LEG.RIGHT_FOOT).hashCode() != other.legState.get(LEG.RIGHT_FOOT).hashCode())
	    return false;
	return true;
    }
    
    public String toString()
    {
    	StringBuilder sb = new StringBuilder();
    	sb.append("hasz= " + hashCode() + "\n");
    	for(LEG l : legState.keySet())
    	{
    		sb.append(l + " " + legState.get(l).toString());
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

	public final Map<LEG, Grip> getLegState() 
	{
		return legState;
	}

	public Grip getLegGrip(LEG activeLeg) 
	{
		return legState.get(activeLeg);
	}

	public final State getNext() {
		return next;
	}

	public final void setNext(State next) {
		this.next = next;
	}
}