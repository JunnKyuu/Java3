import java.awt.Graphics;

public class GOvalTool {
	private int x;
	private int y;
	
	public GOvalTool(int x, int y, Graphics graphics) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(int x, int y, Graphics graphics) {
		graphics.fillOval(x, y, 100, 100);
	}
}
