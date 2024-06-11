package node;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.HashSet;
import java.util.Set;
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

    public Vector<Symbol> getSymbolTable() {
        return symbolTable;
    }

    public Set<String> getSymbolNames() {
        return symbolNames;
    }

    @Override
    public List<String> generate() throws Exception {
        List<String> machineCode = new ArrayList<>();
        for (Symbol symbol : symbolTable) {
            machineCode.add("0x12 " + symbol.getName() + " " + symbol.getSize());
        }
        return machineCode;
    }
}
