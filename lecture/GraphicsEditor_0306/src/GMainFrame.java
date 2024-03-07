import javax.swing.JFrame;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private GMenuBar menuBar;
	
	public GMainFrame() {
		this.setSize(400, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.menuBar = new GMenuBar();
		this.setJMenuBar(menuBar);
	}
}
