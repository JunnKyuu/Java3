package shapetools;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.Serial;
import java.io.Serializable;

public abstract class GShape implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	public abstract void drag(Graphics graphics);

	public enum EDrawingStyle {
		e2PStyle,
		eNPStyle
	}

	private EDrawingStyle eDrawingStyle;

	protected int x1, y1, x2, y2, ox2, oy2;
	public Shape shape;
	public Color color;

	public enum EAnchors {
		eRR(new Cursor(Cursor.HAND_CURSOR)),
		eNN(new Cursor(Cursor.N_RESIZE_CURSOR)),
		eSS(new Cursor(Cursor.S_RESIZE_CURSOR)),
		eEE(new Cursor(Cursor.E_RESIZE_CURSOR)),
		eWW(new Cursor(Cursor.W_RESIZE_CURSOR)),
		eNE(new Cursor(Cursor.NE_RESIZE_CURSOR)),
		eSE(new Cursor(Cursor.SE_RESIZE_CURSOR)),
		eNW(new Cursor(Cursor.NW_RESIZE_CURSOR)),
		eSW(new Cursor(Cursor.SW_RESIZE_CURSOR)),
		eMM(new Cursor(Cursor.HAND_CURSOR));

		private Cursor cursor;
		private EAnchors(Cursor cursor) {
			this.cursor = cursor;
		}
		public Cursor getCursor() {
			return this.cursor;
		}
	}

	private EAnchors eSelectedAnchor;
	protected Ellipse2D.Float[] anchors;

	private double sx, sy;
	private double dx, dy;

	public GShape(EDrawingStyle eDrawingStyle, Shape shape) {
		this.eDrawingStyle = eDrawingStyle;
		this.shape = shape;
		this.anchors = null;
		this.eSelectedAnchor = null;
		this.color = Color.BLACK;

		this.x1 = 0;
		this.y1 = 0;
		this.x2 = 0;
		this.y2 = 0;
		this.ox2 = 0;
		this.oy2 = 0;
	}

	// get&set
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return this.color;
	}
	public EDrawingStyle getEDrawingStyle() {
		return this.eDrawingStyle;
	}
	public EAnchors getSelectedAnchor() {
		return this.eSelectedAnchor;
	}

	public Cursor getCursor() {
		return this.eSelectedAnchor.getCursor();
	}
	public void setSelected(Graphics graphics, int x, int y) {
		this.drawAnchors(graphics);
	}

	public void drawAnchors(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;

		Rectangle rectangle = this.shape.getBounds();
		int x = rectangle.x;
		int y = rectangle.y;
		int w = rectangle.width;
		int h = rectangle.height;
		int ANCHOR_WIDTH = 10;
		int ANCHOR_HEIGHT = 10;

		this.anchors = new Ellipse2D.Float[EAnchors.values().length-1];
		this.anchors[EAnchors.eRR.ordinal()] = new Ellipse2D.Float(x+w/2, y-30, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eNN.ordinal()] = new Ellipse2D.Float(x+w/2, y, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eSS.ordinal()] = new Ellipse2D.Float(x+w/2, y+h, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eEE.ordinal()] = new Ellipse2D.Float(x+w, y+h/2, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eWW.ordinal()] = new Ellipse2D.Float(x, y+h/2, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eNW.ordinal()] = new Ellipse2D.Float(x, y, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eSW.ordinal()] = new Ellipse2D.Float(x, y+h, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eNE.ordinal()] = new Ellipse2D.Float(x+w, y, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eSE.ordinal()] = new Ellipse2D.Float(x+w, y+h, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchors.eNW.ordinal()] = new Ellipse2D.Float(x, y, ANCHOR_WIDTH, ANCHOR_HEIGHT);

		for(Ellipse2D anchor : this.anchors) {
			graphics2D.draw(anchor);
		}
	}

	public void clearAnchors() {
		this.anchors = null;
	}

	public abstract GShape clone();

	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setColor(this.color);
		graphics2D.fill(shape);
	}

	public void setOrigin(int x1, int y1) {
		this.x1 = x1;
		this.y1 = y1;
	}

	public void movePoint(int x2, int y2) {
		this.ox2 = this.x2;
		this.oy2 = this.y2;
		this.x2 = x2;
		this.y2 = y2;
	}

	public void addPoint(int x2, int y2) {
		this.x2 = x2;
		this.y2 = y2;
	}

	public boolean onShape(int x, int y) {
		this.eSelectedAnchor = null;
		if(this.anchors == null) {
			return this.shape.contains(x, y);
		} else {
			for(int i = 0; i < EAnchors.values().length - 1; i++) {
				if(anchors[i].contains(x, y)) {
					this.eSelectedAnchor = EAnchors.values()[i];
					return true;
				}
			}
		}
		boolean isOnshape = this.shape.contains(x, y);
		if(isOnshape) {
			this.eSelectedAnchor = EAnchors.eMM;
		}
		return isOnshape;
	}

	// move
	public void startMove(Graphics graphics, int x, int y) {
		this.drawAnchors(graphics);
		this.x2 = x;
		this.y2 = y;
		this.ox2 = x1;
		this.oy2 = y1;
	}

	public void keepMove(Graphics graphics, int x, int y) {
		// 이전 위치 저장
		int oldX2 = this.x2;
		int oldY2 = this.y2;

		// 현재 위치 업데이트
		this.ox2 = this.x2;
		this.oy2 = this.y2;
		this.x2 = x;
		this.y2 = y;

		// 그래픽 객체 생성
		Graphics2D graphics2D = (Graphics2D) graphics;

		// 이전 위치 지우기
		graphics2D.setColor(graphics2D.getBackground());
		graphics2D.fill(this.shape);

		// 새로운 위치에 도형 그리기
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.setToTranslation(x - ox2, y - oy2);
		this.shape = affineTransform.createTransformedShape(this.shape);

		graphics2D.setColor(this.color);
		graphics2D.fill(this.shape);
	}

	public void stopMove(Graphics graphics, int x, int y) {
		this.drawAnchors(graphics);
	}

	// resize
	public void startResize(Graphics graphics, int x, int y) {
		this.drawAnchors(graphics);
		this.x2 = x;
		this.y2 = y;
		this.ox2 = x1;
		this.oy2 = y1;
	}

	private Point2D getResizeFactor() {
		sx = 1.0;
		sy = 1.0;
		dx = 0;
		dy = 0;

		double cx = 0;
		double cy = 0;
		double w = this.shape.getBounds().getWidth();
		double h = this.shape.getBounds().getHeight();

		switch(this.eSelectedAnchor) {
			case eEE:
				sx = (w + x2 - ox2) / w;
				cx = this.anchors[EAnchors.eWW.ordinal()].getCenterX();
				dx = cx - cx * sx;
				break;
			case eWW:
				if(x2 - ox2 < 0) {
					sx = (w - x2 + ox2) / w;
				} else {
					sx = ( w + x2 - ox2) / w;
				}
				cx = this.anchors[EAnchors.eEE.ordinal()].getCenterX();
				dx = cx - cx * sx;
				break;
			case eSS:
				sy = (h + y2 - oy2) / h;
				cy = this.anchors[EAnchors.eNN.ordinal()].getCenterY();
				dy = cy - cy * sy;
				break;
			case eNN:
				sy = (h - y2 + oy2) / h;
				cy = this.anchors[EAnchors.eSS.ordinal()].getCenterY();
				dy = cy - cy * sy;
				break;
			case eSE:
				sx = (w + x2 - ox2) / w;
				sy = (h + y2 - oy2) / h;
				cx = this.anchors[EAnchors.eNW.ordinal()].getCenterX();
				cy = this.anchors[EAnchors.eNW.ordinal()].getCenterY();
				dx = cx - cx * sx;
				dy = cy - cy * sy;
				break;
			case eSW:
				sx = (w - x2 + ox2) / w;
				sy = (h + y2 - oy2) / h;
				cx = this.anchors[EAnchors.eNE.ordinal()].getCenterX();
				cy = this.anchors[EAnchors.eNE.ordinal()].getCenterY();
				dx = cx - cx * sx;
				dy = cy - cy * sy;
				break;
			case eNE:
				sx = (w + x2 - ox2) / w;
				sy = (h - y2 + oy2) / h;
				cx = this.anchors[EAnchors.eSW.ordinal()].getCenterX();
				cy = this.anchors[EAnchors.eSW.ordinal()].getCenterY();
				dx = cx - cx * sx;
				dy = cy - cy * sy;
				break;
			case eNW:
				sx = (w - x2 + ox2) / w;
				sy = (h - y2 + oy2) / h;
				cx = this.anchors[EAnchors.eSE.ordinal()].getCenterX();
				cy = this.anchors[EAnchors.eSE.ordinal()].getCenterY();
				dx = cx - cx * sx;
				dy = cy - cy * sy;
				break;
			default:
				return null;
		}

		return new Point2D.Double(sx, sy);
	}

	public void keepResize(Graphics graphics, int x, int y) {
		this.ox2 = this.x2;
		this.oy2 = this.y2;
		this.x2 = x;
		this.y2 = y;

		Graphics2D graphics2D = (Graphics2D) graphics;

		graphics2D.setColor(graphics2D.getBackground());
		graphics2D.fill(this.shape);

		Point2D resizeFactor = getResizeFactor();
		sx = resizeFactor.getX();
		sy = resizeFactor.getY();
		dx = dx;
		dy = dy;

		AffineTransform affineTransform = new AffineTransform();
		affineTransform.setToScale(sx, sy);
		Shape resizedShape = affineTransform.createTransformedShape(this.shape);

		AffineTransform translationTransform = new AffineTransform();
		translationTransform.setToTranslation(dx, dy);
		this.shape = translationTransform.createTransformedShape(resizedShape);

		graphics2D.setColor(this.color);
		graphics2D.fill(this.shape);
	}

	public void stopResize(Graphics graphics, int x, int y) {
		this.drawAnchors(graphics);
	}
}
