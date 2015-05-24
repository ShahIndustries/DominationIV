package listeners;

import interfaces.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data.Board;

public class SelectionListener implements ActionListener{

	int buttonID;
	public static boolean exitFlag = false;
	public static final int MOVE_BUTTON = 0;
	public static final int ATTACK_BUTTON = 1;
	public static final int EXIT_BUTTON = 2;
	
	public SelectionListener(int id)
	{
		this.buttonID = id;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(this.buttonID == MOVE_BUTTON)
		{
			moveSelection();
		}
		else if(this.buttonID == ATTACK_BUTTON)
		{
			attackSelection();
		}
		else if(this.buttonID == EXIT_BUTTON)
		{
			exitSelectionMenu();
		}
	}
	
	public void exitSelectionMenu()
	{
		exitFlag = true;
	}
	
	private void moveSelection()
	{
		Main.gameBoard.highlightPlan = Board.MOVE;
	}
	
	private void attackSelection()
	{
		Main.gameBoard.highlightPlan = Board.ATTACK;
	}
}
