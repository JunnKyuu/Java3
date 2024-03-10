import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private GMenuBar menuBar;
	private GMainPanel drawingPanel;
	private GShapeToolBar shapeToolBar;
	
	public GMainFrame() {
		this.setSize(400, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.menuBar = new GMenuBar();
		this.setJMenuBar(menuBar);
		
		this.shapeToolBar = new GShapeToolBar();
		this.add(shapeToolBar,BorderLayout.NORTH);
		
		this.drawingPanel = new GMainPanel();
		this.add(drawingPanel);
	}
}
