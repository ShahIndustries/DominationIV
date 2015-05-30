package importing;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import render.BoardCanvas;
import render.RenderUtils;

public class ImportManager {
	
	public static BufferedImage box;
	public static BufferedImage boxGrid;
	public static BufferedImage boxRed;
	public static BufferedImage boxYellow;
	public static BufferedImage boxBlue;
	public static BufferedImage advancedUnit;
	public static BufferedImage advancedUnitWhite;
	public static BufferedImage mainUnit;
	public static BufferedImage mainUnitWhite;
	public static BufferedImage background;
	public static BufferedImage base;
	public static BufferedImage baseWhite;
	public static BufferedImage howitzer;
	public static BufferedImage howitzerWhite;
	
	public static Image mainUnitIcon;
	public static Image advancedUnitIcon;
	public static Image howitzerIcon;
	
	public static void load()
	{
		//set resizing of original images to highest quality
		RenderUtils.highQuality = true;
		
		box = fetchImage("Box.png");
		createGrid();
		boxRed = fetchImage("BoxRed.png");
		boxYellow = fetchImage("BoxYellow.png");
		boxBlue = fetchImage("BoxBlue.png");
		advancedUnit = fetchImage("AdvancedUnit.png");
		advancedUnitWhite = fetchImage("AdvancedUnitWhite.png");
		mainUnit = fetchImage("MainUnit.png");
		mainUnitWhite = fetchImage("MainUnitWhite.png");
		base = fetchImage("Base.png");
		baseWhite = fetchImage("BaseWhite.png");
		howitzer = fetchImage("Howitzer.png");
		howitzerWhite = fetchImage("HowitzerWhite.png");
		
		//unusual sized images
		try {
			background = RenderUtils.resize(ImageIO.read(ImportManager.class.getResource("Background.png")), BoardCanvas.CANVAS_SIZE, BoardCanvas.CANVAS_SIZE);
			
			mainUnitIcon = RenderUtils.resize(ImageIO.read(ImportManager.class.getResource("MainUnit.png")), 30, 30);
			advancedUnitIcon = RenderUtils.resize(ImageIO.read(ImportManager.class.getResource("AdvancedUnit.png")), 30, 30);
			howitzerIcon = RenderUtils.resize(ImageIO.read(ImportManager.class.getResource("Howitzer.png")), 30, 30);
		} catch (IOException e) {
			System.out.println("Failed to load files");
			System.exit(-1);
		}
		
		//set quality back to normal
		RenderUtils.highQuality = false;
	}
	
	private static void createGrid()
	{
		boxGrid = new BufferedImage(BoardCanvas.CANVAS_SIZE, BoardCanvas.CANVAS_SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = boxGrid.createGraphics();
		//draw grid
		for(int i = 0; i < 20; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				g.drawImage(ImportManager.box, i * BoardCanvas.BOX_SIZE, j * BoardCanvas.BOX_SIZE, null);
			}
		}
		g.dispose();
	}
	
	private static BufferedImage fetchImage(String s)
	{
		try {
			return RenderUtils.resize(ImageIO.read(ImportManager.class.getResource(s)), BoardCanvas.BOX_SIZE, BoardCanvas.BOX_SIZE);
		} catch (IOException e) {
			System.out.println("Failed to load files");
			System.exit(-1);
		}
		return null;
	}

}
