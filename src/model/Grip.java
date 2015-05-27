package model;

public class Grip // implements Point (?)
{
	private final double x;
	private final double y;
	private final double cost; //koszt dojscia z dowolengo uchwytu do tego uchwytu
	private final double h;    // wartosc heurysyki dla uchwytu
	
	

	public Grip(double x, double y, double cost, double h)
	{
	    super();
	    this.x = x;
	    this.y = y;
	    this.cost = cost;
	    this.h = h;
	}



	public double getCost()
	{
		// TODO Auto-generated method stub
		return this.cost;
	}



	@Override
	public int hashCode()
	{
	    final int prime = 31;
	    int result = 1;
	    long temp;
	    temp = Double.doubleToLongBits(cost);
	    result = prime * result + (int) (temp ^ (temp >>> 32));
	    temp = Double.doubleToLongBits(h);
	    result = prime * result + (int) (temp ^ (temp >>> 32));
	    temp = Double.doubleToLongBits(x);
	    result = prime * result + (int) (temp ^ (temp >>> 32));
	    temp = Double.doubleToLongBits(y);
	    result = prime * result + (int) (temp ^ (temp >>> 32));
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
	    Grip other = (Grip) obj;
	    if (Double.doubleToLongBits(cost) != Double
		    .doubleToLongBits(other.cost))
		return false;
	    if (Double.doubleToLongBits(h) != Double.doubleToLongBits(other.h))
		return false;
	    if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
		return false;
	    if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
		return false;
	    return true;
	}



	public double getX()
	{
	    return x;
	}



	public double getY()
	{
	    return y;
	}



	public double getH()
	{
	    return h;
	}
	
	
	
	
	
	

}
