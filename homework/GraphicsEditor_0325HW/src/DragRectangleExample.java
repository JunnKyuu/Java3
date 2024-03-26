import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DragRectangleExample extends JFrame {
    private int startX, startY, endX, endY;

    public DragRectangleExample() {
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new DrawPanel());
        this.setVisible(true);
    }

    class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {
        public DrawPanel() {
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK); // 사각형 색상 설정
            if (startX != 0 || startY != 0 || endX != 0 || endY != 0) {
                g.drawRect(Math.min(startX, endX), Math.min(startY, endY), Math.abs(endX - startX), Math.abs(endY - startY));
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            startX = e.getX();
            startY = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            endX = e.getX();
            endY = e.getY();
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            endX = e.getX();
            endY = e.getY();
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {}

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

    public static void main(String[] args) {
        new DragRectangleExample();
    }
}
