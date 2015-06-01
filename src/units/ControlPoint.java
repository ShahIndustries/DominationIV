//technically does not belong in this package
package units;

import importing.ImportManager;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class ControlPoint {
	
	public static final int MONEY_PER_TURN = 300;
	public int xPos;
	public int yPos;
	public BufferedImage icon;
	//variable to indicate how much control certain team has at this control point
	//-2 is full black control, +2 is full white control
	public int playerBias;
	
	
	
	public ControlPoint(int x, int y)
	{
		this.xPos = x;
		this.yPos = y;
		this.playerBias = 0;
		this.icon = ImportManager.controlPoint;
	}
}
