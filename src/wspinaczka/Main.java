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
    public static void main(String[] args) throws IOException
    {
    	List<String> argsList = new ArrayList<String>();
    	for (String s : args)
    	{
    		argsList.add(s);
    	}
    	
 
		final Controller controller = new Controller(argsList);
		controller.run();

    	
    	
    	//final Model model = new Model(args);

    	// testy
    	//Wall testWall = new Wall(6, 10);

    }
}
