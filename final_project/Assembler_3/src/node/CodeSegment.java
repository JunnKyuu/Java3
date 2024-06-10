package node;

import java.util.Vector;
import java.util.Set;
import lexicalAnalyzer.LexicalAnalyzer;

public class CodeSegment extends Node {

    private Vector<Instruction> instructions;
    private Set<String> symbolTable;

    public CodeSegment(LexicalAnalyzer lexicalAnalyzer, Set<String> symbolTable) {
        super(lexicalAnalyzer);
        this.instructions = new Vector<Instruction>();
        this.symbolTable = symbolTable;
    }

    @Override
    public String parse(String token) throws Exception {
        while (true) {
            String command = lexicalAnalyzer.getToken();
            if (command == null || command.equals(Instruction.ECommand.eEnd.getText())) {
                break;
            }
            Instruction instruction = new Instruction(lexicalAnalyzer, symbolTable);
            token = instruction.parse(command);
            instructions.add(instruction);
        }
        return token;
    }

    @Override
    public String generate() throws Exception { return null; }
}
