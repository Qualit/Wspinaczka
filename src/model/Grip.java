package model;

import configuration.*;


public class Grip
{
	private int idGrip; // nr kolejny uchwytu
	private final double x;	// wspolrzedna x uchwytu
	private final double y;	// wspolrzedna y uchwytu
	private final double cost; //koszt dojscia z dowolengo uchwytu do tego uchwytu
	
	public Grip(int idGrip, double x, double y, double cost)
	{
	    super();
	    this.idGrip = idGrip;
	    this.x = x;
	    this.y = y;
	    this.cost = cost;
	}
	
	public boolean isFeasible(State current, LEG activeLeg) 
	{
		switch (activeLeg)
		{
			case LEFT_HAND:
			{
				return isRealyFeasible(current.getLegGrip(LEG.RIGHT_HAND)) & 
					   isRealyFeasible(current.getLegGrip(LEG.LEFT_FOOT)) & 
					   isRealyFeasible(current.getLegGrip(LEG.RIGHT_FOOT));
			}
			case RIGHT_HAND:
			{
				return isRealyFeasible(current.getLegGrip(LEG.LEFT_HAND)) & 
					   isRealyFeasible(current.getLegGrip(LEG.LEFT_FOOT)) & 
					   isRealyFeasible(current.getLegGrip(LEG.RIGHT_FOOT));
			}
			case LEFT_FOOT:
			{
				return isRealyFeasible(current.getLegGrip(LEG.RIGHT_HAND)) & 
					   isRealyFeasible(current.getLegGrip(LEG.LEFT_HAND)) & 
					   isRealyFeasible(current.getLegGrip(LEG.RIGHT_FOOT));
			}
			case RIGHT_FOOT:
			{
				return isRealyFeasible(current.getLegGrip(LEG.RIGHT_HAND)) & 
					   isRealyFeasible(current.getLegGrip(LEG.LEFT_FOOT)) & 
					   isRealyFeasible(current.getLegGrip(LEG.LEFT_HAND));
			}
			default:
			{
				return false;
			}
		}
	}
	
	public boolean isRealyFeasible(Grip legGrip) 
	{
		return (Math.sqrt(Math.pow(x-legGrip.getX(), 2) + Math.pow(y-legGrip.getY(), 2))) <= Configuration.radius;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 15485857;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + idGrip;
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
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (idGrip != other.idGrip)
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	public String toString()
	{
    	StringBuilder sb = new StringBuilder();

    	sb.append("id=" + idGrip  +" x=" +x + " y=" + y + " cost=" + cost + " \n");

    	return sb.toString();
	}
	
	public String toString2()
	{
    	StringBuilder sb = new StringBuilder();

    	sb.append(x + " " + y + " " + cost + "\n");

    	return sb.toString();
	}
	
	public int getIdGrip()
	{
	    return idGrip;
	}

	public final void setIdGrip(int idGrip) 
	{
		this.idGrip = idGrip;
	}

	public double getX()
	{
	    return x;
	}

	public double getY()
	{
	    return y;
	}

	public double getCost()
	{
		return cost;
	}
}