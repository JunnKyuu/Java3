package main;

import java.util.List;
import java.util.Vector;

import codeGenerator.CodeGenerator;
import lexicalAnalyzer.LexicalAnalyzer;
import parser.Parser;
import symbolTable.Symbol;

public class Assembler {

    private LexicalAnalyzer lexicalAnalyzer;
    private Parser parser;
    private CodeGenerator codeGenerator;

    public Assembler() {
        this.lexicalAnalyzer = new LexicalAnalyzer("test"); // 매개변수로 파일 이름
        this.parser = new Parser();
        this.codeGenerator = new CodeGenerator();
        this.parser.associate(this.lexicalAnalyzer, this.codeGenerator);
    }

    public void run() {
        try {
            // Lexical Analysis 결과 출력
            List<String> tokens = this.lexicalAnalyzer.getAllTokens();
            System.out.println("--------------- lexical analysis ---------------");
            System.out.println("tokens = [");
            for (int i = 0; i < tokens.size(); i++) {
                System.out.print("\"" + tokens.get(i) + "\"");
                if (i < tokens.size() - 1) {
                    System.out.print(", ");
                }
                if ((i + 1) % 5 == 0) {
                    System.out.println();
                }
            }
            System.out.println("]\n");

            this.parser.parse();
            
            // Symbol Table 출력
            Vector<Symbol> symbolTable = this.parser.getProgram().getDataSegment().getSymbolTable();
            System.out.println("-------------- symbol table --------------");
            System.out.println("| name    | size | type     | address     |");
            System.out.println("------------------------------------------");
            for (Symbol symbol : symbolTable) {
                System.out.printf("| %-7s | %-4d | %-7s | ds + %-6d |%n", 
                                  symbol.getName(), 
                                  symbol.getSize(), 
                                  symbol.getType(), 
                                  symbol.getAddress());
            }
            System.out.println("------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        this.lexicalAnalyzer.initialize();
        this.parser.initialize();
        this.codeGenerator.initialize();
    }

    public void finish() {
        this.lexicalAnalyzer.finish();
        this.parser.finish();
        this.codeGenerator.finish();
    }
}
