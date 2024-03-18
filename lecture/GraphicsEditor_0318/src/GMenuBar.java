import javax.swing.JMenuBar;

public class GMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public GFileMenu fileMenu;
	
	public GMenuBar() {
		this.fileMenu = new GFileMenu("File");
		this.add(this.fileMenu);
	}
}
