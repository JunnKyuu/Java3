package domain;

public class Guarantee {

	private String description;
	private String guaranteeName;
	private int maxCoverage;

	public Guarantee(){}

	public void finalize() throws Throwable {}

	// get & set
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public String getGuaranteeName() {return guaranteeName;}
	public void setGuaranteeName(String guaranteeName) {this.guaranteeName = guaranteeName;}
	public int getMaxCoverage() {return maxCoverage;}
	public void setMaxCoverage(int maxCoverage) {this.maxCoverage = maxCoverage;}

}