package shapetools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.io.Serial;

public  class GOval extends GShape  {

	@Serial
	private static final long serialVersionUID = 1L;
	
	public GOval() {
		super(EDrawingStyle.e2PStyle, new Ellipse2D.Float());
	}
	public GOval clone() {
		return new GOval();
	}
	@Override
	public void drag(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;

		RectangularShape shape = (RectangularShape)this.shape;
		shape.setFrame(x1, y1, x2-x1, y2-y1);
		graphics2D.fill(shape);
	}
}
