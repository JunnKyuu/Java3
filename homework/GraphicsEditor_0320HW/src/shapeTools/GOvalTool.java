package shapeTools;

import java.awt.Graphics;

public class GOvalTool extends GShapeTool{
	@Override
	public void draw(Graphics graphics, int x, int y, int w, int h) {
		graphics.drawOval(x, y, w, h);
	}
}
