
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private String shapeText;
	
	public GDrawingPanel() {
		this.setBackground(Color.GRAY);
		
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
	}
	
	public void setShapeText(String shapeText) {
		this.shapeText = shapeText;
		System.out.println(shapeText);
	}
	
	public void paint(Graphics graphics) {}
	
	private void draw(int x, int y) {
		// if(this.shapeText == "rectangle") 이러면 주소를 비교해서 무조건 에러 값 자체를 비교해야한
		// 밑에 코드는 너무 안좋음,,, if, else if, 숫자 넣는 하드코딩,, XXX
		if(this.shapeText.equals("rectangle")) {
			Graphics graphics = this.getGraphics();
			graphics.fillRect(x, y, 100, 100);
		} else if(this.shapeText.equals("oval")) {
			Graphics graphics = this.getGraphics();
			graphics.fillOval(x, y, 100, 100);
		} else if (this.shapeText.equals("line")) {
			Graphics graphics = this.getGraphics();
			graphics.drawLine(x, y, 100, 100);
		} else if (this.shapeText.equals("polygon")) {
			int[] xPoints = {x, x + 25, x + 50};
            int[] yPoints = {y + 50, y, y + 50};
			Graphics graphics = this.getGraphics();
			graphics.fillPolygon(xPoints, yPoints, 3);
		} 
	}
	
	private class MouseEventHandler implements MouseListener, MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}	
		@Override
		public void mousePressed(MouseEvent e) {
			draw(e.getX(), e.getY());
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			draw(e.getX(), e.getY());
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			draw(e.getX(), e.getY());
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {

		}
		@Override
		public void mouseEntered(MouseEvent e) {
			
		}
		@Override
		public void mouseExited(MouseEvent e) {

		}	
	}

	
}
