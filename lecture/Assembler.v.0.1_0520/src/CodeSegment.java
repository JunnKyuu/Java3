import java.util.Vector;

public class CodeSegment extends Node {

	private Vector<Instruction> instructions;
	
	public CodeSegment(LexicalAnalyzer lexicalAnalyzer) {
		super(lexicalAnalyzer);
	}

	@Override
	public String parse(String token) throws Exception {
		String command = lexicalAnalyzer.getToken();
		Instruction.ECommand eCommand = Instruction.ECommand.valueOf(command);
		while(eCommand != null && eCommand != Instruction.ECommand.eEnd) {
			Instruction instruction = new Instruction(lexicalAnalyzer);
			command = instruction.parse(command);
			instructions.add(instruction);
		}
		return command;
	}
}
