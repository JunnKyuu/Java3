package frames;
import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;

import global.Constants;

public class GMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	//component
	//부가적인 애들
	private GMenuBar menuBar;
	private GShapeToolBar shapeToolBar;
	
	private GDrawingPanel drawingPanel; //메인 자식 
	
	public GMainFrame() {
		//configuration 
		
		this.setSize(Constants.GMainFrame.WIDTH, Constants.GMainFrame.HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		LayoutManager layoutManager = new BorderLayout();
		this.setLayout(layoutManager);
		
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);
		

		
		this.shapeToolBar = new GShapeToolBar();
		this.add(shapeToolBar,BorderLayout.NORTH);
		
		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
	
		
		//associate 
		this.menuBar.associate(this.drawingPanel);
		this.shapeToolBar.associate(this.drawingPanel);
		}
	//method
	public void initialize() {
		// 메뉴바 쉐입툴 바,드로잉 패널을 자식으로 가지고 있음 
		this.shapeToolBar.initialize();
		this.menuBar.initialize();
		this.drawingPanel.initialize();
		
		
		
	}
	
		


	
}
