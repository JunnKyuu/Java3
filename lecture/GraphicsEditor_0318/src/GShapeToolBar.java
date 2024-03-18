import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class GShapeToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	public enum EShapeButtons {
		// 얘가 선택되면 drawing 도구를 하나 만들고 싶음
		// 선택되면 그리는 도구를 던져줌
		eRectangle, // 0
		eOval, // 1
		eLine, // 2
		ePolygon // 3
	}
	
	private JRadioButton rectangleButton;
	private JRadioButton ovalButton;
	private JRadioButton lineButton;
	private JRadioButton polygonButton;
	
	public GShapeToolBar(GMainFrame.ShapeActionHandler shapeActionHandler) {
		this.setFloatable(false);

		ButtonGroup buttonGroup = new ButtonGroup();
		
		this.rectangleButton = new JRadioButton("rectangle");
		this.ovalButton = new JRadioButton("oval");
		this.lineButton = new JRadioButton("line");
		this.polygonButton = new JRadioButton("polygon");
		
		this.rectangleButton.addActionListener(shapeActionHandler);
		this.ovalButton.addActionListener(shapeActionHandler);
		this.lineButton.addActionListener(shapeActionHandler);
		this.polygonButton.addActionListener(shapeActionHandler);
		
		buttonGroup.add(rectangleButton);
		buttonGroup.add(ovalButton);
		buttonGroup.add(lineButton);
		buttonGroup.add(polygonButton);
		
		this.add(rectangleButton);
		this.add(ovalButton);
		this.add(lineButton);
		this.add(polygonButton);
	}
}
