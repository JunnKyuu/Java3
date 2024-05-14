
public class CodeSegment extends Node {

	public CodeSegment(LexicalAnalyzer lexicalAnalyzer) {
		super(lexicalAnalyzer);
	}

	@Override
	public String parse(String token) throws Exception {
		String command = lexicalAnalyzer.getToken();
		while(!command.equals(EKeyword.eEnd.getText())) {
			Instruction instruction = new Instruction(lexicalAnalyzer);
			command = instruction.parse(command);
		}
		return command;
	}
}
