package shapetools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.io.Serial;

public class GPolygon extends GShape {

	@Serial
	private static final long serialVersionUID = 1L;

	// constructor
	public GPolygon() {
		super(EDrawingStyle.eNPStyle, new Polygon());
	}

	@Override
	public GPolygon clone() { return new GPolygon(); }

	@Override
	public void drag(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		Polygon polygon = (Polygon) this.shape;
		int npoints = polygon.npoints;

		// 연속된 점 사이에 선 그리기
		for (int i = 1; i < npoints; i++) {
			graphics.drawLine(polygon.xpoints[i - 1], polygon.ypoints[i - 1], polygon.xpoints[i], polygon.ypoints[i]);
		}

		// 마지막 마우스 위치로부터 선 그리기
		graphics.drawLine(polygon.xpoints[npoints - 1], polygon.ypoints[npoints - 1], x2, y2);
	}

	@Override
	public void setOrigin(int x, int y) {
		Polygon polygon = (Polygon) this.shape;
		polygon.addPoint(x, y);
	}
	@Override
	public void addPoint(int x, int y) {
		Polygon polygon = (Polygon) this.shape;
		polygon.addPoint(x, y);
	}
}
