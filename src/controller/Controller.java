package controller;

import java.io.IOException;
import java.util.List;

import algorithm.Algorithm;
import algorithm.Path;

import model.AStar;
import model.DonePath;
import model.Model;

public class Controller
{
    private final Model model; // sciana 
    private final InputHandler inputHandler; // obsluga wejscia
    private final Algorithm algorithm; // pole klasy implementujacej interfejs Algorithm

    public Controller(List<String> argsList) throws IOException
    {
    	this.inputHandler = new InputHandler();
    	inputHandler.prepareParameters(argsList);
    	
    	this.model = new Model(inputHandler.getGrips(), inputHandler.getNumberOfGrips(), inputHandler.getWallHeight());
    	
    	this.algorithm = new AStar(model);
    	
    }
    
    public void work()
    {
    	//AStar algortym = new AStar(model.getWall().getStart(), model.getWall().getGoal(), model.getWall());
    	System.out.println(model.getWall().getStart().toString());
    	System.out.println(model.getWall().getGoal().toString());
    	algorithm.testy();
    	Path path = algorithm.findPath(model.getWall().getStart(), model.getWall().getGoal());
    	if(path != null)
    		path.showPath();
    }
}
