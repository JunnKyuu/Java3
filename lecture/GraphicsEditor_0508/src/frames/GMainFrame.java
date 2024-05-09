package frames;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JRadioButton;

import global.Constants;
import global.Constants.EShapeButtons;

public class GMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	//component
	private GMenuBar menuBar; // 여기 3개는 메인 자식, 여러 번 써야하니까 노출시켜야 함
	private GShapeToolBar shapeToolBar; 
	private GDrawingPanel drawingPanel;  
	
	public GMainFrame() {
		// 실행되는 프로그램에서 스크린 사이즈가 얼마나 되냐를 물어봐야 한다.
		// 사이즈를 가지고 와서 계산 후, 상단의 3분의2가 되게 띄운다. --> 과제 
		this.setSize(Constants.GMainFrame.WIDTH, Constants.GMainFrame.HEIGHT); // 화면 사이즈를 계산해서 중앙에 그릴 것
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 자식은 자식인데 주요 기능은 밑에 3개를 관리
		// 여기서 정의하고 다시 쓰일 일이 없음
		LayoutManager layoutManager = new BorderLayout(); 
		this.setLayout(layoutManager);
		
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);
		
		this.shapeToolBar = new GShapeToolBar();
		this.add(shapeToolBar, BorderLayout.NORTH);
		
		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
			
		//associate 
		this.menuBar.associate(this.drawingPanel);
		this.shapeToolBar.associate(this.drawingPanel);
	}
	
	//method
	public void initialize() {
		this.shapeToolBar.initialize();
		this.menuBar.initialize();
		this.drawingPanel.initialize();	
	}
}
