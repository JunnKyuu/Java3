import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

class GMainPanel extends JPanel {
        private static final long serialVersionUID = 1L;
		private boolean shouldDraw = false;

        public void addRectangle() {
            shouldDraw = true;
            repaint();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (shouldDraw) {
                g.setColor(Color.BLUE);
                g.fillRect(150, 120, 200, 200);
            }
        }
    }