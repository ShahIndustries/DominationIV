package render;

import importing.ImportManager;
import interfaces.Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import data.Board;
import data.Point;
import data.SelectionArrays;
import listeners.CanvasListener;
import units.AdvancedUnit;
import units.HowitzerUnit;
import units.MainUnit;
import units.Plan;
import units.Unit;

public class BoardCanvas extends Canvas{
	
	public static final int EXECUTING = 0;
	public static final int PLANNING = 1;
	public BufferStrategy bs;
	
	public static final Color BLUE = new Color(0, 0, 255);
	public static final Color RED = new Color(255, 0, 0);
	
	private BufferedImage unresized;
	
	public BoardCanvas()
	{
		this.addMouseListener(new CanvasListener());
		unresized = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
	}
	
	public void render(int mode)
	{
		if(this.getBufferStrategy() == null)
		{
			this.createBufferStrategy(2);
			bs = this.getBufferStrategy();
		}
		Graphics2D upperG = (Graphics2D) bs.getDrawGraphics();
		upperG.setRenderingHints(new RenderingHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR));
		Graphics2D g = unresized.createGraphics();
		
		
		//draw background
		g.drawImage(ImportManager.background, 0, 0, null);
		
		Unit tempUnit;
		if(mode == PLANNING)
		{
			//draw units planning
			for(int i = 0; i < Main.gameBoard.units.size(); i++)
			{
				//unit
				tempUnit = Main.gameBoard.units.get(i);
				g.drawImage(tempUnit.icon, tempUnit.xPos * 30, tempUnit.yPos * 30, null);
				
				//health
				g.setColor(new Color(0, 255, 0));
				g.fillRect((tempUnit.xPos * 30) + 2, (tempUnit.yPos * 30) + 24, getHealthWidth(tempUnit), 4);
			}
		}
		else
		{
			//draw units execution
			for(int i = 0; i < Main.gameBoard.units.size(); i++)
			{
				//unit
				tempUnit = Main.gameBoard.units.get(i);
				if(tempUnit.currentPlan == null)
				{
					g.drawImage(tempUnit.icon, tempUnit.xPos * 30, tempUnit.yPos * 30, null);
					
					//health
					g.setColor(new Color(0, 255, 0));
					g.fillRect((tempUnit.xPos * 30) + 2, (tempUnit.yPos * 30) + 24, getHealthWidth(tempUnit), 4);
				}
				else if(tempUnit.currentPlan.typeOfPlan == Plan.MOVE)
				{
					g.drawImage(tempUnit.icon, (int)(tempUnit.currentPlan.perciseX * 30.0f), (int)(tempUnit.currentPlan.perciseY * 30.0f), null);
					
					//health
					g.setColor(new Color(0, 255, 0));
					g.fillRect((int) ((tempUnit.currentPlan.perciseX * 30.0f) + 2), (int) ((tempUnit.currentPlan.perciseY * 30.0f) + 24), getHealthWidth(tempUnit), 4);
				}
				else
				{
					//attacking
					g.drawImage(tempUnit.icon, tempUnit.xPos * 30, tempUnit.yPos * 30, null);
					
					//health
					g.setColor(new Color(0, 255, 0));
					g.fillRect((tempUnit.xPos * 30) + 2, (tempUnit.yPos * 30) + 24, getHealthWidth(tempUnit), 4);
					
					//attacking projectile
					g.setColor(RED);
					g.fillRect((int) ((tempUnit.currentPlan.perciseX * 30.0f) + 12), (int) ((tempUnit.currentPlan.perciseY * 30.0f) + 12), 6, 6);
				}
			}
		}
		
		//draw grid
		g.drawImage(ImportManager.boxGrid, 0, 0, null);
		
