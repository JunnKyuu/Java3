package shapeTools;
import java.awt.Graphics;

public class GRectangleTool extends GShapeTool {
	@Override
	public void draw(Graphics graphics, int x, int y, int w, int h) {
		graphics.drawRect(x, y, w, h);
	}
}
