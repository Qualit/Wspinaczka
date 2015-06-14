package view;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class LegImage 
{
	private final Line2D.Float legLine;
	private final Point2D.Float start;
	private final Point2D.Float finish;

	public LegImage(Coordinate p1, Coordinate p2) 
	{
		this.start = new Point2D.Float(p1.getX() + 8, p1.getY() + 2);
		this.finish = new Point2D.Float(p2.getX() + 8, p2.getY() + 2);
		legLine = new Line2D.Float(start, finish);
	}

	public Line2D.Float getLegLine() 
	{
		return legLine;
	}

	public Point2D.Float getStart() 
	{
		return start;
	}

	public Point2D.Float getFinish() 
	{
		return finish;
	}
}
