package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import configuration.ViewConfiguration;

public class ApplicationFrame extends JFrame
{
    private  View view;
    private WallPanel wallPanel;
    private JPanel inputPanel;
    private JPanel statsPanel;
    private JPanel naviPanel;
    
    
    public ApplicationFrame(final View view)
    {
	 super("Wspinaczka");
	 this.setSize(ViewConfiguration.widthApplicationPanel, ViewConfiguration.heightApplicationPanel);
	 this.view = view;
	 this.wallPanel = new WallPanel(view.getStartWallMockup());
	 wallPanel.initializePanel();
	 wallPanel.setBackground(Color.YELLOW);
	 this.inputPanel = new JPanel();
	 this.statsPanel = new JPanel();
	 this.naviPanel = new JPanel();
	 
	 
    }


    public void initializeFrame()
    {
	this.add(wallPanel, BorderLayout.CENTER);
	JButton startButton = new JButton("START");
	inputPanel.add(startButton);
	JTextField nField = new JTextField(10);
	nField.setToolTipText("podaj N");
	inputPanel.add(nField);
	JTextField wField = new JTextField(10);
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
	naviPanel.add(nextButton);
	
	JButton prevButton = new JButton("PREV");
	naviPanel.add(prevButton);
	
	JTextField costField = new JTextField(10);
	costField.setToolTipText("Koszt sciezki");
	statsPanel.add(costField);
	
	JTextField deepField = new JTextField(10);
	deepField.setToolTipText("Glebokosc");
	statsPanel.add(deepField);
	
	
	
	this.add(inputPanel, BorderLayout.NORTH);
	this.add(statsPanel, BorderLayout.SOUTH);
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


    
    
    
    
    
}
