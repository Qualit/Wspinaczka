package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import mockups.*;
import configuration.Configuration;
import configuration.ViewConfiguration;

public class WallPanel extends JPanel
{
    private final int panelHeight = ViewConfiguration.heightWallPanel;
    private final int panelWidth = ViewConfiguration.widthWallPanel;
    private final  WallMockup startWallMockup;
    private  Map<Coordinate, GripImage> gripsMap;
	
    public WallPanel(final WallMockup wallMockup) 
	{
	    this.startWallMockup = wallMockup;
	}
    	
    public void initializePanel()
    {
    	this.setSize(panelWidth, panelHeight);
    	this.setBackground(Color.YELLOW);
    	    
    	this.setVisible(true);
    	refresh(startWallMockup);    
    }
    	 	
    private Map<Coordinate, GripImage> generateNewGripsMap(final WallMockup wallMockup)
    {
    	Map<Coordinate,GripImage> ret = new HashMap<Coordinate, GripImage>();
    	Color gripColor;
            
    	TreeMap<Integer, GripMockup> temp = (TreeMap<Integer, GripMockup>) (wallMockup.getGripMockupsMap());
    	
    	Double lowestY = (temp.get(temp.firstKey())).getY();
            
    	for(GripMockup gripMockup : wallMockup.getGripMockupsMap().values())
    	{
    		Integer x = convertXToCoordinate(gripMockup.getX());
    		Integer y = convertYToCoordinate(gripMockup.getY(),lowestY);
    		Integer widthOfGrip = calculateWidthOfGrip(gripMockup.getCost());
        	
    		Coordinate coord = new Coordinate(x, y);
    		if(gripMockup.getLeg() == null)
    		{
    			gripColor = Color.BLUE;
    		}
    		else
                {
                
                switch (gripMockup.getLeg())
                {
                	case LEFT_HAND:
                		{
                			gripColor = Color.GREEN;
                			break;
                		}
                	case RIGHT_HAND:
            			{
            				gripColor = Color.RED;
            				break;
            			}
                	case LEFT_FOOT:
            			{
            				gripColor = Color.ORANGE;
            				break;
            			}
                	case RIGHT_FOOT:
                		{
                			gripColor = Color.PINK;
                			break;
                		}
                	default:
            			{
            				gripColor = Color.BLUE;
            				break;
            			}
                	}
                }
    		ret.put(coord, new GripImage(x, y, widthOfGrip, ViewConfiguration.heightOfGrip, gripColor, gripMockup.getIdGrip()));
    	}
    	return ret;
    }
    	
    private Integer calculateWidthOfGrip(double cost) 
    {
    	Float temp = (float) (((ViewConfiguration.maxWidthOfGrip-ViewConfiguration.minWidthOfGrip)/(Configuration.minCost-Configuration.maxCost))*cost 
    			- ((ViewConfiguration.maxWidthOfGrip-ViewConfiguration.minWidthOfGrip)/(Configuration.minCost-Configuration.maxCost))*Configuration.maxCost
				+ ViewConfiguration.minWidthOfGrip);
		return Math.round(temp);
    }

	private Integer convertYToCoordinate(double y, Double lowestY)
	{
	    Float temp = (float) ((y-lowestY)*ViewConfiguration.wallPixelFactor);
	    Integer temp2 = ViewConfiguration.heightWallPanel - Math.round(temp);
	    return temp2;
	}

	private Integer convertXToCoordinate(double x)
	{
	    Float temp = (float) (x * ViewConfiguration.wallPixelFactor);
	    return Math.round(temp);
	}

	public void refresh(final WallMockup wallMockup)
    {
		final WallPanel wPanel = this;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run()
			{
				wPanel.setGripsMap(generateNewGripsMap(wallMockup));
				wPanel.repaint();
    	    }
    	});        
    }
	
    	/** Gets the field image using point */
 	public GripImage getFieldImage(final Point2D point)
	{
 		for(GripImage fieldImage: gripsMap.values())
    	{
    		if(fieldImage.getRect().contains(point))
    		{
    			return fieldImage;
    	   	}
    	}
 		return null;
  	}
    	    
	/** Gets field coordinates of provided point*/
	public Coordinate getCoordinates(final Point2D point)
	{
		GripImage found = getFieldImage(point);
		if(found!=null)
    	{
			for(Coordinate coord : gripsMap.keySet())
    	  	{
				if(gripsMap.get(coord).equals(found))
				{
					return coord;
				}
    	  	}
    	}
		return null;
	}
    	    
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		for(GripImage gripImage: gripsMap.values())
    	{
			g2.setColor(gripImage.getGripColor());
			g2.fill(gripImage.getRect());
		}    
    }

	public Map<Coordinate, GripImage> getGripsMap()
	{
		return gripsMap;
	}

	public void setGripsMap(Map<Coordinate, GripImage> gripsMap)
	{
		this.gripsMap = gripsMap;
	} 
}