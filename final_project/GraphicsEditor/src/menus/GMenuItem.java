package menus;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;

public class GMenuItem extends JMenuItem {
	public GMenuItem() {}
	public GMenuItem(Icon icon) { super(icon); }
	public GMenuItem(String text) { super(text); }
	public GMenuItem(Action a) { super(a); }
	public GMenuItem(String text, Icon icon) { super(text, icon); }
	public GMenuItem(String text, int mnemonic) { super(text, mnemonic); }
}
