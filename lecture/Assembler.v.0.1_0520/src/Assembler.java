
public class Assembler {
	
	private LexicalAnalyzer lexicalAnalyzer;
	private Parser parser;
	
	public Assembler() {
		// 2개를 association
		this.lexicalAnalyzer = new LexicalAnalyzer("test"); // 매개변수로 파일 이름
		this.parser = new Parser(this.lexicalAnalyzer); // 매개변수로 lexicalAnalyzer
	}
	
	// methods
	public void run() {
		try {
			this.parser.parse(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		this.lexicalAnalyzer.initialize();
		this.parser.initialize();
	}
	public void finish() {}
}
