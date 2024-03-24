package shapeTools;

import java.awt.Graphics;

public class GPolygonTool extends GShapeTool {
	@Override
	public void draw(Graphics graphics, int x, int y, int w, int h) {
		int[] xPoints = { x, x + 25, x + 50 };
        int[] yPoints = { y + 50, y, y + 50 };
		graphics.drawPolygon(xPoints, yPoints, 3);
	}
}
