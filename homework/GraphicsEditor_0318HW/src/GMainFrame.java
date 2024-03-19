import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JRadioButton;

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
//		LayoutManager layoutManager = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		LayoutManager layoutManager = new BorderLayout();
		this.setLayout(layoutManager);
		
		this.menuBar = new GMenuBar();
		this.setJMenuBar(menuBar);
		
		ShapeActionHandler shapeActionHandler = new ShapeActionHandler();
		this.shapeToolBar = new GShapeToolBar(shapeActionHandler);
		this.add(shapeToolBar, BorderLayout.NORTH);
		
		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
	}
	
	public class ShapeActionHandler implements ActionListener {
		// 자식들이 써야하기 때문에 public
		@Override
		public void actionPerformed(ActionEvent e) {
			// 버튼이 눌리면 drawingPanel에 전달해야하니까 여기서 보이니까 만듦
			// 어떤 도형이 눌렸다고 알 수 있음
			drawingPanel.setShapeText(((JRadioButton)e.getSource()).getText());
		}
		
	}
}
