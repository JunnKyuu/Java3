import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public GMainFrame() {
		GMainPanel mainPanel = new GMainPanel();
		GButton addRectangleButton = new GButton();
		
		this.setLocation(200, 200);
		this.setSize(400, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		addRectangleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mainPanel.addRectangle();
            }
        });
		
		this.add(addRectangleButton, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.CENTER);
	}
}
