
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public GDrawingPanel() {
		this.setBackground(Color.GRAY);
	}
	
	public void paint(Graphics graphics) {
		graphics.setColor(Color.GREEN);
		graphics.fillRect(150, 100, 100, 100);
	}
}
