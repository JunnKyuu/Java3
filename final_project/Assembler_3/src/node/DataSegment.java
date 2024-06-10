package node;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import lexicalAnalyzer.EKeyword;
import lexicalAnalyzer.LexicalAnalyzer;
import symbolTable.Symbol;

public class DataSegment extends Node {

    private Vector<Symbol> symbolTable;
    private Set<String> symbolNames;
    private int currentAddress;

    public DataSegment(LexicalAnalyzer lexicalAnalyzer) {
        super(lexicalAnalyzer);
        this.symbolTable = new Vector<Symbol>();
        this.symbolNames = new HashSet<String>();
        this.currentAddress = 0;
    }

    @Override
    public String parse(String token) throws Exception {
        String name = lexicalAnalyzer.getToken();

        while (!name.equals(EKeyword.eCode.getText())) {
            int size = Integer.parseInt(lexicalAnalyzer.getToken());
            Symbol symbol = new Symbol(name, size, "variable", currentAddress);
            this.symbolTable.add(symbol);
            this.symbolNames.add(name);
            currentAddress += size;
            name = lexicalAnalyzer.getToken();
        }
        return name;
    }

    public Vector<Symbol> getSymbolTable() { return symbolTable; }
    public Set<String> getSymbolNames() { return symbolNames; }

    @Override
    public String generate() throws Exception { return null; }
}
