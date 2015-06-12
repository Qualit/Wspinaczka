package view;

import java.io.IOException;
import java.util.List;

import javax.swing.SwingUtilities;

import mockups.WallMockup;
import model.State;
import controller.*;
public class View
{
    private final ApplicationFrame applicationFrame;
    private WallMockup wallMockup;
    private Controller controller;
    private State currentState;
    
   
    public View(WallMockup wallMockup)
    {
    	this.wallMockup = wallMockup;
    	applicationFrame = new ApplicationFrame(this);
    	applicationFrame.initializeFrame();
    }
   
    public WallMockup getStartWallMockup()
    {
        return wallMockup;
    }
    
    private String getParamString(int gripMockupId)
    {
	return wallMockup.getGripMockupsMap().get(gripMockupId).toString();
    }
    
    public void displayParams(int gripMockupId)
    {
	final String params = getParamString(gripMockupId);
	SwingUtilities.invokeLater(new Runnable() {
	    
	    @Override
	    public void run()
	    {
		// TODO Auto-generated method stub
		applicationFrame.displayParams(params);
		
	    }
	});
	
    }
    
    public void initializeWall(List<String> params) throws IOException
    {
	controller.replaceWall(params);
	controller.displayLogs();
    }

    public Controller getController()
    {
        return controller;
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

	public final State getCurrentState() 
	{
		return currentState;
	}

	public final void setCurrentState(State currentState) 
	{
		this.currentState = currentState;
	}

	public void refreshView()
	{
		setWallMockup(controller.getNewWallMockup(currentState));
		applicationFrame.updateWallPanel(getWallMockup());
	}

	public final WallMockup getWallMockup() 
	{
		return wallMockup;
	}

	public final void setWallMockup(WallMockup wallMockup) 
	{
		this.wallMockup = wallMockup;
	}

	public void displayStats() 
	{
		SwingUtilities.invokeLater(new Runnable() {
		    
		    @Override
		    public void run()
		    {
			// TODO Auto-generated method stub
			applicationFrame.displayStats(wallMockup.getCurrentCost(), wallMockup.getNumberOfState());
			
		    }
		});
		
	}
    
}
