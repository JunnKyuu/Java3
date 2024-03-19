import java.awt.Graphics;

public class GLineTool {
	private int x;
	private int y;
	
	public GLineTool(int x, int y, Graphics graphics) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(int x, int y, Graphics graphics) {
		graphics.drawLine(x, y, 100, 100);
	}
}
