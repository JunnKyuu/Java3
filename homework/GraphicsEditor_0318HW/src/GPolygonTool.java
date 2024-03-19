import java.awt.Graphics;

public class GPolygonTool {
	private int x;
	private int y;
	
	public GPolygonTool(int x, int y, Graphics graphics) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(int x, int y, Graphics graphics) {
		int[] xPoints = {x, x + 25, x + 50};
        int[] yPoints = {y + 50, y, y + 50};
		graphics.fillPolygon(xPoints, yPoints, 3);
	}
}
