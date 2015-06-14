package mockups;

import java.text.DecimalFormat;

import model.Grip;
import model.LEG;

public class GripMockup 
{
	private final int idGrip;
	private final double x;
	private final double y;
	private final double cost;
	private LEG leg;

	public GripMockup(final Grip grip)
	{
		this.idGrip = grip.getIdGrip();
		this.x = grip.getX();
		this.y = grip.getY();
		this.cost = grip.getCost();
		this.leg = null;
	}

	public final void setLeg(final LEG leg)
	{
		this.leg = leg;
	}
	
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		DecimalFormat df = new DecimalFormat("#.####");
			sb.append("idGrip=" + idGrip + "\n"+ " x=" + df.format(x) + "\n" + " y=" + df.format(y)+ "\n" + " cost=" + df.format(cost)+ "\n" + " leg=" + leg + "\n");
		return sb.toString();
	}

	public int getIdGrip()
	{
	    return idGrip;
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

	public LEG getLeg()
	{
	    return leg;
	}
}