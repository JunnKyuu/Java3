
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

    private String currentShape; // 현재 선택된 도형을 저장할 변수 추가

    public GDrawingPanel() {
        this.setBackground(Color.GRAY);
        
        MouseEventHandler mouseEventHandler = new MouseEventHandler();
        this.addMouseListener(mouseEventHandler);
        this.addMouseMotionListener(mouseEventHandler);
    }
    
    public void paint(Graphics graphics) {}

    // 현재 선택된 도형을 설정하는 메서드
    public void setCurrentShape(String shape) {
        this.currentShape = shape;
    }
    
    private void draw(int x, int y) {
        Graphics graphics = this.getGraphics();
        graphics.setColor(Color.GREEN);

        // 현재 선택된 도형에 따라 그리기 메서드 호출
        switch (currentShape) {
            case "rectangle":
                graphics.fillRect(x, y, 50, 50); // 사각형 그리기
                break;
            case "oval":
                graphics.fillOval(x, y, 50, 50); // 타원 그리기
                break;
            case "line":
                graphics.drawLine(x, y, x + 50, y + 50); // 직선 그리기
                break;
            case "polygon":
                int[] xPoints = {x, x + 25, x + 50}; // 다각형의 x 좌표
                int[] yPoints = {y + 50, y, y + 50}; // 다각형의 y 좌표
                graphics.fillPolygon(xPoints, yPoints, 3); // 삼각형 그리기
                break;
            default:
                break;
        }
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
