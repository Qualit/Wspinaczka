package view;

import java.awt.Color;
import java.awt.geom.Rectangle2D;


/**
 * Class representing image of a single field on the board
 * 
 * @author mk
 *
 */
public class GripImage
{
    /** FieldImage shape*/
    private final Rectangle2D rect;
    
    /** FieldImage color*/ 
    private final Color fieldImageColor;
   
    
    public GripImage(final Rectangle2D rect, final Color fieldcolor, boolean isSelected)
    {
        super();
        this.rect = rect;
        this.fieldImageColor = fieldcolor;
        
    }
    
    /**
     * Class constructor specifying field image's; shape,color, selection state
     * 
     * @param x          
     * @param y
     * @param width
     * @param height
     * @param fieldcolor
     * @param isSelected
     */
    public GripImage(final double x, final double y, final double width, final double height, 
            final Color fieldcolor )
    {
        super();
        this.rect = new Rectangle2D.Double(x,y,width,height);
        this.fieldImageColor = fieldcolor;
       
    }

    /** Gets this field's color*/
    public Color getFieldcolor()
    {
        return fieldImageColor;
    }
    
    /** Gets this field's rectangle*/
    public Rectangle2D getRect()
    {
        return rect;
    }

    
}


