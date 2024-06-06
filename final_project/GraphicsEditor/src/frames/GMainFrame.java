package frames;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.*;

import global.Constants;

public class GMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private GMenuBar menuBar;
	private GShapeToolBar shapeToolBar;
	private GDrawingPanel drawingPanel;
	
	public GMainFrame() {
		// attribute setting
		LayoutManager layoutManager = new BorderLayout();
		this.setLayout(layoutManager);
		this.setSize(Constants.GMainFrame.WIDTH, Constants.GMainFrame.HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// add component
		this.menuBar = new GMenuBar();
		this.shapeToolBar = new GShapeToolBar();
		this.drawingPanel = new GDrawingPanel();
		this.setJMenuBar(this.menuBar);
		this.add(shapeToolBar, BorderLayout.NORTH);
		this.add(drawingPanel, BorderLayout.CENTER);
		
		// association
		this.menuBar.associate(this.drawingPanel);
		this.shapeToolBar.associate(this.drawingPanel);
	}

	public void initialize() {
		this.shapeToolBar.initialize();
		this.menuBar.initialize();
		this.drawingPanel.initialize();
	}
}
