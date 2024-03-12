import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class GMovePanel extends JPanel implements MouseListener, MouseMotionListener {
    private static final long serialVersionUID = 1L;

    private int squareX;
    private int squareY;
    private int squareWidth = 100;
    private int squareHeight = 100;
    private Point initialMousePosition;
    private boolean dragging;

    public GMovePanel() {
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        squareX = 150;
        squareY = 100;
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);

        graphics.setColor(Color.GREEN);
        graphics.fillRect(squareX, squareY, squareWidth, squareHeight);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        initialMousePosition = e.getPoint();
        if (isInsideSquare(initialMousePosition)) {
            dragging = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging) {
            Point currentMousePosition = e.getPoint();
            int dx = currentMousePosition.x - initialMousePosition.x;
            int dy = currentMousePosition.y - initialMousePosition.y;
            squareX += dx;
            squareY += dy;
            initialMousePosition = currentMousePosition;
            repaint();
        }
    }

    private boolean isInsideSquare(Point point) {
        return point.x >= squareX && point.x <= squareX + squareWidth &&
                point.y >= squareY && point.y <= squareY + squareHeight;
    }

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