		if(mode == PLANNING)
		{
			//draw highlight
			ArrayList<Point> array = new ArrayList<Point>();
			if(Main.gameBoard.highlightPlan != Board.NONE && Main.selectedUnit != null)
			{
				array = getSelectionArray();
				for(int i = 0; i < array.size(); i++)
				{
					int x = array.get(i).x;
					int y = array.get(i).y;
					if(!Main.gameBoard.isBoxTaken(x + Main.selectedUnit.xPos, y + Main.selectedUnit.yPos) || Main.gameBoard.highlightPlan == Board.ATTACK)
					{
					if(x + Main.selectedUnit.xPos >= 0 && x + Main.selectedUnit.xPos <= 19 && y + Main.selectedUnit.yPos >= 0 && y + Main.selectedUnit.yPos <= 19)
					{
						g.drawImage(ImportManager.boxYellow, (x + Main.selectedUnit.xPos) * 30, (y + Main.selectedUnit.yPos) * 30, null);
					}
					}
				}
			}
			
			//draw plans
			for(int i = 0; i < Main.gameBoard.units.size(); i++)
			{
				tempUnit = Main.gameBoard.units.get(i);
				if(tempUnit.currentPlan != null)
				{
					//draw square and line
					if(tempUnit.currentPlan.typeOfPlan == Plan.MOVE)
					{
						g.drawImage(ImportManager.boxBlue, tempUnit.currentPlan.x * 30, tempUnit.currentPlan.y * 30, null);
						g.setColor(BLUE);
						g.drawLine((tempUnit.xPos * 30) + 15, (tempUnit.yPos * 30) + 15, (tempUnit.currentPlan.x * 30) + 15, (tempUnit.currentPlan.y * 30) + 15);
					}
					else
					{
						g.drawImage(ImportManager.boxRed, tempUnit.currentPlan.x * 30, tempUnit.currentPlan.y * 30, null);
						g.setColor(RED);
						g.drawLine((tempUnit.xPos * 30) + 15, (tempUnit.yPos * 30) + 15, (tempUnit.currentPlan.x * 30) + 15, (tempUnit.currentPlan.y * 30) + 15);
					}
				}
			}
			
			//draw cursor
			if(Main.gameBoard.highlightPlan != Board.NONE && Main.selectedUnit != null)
			{
				int xPos = (int) (((MouseInfo.getPointerInfo().getLocation().x - this.getLocationOnScreen().x) * (600 / Main.size)) / 30);
				int yPos = (int) (((MouseInfo.getPointerInfo().getLocation().y - this.getLocationOnScreen().y) * (600 / Main.size)) / 30);
				if(xPos >= 0 && xPos <= 19 && yPos >= 0 && yPos <= 19)
				{
					Main.cursor.x = xPos;
					Main.cursor.y = yPos;
				}
				if(Main.gameBoard.highlightPlan == Board.MOVE)
				{
					g.drawImage(ImportManager.boxBlue, Main.cursor.x * 30, Main.cursor.y * 30, null);
				}
				else if(Main.gameBoard.highlightPlan == Board.ATTACK)
				{
					g.drawImage(ImportManager.boxRed, Main.cursor.x * 30, Main.cursor.y * 30, null);
				}
			}
		}
		
		//buffer stuff
		g.dispose();
		upperG.drawImage(unresized, 0, 0, this.getWidth(), this.getHeight(), null);
		upperG.dispose();
		bs.show();
		Toolkit.getDefaultToolkit().sync();
	}
	
	private static int getHealthWidth(Unit u)
	{
		float percent = ((float)u.hp / (float)u.maxHP);
		return (int) (percent * 26);
	}
	
	public static ArrayList<Point> getSelectionArray()
	{
		if(Main.gameBoard.highlightPlan == Board.MOVE)
		{
			if(Main.selectedUnit instanceof MainUnit)
			{
				return SelectionArrays.mainMove;
			}
			else if(Main.selectedUnit instanceof AdvancedUnit)
			{
				return SelectionArrays.advancedMove;
			}
			else if(Main.selectedUnit instanceof HowitzerUnit)
			{
				return SelectionArrays.howitzerMove;
			}
		}
		else if(Main.gameBoard.highlightPlan == Board.ATTACK)
		{
			if(Main.selectedUnit instanceof MainUnit)
			{
				return SelectionArrays.mainAttack;
			}
			else if(Main.selectedUnit instanceof AdvancedUnit)
			{
				return SelectionArrays.advancedAttack;
			}
			else if(Main.selectedUnit instanceof HowitzerUnit)
			{
				return SelectionArrays.howitzerAttack;
			}
		}
		return null;
	}

}
