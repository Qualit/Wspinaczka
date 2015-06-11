package wspinaczka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;

public class Main
{
    public static void main(String[] args) throws IOException
    {
	
    	List<String> argsList = new ArrayList<String>();
    	for (String s : args)
    	{
    		argsList.add(s);
    	}

    	if (argsList.isEmpty())
    		throw new IOException("Wrong number of parameters\n" +
    				"Random grips generating:\tr [number of grips] [wall height]\n" +
    				"Reading data from file:\t\tf [file name]");

    	final Controller controller = new Controller(argsList);
    	System.out.println("ZA CONTROLEEREM");
		//controller.displayLogs();

		//final Model model = new Model(args);

    	// testy
    	//Wall testWall = new Wall(6, 10);
    }
}
