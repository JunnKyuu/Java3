package shapeTools;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GRectangle extends GShape {
	public GRectangle() {}	
	
	@Override
	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		
		// erase old shape
		// 기존의 배경을 그리면 지우는 것
		graphics.drawRect(x1, y1, ox2-x1, oy2-y1);
		
		// draw new shape
		// 그림이 있으면 지우고, 그림이 없으면 그림 
		graphics.drawRect(x1, y1, x2-x1, y2-y1);
	}
}
