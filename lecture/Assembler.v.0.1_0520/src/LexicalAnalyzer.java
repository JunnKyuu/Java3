import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LexicalAnalyzer {
	private String fileName;
	Scanner scanner;
	
	public LexicalAnalyzer(String fileName) {
		this.fileName = fileName;
	}
	
	public void initialize() {
		File file = new File("source/" + fileName + ".txt");
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void finalized() {
		scanner.close();

	}
	
	public String getToken() {
		if(this.scanner.hasNext()) {
			String token = this.scanner.next();
			return token;
		}
		return null;
	}
}