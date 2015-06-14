package controller;

import java.io.IOException;
import java.util.List;

import view.View;
import algorithm.*;
import mockups.WallMockup;
import model.*;

public class Controller
{
    private final Model model;					// sciana 
    private final InputHandler inputHandler;	// obsluga wejscia
    private final Algorithm algorithm;			// pole klasy implementujacej interfejs Algorithm
    private final View view;
    
    public Controller() throws IOException
    {
    	this.inputHandler = new InputHandler();	
    	this.model = new Model(); 	
    	this.algorithm = new AStar(model);
    	this.view = new View(new WallMockup());
    }
    
    public void initializeView()
    {
    	view.setController(this);
    }

    public void displayLogs()
    {
    	System.out.println(model.getWall().getStart().toString());
    	System.out.println(model.getWall().getGoal().toString());

    	//algorithm.printGrips();
    
    	for(int i=1 ; i< model.getWall().getGrips().size(); i++)
    	{
    		double odleglosc = model.getWall().getGrips().get(i-1).distance(model.getWall().getGrips().get(i));
    		model.getWall().getGrips().get(i-1).toString();
    		System.out.print(model.getWall().getGrips().get(i-1).toString() +" Odleglosc: " + odleglosc + "\n");
    	}
    	
    	Path path = algorithm.findPath(model.getWall().getStart(), model.getWall().getGoal());
    	System.out.println("*********************************");

    	if (path != null)
    	{ 	
    		path.showPath(); // wypisanie tekstowe
    	
    		view.setCurrentState(((DonePath) path).getStart());
    		view.refreshView();
    	}
    }

    public Model getModel()
    {
        return model;
    }
    
    public void replaceWall(List<String> argsList) throws IOException
    {
    	inputHandler.prepareParameters(argsList);
    	model.setWall(new Wall(inputHandler.getGrips(), inputHandler.getNumberOfGrips(), inputHandler.getWallHeight()));
    }

	public WallMockup getNewWallMockup(State state) 
	{
		return model.getWallMockup(state);
		
	}
}
