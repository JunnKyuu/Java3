package shapeTools;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GOval extends GShape {
	public GOval() {}	
	
	@Override
	public GShape clone() {
		return new GOval();
	}
	
	@Override
	public void drag(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		
		graphics2D.drawOval(x1, y1, ox2-x1, oy2-y1);
		graphics2D.drawOval(x1, y1, x2-x1, y2-y1);
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.drawOval(x1, y1, x2-x1, y2-y1);
	}
}
