package data;

import importing.ImportManager;
import interfaces.Main;

import java.util.ArrayList;

import render.BoardCanvas;
import units.AdvancedUnit;
import units.BaseUnit;
import units.ControlPoint;
import units.HowitzerUnit;
import units.MainUnit;
import units.MovableUnit;
import units.Plan;
import units.Unit;

public class Board {
	
	public ArrayList<Unit> units = new ArrayList<Unit>();
	public ArrayList<ControlPoint> controlPoints = new ArrayList<ControlPoint>();
	public int highlightPlan = 0;
	
	public static final int NONE = 0;
	public static final int MOVE = 1;
	public static final int ATTACK = 2;

	public boolean isBoxTaken(int x, int y)
	{
		//I don't know who you are, I don't know what you want, but I will find you, and I will kill you.
		boolean taken = false;
		for(int i = 0; i < units.size(); i++)
		{
			if(units.get(i).xPos == x && units.get(i).yPos == y)
			{
				taken = true;
			}
			if(units.get(i).currentPlan != null)
			{
				if(units.get(i).currentPlan.x == x && units.get(i).currentPlan.y == y && units.get(i).currentPlan.typeOfPlan == Plan.MOVE)
				{
					taken = true;
				}
			}
		}
		return taken;
	}
	
	public Unit getUnitAt(int x, int y)
	{
		for(int i = 0; i < units.size(); i++)
		{
			if(units.get(i).xPos == x && units.get(i).yPos == y)
			{
				return units.get(i);
			}
		}
		return null;
	}
	
	//call at setup
	public void addBasesAndControlPoints()
	{
		//black player base
		units.add(new BaseUnit(0, 3, 0));
		
		//white player base
		units.add(new BaseUnit(1, 3, 19));
		
		//control point: Iglesias
		controlPoints.add(new ControlPoint(15, 7));
		
		//control point: Malachi
		controlPoints.add(new ControlPoint(15, 12));
	}
	
	public boolean validSelection(int inputX, int inputY, int mode)
	{
		boolean works = false;
		ArrayList<Point> array = BoardCanvas.getSelectionArray();
		for(int i = 0; i < array.size(); i++)
		{
			int x = array.get(i).x;
			int y = array.get(i).y;
			if(!Main.gameBoard.isBoxTaken(x + Main.selectedUnit.xPos, y + Main.selectedUnit.yPos) || mode == ATTACK)
			{
				if(x + Main.selectedUnit.xPos >= 0 && x + Main.selectedUnit.xPos <= 19 && y + Main.selectedUnit.yPos >= 0 && y + Main.selectedUnit.yPos <= 19)
				{
					if(x + Main.selectedUnit.xPos == inputX && y + Main.selectedUnit.yPos == inputY)
					{
						works = true;
					}
				}
			}
		}
		return works;
	}
	
	public void calculateControlPointValues()
	{
		ControlPoint tempCP;
		int changeInBias;
		for(int i = 0; i < this.controlPoints.size(); i++)
		{
			tempCP = this.controlPoints.get(i);
			changeInBias = 0;
			
			//give money where it's due
			if(tempCP.playerBias == -2)
			{
				Main.players[0].money += ControlPoint.MONEY_PER_TURN;
			}
			else if(tempCP.playerBias == 2)
			{
				Main.players[1].money += ControlPoint.MONEY_PER_TURN;
			}
			
			//look for nearby units
			for(int j = 0; j < SelectionArrays.controlPointCapture.size(); j++)
			{
				if(this.isBoxTaken(tempCP.xPos + SelectionArrays.controlPointCapture.get(j).x, tempCP.yPos + SelectionArrays.controlPointCapture.get(j).y))
				{
					if(this.getUnitAt(tempCP.xPos + SelectionArrays.controlPointCapture.get(j).x, tempCP.yPos + SelectionArrays.controlPointCapture.get(j).y) instanceof MovableUnit)
					{
						if(this.getUnitAt(tempCP.xPos + SelectionArrays.controlPointCapture.get(j).x, tempCP.yPos + SelectionArrays.controlPointCapture.get(j).y).playerOwnerID == 0)
						{
							changeInBias--;
						}
						else
						{
							changeInBias++;
						}
					}
				}
			}
			
			//adjust bias accordingly
			tempCP.playerBias += changeInBias;
			if(tempCP.playerBias < -2)
			{
				tempCP.playerBias = -2;
			}
			else if(tempCP.playerBias > 2)
			{
				tempCP.playerBias = 2;
			}
		}
	}
}