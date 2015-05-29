package wspinaczka;

import model.AStar;
import model.Model;
import model.Wall;

public class Main
{
    public static void main(String[] args)
    {
    	final Model model = new Model(args);

    	// testy
    	Wall testWall = new Wall(6, 10);
    	AStar algortym = new AStar(testWall.getStart(), testWall.getGoal(), testWall);
    	algortym.testy();
    }
}
