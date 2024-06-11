package node;

import lexicalAnalyzer.LexicalAnalyzer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

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
        private static final Map<String, String> COMMAND_MAP = new HashMap<>();

        static {
            COMMAND_MAP.put("add", "0x05");
            COMMAND_MAP.put("move", "0x06");
            COMMAND_MAP.put("compare", "0x07");
            COMMAND_MAP.put("jump", "0x08");
            COMMAND_MAP.put("ge", "0x09");
            COMMAND_MAP.put("halt", "0x0A");
            COMMAND_MAP.put(".end", "0x0B");
        }

        ECommand(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static ECommand fromString(String text) {
            for (ECommand command : ECommand.values()) {
                if (command.text.equalsIgnoreCase(text)) {
                    return command;
                }
            }
            return null;
        }

        public static String getMachineCode(String command) {
            return COMMAND_MAP.get(command.toLowerCase());
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
            if (nextToken == null || ECommand.fromString(nextToken) != null) {
                return nextToken;
            }
            this.operand[i] = nextToken;
        }
        return this.lexicalAnalyzer.getToken();
    }

    public List<String> generate() {
        List<String> code = new ArrayList<>();
        if (eCommand != null) {
            StringBuilder instructionCode = new StringBuilder();
            instructionCode.append(ECommand.getMachineCode(eCommand.getText()));
            for (String op : operand) {
                if (op != null) {
                    instructionCode.append(" ").append(op);
                }
            }
            code.add(instructionCode.toString());
        }
        return code;
    }
}
