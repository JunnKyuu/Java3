package shapeTools;

import java.awt.Graphics;

public class GLineTool extends GShapeTool{
	@Override
	public void draw(Graphics graphics, int x, int y, int w, int h) {
		graphics.drawLine(x, y, w, h);
	}
}
