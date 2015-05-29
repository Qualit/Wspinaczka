package model;

import java.util.Map;

public class Model 
{
	private final Wall wall;
	
//	public Model(final String[] args) 
//	{
//		super();
//    	switch(args.length)
//    	{
//    		case 2:		// przypadek z wczytywaniem, argumenty f filename np. f uchwyty.txt aby wczytac sciane z pliku uchwyty
//    			System.out.println(args[0] + " " + args[1]);
//    			final String fileName = args[1];
//    			this.wall = new Wall(fileName);
//    			break;
//    		case 3:		// przypadek z losowaniem, argumanty r n W np. r 10 5 dla sciany wysokiej na 5 metrow z 10 uchwytami
//    			System.out.println(args[0] + " " + args[1] + " " + args[2]);
//    			final int n = Integer.parseInt(args[1]);
//    			final double w = Double.parseDouble(args[2]);
//    			this.wall = new Wall(n, w);
//    			break;
//        	default:
//    			System.out.println("Zła liczba argumentow");
//    			this.wall = null;
//        		System.exit(1);
//        		break;
//    	}
//		
//	}

	public Model(Map<Integer, Grip> grips, int numberOfGrips, double wallHeight) 
	{
		this.wall = new Wall(grips, numberOfGrips, wallHeight);
	}
}
