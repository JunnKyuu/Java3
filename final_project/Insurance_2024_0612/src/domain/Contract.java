package domain;
public class Contract {
	private int concludeEID;
	private int createContractEID;
	private String createdDate;
	private String concludedDate;
	private int contractID;
	private String contractStatus;
	private int customerID;
	private String evaluation;
	private String expirationDate;
	private int insuranceID;
	private boolean isConclude;
	private boolean isPassUW;
	private int monthlyPremium;
	private int nonPaymentPeriod;
	private PaymentInfo paymentInfo;
	private boolean renewalStatus;
	private String resurrectionDate;
	private String resurrectionReason;
	private int underwritingEID;
	// association
	public Payment m_Payment;
	public PaymentInfo m_PaymentInfo;
	public Contract(){}
	public void finalize() throws Throwable {}
	public boolean endorsementReview(int 계약ID){return false;}
	public void permitContract(int ContractID){}

	// operater
	public void processUnderwriting(int contractID){
	}

	public void requestReUnderwriting(int contractID){
	}

	public void revive(Contract contract){
	}

	public void saveRenewalStatus(boolean renewalStatus){
	}
	public String toString() {
		  return "Contract{" +
	                "concludeEID=" + concludeEID +
	                ", createContractEID=" + createContractEID +
	                ", createdDate='" + createdDate + '\'' +
	                ", concludedDate='" + concludedDate + '\'' +
	                ", contractID=" + contractID +
	                ", contractStatus='" + contractStatus + '\'' +
	                ", customerID=" + customerID +
	                ", evaluation='" + evaluation + '\'' +
	                ", expirationDate='" + expirationDate + '\'' +
	                ", insuranceID=" + insuranceID +
	                ", isConclude=" + isConclude +
	                ", isPassUW=" + isPassUW +
	                ", monthlyPremium=" + monthlyPremium +
	                ", nonPaymentPeriod=" + nonPaymentPeriod +
	                ", paymentInfo=" + (paymentInfo != null ? paymentInfo.toString() : "null") +
	                ", renewalStatus=" + renewalStatus +
	                ", resurrectionDate='" + resurrectionDate + '\'' +
	                ", resurrectionReason='" + resurrectionReason + '\'' +
	                ", underwritingEID=" + underwritingEID +
	                '}';
	}
	//
	public int getConcludeEID() {return concludeEID;}
	public void setConcludeEID(int concludeEID) {this.concludeEID = concludeEID;}
	public String getCreatedDate() {return createdDate;}
	public void setCreatedDate(String createdDate) {this.createdDate = createdDate;}
	public String getConcludedDate() {return concludedDate;}
	public void setConcludedDate(String concludedDate) {this.concludedDate = concludedDate;}
	public int getContractID() {return contractID;}
	public void setContractID(int contractID) {this.contractID = contractID;}
	public int getCreateContractEID() {return createContractEID;}
	public void setCreateContractEID(int createContractEID) {this.createContractEID = createContractEID;}
	public String getContractStatus() {return contractStatus;}
	public void setContractStatus(String contractStatus) {this.contractStatus = contractStatus;}
	public int getCustomerID() {return customerID;}
	public void setCustomerID(int customerID) {this.customerID = customerID;}
	public String getEvaluation() {return evaluation;}
	public void setEvaluation(String evaluation) {this.evaluation = evaluation;}
	public String getExpirationDate() {return expirationDate;}
	public void setExpirationDate(String expirationDate) {this.expirationDate = expirationDate;}
	public int getInsuranceID() {return insuranceID;}
	public void setInsuranceID(int insuranceID) {this.insuranceID = insuranceID;}
	public boolean isConclude() {return isConclude;}
	public void setConclude(boolean isConclude) {this.isConclude = isConclude;}
	public boolean isPassUW() {return isPassUW;}
	public void setPassUW(boolean isPassUW) {this.isPassUW = isPassUW;}
	public int getMonthlyPremium() {return monthlyPremium;}
	public void setMonthlyPremium(int monthlyPremium) {this.monthlyPremium = monthlyPremium;}
	public int getNonPaymentPeriod() {return nonPaymentPeriod;}
	public void setNonPaymentPeriod(int nonPaymentPeriod) {this.nonPaymentPeriod = nonPaymentPeriod;}
	public PaymentInfo getPaymentInfo() {return paymentInfo;}
	public void setPaymentInfo(PaymentInfo paymentInfo) {this.paymentInfo = paymentInfo;}
	public boolean isRenewalStatus() {return renewalStatus;}
	public void setRenewalStatus(boolean renewalStatus) {this.renewalStatus = renewalStatus;}
	public String getResurrectionDate() {return resurrectionDate;}
	public void setResurrectionDate(String resurrectionDate) {this.resurrectionDate = resurrectionDate;}
	public String getResurrectionReason() {return resurrectionReason;}
	public void setResurrectionReason(String resurrectionReason) {this.resurrectionReason = resurrectionReason;}
	public int getUnderwritingEID() {return underwritingEID;}
	public void setUnderwritingEID(int unde$rwritingEID) {this.underwritingEID = underwritingEID;}
	public Payment getM_Payment() {return m_Payment;}
	public void setM_Payment(Payment m_Payment) {this.m_Payment = m_Payment;}
	public PaymentInfo getM_PaymentInfo() {return m_PaymentInfo;}
	public void setM_PaymentInfo(PaymentInfo m_PaymentInfo) {this.m_PaymentInfo = m_PaymentInfo;}
}