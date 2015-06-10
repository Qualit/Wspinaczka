package controller;

import java.io.IOException;
import java.util.List;

import algorithm.*;
import model.*;

public class Controller
{
    private final Model model;					// sciana 
    private final InputHandler inputHandler;	// obsluga wejscia
    private final Algorithm algorithm;			// pole klasy implementujacej interfejs Algorithm

    public Controller(List<String> argsList) throws IOException
    {
    	this.inputHandler = new InputHandler();
    	inputHandler.prepareParameters(argsList);
    	
    	this.model = new Model(inputHandler.getGrips(), inputHandler.getNumberOfGrips(), inputHandler.getWallHeight());
    	
    	this.algorithm = new AStar(model);
    	
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
    	System.out.print(model.getWallMockup(((DonePath) path).getStart()));

    	if (path != null)
    		path.showPath();
    }
}
