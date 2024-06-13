package domain;

public class Payment {

	private int contractID;
	private int customerID;
	private int paymentID;
	private int amount;
	private String dueDateOfPayment;
	private String dateOfPayment;
	private String paymentMethod;
	private boolean statusOfPayment;
	
	public Payment(){}
	public void finalize() throws Throwable {}
	
	public boolean processPayment(int cardNumber, int cvcNumber, int password){
		return false;
	}
	
	// get & set
	public int getAmount() {return amount;}
	public void setAmount(int amount) {this.amount = amount;}
	public int getContractID() {return contractID;}
	public void setContractID(int contractID) {this.contractID = contractID;}
	public int getCustomerID() {return customerID;}
	public void setCustomerID(int customerID) {this.customerID = customerID;}
	public String getDateOfPayment() {return dateOfPayment;}
	public void setDateOfPayment(String dateOfPayment) {this.dateOfPayment = dateOfPayment;}
	public String getDueDateOfPayment() {return dueDateOfPayment;}
	public void setDueDateOfPayment(String dueDateOfPayment) {this.dueDateOfPayment = dueDateOfPayment;}
	public int getPaymentID() {return paymentID;}
	public void setPaymentID(int paymentID) {this.paymentID = paymentID;}
	public String getPaymentMethod() {return paymentMethod;}
	public void setPaymentMethod(String paymentMethod) {this.paymentMethod = paymentMethod;}
	public boolean isPaymentProcessed() {return statusOfPayment;}
	public void setStatusOfPayment(boolean statusOfPayment) {this.statusOfPayment = statusOfPayment;}

}