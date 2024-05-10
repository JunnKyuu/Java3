
public class Program extends Node {

	private String name;
	private HeaderSegment headerSegment;
	private DataSegment dataSegment;
	private CodeSegment codeSegment;	
	
	public Program(LexicalAnalyzer lexicalAnalyzer) {
		super(lexicalAnalyzer);
	}

	@Override
	public String parse() throws Exception {
		this.name = lexicalAnalyzer.getToken(); // 프로그램 이름
		String token = lexicalAnalyzer.getToken(); // .program, .header, .data, .code, .end 중 하나
	
		if(token.equals(EKeyword.eHeader.getText())) {
			headerSegment = new HeaderSegment(lexicalAnalyzer);
			token = headerSegment.parse();
		} else {
			throw new Exception();
		}
		
		token = lexicalAnalyzer.getToken();
		if(token.equals(EKeyword.eData.getText())) {
			dataSegment = new DataSegment(lexicalAnalyzer);
			token = dataSegment.parse();
		} else {
			throw new Exception();
		}
		
		token = lexicalAnalyzer.getToken();
		if(token.equals(EKeyword.eCode.getText())) {
			codeSegment = new CodeSegment(lexicalAnalyzer);
			token = codeSegment.parse();
		} else {
			throw new Exception();
		}
		
		return null; // 나중에 처리
	}
}
