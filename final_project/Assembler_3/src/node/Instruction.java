package node;

import lexicalAnalyzer.LexicalAnalyzer;
import java.util.Set;

public class Instruction extends Node {

    public enum ECommand {
        eAdd("add"),
        eMove("move"),
        eCmp("compare"),
        eJump("jump"),
        eGE("ge"),
        eHalt("halt"),
        eEnd(".end");

        private String text;

        ECommand(String text) { this.text = text; }
        public String getText() { return this.text; }

        public static ECommand fromString(String text) {
            for (ECommand command : ECommand.values()) {
                if (command.text.equalsIgnoreCase(text)) { return command; }
            }
            return null;
        }
    }

    private ECommand eCommand;
    private String[] operand;
    private Set<String> symbolTable;

    public Instruction(LexicalAnalyzer lexicalAnalyzer, Set<String> symbolTable) {
        super(lexicalAnalyzer);
        this.operand = new String[2];
        this.symbolTable = symbolTable;
    }

    public String parse(String token) throws Exception {
        this.eCommand = ECommand.fromString(token);

        for (int i = 0; i < 2; i++) {
            String nextToken = this.lexicalAnalyzer.getToken();
            if (nextToken == null || ECommand.fromString(nextToken) != null) { return nextToken; }
            if (!symbolTable.contains(nextToken) && !isNumeric(nextToken) && !isValidRegister(nextToken)) {
                // label, register 확인
            	continue;
            }
            this.operand[i] = nextToken;
        }
        return this.lexicalAnalyzer.getToken();
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidRegister(String str) {
        // :, r0, r1,, 처리
        return str.matches("r\\d+") || str.endsWith(":");
    }

    @Override
    public String generate() throws Exception { return null; }
}
