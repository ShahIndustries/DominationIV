package listeners;

import interfaces.Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import data.Board;
import units.MovableUnit;
import units.Plan;
import units.Unit;

public class CanvasListener extends MouseAdapter{
	
	public void mousePressed(MouseEvent e)
	{
		if(Main.isPlanning)
		{
			if(Main.selectedUnit == null)
			{
				selectUnit(e);
				Main.gameBoard.highlightPlan = Board.MOVE;
			}
			else if(Main.gameBoard.highlightPlan == Board.MOVE || Main.gameBoard.highlightPlan == Board.ATTACK)
			{
				makePlan();
			}
		}
		else
		{
			
		}
	}
	
	private void selectUnit(MouseEvent e)
	{
		int x = (int) ((e.getX() *(600 / Main.size)) / 30);
		int y = (int) ((e.getY() *(600 / Main.size)) / 30);
		Unit tempUnit;
		if(Main.gameBoard.isBoxTaken(x, y))
		{
			tempUnit = Main.gameBoard.getUnitAt(x, y);
			if(tempUnit instanceof MovableUnit && tempUnit.playerOwnerID == Main.activePlayer)
			{
				Main.selectedUnit = tempUnit;
				Main.updateMenuFrame(Main.SELECTION_PANEL);
				Main.gameBoard.highlightPlan = Board.NONE;
			}
		}
	}
	
	private void makePlan()
	{
		if(Main.cursor.x == Main.selectedUnit.xPos && Main.cursor.y == Main.selectedUnit.yPos)
		{
			Main.selectedUnit.currentPlan = null;
		}
		if(Main.gameBoard.validSelection(Main.cursor.x, Main.cursor.y, Main.gameBoard.highlightPlan))
		{
			if(Main.gameBoard.highlightPlan == Board.MOVE)
			{
				Main.selectedUnit.currentPlan = new Plan(Plan.MOVE, Main.cursor.x, Main.cursor.y);
			}
			else if(Main.gameBoard.highlightPlan == Board.ATTACK)
			{
				Main.selectedUnit.currentPlan = new Plan(Plan.ATTACK, Main.cursor.x, Main.cursor.y);
			}
			SelectionListener.exitFlag = true;
		}
	}

}
