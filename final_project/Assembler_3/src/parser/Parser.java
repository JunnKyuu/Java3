package parser;

import codeGenerator.CodeGenerator;
import lexicalAnalyzer.LexicalAnalyzer;
import node.Program;

public class Parser {
    private LexicalAnalyzer lexicalAnalyzer;
    private Program program;

    public Parser() {}

    public void associate(LexicalAnalyzer lexicalAnalyzer, CodeGenerator codeGenerator) {
        this.lexicalAnalyzer = lexicalAnalyzer;
    }


    public void parse() throws Exception {
        String token = lexicalAnalyzer.getToken();
        if (token.equals(".program")) {
            this.program = new Program(lexicalAnalyzer);
            this.program.parse(token);
        } else {
            throw new Exception("Invalid start of program, found: " + token);
        }
    }

    public Program getProgram() { return program; }

    public void initialize() {}
    public void finish() {}
}
