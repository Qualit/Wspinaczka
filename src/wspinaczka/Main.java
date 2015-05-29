package wspinaczka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import model.AStar;
import model.Model;
import model.Wall;

public class Main
{
    public static void main(String[] args)
    {
    	List<String> argsList = new ArrayList<String>();
    	for (String s : args)
    	{
    		argsList.add(s);
    	}
    	
    	try 
    	{
			final Controller controller = new Controller(argsList);
		} catch (IOException e) 
		{
			System.err.println("Błąd odczytu z pliku");
			e.printStackTrace();
		}
    	//final Model model = new Model(args);

    	// testy
    	//Wall testWall = new Wall(6, 10);
    	//AStar algortym = new AStar(testWall.getStart(), testWall.getGoal(), testWall);
    	//algortym.testy();
    }
}
