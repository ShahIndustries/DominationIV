package data;

import java.util.ArrayList;

public class SelectionArrays {
	
	public static ArrayList<Point> mainMove = new ArrayList<Point>();
	public static ArrayList<Point> mainAttack = new ArrayList<Point>();
	public static ArrayList<Point> advancedMove = new ArrayList<Point>();
	public static ArrayList<Point> advancedAttack = new ArrayList<Point>();
	public static ArrayList<Point> howitzerMove = new ArrayList<Point>();
	public static ArrayList<Point> howitzerAttack = new ArrayList<Point>();
	
	public static void fillArrays()
	{
		//FILL MAIN MOVE
		
		mainMove.add(new Point(-1, -1));
		mainMove.add(new Point(-1, 0));
		mainMove.add(new Point(-1, 1));
		mainMove.add(new Point(0, -1));
		mainMove.add(new Point(0, 1));
		mainMove.add(new Point(1, -1));
		mainMove.add(new Point(1, 0));
		mainMove.add(new Point(1, 1));
		
		//FILL MAIN ATTACK
		
		mainAttack.add(new Point(-1, -1));
		mainAttack.add(new Point(-1, 0));
		mainAttack.add(new Point(-1, 1));
		mainAttack.add(new Point(0, -1));
		mainAttack.add(new Point(0, 1));
		mainAttack.add(new Point(1, -1));
		mainAttack.add(new Point(1, 0));
		mainAttack.add(new Point(1, 1));
		mainAttack.add(new Point(-1, -2));
		mainAttack.add(new Point(0, -2));
		mainAttack.add(new Point(1, -2));
		mainAttack.add(new Point(-2, -1));
		mainAttack.add(new Point(-2, 0));
		mainAttack.add(new Point(-2, 1));
		mainAttack.add(new Point(2, -1));
		mainAttack.add(new Point(2, 0));
		mainAttack.add(new Point(2, 1));
		mainAttack.add(new Point(-1, 2));
		mainAttack.add(new Point(0, 2));
		mainAttack.add(new Point(1, 2));
		
		//FILL ADVANCED MOVE
		
		advancedMove.add(new Point(-1, -1));
		advancedMove.add(new Point(-1, 0));
		advancedMove.add(new Point(-1, 1));
		advancedMove.add(new Point(0, -1));
		advancedMove.add(new Point(0, 1));
		advancedMove.add(new Point(1, -1));
		advancedMove.add(new Point(1, 0));
		advancedMove.add(new Point(1, 1));
		advancedMove.add(new Point(-1, -2));
		advancedMove.add(new Point(0, -2));
		advancedMove.add(new Point(1, -2));
		advancedMove.add(new Point(-2, -1));
		advancedMove.add(new Point(-2, 0));
		advancedMove.add(new Point(-2, 1));
		advancedMove.add(new Point(2, -1));
		advancedMove.add(new Point(2, 0));
		advancedMove.add(new Point(2, 1));
		advancedMove.add(new Point(-1, 2));
		advancedMove.add(new Point(0, 2));
		advancedMove.add(new Point(1, 2));
		advancedMove.add(new Point(-3, 0));
		advancedMove.add(new Point(3, 0));
		advancedMove.add(new Point(0, -3));
		advancedMove.add(new Point(0, 3));
		
		//FILL ADVANCED ATTACK
		
		advancedAttack.add(new Point(-1, -1));
		advancedAttack.add(new Point(-1, 0));
		advancedAttack.add(new Point(-1, 1));
		advancedAttack.add(new Point(0, -1));
		advancedAttack.add(new Point(0, 1));
		advancedAttack.add(new Point(1, -1));
		advancedAttack.add(new Point(1, 0));
		advancedAttack.add(new Point(1, 1));
		advancedAttack.add(new Point(-1, -2));
		advancedAttack.add(new Point(0, -2));
		advancedAttack.add(new Point(1, -2));
		advancedAttack.add(new Point(-2, -1));
		advancedAttack.add(new Point(-2, 0));
		advancedAttack.add(new Point(-2, 1));
		advancedAttack.add(new Point(2, -1));
		advancedAttack.add(new Point(2, 0));
		advancedAttack.add(new Point(2, 1));
		advancedAttack.add(new Point(-1, 2)); 
		advancedAttack.add(new Point(0, 2));
		advancedAttack.add(new Point(1, 2));
		
		//FILL HOWITZER MOVE
		howitzerMove.add(new Point(0, -1));
		howitzerMove.add(new Point(-1, 0));
		howitzerMove.add(new Point(1, 0));
		howitzerMove.add(new Point(0, 1));
		
		//FILL HOWITZER ATTACK
		for(int i = -15; i < 16; i++)
		{
			for(int j = -15; j < 16; j++)
			{
				if(i == 0 && j == 0)
				{
					
				}
				else
				{
					howitzerAttack.add(new Point(i, j));
				}
			}
		}
		
	}

}
