package controller;

import java.io.IOException;
import java.util.List;

import algorithm.Algorithm;

import model.AStar;
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
    	algorithm.testy();
    	algorithm.findPath(model.getWall().getStart(), model.getWall().getGoal());
    }
}
