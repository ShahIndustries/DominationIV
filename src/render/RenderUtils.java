package render;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class RenderUtils {
	
	//unused currently
	public static BufferedImage resize(BufferedImage b, int x, int y)
	{
		BufferedImage revised = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = revised.createGraphics();
		g.drawImage(b, 0, 0, x, y, null);
		g.dispose();
		return revised;
	}

}
