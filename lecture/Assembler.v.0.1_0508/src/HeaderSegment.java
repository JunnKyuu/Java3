
public class HeaderSegment extends Node {

	private int sizeStack;
	private int sizeHeap;
	
	public HeaderSegment(LexicalAnalyzer lexicalAnalyzer) {
		super(lexicalAnalyzer);
	}

	@Override
	public String parse() throws Exception {
		// 다음 코드의 키워드가 나올 때까지 반복
		// 마찬가지로 datasegment, codesegment
		String keyword = lexicalAnalyzer.getToken();

		while(!keyword.equals(EKeyword.eData.getText())) {
			// eData가 아닌동안 반복
			String size = lexicalAnalyzer.getToken();
			if(keyword.equals(EKeyword.eStack.getText())) {
				this.sizeStack = Integer.parseInt(size);
			} else if(keyword.equals(EKeyword.eHeap.getText())) {
				this.sizeHeap = Integer.parseInt(size);
			} else {
				throw new Exception();
			}
			keyword = lexicalAnalyzer.getToken();
		}
		return null;
	}
}
