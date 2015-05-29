package importing;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		try
		{
			box = ImageIO.read(ImportManager.class.getResource("Box.png"));
			createGrid();
			boxRed = ImageIO.read(ImportManager.class.getResource("BoxRed.png"));
			boxYellow = ImageIO.read(ImportManager.class.getResource("BoxYellow.png"));
			boxBlue = ImageIO.read(ImportManager.class.getResource("BoxBlue.png"));
			advancedUnit = ImageIO.read(ImportManager.class.getResource("AdvancedUnit.png"));
			advancedUnitWhite = ImageIO.read(ImportManager.class.getResource("AdvancedUnitWhite.png"));
			mainUnit = ImageIO.read(ImportManager.class.getResource("MainUnit.png"));
			mainUnitWhite = ImageIO.read(ImportManager.class.getResource("MainUnitWhite.png"));
			background = ImageIO.read(ImportManager.class.getResource("Background.png"));
			base = ImageIO.read(ImportManager.class.getResource("Base.png"));
			baseWhite = ImageIO.read(ImportManager.class.getResource("BaseWhite.png"));
			howitzer = ImageIO.read(ImportManager.class.getResource("Howitzer.png"));
			howitzerWhite = ImageIO.read(ImportManager.class.getResource("HowitzerWhite.png"));
			
			mainUnitIcon = ImageIO.read(ImportManager.class.getResource("MainUnit.png"));
			advancedUnitIcon = ImageIO.read(ImportManager.class.getResource("AdvancedUnit.png"));
			howitzerIcon = ImageIO.read(ImportManager.class.getResource("Howitzer.png"));
		}
		catch (IOException e)
		{
			System.out.println("Failed to load files");
			System.exit(-1);
		}
	}
	
	private static void createGrid()
	{
		boxGrid = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = boxGrid.createGraphics();
		//draw grid
		for(int i = 0; i < 20; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				g.drawImage(ImportManager.box, i * 30, j * 30, null);
			}
		}
		g.dispose();
	}

}
