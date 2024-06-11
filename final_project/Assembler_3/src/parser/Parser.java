package parser;

import node.Program;
import lexicalAnalyzer.LexicalAnalyzer;
import codeGenerator.CodeGenerator;

public class Parser {
    private LexicalAnalyzer lexicalAnalyzer;
    private CodeGenerator codeGenerator;
    private Program program;

    public void associate(LexicalAnalyzer lexicalAnalyzer, CodeGenerator codeGenerator) {
        this.lexicalAnalyzer = lexicalAnalyzer;
        this.codeGenerator = codeGenerator;
    }

    public void parse() throws Exception {
        program = new Program(lexicalAnalyzer);
        program.parse(null);
    }

    public Program getProgram() { return program; }
    public void initialize() {}
    public void finish() {}
}
