package frames;

import javax.swing.JMenuBar;

import global.Constants;
import menus.GFileMenu;
import menus.GMenuItem;

import java.io.Serial;

public class GMenuBar extends JMenuBar {

	@Serial
	private static final long serialVersionUID = 1L;

	private GFileMenu fileMenu;
	private GFileMenu editMenu;
	private GDrawingPanel drawingPanel;

	public GMenuBar() {
		// add component
		this.fileMenu = new GFileMenu(Constants.GMenuBar.EMenu.eFile.getText());
		this.editMenu = new GFileMenu(Constants.GMenuBar.EMenu.eEdit.getText());
		this.add(this.fileMenu);
		this.add(this.editMenu);
		
		fileMenu.add(new GMenuItem("tool"));
		fileMenu.add(new GMenuItem("exit"));
		fileMenu.add(new GMenuItem("setting"));

		editMenu.add(new GMenuItem("upload"));
		editMenu.add(new GMenuItem("download"));
	}

	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
		this.fileMenu.associate(drawingPanel);
	}

	public void initialize() {}
}