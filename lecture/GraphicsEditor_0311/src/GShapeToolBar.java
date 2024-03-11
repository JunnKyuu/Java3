import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class GShapeToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	private JRadioButton rectangleButton;
	private JRadioButton ovalButton;
	private JRadioButton lineButton;
	private JRadioButton polygonButton;
	
	public GShapeToolBar() {
		ButtonGroup buttonGroup = new ButtonGroup();
		
		this.rectangleButton = new JRadioButton("rectangle");
		this.ovalButton = new JRadioButton("oval");
		this.lineButton = new JRadioButton("line");
		this.polygonButton = new JRadioButton("polygon");
		
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
