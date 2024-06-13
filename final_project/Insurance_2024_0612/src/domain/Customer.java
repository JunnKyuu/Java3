package domain;
import java.util.ArrayList;

import IF.AccidentList;
import IF.CompensationList;
import IF.ContractList;
import IF.CounselList;
import IF.InsuranceList;
import IF.PaymentList;
import exception.DuplicateIDException;
import exception.NotFoundProfileException;

public class Customer {
	private String account;
	private String address;
	private int age;
	private String birthDate;
	private ArrayList<Contract> contractedList;
	private int customerID;
	private String customerPW;
	private String email;
	private String gender;
	private int height;
	private String job;
	private MedicalHistory medicalHistory;
	private String name;
	private String phone;
	private int weight;
	
	// associations
	public CounselList counselListImpl;
	public ContractList contractListImpl;
	public MedicalHistory m_MedicalHistory;
	public PaymentList paymentListImpl;
	public InsuranceList insuranceListImpl;
	public AccidentList accidentListImpl;
	public CompensationList compensationListImpl;
	
	public Customer(){
		contractedList = new ArrayList<>();
	}
	public void finalize() throws Throwable {

	}
	public boolean requestContract(Customer customer, Insurance insurance) {
		
		return false;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public ArrayList<Contract> getContractedList() {
		return contractedList;
	}
	public void setContractedList(ArrayList<Contract> contractedList) {
		this.contractedList = contractedList;
	}
	public String getCustomerPW() {
		return customerPW;
	}
	public void setCustomerPW(String customerPW) {
		this.customerPW = customerPW;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public MedicalHistory getMedicalHistory() {
		return medicalHistory;
	}
	public void setMedicalHistory(MedicalHistory medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public ContractList getContractList() {
		return contractListImpl;
	}
	public void setM_Contract(ContractList contractListImpl) {
		this.contractListImpl = contractListImpl;
	}
	public void setAccidentList(AccidentList accidentListImpl) {
		this.accidentListImpl = accidentListImpl;
	}
	public AccidentList getAccidentList() {
		return accidentListImpl;
	}
	public String createAccident(Accident accident) throws DuplicateIDException {
		if(accidentListImpl.add(accident)) {
			return "[success] 사고접수가 완료되었습니다.";
		}
		else throw new DuplicateIDException();
	}
	public String updateAccident(int accidentID, Accident accident) throws NotFoundProfileException {
		if(accidentListImpl.update(accidentID, accident)) {
			return "[success] 사고접수가 수정되었습니다.";
		}
		else throw new NotFoundProfileException("[Exception] 사고 정보를 업데이트하는 동안 오류가 발생했습니다. 다시 시도해주세요.");

	}
	public String deleteAccident(int accidentId) throws NotFoundProfileException {
		if(accidentListImpl.deleteById(accidentId)) {
			return "[success] 사고접수가 삭제되었습니다.";
		}
		else throw new NotFoundProfileException("[Exception] 해당 사고ID가 존재하지 않습니다.");
	}
	public CompensationList getCompensationList() {
		return compensationListImpl;
	}
	public void setCompensationList(CompensationList compensationListImpl) {
		this.compensationListImpl = compensationListImpl;
	}
	public String createCompensation(Compensation compensation) throws DuplicateIDException {
		if(compensationListImpl.add(compensation)) {
			return "[success] 보상 신청이 완료되었습니다.";
		}
		else throw new DuplicateIDException();
	}

	public String updateCompensation(Compensation compensation, int compensationID) throws NotFoundProfileException {
		if(compensationListImpl.update(compensation, compensationID)) {
			return "[success] 보상 신청이 수정되었습니다.";
		}
		else throw new NotFoundProfileException("[Exception] 보상 신청을 업데이트하는 동안 오류가 발생했습니다. 다시 시도해주세요.");
	}
	public boolean createBill(Compensation compensation, int compensationID) {
		return this.compensationListImpl.update(compensation, compensationID);
	}
	public void setCounselList(CounselList counselListImpl) {
		this.counselListImpl = counselListImpl;
	}
	public String requestCounsel(Counsel counsel) throws DuplicateIDException {		
		if(counselListImpl.add(counsel)) 
			return "[success] 상담 신청이 완료되었습니다.";
		else throw new DuplicateIDException();
	}
	public boolean deleteCounsel(int counselID) {
		return counselListImpl.delete(counselID);
	}
	public void setPaymentList(PaymentList paymentListImpl) {
		this.paymentListImpl = paymentListImpl;
	}
	public void setInsuranceList(InsuranceList insuranceListImpl) {
		this.insuranceListImpl = insuranceListImpl;
	}

	public String cancelContract(Contract contract) {
		if(contractListImpl.deleteById(contract.getContractID())) 
			return "[success] 보험 계약이 해지되었습니다.";
		else 
			return "[error] 계약 ID가 존재하지 않습니다.";
	}
	public String payPremium(Payment payment, int cardNumber, int cvcNumber, int password) {
		if(payment.processPayment(cardNumber, cvcNumber, password))
			return "[success] 보험료가 납부되었습니다.";
		else 
			return "[error] 결제에 실패하였습니다.";
	}
	public String requestContract(Contract contract) throws DuplicateIDException {
		Contract retrieveContract = contractListImpl.retrieveById(contract.getContractID());
		if(retrieveContract != null)
			throw new DuplicateIDException();
		if(contractListImpl.add(contract))
			return "[success] 보험 가입 신청이 완료되었습니다.";
		else
			return "[error] 가입 신청에 실패하였습니다.";
	}

}