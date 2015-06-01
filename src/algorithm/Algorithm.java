package algorithm;

public interface Algorithm
{
	public Path findPath(AbstractState start, AbstractState goal);
	public void printGrips();
}
