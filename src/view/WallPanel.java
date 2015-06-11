package view;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import mockups.*;
import configuration.ViewConfiguration;



public class WallPanel extends JPanel
{
    	private final int panelHeight = ViewConfiguration.heightWallPanel;
    	private final int panelWidth = ViewConfiguration.widthWallPanel;
    	private  Map<Coordinate, GripImage> gripsMap;
	
    	public WallPanel() 
	{
	    
	}
    	
    	public void initializePanel()
    	{
    	    this.setSize(panelWidth, panelHeight);
    	}
    	
    	
    	/*private void fillFieldsMap()
        {
            double x_d=0.0;
            double y_d=0.0;
            double fieldWidth = width/8;
            double fieldHeight = height/8;
            int col=0;
            int row=0;
            for(col=0; col<8; ++col)
            {
                for(row=0; row<8; ++row)
                {
                    x_d = row*fieldWidth;
                    y_d = col*fieldHeight;
                    Color color = Color.WHITE;
                    if((col+row)%2==0)
                    {
                        color = brightFieldsColor;
                    }
                    else
                    {
                        color = darkFieldsColor;
                    }
                    FieldImage fieldImage = new FieldImage(x_d, y_d, fieldWidth, fieldHeight, color, false);
                    Coordinate pos = new Coordinate(row,col);
                    gripsMap.put(pos, fieldImage); 
                }
            }
        }*/
    	
    	private Map<Coordinate, GripImage> generateNewGripsMap(final WallMockup wallMockup)
        {
            Map<Coordinate,GripImage> ret = new HashMap<Coordinate, GripImage>();
            Color gripColor;
            
            TreeMap<Integer, GripMockup> temp = (TreeMap<Integer, GripMockup>) wallMockup.getGripMockupsMap();
            
            Double lowestY = temp.get(temp.firstEntry()).getY();
            
            for(GripMockup gripMockup : wallMockup.getGripMockupsMap().values())
            {
        	Integer x = convertXToCoordinate(gripMockup.getX());
        	Integer y = convertYToCoordinate(gripMockup.getY(),lowestY);
                Coordinate coord = new Coordinate(x, y);
                gripColor = Color.BLUE;
                ret.put(coord, new GripImage(x, y, ViewConfiguration.widthOfGrip, ViewConfiguration.heightOfGrip, gripColor));
            }
            return ret;
        }
    	
    	 private Integer convertYToCoordinate(double y, Double lowestY)
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	private Integer convertXToCoordinate(double x)
	{
	    // TODO Auto-generated method stub
	    return null;
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

	    public Map<Coordinate, GripImage> getGripsMap()
	    {
	        return gripsMap;
	    }

	    public void setGripsMap(Map<Coordinate, GripImage> gripsMap)
	    {
	        this.gripsMap = gripsMap;
	    }
    	    
    	    
    	    
    	    
    
}
