package shapeTools;
import java.awt.Graphics;

public abstract class GShape {
	public int x1, y1, x2, y2, ox2, oy2;

	public GShape() {
		this.x1 = 0;
		this.x2 = 0;
		this.y1 = 0;
		this.y2 = 0;
		this.ox2 = 0;
		this.oy2 = 0;
	}
	
	public void setP1(int x1, int y1) {
		// 처음에는 두점이 한 곳에 모여있는 것으로 초기화
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x1;
		this.y2 = y1;
	}
	
	public void setP2(int x2, int y2) {
		this.ox2 = this.x2;
		this.oy2 = this.y2;
		this.x2 = x2;
		this.y2 = y2;
	}
	public abstract void draw(Graphics graphics);
}
