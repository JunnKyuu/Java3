package frames;
import javax.swing.JMenuBar;

import menus.GFileMenu;

public class GMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public GFileMenu fileMenu;
	
	public GMenuBar() {
		this.fileMenu = new GFileMenu("File");
		this.add(this.fileMenu);
	}
}
