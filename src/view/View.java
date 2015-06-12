package view;

import java.io.IOException;
import java.util.List;

import javax.swing.SwingUtilities;

import mockups.WallMockup;
import controller.*;
public class View
{
    private final ApplicationFrame applicationFrame;
    private final WallMockup wallMockup;
    private Controller controller;
    
   
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
    
    
    
    
    
    
}
