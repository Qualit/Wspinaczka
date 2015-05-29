package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.Grip;

public class InputHandler 
{
	private Map<Integer, Grip> grips;
	private int numberOfGrips;
	private double wallHeight;
	
	public InputHandler() 
	{
		this.grips = new HashMap<Integer, Grip>();
		this.numberOfGrips = 0;
		this.wallHeight = 0;
	}
	
	public void prepareParameters(List<String> argsList) throws IOException 
	{
		switch (argsList.get(0))
		{
			case "r":
				randomGrips(Integer.parseInt(argsList.get(1)), Double.parseDouble(argsList.get(2)));
				break;
			case "f":
				gripsFromFile(argsList.get(1));
				break;
			default:
				break;
				
		}
	}
	
	public void prepareParametersFromGUI(List<String> argsList, INPUT_OPTION option) throws IOException
	{
		switch (option) {
			case FILE:
			{
				gripsFromFile(argsList.get(0));
				
				break;
			}
			case RANDOM:
			{
				randomGrips(Integer.parseInt(argsList.get(0)), Double.parseDouble(argsList.get(1)));

				break;
			}
			default:
				break;
		}
	}
	
	private void gripsFromFile(String fileName) throws IOException
	{
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		  
		String textLine = bufferedReader.readLine();
		String delims = "[ ]+";
		String[] tokens = textLine.split(delims);
		numberOfGrips = Integer.parseInt(tokens[0]);
		wallHeight = Double.parseDouble(tokens[1]);
		
		grips.put(0, new Grip(1.5 , 0.0, 2));
		
		for (int i=0 ; i < numberOfGrips ; i++)
		{
			textLine = bufferedReader.readLine();
			tokens = textLine.split(delims);
			grips.put(i+1, new Grip(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2])));
		}
		
		grips.put(numberOfGrips+1, new Grip(1.5 , wallHeight, 1));

		bufferedReader.close();
		
	}

	private void randomGrips(int n, double w) 
	{
		// TODO Auto-generated method stub
	    
	    numberOfGrips = n;
	    wallHeight = w;
	    
	    Map<GEN_PARAM, Double> gen = new HashMap<GEN_PARAM, Double>();
	    Map<Double, Grip> tmp = new TreeMap<Double, Grip>();
	    
	    for(int i=0; i<n; i++)
	    {
		gen = GripParamsRandomGenerator.generateParams(n, w);
		tmp.put(gen.get(GEN_PARAM.Y), new Grip(gen.get(GEN_PARAM.X), gen.get(GEN_PARAM.Y), gen.get(GEN_PARAM.COST)));
		
	    }
	    int j=0;
	    for(Grip g : tmp.values())
	    {
		grips.put(j,g);
		j++;
	    }
		
	}

	public final Map<Integer, Grip> getGrips() 
	{
		return grips;
	}
	public final int getNumberOfGrips() 
	{
		return numberOfGrips;
	}
	public final double getWallHeight() 
	{
		return wallHeight;
	}
	public final void setGrips(Map<Integer, Grip> grips) 
	{
		this.grips = grips;
	}
	public final void setNumberOfGrips(int numberOfGrips) 
	{
		this.numberOfGrips = numberOfGrips;
	}
	public final void setWallHeight(double wallHeight) 
	{
		this.wallHeight = wallHeight;
	}



	

}
