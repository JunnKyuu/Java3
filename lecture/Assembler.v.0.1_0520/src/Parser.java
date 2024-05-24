
public class Parser extends Node {
	private Program program;
	
	public Parser(LexicalAnalyzer lexicalAnalyzer) {
			// lexical analyzer를 받아와야 함
			super(lexicalAnalyzer);
	}
	
	public void initialize() {
		
	}

	@Override
	public String parse(String token) throws Exception {
		token = this.lexicalAnalyzer.getToken();
		if(token.equals(EKeyword.eProgram.getText())) {
			this.program = new Program(this.lexicalAnalyzer);
			this.program.parse(token); // 프로그램이면 node를 하나 만듦
		} 
		throw new Exception();
		
		// 얘가 각각의 세그먼트에서 만들어진 string 값을 트리로 만들어서 program을 리턴해줘야 함
		// 아니면 node를 상속받지 말던가
		
		// 이후에 과정은 codeGenerator가 받은 tree를 읽으면서 code를 쭉쭉 생성
	}
}