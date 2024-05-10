
public abstract class Node  {
	protected LexicalAnalyzer lexicalAnalyzer;
	
	public Node(LexicalAnalyzer lexicalAnalyzer) {
		// 최상위 node는 프로그램의 terminal, non-terminal을 가지고 있음
		this.lexicalAnalyzer = lexicalAnalyzer; 
	}
	public abstract String parse() throws Exception; 
	// parse는 node를 만들어서 return 함
	// 이 node를 나에게 붙일 것
	
	// 하나 더 읽은 것을
}
