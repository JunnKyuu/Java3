
import javax.swing.JMenu;

public class GFileMenu extends JMenu {

	private static final long serialVersionUID = 1L;
	
	private GFileMenuItem newFile;
	private GFileMenuItem saveFile;
	private GFileMenuItem exitFile;
	
	public GFileMenu(String str) {
		super(str);
		
		this.newFile = new GFileMenuItem("new");
		this.saveFile = new GFileMenuItem("save");
		this.exitFile = new GFileMenuItem("exit");
		
		this.add(newFile);
		this.addSeparator();
		this.add(saveFile);
		this.addSeparator();
		this.add(exitFile);
	}
}
