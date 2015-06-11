package view;

import java.io.Serializable;


/**
 * Class for representing fields' and pieces' coordinates
 * on the board
 * 
 * @author mk
 *
 */
public class Coordinate implements Serializable
{
    public static final long serialVersionUID =1L;
    /** X-AXIS coordinate*/
    private final int x;
    
    /** Y-AXIS coordinate*/
    private final int y;
    
    /** Default class constructor*/
    public Coordinate()
    {
        this(0,0);
    }
    
    /** Class constructor based on integer coordinates*/
    public Coordinate(int x, int y)
    {
        super();
        this.x = x;
        this.y = y;
    }
    
    /** Copy instance constructor*/
    public Coordinate(Coordinate other)
    {
        this.x = other.x;
        this.y = other.y;
    }
    
    /** Gets the X-AXIS coordinate*/
    public int getX()
    {
        return x;
    }
    
    /** Gets the Y-AXIS coordinate*/
    public int getY()
    {
        return y;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
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
        Coordinate other = (Coordinate) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
