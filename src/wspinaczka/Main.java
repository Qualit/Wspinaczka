package wspinaczka;

import model.AStar;
import model.Wall;

public class Main
{
    public static void main(String[] args)
    {
    	switch(args.length)
    	{
    		case 2:		// przypadek z wczytywaniem, argumenty f filename np. f uchwyty.txt aby wczytac sciane z pliku uchwyty
    			break;
    		case 3:		// przypadek z losowaniem, argumanty r n W np. r 10 5 dla sciany wysokiej na 5 metrow z 10 uchwytami
    			System.out.println(args[0] + " " + args[1] + " " + args[2]);
    			break;
        	default:
    			System.out.println("Zła liczba argumentow");
        		System.exit(1);
        		break;
    	}

    	// testy
    	Wall testWall = new Wall(6, 10);
    	AStar algortym = new AStar(testWall.getStart(), testWall.getGoal(), testWall);
    	algortym.testy();
    }
}
