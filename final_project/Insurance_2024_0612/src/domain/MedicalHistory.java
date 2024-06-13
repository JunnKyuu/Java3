package domain;
import java.util.ArrayList;

/**
 * @author Owner
 * @version 1.0
 * @created 16-5-2024 오후 6:54:56
 */
public class MedicalHistory {
	private String curePeriod;
	private ArrayList<String> diseases;
	private boolean isCured;
	public MedicalHistory(){
	}
	public void finalize() throws Throwable {
	}
	public String getCurePeriod() {
		return curePeriod;
	}
	public void setCurePeriod(String curePeriod) {
		this.curePeriod = curePeriod;
	}
	public ArrayList<String> getDiseases() {
		return diseases;
	}
	public void setDiseases(ArrayList<String> diseases) {
		this.diseases = diseases;
	}
	public boolean isCured() {
		return isCured;
	}
	public void setCured(boolean isCured) {
		this.isCured = isCured;
	}
	
}