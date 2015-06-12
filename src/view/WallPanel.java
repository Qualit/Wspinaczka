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
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
    	this.setOpaque(true);
    	this.setVisible(true);
    	refresh(startWallMockup);    
    }
    	 	
    private Map<Coordinate, GripImage> generateNewGripsMap(final WallMockup wallMockup)
    {
    	Map<Coordinate,GripImage> ret = new HashMap<Coordinate, GripImage>();
    	Color gripColor;
            
    	TreeMap<Integer, GripMockup> temp = (TreeMap<Integer, GripMockup>) (wallMockup.getGripMockupsMap());
    	//System.out.print(temp.size());
    	Double lowestY = 0.0; 
        
    	if(temp.isEmpty())
    	{
    	    	lowestY = 0.0;
        }
    	else
    	{
    	    	lowestY = (temp.get(temp.firstKey())).getY();
    	}
    	    
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
				wPanel.setFocusable(true);
				wPanel.removeAll();
				wPanel.repaint();
				wPanel.setGripsMap(generateNewGripsMap(wallMockup));
				wPanel.repaint();
			}
		});        
	}
	
    	/** Gets the field image using point */
 	public GripImage getGripImage(final Point2D point)
	{
 		for(GripImage gripImage: gripsMap.values())
    	{
    		if(gripImage.getRect().contains(point))
    		{
    			return gripImage;
    	   	}
    	}
 		return null;
  	}
    	    
	/** Gets field coordinates of provided point*/
	public Coordinate getCoordinates(final Point2D point)
	{
		GripImage found = getGripImage(point);
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
		
		g2.setColor(getBackground());
		g2.fillRect(0, 0, getWidth(), getHeight());
		
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

	public void clear() 
	{
		this.removeAll();
		this.repaint();
	} 
	
//	@Override
//	protected void paintBorder(Graphics g) 
//	{
//	    Graphics2D g2 = (Graphics2D) g;
//	    g2.setColor(Color.BLACK);
//	    g2.draw(this.getBounds());
//	    System.out.println(this.getBounds().getMinX()+" "+this.getBounds().getMinY());
//	}
}