package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mockups.WallMockup;
import model.State;
import utils.NumericChecker;
import configuration.ViewConfiguration;

public class ApplicationFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;

	private View view;
	private WallPanel wallPanel;
	private JPanel inputPanel;
	private JTextField nField;
	private JTextField wField;
	private JTextArea deepField;
	private JTextArea costField;
	private JTextArea gripField;
	private JButton openBtn;
	private JFileChooser filechoser;
	private String filePath = "";
	private JPanel naviPanel;
	private ExecutorService executor = Executors.newCachedThreadPool();

	public ApplicationFrame(final View view) 
	{
		super("Wspinaczka");
		this.setSize(ViewConfiguration.widthApplicationPanel,
				ViewConfiguration.heightApplicationPanel);
		this.view = view;
		this.wallPanel = new WallPanel(view.getStartWallMockup());
		wallPanel.initializePanel();
		wallPanel.setBackground(new Color(243, 250, 195));
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
		JLabel nLabel = new JLabel("Podaj n:");
		nField = new JTextField(10);
		nField.setToolTipText("podaj n");
		inputPanel.add(nLabel);
		inputPanel.add(nField);
		JLabel wLabel = new JLabel("Podaj W:");
		wField = new JTextField(10);
		wField.setToolTipText("podaj W");
		inputPanel.add(wLabel);
		inputPanel.add(wField);
		openBtn = new JButton("Open File");
		inputPanel.add(openBtn);
		inputPanel.setVisible(true);

		JButton nextButton = new JButton("NEXT");
		naviPanel.setLayout(new BoxLayout(naviPanel, BoxLayout.PAGE_AXIS));
		naviPanel.add(nextButton);

		JButton prevButton = new JButton("PREV");
		naviPanel.add(prevButton);

		costField = new JTextArea(10, 1);
		costField.setToolTipText("Koszt sciezki");
		naviPanel.add(costField);

		deepField = new JTextArea(10, 1);
		deepField.setToolTipText("Glebokosc");
		naviPanel.add(deepField);

		gripField = new JTextArea(10, 1);
		gripField.setToolTipText("Uchwyt");
		naviPanel.add(gripField);

		this.add(inputPanel, BorderLayout.WEST);

		this.add(naviPanel, BorderLayout.EAST);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		startButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				List<String> argsList = new ArrayList<String>();
				if (filePath.isEmpty()) 
				{
					argsList.add("r");
					String n = nField.getText();
					if (NumericChecker.isNumericInteger(n)) 
					{
						argsList.add(n);
					} 
					else 
					{
						return;
					}
					String w = wField.getText();
					if (NumericChecker.isNumericDouble(w)) 
					{
						argsList.add(w);
					} 
					else 
					{
						return;
					}
					if (argsList.size() != 3) 
					{
						return;
					}

					for (String s : argsList) 
					{
						System.out.println(s);
					}
					try 
					{
						view.initializeWall(argsList);
						view.displayStats();
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				} 
				else 
				{
					argsList.add("f");
					argsList.add(filePath);
					for (String s : argsList) 
					{
						System.out.println(s);
					}
					try 
					{
						view.initializeWall(argsList);
						view.displayStats();
					} 
					catch (IOException e1) 
					{

						e1.printStackTrace();
					}
				}

			}
		});

		nextButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final State next = view.getCurrentState().getNext();
				if (next != null) 
				{
					wallPanel.clear();
					view.setCurrentState(next);
					view.displayStats();
					view.refreshView();
				}
			}
		});

		prevButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				final State prev = view.getCurrentState().getPrevious();
				if (prev != null) 
				{
					view.setCurrentState(prev);
					view.displayStats();
					view.refreshView();
				}
			}
		});

		openBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				filechoser = new JFileChooser();
				filechoser.showOpenDialog(null);
				filePath = filechoser.getSelectedFile().getAbsolutePath();
			}
		});

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
		gripField.setText(params);
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
			if (clickedImage == null) 
			{
				return;
			}
			executor.execute(new Runnable() 
			{

				@Override
				public void run() 
				{
					view.displayParams(clickedImage.getIdGrip());
				}
			});
		}
	}

	public void updateWallPanel(WallMockup wallMockup) 
	{
		wallPanel.refresh(wallMockup);
	}

	public void displayStats(double currentCost, int numberOfState) 
	{
		DecimalFormat df = new DecimalFormat("#.####");
		costField.setText("Koszt dojścia:\n" + df.format(currentCost));
		deepField.setText("Numer stanu:\n" + numberOfState);
	}
}