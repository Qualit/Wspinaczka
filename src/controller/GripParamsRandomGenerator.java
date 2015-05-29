package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import wspinaczka.*;

public class GripParamsRandomGenerator
{
    public static Map<GEN_PARAM, Double> generateParams(int n, double w)
    {
	Map<GEN_PARAM, Double>params = new HashMap<GEN_PARAM, Double>();
	
	Random rx = new Random();
	double xTmp = Configuration.minX + (Configuration.maxX - Configuration.minX)* rx.nextDouble();
	Double x = new Double(xTmp);
	params.put(GEN_PARAM.X, x);
	
	Random ry = new Random();
	double yTmp = Configuration.minY + (w - Configuration.minX)* ry.nextDouble();
	Double y = new Double(yTmp);
	params.put(GEN_PARAM.Y, y);
	
	Random rCost = new Random();
	double costTmp = Configuration.minCost + (Configuration.maxCost - Configuration.minCost)* rCost.nextDouble();
	Double cost = new Double(costTmp);
	params.put(GEN_PARAM.COST, cost);
	
	return params;
	
	
	
	
	
    }
}
