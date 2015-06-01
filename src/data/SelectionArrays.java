package data;

import java.util.ArrayList;

public class SelectionArrays {
	
	public static ArrayList<Point> controlPointCapture = new ArrayList<Point>();
	
	public static ArrayList<Point> mainMove = new ArrayList<Point>();
	public static ArrayList<Point> mainAttack = new ArrayList<Point>();
	public static ArrayList<Point> advancedMove = new ArrayList<Point>();
	public static ArrayList<Point> advancedAttack = new ArrayList<Point>();
	public static ArrayList<Point> howitzerMove = new ArrayList<Point>();
	public static ArrayList<Point> howitzerAttack = new ArrayList<Point>();
	
	public static void fillArrays()
	{
		mainMove = getCircularArray(1.5f);
		mainAttack = getCircularArray(2.5f);

		advancedMove = getCircularArray(3f);
		advancedAttack = getCircularArray(2.5f);
		
		howitzerMove = getCircularArray(1f);
		howitzerAttack = getCircularArray(6.5f);
		
		controlPointCapture = getCircularArray(1.5f);
	}
	
	//generate circular selection arrays
	private static ArrayList<Point> getCircularArray(float x)
	{
		int pointsInside;
		//x is the radius
		ArrayList<Point> insidePositions = new ArrayList<Point>();
		for(int i = (int) (0 - (x + 1)); i < (int)(x + 1); i++)
		{
			for(int j = (int) (0 - (x + 1)); j < (int)(x + 1); j++)
			{
				pointsInside = 0;
				if(Math.sqrt(Math.pow((i + 0.25), 2) + Math.pow((j - 0.25), 2)) <= x)
				{
					pointsInside++;
				}
				if(Math.sqrt(Math.pow((i - 0.25), 2) + Math.pow((j - 0.25), 2)) <= x)
				{
					pointsInside++;
				}
				if(Math.sqrt(Math.pow((i + 0.25), 2) + Math.pow((j + 0.25), 2)) <= x)
				{
					pointsInside++;
				}
				if(Math.sqrt(Math.pow((i - 0.25), 2) + Math.pow((j + 0.25), 2)) <= x)
				{
					pointsInside++;
				}
				
				if(pointsInside > 1)
				{
					if(i == 0 && j == 0)
					{
						
					}
					else
					{
						insidePositions.add(new Point(i, j));
					}
				}
			}
		}
		return insidePositions;
	}

}
