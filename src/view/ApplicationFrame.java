package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.org.apache.xerces.internal.dom.DeepNodeListImpl;

import configuration.ViewConfiguration;

public class ApplicationFrame extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  View view;
    private WallPanel wallPanel;
    private JPanel inputPanel;
    private JTextField nField;
    private JTextField wField;
    private JTextArea deepField;
    private JTextField costField;
    
    
    private JPanel naviPanel;
    private ExecutorService executor = Executors.newCachedThreadPool();
    
    
    public ApplicationFrame(final View view)
    {
	 super("Wspinaczka");
	 this.setSize(ViewConfiguration.widthApplicationPanel, ViewConfiguration.heightApplicationPanel);
	 this.view = view;
	 this.wallPanel = new WallPanel(view.getStartWallMockup());
	 wallPanel.initializePanel();
	 wallPanel.setBackground(Color.YELLOW);
	 wallPanel.addMouseListener(new WallPanelListener());
	 this.inputPanel = new JPanel();
	
	 this.naviPanel = new JPanel();
	 
	 
    }


    public void initializeFrame()
    {
    	this.add(wallPanel, BorderLayout.CENTER);
    	inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
    	JButton startButton = new JButton("START");
    	inputPanel.add(startButton);
    	nField = new JTextField(10);
    	nField.setToolTipText("podaj N");
    	inputPanel.add(nField);
    	wField = new JTextField(10);
    	wField.setToolTipText("podaj W");
    	inputPanel.add(wField);
    	inputPanel.setVisible(true);
	
    	startButton.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e)
    		{
    			// TODO Auto-generated method stub
		
    			
    		}
    	});
	
    	JButton nextButton = new JButton("NEXT");
    	naviPanel.setLayout(new BoxLayout(naviPanel, BoxLayout.PAGE_AXIS));
    	naviPanel.add(nextButton);
	
    	JButton prevButton = new JButton("PREV");
    	naviPanel.add(prevButton);
	
    	costField = new JTextField(10);
    	costField.setToolTipText("Koszt sciezki");
    	naviPanel.add(costField);
	
    	 deepField = new JTextArea(10,1);
    	deepField.setToolTipText("Glebokosc");
    	naviPanel.add(deepField);

    	this.add(inputPanel, BorderLayout.WEST);
    	
    	this.add(naviPanel, BorderLayout.EAST);
    	this.setVisible(true);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public View getView()
    {
        return view;
    }

    public void setView(final View view)
    {
        this.view = view;
    } 
    
    public void displayParams(String params)
    {
	deepField.setText(params);
    }
    
    class WallPanelListener extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            super.mouseClicked(e);
            final GripImage clickedImage = wallPanel.getGripImage(e.getPoint());
            if(clickedImage==null)
            {
                return;
            }
            executor.execute(new Runnable() {
                
                @Override
                public void run()
                {
                    view.displayParams(clickedImage.getIdGrip());
                }
            });
        }
    }
}