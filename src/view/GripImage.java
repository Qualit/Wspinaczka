package view;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 * Class representing image of a single field on the board
 * @author mk
 */
public class GripImage
{
    private final Rectangle2D rect;
    private final Color gripColor;
    private final Integer idGrip;
    
    /**
     * Class constructor specifying field image's; shape,color, selection state
     * @param x          
     * @param y
     * @param width
     * @param height
     * @param gripColor
     */
    public GripImage(final double x, final double y, final double width, final double height, 
            final Color gripColor, final Integer idGrip )
    {
        super();
        this.rect = new Rectangle2D.Double(x,y,width,height);
        this.gripColor = gripColor;
        this.idGrip = idGrip;
    }

    /** Gets this field's color*/
    public Color getGripColor()
    {
        return gripColor;
    }
    
    /** Gets this field's rectangle*/
    public Rectangle2D getRect()
    {
        return rect;
    }

    public Integer getIdGrip()
    {
        return idGrip;
    }   
    
    
}