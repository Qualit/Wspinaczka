package view;

import javax.swing.SwingUtilities;

import mockups.WallMockup;

public class View
{
    private final ApplicationFrame applicationFrame;
    private final WallMockup wallMockup;
    
   
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
    
    
    
    
}
