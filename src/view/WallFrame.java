package view;

import javax.swing.JFrame;

public class WallFrame extends JFrame
{
    private  View view;
    
    
    public WallFrame()
    {
	// TODO Auto-generated constructor stub
	 super("Wspinaczka");
	 
    }


    public View getView()
    {
        return view;
    }


    public void setView(final View view)
    {
        this.view = view;
    }


    
    
    
    
    
}
