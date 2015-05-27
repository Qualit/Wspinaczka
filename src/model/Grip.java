package model;

public class Grip // implements Point (?)
{
	private double x;
	private double y;
	private double cost; //koszt dojscia z dowolengo uchwytu do tego uchwytu
	private double h;    // wartosc heurysyki dla uchwytu

	public double getCost()
    {
		// TODO Auto-generated method stub
		return this.cost;
    }

}
