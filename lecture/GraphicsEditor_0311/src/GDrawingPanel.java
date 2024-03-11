
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public GDrawingPanel() {
		this.setBackground(Color.GRAY);
	}
	
	public void paint(Graphics graphics) {
		// os가 자동으로 graphics를 불러줌
		// paint를 호출하면 에러가 뜸
		// main에서 paint를 부를 수 없으니까 setVisible을 부름
		
		graphics.drawRect(20, 30, 20, 30);
		
	}
}
