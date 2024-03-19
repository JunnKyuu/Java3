
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
		if(this.shapeText.equals("rectangle")) {
			Graphics graphics = this.getGraphics();
			GRectangleTool rectangleTool = new GRectangleTool(x, y, graphics);
			rectangleTool.draw(x, y, graphics);
		} else if(this.shapeText.equals("oval")) {
			Graphics graphics = this.getGraphics();
			GOvalTool ovalTool = new GOvalTool(x, y, graphics);
			ovalTool.draw(x, y, graphics);
		} else if (this.shapeText.equals("line")) {
			Graphics graphics = this.getGraphics();
			GLineTool lineTool = new GLineTool(x, y, graphics);
			lineTool.draw(x, y, graphics);
		} else if (this.shapeText.equals("polygon")) {
            Graphics graphics = this.getGraphics();
			GPolygonTool polygonTool = new GPolygonTool(x, y, graphics);
			polygonTool.draw(x, y, graphics);
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
