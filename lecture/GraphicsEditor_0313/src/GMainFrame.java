import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import javax.swing.JFrame;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private GMenuBar menuBar;
	private GShapeToolBar shapeToolBar;
	private GDrawingPanel drawingPanel;
	
	public GMainFrame() {
		this.setSize(400, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

//		LayoutManager layoutManager = new FlowLayout();
//		LayoutManager layoutManager = new CardLayout();
//		LayoutManager layoutManager = new BorderLayout();
		LayoutManager layoutManager = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);
		
		this.menuBar = new GMenuBar();
		this.setJMenuBar(menuBar);
		
		this.shapeToolBar = new GShapeToolBar();
		this.add(shapeToolBar);
		
		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel);
	}
}
