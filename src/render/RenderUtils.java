package render;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class RenderUtils {
	
	public static boolean highQuality = false;
	
	public static BufferedImage resize(BufferedImage b, int x, int y)
	{
		BufferedImage revised = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = revised.createGraphics();
		if(highQuality)
		{
			g.setRenderingHints(new RenderingHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC));
		}
		g.drawImage(b, 0, 0, x, y, null);
		g.dispose();
		return revised;
	}

}
