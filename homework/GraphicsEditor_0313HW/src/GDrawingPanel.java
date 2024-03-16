
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public GDrawingPanel() {
		this.setBackground(Color.GRAY);
		
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
	}
	
	public void paint(Graphics graphics) {}
	
	private void draw(int x, int y) {
		Graphics graphics = this.getGraphics();
		graphics.fillRect(x, y, 100, 100);
	}
	
	private class MouseEventHandler implements MouseListener, MouseMotionListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		    StackTraceElement methodCaller = stackTrace[1];
		    String methodName = methodCaller.getMethodName();
		    System.out.println(methodName);
		}
	
		@Override
		public void mousePressed(MouseEvent e) {
			draw(e.getX(), e.getY());
			StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		    StackTraceElement methodCaller = stackTrace[1];
		    String methodName = methodCaller.getMethodName();
		    System.out.println(methodName);
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			draw(e.getX(), e.getY());
			StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		    StackTraceElement methodCaller = stackTrace[1];
		    String methodName = methodCaller.getMethodName();
		    System.out.println(methodName);
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			draw(e.getX(), e.getY());
			StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		    StackTraceElement methodCaller = stackTrace[1];
		    String methodName = methodCaller.getMethodName();
		    System.out.println(methodName);
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		    StackTraceElement methodCaller = stackTrace[1];
		    String methodName = methodCaller.getMethodName();
		    System.out.println(methodName);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		    StackTraceElement methodCaller = stackTrace[1];
		    String methodName = methodCaller.getMethodName();
		    System.out.println(methodName);
		}
		@Override
		public void mouseExited(MouseEvent e) {
			StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		    StackTraceElement methodCaller = stackTrace[1];
		    String methodName = methodCaller.getMethodName();
		    System.out.println(methodName);
		}

	}
}
