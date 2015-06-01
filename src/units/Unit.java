package units;

import java.awt.Image;
import java.awt.image.BufferedImage;

//package superclass
public class Unit {
	
	public int maxHP;
	public int hp;
	public BufferedImage icon;
	public int playerOwnerID;
	public int xPos;
	public int yPos;
	public Plan currentPlan = null;

}
