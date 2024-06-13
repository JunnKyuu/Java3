package domain;

public class Insurance {

	private int insuranceID;
	private String insuranceName;
	private String category;
	private int minimumPeriod;
	private int minimumPremium;
	private String processOfCompoensation;
	private String processOfSubscription;
	private int insuranceRate;
	private String notice;
	private Guarantee guarantee;
	private SpecialProvision specialProvision;
	public SpecialProvision m_SpecialProvision;
	public Guarantee m_Guarantee;
	public Contract m_Contract;	

	public Insurance() {}

	public void finalize() throws Throwable {

	}

	public int CalculateInsuranceRate(){
		return 0;
	}
	
	// get & set
	public String getCategory() { return category;}
	public void setCategory(String category) {this.category = category;}
	public Guarantee getGuarantee() {return guarantee;}
	public void setGuarantee(Guarantee guarantee) {this.guarantee = guarantee;}
	public int getInsuranceID() {return insuranceID;}
	public void setInsuranceID(int insuranceID) {this.insuranceID = insuranceID;}
	public String getInsuranceName() {return insuranceName;}
	public void setInsuranceName(String insuranceName) {this.insuranceName = insuranceName;}
	public int getInsuranceRate() {return insuranceRate;}
	public void setInsuranceRate(int insuranceRate) {this.insuranceRate = insuranceRate;}
	public int getMinimumPeriod() {return minimumPeriod;}
	public void setMinimumPeriod(int minimumPeriod) {this.minimumPeriod = minimumPeriod;}
	public int getMinimumPremium() {return minimumPremium;}
	public void setMinimumPremium(int minimumPremium) {this.minimumPremium = minimumPremium;}
	public String getNotice() {return notice;}
	public void setNotice(String notice) {this.notice = notice;}
	public String getProcessOfCompoensation() {return processOfCompoensation;}
	public void setProcessOfCompoensation(String processOfCompoensation) {this.processOfCompoensation = processOfCompoensation;}
	public String getProcessOfSubscription() {return processOfSubscription;}
	public void setProcessOfSubscription(String processOfSubscription) {this.processOfSubscription = processOfSubscription;}
	public SpecialProvision getSpecialProvision() {return specialProvision;}
	public void setSpecialProvision(SpecialProvision specialProvision) {this.specialProvision = specialProvision;}

}