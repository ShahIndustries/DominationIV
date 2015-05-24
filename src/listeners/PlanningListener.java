package listeners;

import interfaces.Main;
import interfaces.Planning;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import units.AdvancedUnit;
import units.HowitzerUnit;
import units.MainUnit;

public class PlanningListener implements ActionListener{

	int buttonID;
	public static final int MAIN_BUTTON = 0;
	public static final int ADVANCED_BUTTON = 1;
	public static final int EXECUTE_BUTTON = 2;
	public static final int HOWITZER_BUTTON = 3;
	
	
	public PlanningListener(int id)
	{
		buttonID = id;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(buttonID == MAIN_BUTTON)
		{
			addMain(Main.activePlayer);
		}
		else if(buttonID == ADVANCED_BUTTON)
		{
			addAdvanced(Main.activePlayer);
		}
		else if(buttonID == EXECUTE_BUTTON)
		{
			beginExecution();
		}
		else if(buttonID == HOWITZER_BUTTON)
		{
			addHowitzer(Main.activePlayer);
		}
	}
	
	private void addMain(int id)
	{
		int a;
		if(id == 0)
		{
			a = 0;
		}
		else
		{
			a = 19;
		}
		int i = -1;
		boolean viable = false;
		while(i < 19 && !viable)
		{
			i++;
			if(!Main.gameBoard.isBoxTaken(i, a))
			{
				viable = true;
			}
		}
		if(i == 19 && !viable)
		{
			
		}
		else
		{
			Main.gameBoard.units.add(new MainUnit(id, i, a));
			Main.players[id].money -= 500;
			Main.planningPanel.updateValues();
		}
	}
	
	private void addAdvanced(int id)
	{
		int a;
		if(id == 0)
		{
			a = 0;
		}
		else
		{
			a = 19;
		}
		int i = -1;
		boolean viable = false;
		while(i < 19 && !viable)
		{
			i++;
			if(!Main.gameBoard.isBoxTaken(i, a))
			{
				viable = true;
			}
		}
		if(i == 19 && !viable)
		{
			
		}
		else
		{
			Main.gameBoard.units.add(new AdvancedUnit(id, i, a));
			Main.players[id].money -= 2500;
			Main.planningPanel.updateValues();
		}
	}
	
	private void addHowitzer(int id)
	{
		int a;
		if(id == 0)
		{
			a = 0;
		}
		else
		{
			a = 19;
		}
		int i = -1;
		boolean viable = false;
		while(i < 19 && !viable)
		{
			i++;
			if(!Main.gameBoard.isBoxTaken(i, a))
			{
				viable = true;
			}
		}
		if(i == 19 && !viable)
		{
			
		}
		else
		{
			Main.gameBoard.units.add(new HowitzerUnit(id, i, a));
			Main.players[id].money -= 4000;
			Main.planningPanel.updateValues();
		}
	}
	
	private void beginExecution()
	{
		Main.updateMenuFrame(Main.WAIT_PANEL);
		Main.isPlanning = false;
		//calculate movements
		for(int i = 0; i < Main.gameBoard.units.size(); i++)
		{
			if(Main.gameBoard.units.get(i).currentPlan != null)
			{
				Main.gameBoard.units.get(i).currentPlan.calculateMovement(Main.gameBoard.units.get(i).xPos, Main.gameBoard.units.get(i).yPos);
			}
		}
		Main.executionCountdown = 30;
	}
}
