package controller;

import java.io.IOException;
import java.util.List;

import model.AStar;
import model.Model;
import model.Wall;

public class Controller
{
    private final Model model; // sciana 
    private final InputHandler inputHandler; // obsluga wejscia

    public Controller(List<String> argsList) throws IOException
    {
    	this.inputHandler = new InputHandler();
    	inputHandler.prepareParameters(argsList);
    	
    	this.model = new Model(inputHandler.getGrips(), inputHandler.getNumberOfGrips(), inputHandler.getWallHeight());
    }
}
