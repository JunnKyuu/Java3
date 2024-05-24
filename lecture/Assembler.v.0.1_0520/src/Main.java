
public class Main {
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.initialize();
		main.run();
		main.finish();

	}
	
	private Assembler assembler;
	
	public Main() {
		this.assembler = new Assembler();
	}

	private void initialize() {
		this.assembler.initialize();
	}

	private void run() throws Exception {
		this.assembler.run();
	}

	private void finish() {
		this.assembler.finish();
	}
}
