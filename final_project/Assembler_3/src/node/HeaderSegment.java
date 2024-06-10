package node;

import lexicalAnalyzer.EKeyword;
import lexicalAnalyzer.LexicalAnalyzer;

public class HeaderSegment extends Node {

    private int sizeStack;
    private int sizeHeap;

    public HeaderSegment(LexicalAnalyzer lexicalAnalyzer) { super(lexicalAnalyzer); }

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
                throw new Exception("Expected .stack or .heap, found " + keyword);
            }
            keyword = lexicalAnalyzer.getToken();
        }
        return keyword;
    }

    @Override
    public String generate() throws Exception { return null; }
}
