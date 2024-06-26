package shapeTools;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GLine extends GShape {
	public GLine() {}	
	
	@Override
	public GShape clone() {
		return new GLine();
	}
	
	@Override
	public void drag(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		
		graphics2D.drawLine(x1, y1, ox2, oy2);
		graphics2D.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.drawLine(x1, y1, x2, y2);
	}
}
