package view;

import mockups.WallMockup;

public class View
{
    private final ApplicationFrame applicationFrame;
    private final WallMockup startWallMockup;
    
   
    public View(WallMockup wallMockup)
    {
	this.startWallMockup = wallMockup;
	applicationFrame = new ApplicationFrame(this);
	applicationFrame.initializeFrame();
    }
   
    public WallMockup getStartWallMockup()
    {
        return startWallMockup;
    }
    
    
}
