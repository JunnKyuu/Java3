package domain;

public class Counsel {

	private int counselID;
	private int customerID;
	private int employeeID;
	private String insuranceCategory;
	private String content;
	private String counselDetail;
	private String dateOfCounsel;
	private String timeOfCounsel;
	private boolean statusOfCounsel;
	private String note;
	
	public void finalize() throws Throwable {}

	public boolean confirmCounsel(){
		return false;
	}

	public boolean updateCounsel(String counselDetail, String note) {
		this.counselDetail = counselDetail;
		this.note = note;
		return true;
	}
	public String isConfirmedCounsel(){
		if(statusOfCounsel) return "확정됨";
		else return "대기중";
		}
	
	// get & set
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	public String getCounselDetail() {return counselDetail;}
	public void setCounselDetail(String counselDetail) {this.counselDetail = counselDetail;}
	public int getCounselID() {return counselID;}
	public void setCounselID(int counselID) {this.counselID = counselID;}
	public int getCustomerID() {return customerID;}
	public void setCustomerID(int customerID) {this.customerID = customerID;}
	public String getDateOfCounsel() {return dateOfCounsel;}
	public void setDateOfCounsel(String dateOfCounsel) {this.dateOfCounsel = dateOfCounsel;}
	public int getEmployeeID() {return employeeID;}
	public void setEmployeeID(int employeeID) {this.employeeID = employeeID;}
	public String getInsuranceCategory() {return insuranceCategory;}
	public void setInsuranceCategory(String insuranceCategory) {this.insuranceCategory = insuranceCategory;}
	public String getNote() {return note;}
	public void setNote(String note) {this.note = note;}
	public boolean getStatusOfCounsel() {return statusOfCounsel;}
	public void setStatusOfCounsel(boolean statusOfCounsel) {this.statusOfCounsel = statusOfCounsel;}
	public String getTimeOfCounsel() {return timeOfCounsel;}
	public void setTimeOfCounsel(String timeOfCounsel) {this.timeOfCounsel = timeOfCounsel;}

}