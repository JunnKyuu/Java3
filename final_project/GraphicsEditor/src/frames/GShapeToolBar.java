package frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

import javax.swing.*;

import global.Constants.EShapeButtons;
import shapetools.GColor;

public class GShapeToolBar extends JToolBar {

	@Serial
	private static final long serialVersionUID = 1L;

	private GDrawingPanel drawingPanel;

	public GShapeToolBar() {
		// attribute setting
		this.setFloatable(false);

		// add actionHandler
		ButtonGroup buttonGroup = new ButtonGroup();
		ShapeActionHandler shapeActionHandler = new ShapeActionHandler();

		for (EShapeButtons eShapeButtons : EShapeButtons.values()) {
			JRadioButton button = new JRadioButton(eShapeButtons.getText());
			button.addActionListener(shapeActionHandler);
			button.setActionCommand(eShapeButtons.toString());

			this.add(button);
			buttonGroup.add(button);
		}

		// Add color selection button
		JRadioButton colorButton = new JRadioButton("color");
		colorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingPanel.actionPerformed(e);
			}
		});
		this.add(colorButton);
		buttonGroup.add(colorButton);
	}

	public void initialize() {
		JRadioButton defaultButton = (JRadioButton)(this.getComponent(EShapeButtons.eRactangle.ordinal()));
		defaultButton.doClick();
	}

	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	private void setShapeTool(EShapeButtons eShapeButton ) {
		drawingPanel.setShapeTool(eShapeButton.getShapeTool());
	}

	private class ShapeActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			EShapeButtons eShapeButton = EShapeButtons.valueOf(e.getActionCommand());
			setShapeTool(eShapeButton);
		}
	}
}
