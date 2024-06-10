package node;

import lexicalAnalyzer.EKeyword;
import lexicalAnalyzer.LexicalAnalyzer;

public class Program extends Node {

    private String name;
    private HeaderSegment headerSegment;
    private DataSegment dataSegment;
    private CodeSegment codeSegment;

    public Program(LexicalAnalyzer lexicalAnalyzer) { super(lexicalAnalyzer); }

    @Override
    public String parse(String token) throws Exception {
        this.name = lexicalAnalyzer.getToken(); // 프로그램 이름
        token = lexicalAnalyzer.getToken(); // 다음 토큰

        // .header 섹션으로 넘어감
        while (token != null && !token.equals(EKeyword.eHeader.getText())) {
            token = lexicalAnalyzer.getToken();
        }

        if (token != null && token.equals(EKeyword.eHeader.getText())) {
            headerSegment = new HeaderSegment(lexicalAnalyzer);
            token = headerSegment.parse(token);
        } else {
            throw new Exception("Expected .header, found " + token);
        }

        // .data 섹션으로 넘어감
        while (token != null && !token.equals(EKeyword.eData.getText())) {
            token = lexicalAnalyzer.getToken();
        }

        if (token != null && token.equals(EKeyword.eData.getText())) {
            dataSegment = new DataSegment(lexicalAnalyzer);
            token = dataSegment.parse(token);
        } else {
            throw new Exception("Expected .data, found " + token);
        }

        // .code 섹션으로 넘어감
        while (token != null && !token.equals(EKeyword.eCode.getText())) {
            token = lexicalAnalyzer.getToken();
        }

        if (token != null && token.equals(EKeyword.eCode.getText())) {
            codeSegment = new CodeSegment(lexicalAnalyzer, dataSegment.getSymbolNames());
            token = codeSegment.parse(token);
        } else {
            throw new Exception("Expected .code, found " + token);
        }

        return null;
    }

    public DataSegment getDataSegment() { return dataSegment; }

    @Override
    public String generate() throws Exception { return null; }
}
