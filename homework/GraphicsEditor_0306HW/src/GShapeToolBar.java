import javax.swing.ButtonGroup;
import javax.swing.JToolBar;

public class GShapeToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	
	private GShapeButton rectangleButton;
	private GShapeButton ovalButton;
	private GShapeButton lineButton;
	private GShapeButton polygonButton;
	private ButtonGroup group;
	
	public GShapeToolBar() {
		this.setFloatable(false);
		
		this.rectangleButton = new GShapeButton("rectangle");
		this.ovalButton = new GShapeButton("oval");
		this.lineButton = new GShapeButton("line");
		this.polygonButton = new GShapeButton("polygon");
		this.group = new ButtonGroup();
		
		this.group.add(rectangleButton);
		this.group.add(ovalButton);
		this.group.add(lineButton);
		this.group.add(polygonButton);
		
		this.add(rectangleButton);
		this.add(ovalButton);
		this.add(lineButton);
		this.add(polygonButton);
	}
}
