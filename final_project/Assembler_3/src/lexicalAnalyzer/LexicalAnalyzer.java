package lexicalAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LexicalAnalyzer {
    private String fileName;
    private Scanner scanner;
    private List<String> tokens;
    private int currentIndex;

    public LexicalAnalyzer(String fileName) {
        this.fileName = fileName;
        this.tokens = new ArrayList<>();
        this.currentIndex = 0;
    }

    public void initialize() {
        File file = new File("source/" + fileName + ".txt");
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                tokens.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public String getToken() {
        if (currentIndex < tokens.size()) { return tokens.get(currentIndex++); }
        return null;
    }
    public List<String> getAllTokens() { return new ArrayList<>(tokens); }
    
    public void finalize() { scanner.close(); }
    public void finish() { scanner.close(); }
}
