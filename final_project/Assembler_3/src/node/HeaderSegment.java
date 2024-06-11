package node;

import java.util.ArrayList;
import java.util.List;
import lexicalAnalyzer.EKeyword;
import lexicalAnalyzer.LexicalAnalyzer;

public class HeaderSegment extends Node {

    private int sizeStack;
    private int sizeHeap;

    public HeaderSegment(LexicalAnalyzer lexicalAnalyzer) {
        super(lexicalAnalyzer);
    }

    @Override
    public String parse(String token) throws Exception {
        String keyword = lexicalAnalyzer.getToken();

        while (!keyword.equals(EKeyword.eData.getText())) {
            String size = lexicalAnalyzer.getToken();
            if (keyword.equals(EKeyword.eStack.getText())) {
                this.sizeStack = Integer.parseInt(size);
            } else if (keyword.equals(EKeyword.eHeap.getText())) {
                this.sizeHeap = Integer.parseInt(size);
            } else {
                throw new Exception();
            }
            keyword = lexicalAnalyzer.getToken();
        }
        return keyword;
    }

    @Override
    public List<String> generate() throws Exception {
        List<String> machineCode = new ArrayList<>();
        machineCode.add("0x10 " + sizeStack);
        machineCode.add("0x11 " + sizeHeap);
        return machineCode;
    }
}
