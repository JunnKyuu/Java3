import java.awt.Graphics;

public class GRectangleTool extends GShapeTool {
	private int x;
	private int y;
	
	public GRectangleTool(int x, int y, Graphics graphics) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(int x, int y, Graphics graphics) {
		graphics.fillRect(x, y, 100, 100);
	}
}
