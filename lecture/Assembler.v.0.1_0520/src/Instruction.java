
public class Instruction extends Node {
	
	public enum ECommand {
		eAdd("add"),
		eMove("move"),
		eCmp("cmp"),
		eJump("jump"),
		eGE("ge"),
		eHalt("halt"),
		eEnd(".end"); 
		
		private String text;
		private ECommand(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}
	
	private ECommand eCommand;
	private String operand[];
	
	public Instruction(LexicalAnalyzer lexicalAnalyzer) {
		super(lexicalAnalyzer);
		this.operand = new String[2];
	}

	public String parse(String token) throws Exception {
		this.eCommand = ECommand.valueOf(token);
		String nextToken = this.lexicalAnalyzer.getToken();
		ECommand eNextCommand = ECommand.valueOf(nextToken);
		
		for(int i = 0; eNextCommand != null || i < 2; i ++) {
			this.operand[i] = nextToken;
			nextToken = this.lexicalAnalyzer.getToken();
			eNextCommand = ECommand.valueOf(nextToken);
		}
		
		return nextToken;
	}

}
