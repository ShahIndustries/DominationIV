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
import units.ControlPoint;
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
	
	//canvas size
	public static final int CANVAS_SIZE = 1000;
	public static final int BOX_SIZE = CANVAS_SIZE / 20;
	
	private BufferedImage unresized;
	
	public BoardCanvas()
	{
		this.addMouseListener(new CanvasListener());
		unresized = new BufferedImage(CANVAS_SIZE, CANVAS_SIZE, BufferedImage.TYPE_INT_ARGB);
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
		ControlPoint tempCP;
		if(mode == PLANNING)
		{
			//draw units planning
			for(int i = 0; i < Main.gameBoard.units.size(); i++)
			{
				//unit
				tempUnit = Main.gameBoard.units.get(i);
				g.drawImage(tempUnit.icon, tempUnit.xPos * BOX_SIZE, tempUnit.yPos * BOX_SIZE, null);
				
				//health
				g.setColor(new Color(0, 255, 0));
				g.fillRect((tempUnit.xPos * BOX_SIZE) + (BOX_SIZE / 15), (tempUnit.yPos * BOX_SIZE) + (int)(BOX_SIZE * 0.8f), getHealthWidth(tempUnit), (int)(BOX_SIZE * (4f / 30f)));
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
					g.drawImage(tempUnit.icon, tempUnit.xPos * BOX_SIZE, tempUnit.yPos * BOX_SIZE, null);
					
					//health
					g.setColor(new Color(0, 255, 0));
					g.fillRect((tempUnit.xPos * BOX_SIZE) + (BOX_SIZE / 15), (tempUnit.yPos * BOX_SIZE) + (int)(BOX_SIZE * 0.82f), getHealthWidth(tempUnit), (int)(BOX_SIZE * (4f / 30f)));
				}
				else if(tempUnit.currentPlan.typeOfPlan == Plan.MOVE)
				{
					g.drawImage(tempUnit.icon, (int)(tempUnit.currentPlan.perciseX * (float)BOX_SIZE), (int)(tempUnit.currentPlan.perciseY * (float)BOX_SIZE), null);
					
					//health
					g.setColor(new Color(0, 255, 0));
					g.fillRect((int) ((tempUnit.currentPlan.perciseX * (float)BOX_SIZE) + (BOX_SIZE / 15)), (int) ((tempUnit.currentPlan.perciseY * (float)BOX_SIZE) + (BOX_SIZE * 0.82f)), getHealthWidth(tempUnit), (int)(BOX_SIZE * (4f / 30f)));
				}
				else
				{
					//attacking
					g.drawImage(tempUnit.icon, tempUnit.xPos * BOX_SIZE, tempUnit.yPos * BOX_SIZE, null);
					
					//health
					g.setColor(new Color(0, 255, 0));
					g.fillRect((tempUnit.xPos * BOX_SIZE) + (BOX_SIZE / 15), (tempUnit.yPos * BOX_SIZE) + (int)(BOX_SIZE * 0.82f), getHealthWidth(tempUnit), (int)(BOX_SIZE * (4f / 30f)));
					
					//attacking projectile
					g.setColor(RED);
					g.fillRect((int) ((tempUnit.currentPlan.perciseX * (float)BOX_SIZE) + (BOX_SIZE * 0.4f)), (int) ((tempUnit.currentPlan.perciseY * (float)BOX_SIZE) + (BOX_SIZE * 0.4f)), (int)(BOX_SIZE * 0.2f), (int)(BOX_SIZE * 0.2f));
				}
			}
		}
		
		//draw control points
		for(int i =0; i < Main.gameBoard.controlPoints.size(); i++)
		{
			tempCP = Main.gameBoard.controlPoints.get(i);
			
			//control point icon
			g.drawImage(tempCP.icon, tempCP.xPos * BOX_SIZE, tempCP.yPos * BOX_SIZE, null);
			
			//control point bias
			g.setColor(Color.WHITE);
			g.fillRect((tempCP.xPos * BOX_SIZE) + (BOX_SIZE / 15), (tempCP.yPos * BOX_SIZE) + (int)(BOX_SIZE * 0.82f), getWhiteBiasWidth(tempCP), (int)(BOX_SIZE * (4f / 30f)));
			g.setColor(Color.BLACK);
			g.fillRect((tempCP.xPos * BOX_SIZE) + (BOX_SIZE / 15) + getWhiteBiasWidth(tempCP), (tempCP.yPos * BOX_SIZE) + (int)(BOX_SIZE * 0.82f), getBlackBiasWidth(tempCP), (int)(BOX_SIZE * (4f / 30f)));
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
						g.drawImage(ImportManager.boxYellow, (x + Main.selectedUnit.xPos) * BOX_SIZE, (y + Main.selectedUnit.yPos) * BOX_SIZE, null);
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
						g.drawImage(ImportManager.boxBlue, tempUnit.currentPlan.x * BOX_SIZE, tempUnit.currentPlan.y * BOX_SIZE, null);
						g.setColor(BLUE);
						g.drawLine((tempUnit.xPos * BOX_SIZE) + (int)(BOX_SIZE * 0.5f), (tempUnit.yPos * BOX_SIZE) + (int)(BOX_SIZE * 0.5f), (tempUnit.currentPlan.x * BOX_SIZE) + (int)(BOX_SIZE * 0.5f), (tempUnit.currentPlan.y * BOX_SIZE) + (int)(BOX_SIZE * 0.5f));
					}
					else
					{
						g.drawImage(ImportManager.boxRed, tempUnit.currentPlan.x * BOX_SIZE, tempUnit.currentPlan.y * BOX_SIZE, null);
						g.setColor(RED);
						g.drawLine((tempUnit.xPos * BOX_SIZE) + (int)(BOX_SIZE * 0.5f), (tempUnit.yPos * BOX_SIZE) + (int)(BOX_SIZE * 0.5f), (tempUnit.currentPlan.x * BOX_SIZE) + (int)(BOX_SIZE * 0.5f), (tempUnit.currentPlan.y * BOX_SIZE) + (int)(BOX_SIZE * 0.5f));
					}
				}
			}
			
			//draw cursor
			if(Main.gameBoard.highlightPlan != Board.NONE && Main.selectedUnit != null)
			{
				int xPos = (int) (((MouseInfo.getPointerInfo().getLocation().x - this.getLocationOnScreen().x) * (CANVAS_SIZE / Main.size)) / BOX_SIZE);
				int yPos = (int) (((MouseInfo.getPointerInfo().getLocation().y - this.getLocationOnScreen().y) * (CANVAS_SIZE / Main.size)) / BOX_SIZE);
				if(xPos >= 0 && xPos <= 19 && yPos >= 0 && yPos <= 19)
				{
					Main.cursor.x = xPos;
					Main.cursor.y = yPos;
				}
				if(Main.gameBoard.highlightPlan == Board.MOVE)
				{
					g.drawImage(ImportManager.boxBlue, Main.cursor.x * BOX_SIZE, Main.cursor.y * BOX_SIZE, null);
				}
				else if(Main.gameBoard.highlightPlan == Board.ATTACK)
				{
					g.drawImage(ImportManager.boxRed, Main.cursor.x * BOX_SIZE, Main.cursor.y * BOX_SIZE, null);
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
		return (int) (percent * (27f / 30f) * BOX_SIZE);
	}
	
	private static int getWhiteBiasWidth(ControlPoint u)
	{
		float percent = ((float)(u.playerBias + 2f) / 4f);
		return (int) (percent * (27f / 30f) * BOX_SIZE);
	}
	
	private static int getBlackBiasWidth(ControlPoint u)
	{
		float percent = ((float)(u.playerBias + 2f) / 4f);
		return (int) ((1f - percent) * (27f / 30f) * BOX_SIZE);
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
