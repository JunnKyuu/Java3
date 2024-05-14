import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Assembler {
	
	private LexicalAnalyzer lexicalAnalyzer;
	private Parser parser;
	
	public Assembler() {
		// 2개를 association
		this.lexicalAnalyzer = new LexicalAnalyzer("source/text.txt"); // 매개변수로 파일 이름
		this.parser = new Parser(this.lexicalAnalyzer); // 매개변수로 lexicalAnalyzer
	}
	
	// methods
	public void initialize() {
		
	}
	
	public void run() {
		try {
			this.parser.parse(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // return 값으로 노드가 나옴. 이것을 나에게 딱딱 붙임
	}


	public void finish() {
		
	}
}
