package view;

import javax.swing.JPanel;

import configuration.ViewConfiguration;


public class WallPanel extends JPanel
{
    	private final int panelHeight = ViewConfiguration.heightWallPanel;
    	private final int panelWidth = ViewConfiguration.widthWallPanel;
	
    	public WallPanel() 
	{
	    
	}
    	
    	public void initializePanel()
    	{
    	    this.setSize(panelWidth, panelHeight);
    	}
    
}
