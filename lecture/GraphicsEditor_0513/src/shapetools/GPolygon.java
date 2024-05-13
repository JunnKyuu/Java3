package shapetools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

import global.Constants;

public class GPolygon extends GShape {

	private int xPoints[];
	private int yPoints[];
	private int nPoints;
	
	
	public GPolygon() {
		super(EDrawingStyle.eNPStyle);
		this.xPoints = new int[Constants.NUM_POINTS];
		this.yPoints = new int[Constants.NUM_POINTS];
		this.nPoints =0;
	}
	public GPolygon clone() {
		return new GPolygon();
	}
	
	
	@Override
	public void drag(Graphics graphics) {
//		Graphics2D graphics2D = (Graphics2D) graphics;
//		graphics2D.setXORMode(graphics2D.getBackground());
//		//erase old shape 
//		graphics.drawline(xPoints, yPoints, nPoints);
//		//draw new shape 
//		graphics.drawPolyline(xPoints, yPoints, nPoints);
		//ox2 oy2에 값을 넣어줘야함.
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		//erase old shape 
		graphics2D.drawLine(x1, y1, ox2, oy2);
		
		//draw new shape 
		graphics2D.drawLine(x1, y1, x2, y2);

	}
	@Override
	public void draw(Graphics graphics) {
		//draw new shape 
		graphics.drawPolygon(xPoints, yPoints, nPoints);
		
	}
	@Override
	public void setOrigin(int x,int y) {
		this.xPoints[nPoints] = x;
		this.yPoints[nPoints] = y;
		
		this.nPoints++;
		this.xPoints[nPoints] = x;
		this.yPoints[nPoints] = y;
		
	
	}
	@Override
	public void movePoint(int x,int y) {
		this.xPoints[nPoints] = x;
		this.yPoints[nPoints] = y;
	}
	@Override
	public void addPoint(int x,int y) {
		this.nPoints++;
		this.xPoints[nPoints] = x;
		this.yPoints[nPoints] = y;
}
}