
import javax.swing.JMenu;

public class GEditMenu extends JMenu {

	private static final long serialVersionUID = 1L;
	
	private GEditMenuItem copyFile;
	private GEditMenuItem pasteFile;
	private GEditMenuItem cutFile;
	
	public GEditMenu(String str) {
		super(str);
		
		this.copyFile = new GEditMenuItem("copy");
		this.pasteFile = new GEditMenuItem("paste");
		this.cutFile = new GEditMenuItem("cut");
		
		this.add(copyFile);
		this.addSeparator();
		this.add(pasteFile);
		this.addSeparator();
		this.add(cutFile);
	}

}
