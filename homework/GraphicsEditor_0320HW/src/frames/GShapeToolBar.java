package frames;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import global.Constants.EShapeButtons;

public class GShapeToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	
	public GShapeToolBar(GMainFrame.ShapeActionHandler shapeActionHandler) {
		this.setFloatable(false);

		ButtonGroup buttonGroup = new ButtonGroup();
		
		for(EShapeButtons eShapeButton : EShapeButtons.values()) {
			JRadioButton button = new JRadioButton(eShapeButton.getText());
			button.addActionListener(shapeActionHandler);
			button.setActionCommand(eShapeButton.toString());
			buttonGroup.add(button);
			this.add(button);
		}
	}
}
