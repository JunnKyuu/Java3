import javax.swing.JMenuBar;

public class GMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public GFileMenu fileMenu;
	public GEditMenu drawingMenu;
	
	public GMenuBar() {
		this.fileMenu = new GFileMenu("File");
		this.add(this.fileMenu);
		
		this.drawingMenu = new GEditMenu("Edit");
		this.add(this.drawingMenu);
	}
}
