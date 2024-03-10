import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class GMainPanel extends JPanel {
	// 마우스로 드래그하면 사각형 그려짐 

	private static final long serialVersionUID = 1L;
	
	private int startX, startY, endX, endY;

    
	public GMainPanel() {
		this.setBackground(Color.LIGHT_GRAY);
		
		addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                repaint();
                startX = e.getX();
                startY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                repaint();
                endX = e.getX();
                endY = e.getY();
            }
        });
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.drawRect(startX, startY, endX - startX, endY - startY);
    }

}
