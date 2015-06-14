package view;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class GripImage
{
    private final Rectangle2D rect;
    private final Color gripColor;
    private final Integer idGrip;
    
    public GripImage(final double x, final double y, final double width, final double height, 
            final Color gripColor, final Integer idGrip )
    {
        super();
        this.rect = new Rectangle2D.Double(x,y,width,height);
        this.gripColor = gripColor;
        this.idGrip = idGrip;
    }

    public Color getGripColor()
    {
        return gripColor;
    }
    
    public Rectangle2D getRect()
    {
        return rect;
    }

    public Integer getIdGrip()
    {
        return idGrip;
    }     
}