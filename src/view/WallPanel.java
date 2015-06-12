package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicLookAndFeel;

import mockups.*;
import model.LEG;
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
    private List<LegImage> legImageList = new ArrayList<LegImage>();
    
    
    
	
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
    	Map<LEG,GripMockup> tempLegs = new HashMap<LEG, GripMockup>();  
    	for(GripMockup gripMockup : wallMockup.getGripMockupsMap().values())
    	{
    	    	
    		Integer x = convertXToCoordinate(gripMockup.getX());
    		Integer y = convertYToCoordinate(gripMockup.getY(),lowestY);
    		Integer widthOfGrip = calculateWidthOfGrip(gripMockup.getCost());
    		Map<LEG, Point2D> legPoints = new HashMap<LEG, Point2D>();
    		
        	legImageList = new ArrayList<LegImage>();
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
                			tempLegs.put(LEG.LEFT_HAND, gripMockup);
                			System.out.println("dodaje lewa reke");
                			break;
                		}
                	case RIGHT_HAND:
            			{
            				gripColor = Color.RED;
            				tempLegs.put(LEG.RIGHT_HAND, gripMockup);
            				System.out.println("dodaje prawa reke");
            				break;
            			}
                	case LEFT_FOOT:
            			{
            				gripColor = Color.ORANGE;
            				tempLegs.put(LEG.LEFT_FOOT, gripMockup);
            				System.out.println("dodaje lewa noge");
            				break;
            			}
                	case RIGHT_FOOT:
                		{
                			gripColor = Color.PINK;
                			tempLegs.put(LEG.RIGHT_FOOT, gripMockup);
                			System.out.println("dodaje prawa noge");
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
    	if(tempLegs.size()==4)
    	{
        	double dx1 = tempLegs.get(LEG.LEFT_HAND).getX();
        	double dy1 = tempLegs.get(LEG.LEFT_HAND).getY();
        	Integer ix1 = convertXToCoordinate(dx1);
        	Integer iy1 = convertYToCoordinate(dy1, lowestY);
        	Coordinate p1 = new Coordinate(ix1,iy1);
        	double dx2 = tempLegs.get(LEG.RIGHT_FOOT).getX();
        	double dy2 = tempLegs.get(LEG.RIGHT_FOOT).getY();
        	Integer ix2 = convertXToCoordinate(dx2);
        	Integer iy2 = convertYToCoordinate(dy2, lowestY);
        	Coordinate p2 = new Coordinate(ix2, iy2);
        	legImageList.add(new LegImage(p1,p2));
        	System.out.println("tworze legimage");
        	
        	double dx3 = tempLegs.get(LEG.RIGHT_HAND).getX();
        	double dy3 = tempLegs.get(LEG.RIGHT_HAND).getY();
        	Integer ix3 = convertXToCoordinate(dx3);
        	Integer iy3 = convertYToCoordinate(dy3, lowestY);
        	Coordinate p3 = new Coordinate(ix3,iy3);
        	double dx4 = tempLegs.get(LEG.LEFT_FOOT).getX();
        	double dy4 = tempLegs.get(LEG.LEFT_FOOT).getY();
        	Integer ix4 = convertXToCoordinate(dx4);
        	Integer iy4 = convertYToCoordinate(dy4, lowestY);
        	Coordinate p4 = new Coordinate(ix4, iy4);
        	legImageList.add(new LegImage(p3,p4));
        	System.out.println("tworze legimage");
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
		
		for(LegImage legImage : legImageList)
		{
		    g2.setColor(Color.BLACK);
		    g2.draw(legImage.getLegLine());
		    System.out.println("Rysuje linie"+legImage.getStart());
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
