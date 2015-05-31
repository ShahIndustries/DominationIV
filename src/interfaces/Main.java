package interfaces;

import importing.ImportManager;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import listeners.SelectionListener;
import data.Board;
import data.Player;
import data.Point;
import data.SelectionArrays;
import render.BoardCanvas;
import units.BaseUnit;
import units.MovableUnit;
import units.Plan;
import units.Unit;

public class Main {

	public static Frame frame;
	public static JFrame menuFrame;
	public static BoardCanvas canvas;
	public static Selection selectionPanel;
	public static Wait waitPanel;
	public static Planning planningPanel;
	public static Insets frameInsets;
	public static int activePlayer = 0;
	public static Player[] players = new Player[2];
	public static Board gameBoard = new Board();
	public static int currentFrame;
	
	public static final int SELECTION_PANEL = 0;
	public static final int WAIT_PANEL = 1;
	public static final int PLANNING_PANEL = 2;
	
	public static boolean isPlanning = true;
	public static Unit selectedUnit = null;
	
	public static Point cursor = new Point(0, 0);
	public static int executionCountdown = 30;
	
	private static boolean dialogShown = false;
	
	//frame stuff
	public static float size = 600;
	
	public static void main(String[] args) throws InterruptedException
	{

		//frame setup
		setupFrame();
		
		//game setup
		for(int i = 0; i < 2; i++)
		{
			players[i] = new Player(i, 6000);
		}
		gameBoard.addBases();
		SelectionArrays.fillArrays();
		
		//menu setup
		setupMenuFrame();
		
		//the actual game loop
		long lastTime = 0;
		long currentTime = 0;
		while(true)
		{
			updateCanvasSize();
			//check for winning
			boolean blackBase = false;
			boolean whiteBase = false;
			for(int i = 0; i < gameBoard.units.size(); i++)
			{
				Unit tempUnit = gameBoard.units.get(i);
				if(tempUnit instanceof BaseUnit && tempUnit.playerOwnerID == 0)
				{
					blackBase = true;
				}
				else if(tempUnit instanceof BaseUnit && tempUnit.playerOwnerID == 1)
				{
					whiteBase = true;
				}
			}
			
			if(!blackBase && !dialogShown)
			{
				JOptionPane.showMessageDialog(Main.frame, "White has won!");
				dialogShown = true;
			}
			if(!whiteBase && !dialogShown)
			{
				JOptionPane.showMessageDialog(Main.frame, "Black has won!");
				dialogShown = true;
			}
				
			
			lastTime = System.currentTimeMillis();
			if(isPlanning)
			{
				//planning mode code
				canvas.render(BoardCanvas.PLANNING);
				if(SelectionListener.exitFlag)
				{
					selectedUnit = null;
					updateMenuFrame(PLANNING_PANEL);
					planningPanel.updateValues();
					SelectionListener.exitFlag = false;
				}
			}
			else
			{
				//executing mode code
				canvas.render(BoardCanvas.EXECUTING);
				for(int i= 0; i < Main.gameBoard.units.size(); i++)
				{
					if(Main.gameBoard.units.get(i).currentPlan != null)
					{
						Main.gameBoard.units.get(i).currentPlan.updatePrecisePosition();
					}
				}
				executionCountdown--;
				if(executionCountdown == 0)
				{
					endExecution();
				}
				
			}
			currentTime = System.currentTimeMillis();
			if(currentTime - lastTime < 33)
			{
				Thread.sleep(33 - (currentTime - lastTime));
			}
		}
	}
	
	private static void setupFrame()
	{
		ImportManager.load();
		frame = new Frame("Insert Strategy Game Name Here");
		canvas = new BoardCanvas();
		frame.setSize(600, 600);
		canvas.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(canvas);
		frameInsets = frame.getInsets();
		updateCanvasSize();
	}
	
	private static void setupMenuFrame()
	{
		menuFrame = new JFrame("Menu");
		menuFrame.setSize(300, 200);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		selectionPanel = new Selection();
		menuFrame.add(selectionPanel);
		waitPanel = new Wait();
		menuFrame.add(waitPanel);
		planningPanel = new Planning();
		menuFrame.add(planningPanel);
		updateMenuFrame(PLANNING_PANEL);
		menuFrame.setVisible(true);
	}
	
	public static void updateMenuFrame(int id)
	{
		if(id == SELECTION_PANEL)
		{
			currentFrame = SELECTION_PANEL;
			selectionPanel.setVisible(true);
			waitPanel.setVisible(false);
			planningPanel.setVisible(false);
		}
		else if(id == WAIT_PANEL)
		{
			currentFrame = WAIT_PANEL;
			selectionPanel.setVisible(false);
			waitPanel.setVisible(true);
			planningPanel.setVisible(false);
		}
		else if(id == PLANNING_PANEL)
		{
			currentFrame = PLANNING_PANEL;
			selectionPanel.setVisible(false);
			waitPanel.setVisible(false);
			planningPanel.setVisible(true);
		}
	}
	
	private static void endExecution()
	{
		isPlanning = true;
		//update unit positions
		for(int i = 0; i < Main.gameBoard.units.size(); i++)
		{
			Unit tempUnit = Main.gameBoard.units.get(i);
			if(tempUnit.currentPlan != null)
			{
				if(tempUnit.currentPlan.typeOfPlan == Plan.MOVE)
				{
					tempUnit.xPos = tempUnit.currentPlan.x;
					tempUnit.yPos = tempUnit.currentPlan.y;
					tempUnit.currentPlan = null;
				}
			}
		}
		for(int i = 0; i < Main.gameBoard.units.size(); i++)
		{
			Unit tempUnit = Main.gameBoard.units.get(i);
			if(tempUnit.currentPlan != null)
			{
				if(tempUnit.currentPlan.typeOfPlan == Plan.ATTACK)
				{
					if(Main.gameBoard.isBoxTaken(tempUnit.currentPlan.x, tempUnit.currentPlan.y))
					{
						MovableUnit tempUnitMovable = null;
						if(tempUnit instanceof MovableUnit)
						{
							tempUnitMovable = (MovableUnit) tempUnit;
						}
						Unit tempUnit2 = Main.gameBoard.getUnitAt(tempUnit.currentPlan.x, tempUnit.currentPlan.y);
						tempUnit2.hp -= tempUnitMovable.attack;
						if(tempUnit2.hp <= 0)
						{
							Main.gameBoard.units.remove(tempUnit2);
						}
					}
					tempUnit.currentPlan = null;
				}
			}
		}
		Main.players[Main.activePlayer].money += 400;
		if(Main.activePlayer == 0)
		{
			Main.activePlayer = 1;
		}
		else
		{
			Main.activePlayer = 0;
		}
		updateMenuFrame(PLANNING_PANEL);
		planningPanel.updateValues();
	}
	
	public static void updateCanvasSize()
	{
		int actualScreenX;
		int actualScreenY;
		//includes borders when calculating size of the canvas
		actualScreenX = Main.frame.getWidth() - Main.frameInsets.left - Main.frameInsets.right;
		actualScreenY = Main.frame.getHeight() - Main.frameInsets.top - Main.frameInsets.bottom;
		if(actualScreenX < actualScreenY)
		{
			size = actualScreenX;
		}
		else
		{
			size = actualScreenY;
		}
		Main.canvas.setBounds(Main.frameInsets.left + ((actualScreenX - (int)size) / 2), Main.frameInsets.top + ((actualScreenY - (int)size) / 2), (int)size, (int)size);
	}

}
