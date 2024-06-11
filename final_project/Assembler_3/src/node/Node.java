package node;
import java.util.List;

import lexicalAnalyzer.LexicalAnalyzer;

public abstract class Node  {
	protected LexicalAnalyzer lexicalAnalyzer;	
	public Node(LexicalAnalyzer lexicalAnalyzer) { this.lexicalAnalyzer = lexicalAnalyzer; }
	public abstract String parse(String token) throws Exception;
	public abstract List<String> generate() throws Exception; 
}
