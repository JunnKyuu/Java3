package shapetools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.Serial;

public  class GLine extends GShape  {

	@Serial
	private static final long serialVersionUID = 1L;

	public GLine() { super(EDrawingStyle.e2PStyle, new Line2D.Float()); }
	public GLine clone() {
		return new GLine();
	}
	
	@Override
	public void drag(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;

		Line2D.Float shape = (Line2D.Float)this.shape;
		shape.setLine(x1, y1, x2, y2);
		graphics2D.draw(shape);	
	}
}
