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
    	//this.model = new Model(inputHandler.getGrips(), inputHandler.getNumberOfGrips(), inputHandler.getWallHeight());
    	
    	this.algorithm = new AStar(model);
    	//this.view = new View((model.getWallMockup(model.getWall().getStart())));
    	this.view = new View(new WallMockup());
    }
    
    public void initializeView()
    {
	view.setController(this);
    }

    public void displayLogs()
    {
    	//AStar algortym = new AStar(model.getWall().getStart(), model.getWall().getGoal(), model.getWall());
    	System.out.println(model.getWall().getStart().toString());
    	System.out.println(model.getWall().getGoal().toString());

    	algorithm.printGrips();
    	((AStar) algorithm).printGrips2();
    	
    	Path path = algorithm.findPath(model.getWall().getStart(), model.getWall().getGoal());
    	System.out.println("*********************************");
    	//System.out.print(model.getWallMockup(((DonePath) path).getStart()));

    	if (path != null)
    	{
    	    
    	
    		path.showPath();
    	
    	
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
