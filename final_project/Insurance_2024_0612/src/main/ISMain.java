  package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import IF.AccidentList;
import IF.CompensationList;
import IF.ContractList;
import IF.CounselList;
import IF.CustomerList;
import IF.EmployeeList;
import IF.InsuranceList;
import IF.PaymentList;
import IF.RuleList;
import constant.Constant;
import daoList.AccidentListImpl;
import daoList.CompensationListImpl;
import daoList.ContractListImpl;
import daoList.CounselListImpl;
import daoList.CustomerListImpl;
import daoList.EmployeeListImpl;
import daoList.InsuranceListImpl;
import daoList.PaymentListImpl;
import daoList.RuleListImpl;
import domain.Accident;
import domain.AutomaticPayment;
import domain.BankPayment;
import domain.Bill;
import domain.CancerHealth;
import domain.Car;
import domain.CardPayment;
import domain.Compensation;
import domain.Contract;
import domain.Counsel;
import domain.Customer;
import domain.Employee;
import domain.Guarantee;
import domain.HouseFire;
import domain.Insurance;
import domain.InternationalTravel;
import domain.Loss;
import domain.MedicalHistory;
import domain.Payment;
import domain.PaymentInfo;
import domain.Rule;
import domain.SpecialProvision;
import exception.AuthenticationException;
import exception.AuthorizationException;
import exception.DuplicateIDException;
import exception.NotFoundProfileException;
import token.TokenManager;
public class ISMain {
	// main attributes
	private static String token;
	private static AccidentList accidentListImpl;
	private static CompensationList compensationListImpl;
	private static ContractList contractListImpl;
	private static CounselList counselListImpl;
	private static CustomerList customerListImpl;
	private static EmployeeList employeeListImpl;
	private static InsuranceList insuranceListImpl;
	private static PaymentList paymentListImpl;
	private static RuleList ruleListImpl;
	// 멤버변수 수정사항
	private static Employee employee;
	private static Customer customer;
	private static BufferedReader clientInputReader;
	public ISMain() {
		clientInputReader = new BufferedReader(new InputStreamReader(System.in));
		accidentListImpl = new AccidentListImpl();
		compensationListImpl = new CompensationListImpl();
		contractListImpl = new ContractListImpl();
		counselListImpl = new CounselListImpl();
		customerListImpl = new CustomerListImpl();
		employeeListImpl = new EmployeeListImpl();
		insuranceListImpl = new InsuranceListImpl();
		paymentListImpl = new PaymentListImpl();
		ruleListImpl = new RuleListImpl();
	}
	public static void main(String[] args) throws IOException, ParseException, AuthenticationException, AuthorizationException {
		ISMain main = new ISMain();
		// ListImpl Settings
		main.startInsuranceService();
	}
	private static void printMainMenu() {
		System.out.println("***************** 보험사 시스템 웹페이지 *****************");
		System.out.println("1. 고객서비스");
		System.out.println("2. 직원서비스");
		System.out.println("X. 종료");
	}
	private void startInsuranceService() throws IOException, ParseException, AuthenticationException, AuthorizationException{
		while(true) {
			try {
				printMainMenu();
				String clientChoice = clientInputReader.readLine().trim();
				if (clientChoice.equals("1")) startCustomerService();
				else if (clientChoice.equals("2")) startEmployeeService();
				else if (clientChoice.equals("X")) {
					System.out.println("|*** 보험사 시스템을 이용해주셔서 감사합니다! ***|");
					System.exit(0);
				}
				else System.out.println("--잘못된 입력입니다.--");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
	}
	private static void printCustomerMainMenu() {
		System.out.println("***************** 고객 서비스 메뉴 *****************");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 로그아웃");
		System.out.println("4. 회원탈퇴");
		System.out.println("5. 사고접수 카테고리");
		System.out.println("6. 보상 카테고리");
		System.out.println("7. 상담 신청 카테고리");
		System.out.println("8. 보험 상품 종류 카테고리");
		System.out.println("9. 보유 계약 조회 카테고리");
		System.out.println("10. 보험료 납부 카테고리");
		System.out.println("R. 홈페이지");
	}
	private void startCustomerService() throws IOException, AuthenticationException, AuthorizationException{
		while(true) {
			printCustomerMainMenu();
			String clientChoice = clientInputReader.readLine().trim();
			try {
				if (clientChoice.equals("1")) login(Constant.Customer);
				else if (clientChoice.equals("2")) signUp(Constant.Customer);
				else if (clientChoice.equals("3")) logout();
				else if (clientChoice.equals("4")) deleteMembership(Constant.Customer);
				else if (clientChoice.equals("5")) accidentCategory();
				else if (clientChoice.equals("6")) compensationCategory();
				else if (clientChoice.equals("7")) counselCategory();
				else if (clientChoice.equals("8")) insuranceTypeCategory();
				else if (clientChoice.equals("9")) contractRetrieveCategory();
				else if (clientChoice.equals("10")) paymentCategory();
				else if (clientChoice.equals("R")) {
					System.out.println("|*** 본 홈페이지로 돌아갑니다. ***|");
					return;
				}
				else System.out.println("invalid choice");
			} catch (DuplicateIDException e) {
				System.out.println(e.getMessage());
			} catch (NotFoundProfileException e) {
				System.out.println(e.getMessage());
			} 
			catch (AuthenticationException e) {
				System.out.println(e.getMessage());
			} 
//			catch (AuthorizationException e) {
//				System.out.println(e.getMessage());
//			} 
		}
	}
	//// 사고접수 카테고리 - 사고접수 신청, 사고접수 조회, 사고접수 수정, 사고접수 삭제
	private void accidentCategory() throws IOException, AuthenticationException, DuplicateIDException, AuthorizationException, NotFoundProfileException {
		if (customer==null&&employee==null) {
			throw new AuthenticationException();
		}
		while(true) {
			System.out.println("***************** 사고접수 카테고리 메뉴 *****************");
			System.out.println("1. 사고접수 신청");
			System.out.println("2. 사고접수 조회");
			System.out.println("3. 사고접수 수정");
			System.out.println("4. 사고접수 삭제");
			System.out.println("R. 돌아가기");
			String clientChoice = clientInputReader.readLine().trim();
			if (clientChoice.equals("1")) createAccident();
			else if (clientChoice.equals("2")) showAccidentList();
			else if (clientChoice.equals("3")) updateAccident();
			else if (clientChoice.equals("4")) deleteAccident();
			else if (clientChoice.equals("R")) {
				System.out.println("|*** 이전으로 돌아갑니다. ***|");
				return;
			}
			else System.out.println("유효하지 않은 메뉴 번호입니다.");
		}
	}
	// 사고접수 신청
	private void createAccident() throws IOException, AuthenticationException, DuplicateIDException {
		if (customer==null) {
			throw new AuthenticationException("[Exception] 현재 이 기능은 고객서비스입니다.");
		}
		System.out.println("-- 사고 정보 입력란 --");

		// basic attribute settings
		System.out.print("사고ID: "); String accidentID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("사고날짜: "); String accidentDate = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("사고위치: "); String accidentLocation = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("사고유형: "); String accidentType = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("차량정보: "); String carInfomation = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("차량번호: "); String carNumber = dataValidation(clientInputReader.readLine().trim(), "Integer");
		
		// AccidentImpl Add
		Accident accident = new Accident();
		System.out.println("-- 사고를 접수하시겠습니까?[Y/N] --");
		String save = dataValidation(clientInputReader.readLine().trim(), "String");
		if(save.equals("Y")) {
			accident.setAccidentID(Integer.parseInt(accidentID));
			accident.setCustomerID(customer.getCustomerID());
			accident.setAccidentDate((accidentDate));
			accident.setAccidentLocation(accidentLocation);
			accident.setAccidentType(accidentType);
			accident.setCarInformation(carInfomation);
			accident.setCarNumber(Integer.parseInt(carNumber));
			
			// Association setting
			customer.setAccidentList(accidentListImpl);
			System.out.println(customer.createAccident(accident));
		} else {
			System.out.println("[info] 사고접수를 취소했습니다. 본 페이지를 다시 출력합니다.");
		}
	}
	// 사고접수 조회
	private void showAccidentList(){
		int index = 1;
		ArrayList<Accident> accidentList = accidentListImpl.retrieveByCustomerId(customer.getCustomerID());
		if(accidentList.size() == 0) {
			System.out.println("No Accident");
			return;
		}
		System.out.println("-- 사고 리스트 --");
		for(Accident accident : accidentList) {
			System.out.println(index + ". 사고ID: " + accident.getAccidentID() + " 고객ID: " + accident.getCustomerID()+ " 사고날짜: " + accident.getAccidentDate() + " 사고위치: " + accident.getAccidentLocation() + " 사고유형: " + accident.getAccidentType() + " 차량정보: " + accident.getCarInformation() + " 차량번호: " + accident.getCarNumber());
			index++;
		}
	}
	// 사고접수 수정
	private void updateAccident() throws IOException, AuthenticationException, NotFoundProfileException {
	    System.out.println("-- 사고 정보 수정란 --");
	    
	    // get accidentID to update
	    System.out.print("수정할 사고ID: ");
	    String accidentID = dataValidation(clientInputReader.readLine().trim(), "Integer");
	    Accident accident = accidentListImpl.retrieveById(Integer.parseInt(accidentID));
	    if (accident == null) {
	    	throw new NotFoundProfileException("[Exception] 해당 사고ID의 사고가 존재하지 않습니다.");
	    }

		// new attribute settings
	    System.out.print("새로운 사고날짜: ");
	    String accidentDate = dataValidation(clientInputReader.readLine().trim(), "String");
	    System.out.print("새로운 사고위치: ");
	    String accidentLocation = dataValidation(clientInputReader.readLine().trim(), "String");
	    System.out.print("새로운 사고유형: ");
	    String accidentType = dataValidation(clientInputReader.readLine().trim(), "String");
	    System.out.print("새로운 차량정보: ");
	    String carInformation = dataValidation(clientInputReader.readLine().trim(), "String");
	    System.out.print("새로운 차량번호: ");
	    String carNumber = dataValidation(clientInputReader.readLine().trim(), "Integer");

	    System.out.println("-- 사고를 수정하시겠습니까?[Y/N] --");
	    String save = dataValidation(clientInputReader.readLine().trim(), "String");
	    if (save.equalsIgnoreCase("Y")) {
	        accident.setAccidentDate(accidentDate);
	        accident.setAccidentLocation(accidentLocation);
	        accident.setAccidentType(accidentType);
	        accident.setCarInformation(carInformation);
	        accident.setCarNumber(Integer.parseInt(carNumber));

			customer.setAccidentList(accidentListImpl);
			System.out.println(customer.updateAccident(Integer.parseInt(accidentID), accident));
	    } else {
	        System.out.println("[info] 사고 수정을 취소했습니다. 본 페이지를 다시 출력합니다.");
	    }
	}
	// 사고접수 삭제
	private void deleteAccident() throws IOException, AuthenticationException, AuthorizationException, NotFoundProfileException {
		if (employee==null) {
			throw new AuthorizationException();			
		}
		System.out.println("--사고 정보 입력란--");
		System.out.print("사고ID: "); String accidentID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		
		// Association setting
		customer.setAccidentList(accidentListImpl);
		System.out.println(customer.deleteAccident(Integer.parseInt(accidentID)));
	}
	// -------------------------------------------------------------
	
	private void createPayment() throws IOException {
		if (!TokenManager.isValidToken(token)) {
			System.out.println("[error] please login first.");
			return;
		}
		System.out.println("--Create Payment Infomation--");
		System.out.print("Payment ID: "); String paymentID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("Contract ID: "); String contractID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		Contract contract = contractListImpl.retrieveById(Integer.parseInt(contractID));
		if (contract == null) {
			System.out.println("[error] A contract for that ID does not exist.");
			return;
		}
		int customerID = contract.getCustomerID();
		int amount = contract.getMonthlyPremium();
		System.out.println("Due Date of Payment: "); String dueDateOfPayment = dataValidation(clientInputReader.readLine().trim(), "String");
		boolean statusOfPayment = false;

		// ListImpl Add
		Payment payment = new Payment();
		payment.setPaymentID(Integer.parseInt(paymentID));
		payment.setContractID(Integer.parseInt(contractID));
		payment.setCustomerID(customerID);
		payment.setAmount(amount);
		payment.setDueDateOfPayment(dueDateOfPayment);
		payment.setStatusOfPayment(statusOfPayment);
		
		Employee employee = employeeListImpl.retrieveById(Integer.parseInt(TokenManager.getID(token)));
		boolean response = employee.createPayment(payment);
		if(response) System.out.println("[success] Successfully Create Payment!");
		else System.out.println("[error] Payment ID duplicate. Please try again");
	}
	private void deletePayment() throws IOException {
		if (!TokenManager.isValidToken(token)) {
			System.out.println("[error] please login first.");
			return;
		}
		System.out.println("--Delete Payment Infomation--");
		System.out.print("payment ID: "); String paymentID = dataValidation(clientInputReader.readLine().trim(), "String");
		
		Employee employee = employeeListImpl.retrieveById(Integer.parseInt(TokenManager.getID(token)));
		boolean response = employee.deletePayment(Integer.parseInt(paymentID));
		if(response) System.out.println("[success] Successfully deleted this Payment!");
		else System.out.println("[error] The payment id does not exist.");
	}
	private static void printEmployeeMainMenu() {
		System.out.println("***************** 직원 서비스 메뉴 *****************");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 로그아웃");
		System.out.println("4. 회원탈퇴");
		System.out.println("5. 인수심사 카테고리");
		System.out.println("6. 계약체결 카테고리");
		System.out.println("7. 고객정보 DB 서비스 카테고리");
		System.out.println("8. 상담신청 일정 관리 카테고리");
		System.out.println("9. 상담 내역 관리 카테고리");
		System.out.println("10. 사고접수 카테고리");
		System.out.println("11. 보상 카테고리");
		System.out.println("12. 상품 개발 카테고리");
		System.out.println("13. 제관리 지침 카테고리");
		System.out.println("14. 수금을 관리한다");
		System.out.println("15. 미납을 관리한다");
		System.out.println("16. 부활관리를 한다");
		System.out.println("17. 만기계약을 관리한다");
		System.out.println("18. 재계약을 관리한다");
		System.out.println("19. 배서을 관리한다");
		System.out.println("R. 홈페이지");
	}
	private void startEmployeeService() throws IOException, ParseException{
		while(true) {
			printEmployeeMainMenu();
			String clientChoice = clientInputReader.readLine().trim();
			try {
				if (clientChoice.equals("1")) login(Constant.Employee);
				else if (clientChoice.equals("2")) signUp(Constant.Employee);
				else if (clientChoice.equals("3")) logout();
				else if (clientChoice.equals("4")) deleteMembership(Constant.Employee);
				else if (clientChoice.equals("5")) underWritingCategory();
				else if (clientChoice.equals("6")) concludeContractCategory();
				else if (clientChoice.equals("7")) customerDBServiceCategory();
				else if (clientChoice.equals("8")) counselScheduleCategory();
				else if (clientChoice.equals("9")) counselDetailCategory();
				else if (clientChoice.equals("10")) accidentCategory();
				else if (clientChoice.equals("11")) compensationCategory();
				else if (clientChoice.equals("12")) createInsuranceCategory();
				else if (clientChoice.equals("13")) ruleCategory();
				else if (clientChoice.equals("14")) setPaymentInfo();
				else if (clientChoice.equals("15")) manageLatePayment();
				else if (clientChoice.equals("16")) manageRevive();
				else if (clientChoice.equals("17")) manageExpirationContract();
				else if (clientChoice.equals("18")) manageRenewalContract();
				else if (clientChoice.equals("19")) manageUpdate();
				else if (clientChoice.equals("R")) {
					System.out.println("|*** 본 홈페이지로 돌아갑니다. ***|");
					return;
				}
				else System.out.println("--잘못된 입력입니다.--");
			} catch (DuplicateIDException e) {
				System.out.println(e.getMessage());
			} catch (AuthorizationException e) {
				System.out.println(e.getMessage());
			} catch (NotFoundProfileException e) {
				System.out.println(e.getMessage());
			} catch (AuthenticationException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	//// 고객 DB 서비스 카테고리 - 입수한 고객정보를 DB에 반영한다.
	private void customerDBServiceCategory() throws IOException, DuplicateIDException, AuthenticationException, AuthorizationException, NotFoundProfileException {
		if (employee==null) {
			throw new AuthenticationException();
		}
		while(true) {
			System.out.println("***************** 고객 DB 서비스 카테고리 메뉴 *****************");
			System.out.println("1. 고객정보 생성");
			System.out.println("2. 고객정보 조회");
			System.out.println("3. 고객정보 수정");
			System.out.println("4. 고객정보 삭제");
			System.out.println("R. 돌아가기");
			String clientChoice = clientInputReader.readLine().trim();
			if (clientChoice.equals("1")) createCustomer(clientInputReader);
			else if (clientChoice.equals("2")) retrieveCustomer(clientInputReader);
			else if (clientChoice.equals("3")) updateCustomer(clientInputReader);
			else if (clientChoice.equals("4")) deleteCustomer(clientInputReader);
			else if (clientChoice.equals("R")) {
				System.out.println("|*** Return to Employee Main Menu ***|");
				return;
			}
			else System.out.println("invalid choice");
		}
	}
	// 입수한 고객정보를 DB에 반영한다.
	private void createCustomer(BufferedReader clientInputReader) throws IOException, DuplicateIDException, AuthorizationException {
		if (!employee.getType().equals(Constant.CutomerInfomationManage)) {
			throw new AuthorizationException();
		}
		System.out.println("-- 입수한 고객 정보 입력 --");
		
		// basic attribute settings
		System.out.print("이름 : "); String name = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("ID: "); String customerID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("PW: "); String customerPW = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("계좌: "); String account = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("주소: "); String address = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("나이: "); String age = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("생일: "); String birthDate = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("이메일: "); String email = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("성별: enter [M/W]"); String gender = dataValidation(clientInputReader.readLine().trim(), "gender");
		System.out.print("키: "); String height = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("직업: "); String job = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("폰번호: "); String phone = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("몸무게: "); String weight = dataValidation(clientInputReader.readLine().trim(), "Integer");
		
		// composition to whole settings
		// MedicalHistorys
		MedicalHistory medicalHistory = new MedicalHistory();
		ArrayList<String> diseases = new ArrayList<>();
		System.out.print("치료기간: "); String curePeriod = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("치료유무: enter [Y/N]"); String isCuredResult = dataValidation(clientInputReader.readLine().trim(), "boolean");
		boolean isCured;
		if (isCuredResult.equals("Y")) isCured = true;
		else isCured = false;
		System.out.print("질병: "); String diseasesString = dataValidation(clientInputReader.readLine().trim(), "multiValue");
		String[] diseasesStrings = diseasesString.split(" ");
		for (String disease : diseasesStrings) {
			diseases.add(disease);
		}
		medicalHistory.setCured(isCured);
		medicalHistory.setCurePeriod(curePeriod);
		medicalHistory.setDiseases(diseases);
		
		// ListImpl Add
		Customer customer = new Customer();
		customer.setAccount(account);
		customer.setAddress(address);
		customer.setAge(Integer.parseInt(age));
		customer.setBirthDate(birthDate);
		customer.setCustomerID(Integer.parseInt(customerID));
		customer.setCustomerPW(customerPW);
		customer.setEmail(email);
		customer.setGender(gender);
		customer.setHeight(Integer.parseInt(height));
		customer.setJob(job);
		customer.setMedicalHistory(medicalHistory);
		customer.setName(name);
		customer.setPhone(phone);
		customer.setWeight(Integer.parseInt(weight));
		
		// association setting
		customer.setCounselList(counselListImpl);
		customer.setPaymentList(paymentListImpl);
		customer.setInsuranceList(insuranceListImpl);
		System.out.print("해당 고객의 정보를 DB에 추가하여 반영하시겠습니까? [Y/N]"); String result = dataValidation(clientInputReader.readLine().trim(), "boolean");
		System.out.println(employee.createCustomer(customer, result));
	}
	private void retrieveCustomer(BufferedReader clientInputReader) throws AuthorizationException {
		showCustomerList();
	}
	private void updateCustomer(BufferedReader clientInputReader) throws IOException, NotFoundProfileException {
		System.out.println("-- 수정할 고객 정보 입력 --");
		System.out.print("수정할 고객 ID: "); String customerID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.println("-- 새로운 고객 정보 입력 --");
		// basic attribute settings
		System.out.print("이름: "); String name = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("계좌: "); String account = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("주소: "); String address = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("나이: "); String age = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("생일: "); String birthDate = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("이메일: "); String email = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("성별: enter [M/W]"); String gender = dataValidation(clientInputReader.readLine().trim(), "gender");
		System.out.print("키: "); String height = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("직업: "); String job = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("폰번호: "); String phone = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("몸무게: "); String weight = dataValidation(clientInputReader.readLine().trim(), "Integer");
		// composition to whole settings
		// MedicalHistorys
		MedicalHistory medicalHistory = new MedicalHistory();
		ArrayList<String> diseases = new ArrayList<>();
		System.out.print("치료기간: "); String curePeriod = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("치료유무: enter [Y/N]"); String isCuredResult = dataValidation(clientInputReader.readLine().trim(), "boolean");
		boolean isCured;
		if (isCuredResult.equals("Y")) isCured = true;
		else isCured = false;
		System.out.print("질병: "); String diseasesString = dataValidation(clientInputReader.readLine().trim(), "multiValue");
		String[] diseasesStrings = diseasesString.split(" ");
		for (String disease : diseasesStrings) {
			diseases.add(disease);
		}
		medicalHistory.setCured(isCured);
		medicalHistory.setCurePeriod(curePeriod);
		medicalHistory.setDiseases(diseases);
		System.out.println(employee.updateCustomer(customerID, name, account, address, age, birthDate, email, gender, height, job, phone, weight, medicalHistory));
	}
	private void deleteCustomer(BufferedReader clientInputReader) throws IOException {
		System.out.println("-- 삭제할 고객 정보 입력 --");
		System.out.print("삭제할 고객 ID: "); String customerID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.println(employee.deleteCustomer(Integer.parseInt(customerID)));
	}
	// -------------------------------------------------------------
	
	//// 계약체결 카테고리 - 계약을 체결한다.
	private void concludeContractCategory() throws IOException, AuthenticationException {
		if (employee==null) {
			throw new AuthenticationException();
		}
		while(true) {
			System.out.println("***************** 계약체결 카테고리 메뉴 *****************");
			System.out.println("1. 계약체결 진행");
			System.out.println("2. 재심사 요청");
			System.out.println("R. 돌아가기");
			String clientChoice = clientInputReader.readLine().trim();
			if (clientChoice.equals("1")) concludeContract();
			else if (clientChoice.equals("2")) requestReUnderwriting();
			else if (clientChoice.equals("R")) {
				System.out.println("|*** Return to Employee Main Menu ***|");
				return;
			}
			else System.out.println("invalid choice");
		}
	}
	private void concludeContract() throws IOException {
		int index = 1;
		ArrayList<Contract> permitContractList = contractListImpl.retrieveByContractStatus(Constant.contractStatus4);
		if(permitContractList.size() == 0) {
			System.out.println("계약진행이 허용된 계약이 존재하지 않습니다.");
			return;
		}
		System.out.println("-- 계약진행이 허용된 계약 리스트. --");
		for(Contract contract : permitContractList) {
			System.out.println(index + ". " + "계약 id: "+contract.getContractID() + "계약상태: "+contract.getContractStatus() +" 고객id: "+contract.getCustomerID()+" 계약날짜: "+contract.getCreatedDate()+" 보험 상품 id: "+contract.getInsuranceID() +" 인수한 U/W직원 id: " +contract.getUnderwritingEID());
			index++;
		}
		System.out.println("-----------------------------");
		System.out.print("체결할 계약 id: "); String contractID = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("계약을 체결하시겠습니까? [Y/N]"); String result = dataValidation(clientInputReader.readLine().trim(), "boolean");
		Contract contract = contractListImpl.retrieveById(Integer.parseInt(contractID));
		System.out.println(employee.concludeContract(contract, result));
	}
	private void requestReUnderwriting() throws IOException {
		boolean status = showRejectedUnderwriteContractList();
		if (!status) return;
		System.out.print("재심사 요청할 계약 id: "); String contractID = dataValidation(clientInputReader.readLine().trim(), "String");
		Contract contract = contractListImpl.retrieveById(Integer.parseInt(contractID));
		System.out.println("-- 재심사를 요청하려는 계약의 정보");
		System.out.println("재심사 하려는 계약정보. " + "계약 id: "+contract.getContractID() + ", 탈락사유: "+contract.getEvaluation());
		System.out.println(employee.requestReUnderwriting(contract));;
	}
	// ------------------------------------------------------
	
	//// 인수심사 카테고리 - 계약의 인수심사를 하다, 계약 진행을 허가한다.	
	private void underWritingCategory() throws IOException, AuthenticationException {
		if (employee==null) {
			throw new AuthenticationException();
		}
		while(true) {
			System.out.println("***************** 인수심사 카테고리 메뉴 *****************");
			System.out.println("1. 인수심사 진행");
			System.out.println("2. 인수심사된 계약 조회");
			System.out.println("3. 인수심사 거절된 계약 조회");
			System.out.println("4. 계약 진행 허가");
			System.out.println("R. 돌아가기");
			String clientChoice = clientInputReader.readLine().trim();
			if (clientChoice.equals("1")) processUnderwriting();
			else if (clientChoice.equals("2")) showUnderwritedContractList();
			else if (clientChoice.equals("3")) showRejectedUnderwriteContractList();
			else if (clientChoice.equals("4")) permitContract();
			else if (clientChoice.equals("R")) {
				System.out.println("|*** Return to Employee Main Menu ***|");
				return;
			}
			else System.out.println("invalid choice");
		}
	}
	// 계약 진행을 허가한다.	
	private void permitContract() throws IOException {
		boolean status = showUnderwritedContractList();
		if (!status) return;
		System.out.print("계약진행 허가할 계약 id: "); String contractID = dataValidation(clientInputReader.readLine().trim(), "String");
		Contract contract = contractListImpl.retrieveById(Integer.parseInt(contractID));
		System.out.println("-- 계약진행을 허가하려는 계약의 정보 --");
		System.out.println("진행 허가 계약정보. " + "계약 id: "+contract.getContractID() + " 고객id: "+contract.getCustomerID()+" 계약날짜: "+contract.getCreatedDate()+" 보험 상품 id: "+contract.getInsuranceID() +" 인수한 U/W직원 id: " +contract.getUnderwritingEID());
		System.out.print("계약 진행 허가 [Y/N]"); String result = dataValidation(clientInputReader.readLine().trim(), "boolean");
		System.out.println(employee.permitContract(contract, result));
	}
	// 계약의 인수심사를 한다.
	private void showRequestedUnderwriteContractList() throws IOException {
		int index = 1;
		ArrayList<Contract> requestedUnderwriteContractList = contractListImpl.retrieveByContractStatus(Constant.contractStatus1);
		if(requestedUnderwriteContractList.size() == 0) {
			System.out.println("인수심사 요청된 계약이 존재하지 않습니다.");
			return;
		}
		System.out.println("-- 인수심사 요청된 계약 리스트. --");
		for(Contract contract : requestedUnderwriteContractList) {
			System.out.println(index + ". " + "계약ID: " + contract.getContractID() + " 계약을 생성한 직원: " + contract.getCreateContractEID() + " 계약상태: " + contract.getContractStatus());
			index++;
		}
	}
	private void processUnderwriting() throws IOException {
		showRequestedUnderwriteContractList();
		System.out.print("인수심사 진행할 계약 ID: "); String contractID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		Contract contract = contractListImpl.retrieveById(Integer.parseInt(contractID));
		Customer customer = customerListImpl.retrieveById(contract.getCustomerID());
		System.out.println("-- 인수심사 대상 계약 및 고객 정보 --");
		System.out.println("계약id : "+contract.getContractID());
		System.out.println("계약날짜 : "+contract.getCreatedDate());
		System.out.println("계약을 생성한 직원id : "+contract.getCreateContractEID());
		System.out.println("고객이름 : "+customer.getName());
		System.out.println("전화번호 : "+customer.getPhone());
		System.out.println("이메일 : "+customer.getEmail());
		System.out.println("주소 : "+customer.getAddress());
		System.out.println("생년월일 : "+customer.getBirthDate());
		System.out.println("신청보험id : "+contract.getInsuranceID());
		System.out.println("키 : "+customer.getHeight());
		System.out.println("몸무게 : "+customer.getWeight());
		System.out.println("나이 : "+customer.getAge());
		MedicalHistory medicalHistory = customer.getMedicalHistory();
		ArrayList<String> diseaseList = medicalHistory.getDiseases();
		String diseases = "";
		for(String disease : diseaseList) diseases+= ", "+disease;
		System.out.println("고객병력-치료기간 : "+medicalHistory.getCurePeriod());
		System.out.println("고객병력-질환 : "+ diseases);
		System.out.println("고객병력-치료여부 : "+medicalHistory.isCured());
		System.out.println("-------------------------------");
		System.out.print("평가결과: "); String evaluation = clientInputReader.readLine().trim();
		System.out.println("[info] 평가결과가 저장되었습니다.");
		System.out.print("인수여부 [Y/N]: "); String result = dataValidation(clientInputReader.readLine().trim(), "boolean");
		System.out.println(employee.processUnderwriting(contract, evaluation, result));
	}
	private boolean showUnderwritedContractList() {
		int index = 1;
		ArrayList<Contract> underwritedContractList = contractListImpl.retrieveByContractStatus(Constant.contractStatus3);
		if(underwritedContractList.size() == 0) {
			System.out.println("인수심사 완료된 계약이 존재하지 않습니다.");
			return false;
		}
		System.out.println("-- 인수심사 완료된 계약 리스트. --");
		for(Contract contract : underwritedContractList) {
			System.out.println(index + ". " + "계약 id: "+contract.getContractID() + " 고객id: "+contract.getCustomerID()+" 계약날짜: "+contract.getCreatedDate()+" 보험 상품 id: "+contract.getInsuranceID() +" 인수한 U/W직원 id: " +contract.getUnderwritingEID());
			index++;
		}
		System.out.println("---------------------------");
		return true;
	}
	private boolean showRejectedUnderwriteContractList() {
		int index = 1;
		ArrayList<Contract> rejectedUnderwriteContractList = contractListImpl.retrieveByContractStatus(Constant.contractStatus2);
		if(rejectedUnderwriteContractList.size() == 0) {
			System.out.println("인수심사 거절된 계약이 존재하지 않습니다.");
			return false;
		}
		System.out.println("-- 인수심사 거절된 계약 리스트. --");
		for(Contract contract : rejectedUnderwriteContractList) {
			System.out.println(index + ". " + "계약 id: "+contract.getContractID() + " 고객id: "+contract.getCustomerID()+" 계약날짜: "+contract.getCreatedDate()+" 보험 상품 id: "+contract.getInsuranceID() +" 인수한 U/W직원 id: " +contract.getUnderwritingEID());
			index++;
		}
		System.out.println("---------------------------");
		return true;
	}
	//-----------------------------------------------------------------

	//// 상담 신청 카테고리 - 보험 상담을 신청한다
	private void counselCategory() throws IOException, AuthenticationException, DuplicateIDException {
		if (customer == null) {
			throw new AuthenticationException();
		}
		while(true) {
			System.out.println("***************** 상담 신청 카테고리 메뉴 *****************");
			System.out.println("1. 상담 신청");
			System.out.println("2. 상담 신청 내역 조회");
			System.out.println("R. 돌아가기");
			
			String clientChoice = clientInputReader.readLine().trim();
			if (clientChoice.equals("1")) createCounsel();
			else if (clientChoice.equals("2")) showCounselList();
			else if (clientChoice.equals("R")) {
				System.out.println("|*** 본 홈페이지로 돌아갑니다. ***|");
				return;
			}
			else System.out.println("잘못된 입력입니다.");
		}
	}
	// 보험 상담을 신청한다
	private void createCounsel() throws IOException, DuplicateIDException {
		System.out.println("-- 상담 신청 정보--");
		// basic attribute settings
		System.out.print("상담 ID: "); String counselID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("보험 종류: 1. 자동차 2. 주택화재 3. 암건강 4. 해외여행 "); String insuranceCategoryInput = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("상담 일자: "); String dateOfCounsel = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("상담 시간: "); String timeOfCOunsel = dataValidation(clientInputReader.readLine().trim(), "String");
		boolean statusOfCounsel = false;
		
		String insuranceCategory = "";
		if(insuranceCategoryInput == "1") insuranceCategory = "자동차";
		else if(insuranceCategoryInput == "2") insuranceCategory = "주택화재";
		else if(insuranceCategoryInput == "3") insuranceCategory = "암건강";
		else if(insuranceCategoryInput == "4") insuranceCategory = "해외여행";
		
		
		// ListImpl Add
		Counsel counsel = new Counsel();
		counsel.setCounselID(Integer.parseInt(counselID));
		counsel.setCustomerID(customer.getCustomerID());
		counsel.setInsuranceCategory(insuranceCategory);
		counsel.setDateOfCounsel(dateOfCounsel);
		counsel.setTimeOfCounsel(timeOfCOunsel);
		counsel.setStatusOfCounsel(statusOfCounsel);
		
		System.out.println("해당 일자에 상담을 신청하시겠습니까? [Y/N]: "); String result = clientInputReader.readLine().trim();
		if(result.equals("Y")) System.out.println(customer.requestCounsel(counsel));
		else if(result.equals("N")) System.out.println("|*** 본 페이지로 돌아갑니다. ***|");
		else System.out.println("잘못된 입력입니다.");
	}
	
	// 상담 신청 조회
	private void showCounselList() {
		ArrayList<Counsel> counselList = counselListImpl.retrieveByCustomerID(customer.getCustomerID());
		if(counselList.size() == 0) {
			System.out.println("신청한 상담이 없습니다.");
			return;
		}
		int index = 1;
		System.out.println("-- 신청한 상담 리스트 --");
		for(Counsel counsel : counselList) {
			System.out.println(index + ". 상담 ID: " + counsel.getCounselID() + " 고객 ID: " + counsel.getCustomerID()+" 보험 종류: " +counsel.getInsuranceCategory()+ " 상담 일자: "+counsel.getDateOfCounsel()+" 상담 시간: "+counsel.getTimeOfCounsel()+" 상담 상태: " + counsel.isConfirmedCounsel());
			index++;
		}
	}
	// -------------------------------------------------------------
    //// 보험 상품 종류 카테고리 - 보험 상품을 조회하다, 보험 가입 신청하다
	private void insuranceTypeCategory() throws IOException, AuthenticationException, DuplicateIDException {
		if (customer == null) {
			throw new AuthenticationException();
		}
		while(true) {
			System.out.println("***************** 보험 상품 종류 카테고리 메뉴 *****************");
			System.out.println("1. 자동차");
			System.out.println("2. 생활");
			System.out.println("3. 건강");
			System.out.println("4. 여행");
			System.out.println("R. 돌아가기");
			
			String clientChoice = clientInputReader.readLine().trim();
			if(clientChoice.equals("1") || clientChoice.equals("2") || clientChoice.equals("3")|| clientChoice.equals("4")) {
				showInsuranceTypeList(clientChoice);
				return;
			}else if (clientChoice.equals("R")) {
				System.out.println("|*** 본 홈페이지로 돌아갑니다. ***|");
				return;
			} else System.out.println("잘못된 입력입니다.");
		}
	}

	// 보험 상품 종류 조회
	private void showInsuranceTypeList(String clientChoice) throws IOException, DuplicateIDException {
		String insuranceCategory = "";
		if(clientChoice == "1") insuranceCategory = "자동차";
		else if(clientChoice == "2") insuranceCategory = "주택화재";
		else if(clientChoice == "3") insuranceCategory = "암건강";
		else if(clientChoice == "4") insuranceCategory = "해외여행";
		
		ArrayList<Insurance> insuranceList = insuranceListImpl.retrieveTypeAll(insuranceCategory);
		if(insuranceList.size() == 0) {
			System.out.println("[info] 해당 종류의 보험 상품이 존재하지 않습니다.");
			return;
		}
		int index = 1;
		System.out.println("-- 보험 상품명 리스트 --");
		for(Insurance insurance: insuranceList) {
			System.out.println(index + ". 보험 ID: " + insurance.getInsuranceID() + " 보험 상품명: " + insurance.getInsuranceName());
			index++;
		}
		System.out.println("상세 내용 조회를 원하는 보험의 ID를 입력해주세요.");
		System.out.println("R. 돌아가기");
		
		clientChoice = clientInputReader.readLine().trim();
		if (clientChoice.equals("R")) {
			System.out.println("|*** 보험 상품 종류 카테고리로 돌아갑니다. ***|");
			return;
		}
		Insurance insurance = insuranceListImpl.retrieve(Integer.parseInt(clientChoice));
		if(insurance != null) showInsuranceDetail(insurance, clientInputReader);
		else System.out.println("[error] 보험 ID가 존재하지 않습니다.");
	}
	// 보험 상품 상세 내용 조회
	private void showInsuranceDetail(Insurance insurance, BufferedReader clientInputReader) throws IOException, DuplicateIDException {
			System.out.println("보험 ID: "+ insurance.getInsuranceID());
			System.out.println("보험 상품명: "+ insurance.getInsuranceName());
			System.out.println("보험 종류: " + insurance.getCategory());
			System.out.println("보장 내용: " + insurance.getGuarantee().getGuaranteeName());
			System.out.println("가입 절차: "+ insurance.getProcessOfSubscription());
			System.out.println("보상 절차: "+ insurance.getProcessOfCompoensation());
			System.out.println("최소 가입기간: " + insurance.getMinimumPeriod());
			System.out.println("최소 월 보험료: " +insurance.getMinimumPremium());
			System.out.println("J. 가입 신청");
			System.out.println("R. 돌아가기");
			
			String clientChoice = clientInputReader.readLine().trim();
			if(clientChoice.equals("J")) requestContract(insurance);
			else if (clientChoice.equals("R")) System.out.println("|*** 보험 상품 종류 카테고리로 돌아갑니다. ***|");
			else System.out.println("잘못된 입력입니다.");
			
//			if(clientChoice.equals("J")) {
//				boolean response = customer.requestContract(customer, insurance);
//				if(response) System.out.println("[success] 가입 신청이 완료되었습니다.");
//				else System.out.println("[error] 이미 가입된 보험 ID 입니다.");
//			} else if (clientChoice.equals("R")) {
//				System.out.println("|*** 보험 상품 종류 카테고리로 돌아갑니다. ***|");
//			} else System.out.println("잘못된 입력입니다.");
	}
	// 보험 가입 신청하다
	private void requestContract(Insurance insurance) throws IOException, DuplicateIDException {
		System.out.println("---- 계약 신청 정보 ----");
		// basic attribute settings
		System.out.print("계약 ID: "); String contractID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		String contractStatus = Constant.contractStatus1;
		System.out.print("고객 ID: "); int customerID = customer.getCustomerID();
		System.out.print("계약 만기일: "); String expirationDate = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("보험 ID: "); int insuranceID = insurance.getInsuranceID();
		String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		boolean isConclude = false;
		boolean isPassUW = false;
//		System.out.print("월 보험료: "); String monthlyPremium = dataValidation(clientInputReader.readLine().trim(), "Integer");

		// composition to whole settings
		PaymentInfo paymentInfo = new PaymentInfo();
		System.out.println("--결제 정보--");
		System.out.print("결제정보 ID: "); String paymentInfoID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("월 납입금 : "); String fixedMonthlyPayment = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("월 납부일: "); String fixedMonthlyPaymentDate = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("납부 방법: "); String paymentType = dataValidation(clientInputReader.readLine().trim(), "String");
		paymentInfo.setPaymentInfoID(Integer.parseInt(paymentInfoID));
		paymentInfo.setFixedMonthlyPayment(Integer.parseInt(fixedMonthlyPayment));
		paymentInfo.setFixedMonthlyPaymentDate(fixedMonthlyPaymentDate);
		paymentInfo.setPaymentType(paymentType);
		if(paymentType.equals(Constant.paymentInfoCard)) {
			System.out.println("--카드 정보 입력란--");
			System.out.print("카드 번호: ");String cardNum= dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("CVC 번호: ");String cvcNum= dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("비밀번호: ");String password= dataValidation(clientInputReader.readLine().trim(), "String");
			CardPayment cardPayment = new CardPayment();
			cardPayment.setCardNum(cardNum);
			cardPayment.setCvcNum(cvcNum);
			cardPayment.setPassword(password);
		}else if(paymentType.equals(Constant.paymentInfoBank)) {
			System.out.println("--계좌 정보 입력란--");
			System.out.print("계좌 주 이름: ");String payerName= dataValidation(clientInputReader.readLine().trim(), "String");;
			System.out.print("계좌 주 전화번호: ");String payerPhoneNum= dataValidation(clientInputReader.readLine().trim(), "String");
			BankPayment bankPayment = new BankPayment();
			bankPayment.setPayerName(payerName);
			bankPayment.setPayerPhoneNum(payerPhoneNum);
		}else if(paymentType.equals(Constant.paymentInfoAutomatic)) {
			System.out.println("--자동 이체 정보 입력란--");
			System.out.print("계좌 번호: ");String accountNum = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("신청자 명: ");String applicantName = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("신청자 RRN: ");String applicantRRN = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("은행 이름: ");String paymentCompanyName = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("신청인과의 관계: ");String relationshipToApplicant = dataValidation(clientInputReader.readLine().trim(), "String");
			AutomaticPayment automaticPayment = new AutomaticPayment();
			automaticPayment.setAccountNum(accountNum);
			automaticPayment.setApplicantName(applicantName);
			automaticPayment.setApplicantRRN(applicantRRN);
			automaticPayment.setPaymentCompanyName(paymentCompanyName);
			automaticPayment.setRelationshipToApplicant(relationshipToApplicant);
		}
		// ListImpl Add
		Contract contract = new Contract();
		contract.setContractID(Integer.parseInt(contractID));
		contract.setContractStatus(contractStatus);
		contract.setCustomerID(customerID);
//		contract.setCreateContractEID(employee.getEmployeeID());
		contract.setExpirationDate(expirationDate);
		contract.setInsuranceID(insuranceID);
		contract.setCreatedDate(createdDate);
		contract.setConclude(isConclude);
		contract.setPassUW(isPassUW);
//		contract.setMonthlyPremium(Integer.parseInt(monthlyPremium));
		contract.setPaymentInfo(paymentInfo);

//		boolean response = employee.createContract(contract);
		
		System.out.println("가입 신청을 완료하시겠습니까? [Y/N]: "); String result = clientInputReader.readLine().trim();
		if(result.equals("Y")) System.out.println(customer.requestContract(contract));
		else if(result.equals("N")) System.out.println("|*** 본 페이지로 돌아갑니다. ***|");
		else System.out.println("잘못된 입력입니다.");
	}
	// -------------------------------------------------------------
	
	//// 보유 계약 조회 카테고리 - 계약을 확인하다, 계약을 해지하다
	private void contractRetrieveCategory() throws IOException, AuthenticationException {
		if (customer == null) {
			throw new AuthenticationException();
		}
		while(true) {
			System.out.println("***************** 계약 조회 카테고리 메뉴 *****************");
			System.out.println("1. 보유 계약 조회");
			System.out.println("2. 신청한 계약 조회");
			System.out.println("R. 돌아가기");
			
			String clientChoice = clientInputReader.readLine().trim();
			if(clientChoice.equals("1")) showContractList(clientInputReader);
			else if (clientChoice.equals("2")) showRequestedContractList(clientInputReader);
			else if (clientChoice.equals("R")) {
				System.out.println("|*** 본 홈페이지로 돌아갑니다. ***|");
				return;
			} else System.out.println("잘못된 입력입니다.");
		}
	}
	
	// 보유한 계약을 확인하다
	private void showContractList(BufferedReader clientInputReader) throws IOException {
		ArrayList<Contract> contractList = contractListImpl.retrieveByCustomerId(customer.getCustomerID());
		if(contractList.size() == 0) {
			System.out.println("[info] 보유한 계약이 없습니다.");
			return;
		}
		int index = 1;
		System.out.println("-- 보유 계약 리스트 --");
		for(Contract contract : contractList) {
			Insurance insurance = insuranceListImpl.retrieve(contract.getInsuranceID());
			System.out.println(index + ". 계약 ID: " + contract.getContractID() + " 보험 상품명: "+insurance.getInsuranceName() + " 고객 ID: "+ customer.getCustomerID());
			index++;
		}
		System.out.println();
		System.out.println("상세 내용 조회를 원하는 계약 ID를 입력해주세요.");
		System.out.println("R. 돌아가기");
		
		String clientChoice = clientInputReader.readLine().trim();
		if (clientChoice.equals("R")) {
			System.out.println("|*** 계약 조회 카테고리 메뉴로 돌아갑니다. ***|");
			return;
		}
		Contract contract = contractListImpl.retrieveById(Integer.parseInt(clientChoice));
		if(contract != null) showContractDetail(contract, clientInputReader);
		else System.out.println("[error] 계약 ID가 존재하지 않습니다.");
	}
	
	// 신청한 계약을 확인하다
	private void showRequestedContractList(BufferedReader clientInputReader) throws IOException {
		ArrayList<Contract> contractList = contractListImpl.retrieveRequestedContractList(customer.getCustomerID());
		if(contractList.size() == 0) {
			System.out.println("[info] 신청한 계약이 없습니다.");
			return;
		}
		int index = 1;
		System.out.println("-- 신청한 계약 리스트 --");
		for(Contract contract : contractList) {
			Insurance insurance = insuranceListImpl.retrieve(contract.getInsuranceID());
			System.out.println(index + ". 계약 ID: " + contract.getContractID() + " 보험 상품명: "+insurance.getInsuranceName() + " 고객 ID: "+ customer.getCustomerID() + "계약 상태: "+ contract.getContractStatus());
			index++;
		}
	}
	
	// 계약 상세 내용
	private void showContractDetail(Contract contract, BufferedReader clientInputReader) throws IOException {
		Insurance insurance = insuranceListImpl.retrieve(contract.getInsuranceID());
		Customer customer = customerListImpl.retrieveById(contract.getCustomerID());
		System.out.println("계약 ID: " + contract.getContractID());
		System.out.println("보험 상품명: "+insurance.getInsuranceName());
		System.out.println("고객 ID: " + customer.getCustomerID());
		System.out.println("고객 이름: " + customer.getName());
		System.out.println("전화번호: "+ customer.getPhone());
		System.out.println("보장 내용: " + insurance.getGuarantee().getGuaranteeName());
		System.out.println("월 보험료: " + contract.getMonthlyPremium());
		System.out.println("계약 시작일: " + contract.getCreatedDate());
		System.out.println("계약 만기일: "+ contract.getExpirationDate());
		System.out.println("계약 상태: "+ contract.getContractStatus());
		System.out.println("D. 계약 해지");
		System.out.println("R. 돌아가기");
		
		String clientChoice = clientInputReader.readLine().trim();
		if(clientChoice.equals("D")) cancelContract(customer, contract);
		else if (clientChoice.equals("R")) System.out.println("|*** 계약 조회 카테고리 메뉴로 돌아갑니다. ***|");
		else System.out.println("잘못된 입력입니다.");
	}
	// 계약을 해지한다
	private void cancelContract(Customer customer, Contract contract) throws IOException {
		System.out.println("보험 계약을 해지하시겠습니까? [Y/N]: "); String result = clientInputReader.readLine().trim();
		if(result.equals("Y")) System.out.println(customer.cancelContract(contract));
		else if(result.equals("N")) System.out.println("|*** 본 페이지로 돌아갑니다. ***|");
		else System.out.println("잘못된 입력입니다.");
	}
	// -------------------------------------------------------------

	//// 상품 개발 카테고리 - 상품을 개발한다. 상품리스트를 확인한다.
	private void createInsuranceCategory() throws IOException, AuthenticationException {
		while(true) {
			if (employee==null) {
				throw new AuthenticationException();
			}
			System.out.println("***************** 상품 개발 카테고리 메뉴 *****************");
			System.out.println("1. 상품 추가");
			System.out.println("2. 상품 리스트 확인");
			System.out.println("R. 돌아가기");
			String clientChoice = clientInputReader.readLine().trim();
			if (clientChoice.equals("1")) createInsurance();
			else if (clientChoice.equals("2")) showInsuranceList();
			else if (clientChoice.equals("R")) {
				System.out.println("|*** 이전으로 돌아갑니다. ***|");
				return;
			}
			else System.out.println("유효하지 않은 메뉴 번호입니다.");
		}
	}
	// 상품을 개발한다. (만들어진 함수 그대로 씀)
	private void createInsurance() throws IOException {
		// 상품 정보 입력
		System.out.println("--상품 정보 입력란--");
		System.out.print("보험 ID: "); String insuranceID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("보험 이름: "); String insuranceName = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.println("상품 종류: 1. 자동차  2. 주택화재  3. 암건강  4. 해외여행"); 
		System.out.print("상품 종류: "); String category = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("최소 계약 기간: "); String minimumPeriod = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("최대 계약 기간: "); String minimumPremium = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("보상 규정: "); String processOfCompensation = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("신청 규정: "); String processOfSubscription = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("메모: "); String notice = dataValidation(clientInputReader.readLine().trim(), "String");
		
		Guarantee guarantee = new Guarantee();
		System.out.println("--보장 내역--");
		System.out.print("보장 이름: "); String guaranteeName = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("내용: "); String guranteeDescription = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("최대 보상료: "); String maxCoverage = dataValidation(clientInputReader.readLine().trim(), "Integer");
		guarantee.setGuaranteeName(guaranteeName);
		guarantee.setDescription(guranteeDescription);
		guarantee.setMaxCoverage(Integer.parseInt(maxCoverage));
		
		SpecialProvision specialProvision = new SpecialProvision();
		System.out.println("--특별 조항--");
		System.out.print("특별 조항 이름: "); String specialProvisionName = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("내용: "); String provisionDescription = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("할인율: "); String rateOfDiscount  = dataValidation(clientInputReader.readLine().trim(), "Double");
		specialProvision.setSpecialProvisionName(specialProvisionName);
		specialProvision.setDescription(provisionDescription);
		specialProvision.setRateOfDiscount(Double.parseDouble(rateOfDiscount));
		
		Insurance insurance = null;
		// 상품 카테고리에 맞는 추가 정보 입력
		if(category.equals("1")) {
			insurance = new Car();
			System.out.println("--차 보험 정보--");
			System.out.print("차 종: "); String model = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("가격: "); String carPrice = dataValidation(clientInputReader.readLine().trim(), "Integer");
			System.out.print("VIN: "); String VIN = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("블랙박스 유무[Y/N]"); String blackbox = dataValidation(clientInputReader.readLine().trim(), "boolean");
			((Car) insurance).setModel(model);
			((Car) insurance).setPriceOfCar(Integer.parseInt(carPrice));
			((Car) insurance).setVIN(VIN);
			if(blackbox.equals("Y")) ((Car) insurance).setHasBlackbox(true);
			else if(blackbox.equals("N")) ((Car) insurance).setHasBlackbox(false);
			
		}else if(category.equals("2")) {
			insurance = new HouseFire();
			System.out.println("--화재 보험 정보--");
			System.out.print("주택 종류: "); String categoryOfHouse = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("주택 가격: "); String housePrice = dataValidation(clientInputReader.readLine().trim(), "Integer");
			((HouseFire) insurance).setCategoryOfHouse(categoryOfHouse);
			((HouseFire) insurance).setPriceOfHouse(Integer.parseInt(housePrice));
			
		}else if(category.equals("3")) {
			insurance = new CancerHealth();
			System.out.println("--암 보험 정보--");
			System.out.print("암의 종류: "); String categoryOfCancer = dataValidation(clientInputReader.readLine().trim(), "String");
			((CancerHealth) insurance).setCategoryOfCancer(categoryOfCancer);
			
		}else if(category.equals("4")) {
			insurance = new InternationalTravel();
			System.out.println("--해외 여행 보험 정보--");
			System.out.print("여행 국가: "); String travelCountry = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("여행 기간: "); String travelPeriod = dataValidation(clientInputReader.readLine().trim(), "Integer");
			((InternationalTravel) insurance).setTravelCountry(travelCountry);
			((InternationalTravel) insurance).setTravelPeriod(Integer.parseInt(travelPeriod));
		}
		// 상품 저장 버튼
		System.out.println("--상품을 저장하시겠습니까?[Y/N]--");
		String save = dataValidation(clientInputReader.readLine().trim(), "String");
		if(save.equals("Y")) {
			insurance.setInsuranceID(Integer.parseInt(insuranceID));
			insurance.setInsuranceName(insuranceName);
			insurance.setCategory(category);
			insurance.setMinimumPeriod(Integer.parseInt(minimumPeriod));
			insurance.setMinimumPremium(Integer.parseInt(minimumPremium));
			insurance.setProcessOfCompoensation(processOfCompensation);
			insurance.setProcessOfSubscription(processOfSubscription);
			insurance.setNotice(notice);
			insurance.setGuarantee(guarantee);
			insurance.setSpecialProvision(specialProvision);
			System.out.println("상품을 저장했습니다.");
		}else {
			System.out.println("상품 저장을 취소했습니다.");
		}
		//보험요율 저장 버튼
		System.out.print("보험요율 입력: "); String insuranceRate = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.println("--보험요율을 저장하시겠습니까?[Y/N]--");
		if(save.equals("Y")) {
			insurance.setInsuranceRate(Integer.parseInt(insuranceRate));
			System.out.println("보험요율을 저장했습니다.");
		}else {
			System.out.println("보험요율 저장을 취소했습니다.");
		}
		//보험요율 확인 버튼
		System.out.println("--보험요율을 확인하시겠습니까?[Y/N]--");
		if(save.equals("Y")) {System.out.println("보험요율:" + insuranceRate);}
	
		Employee employee = employeeListImpl.retrieveById(Integer.parseInt(TokenManager.getID(token)));
		try {
			System.out.println(employee.createInsurance(insurance));
		} catch (DuplicateIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 상품 리스트를 확인한다. (만들어진 함수 그대로 씀)
	private void showInsuranceList() {
		if (!TokenManager.isValidToken(token)) {
			System.out.println("[error] 로그인 먼저 해주세요.");
			return;
		}
		int index = 1;
		System.out.println();
		ArrayList<Insurance> insuranceList = insuranceListImpl.retrieveAll();
		if(insuranceList.size() == 0) {
			System.out.println("[error] 보험이 없습니다.");
			return;
		}
		System.out.println("-- 보험 리스트 --");
		for(Insurance insurance: insuranceList) {
			System.out.println(index + ". 보험ID: " + insurance.getInsuranceID() + "이름: " + insurance.getInsuranceName() + " 보험 종류: "+insurance.getCategory());
			index++;
		}
	}
	// -------------------------------------------------------------
	//// 제관리 지침 관리 카테고리 - 제관리 지침을 관리한다.
	private void ruleCategory() throws IOException, AuthenticationException {
		while(true) {
			if (employee==null) {
				throw new AuthenticationException();
			}
			System.out.println("***************** 제관리 지침 카테고리 메뉴 *****************");
			System.out.println("1. 제관리 지침 리스트 조회");
			System.out.println("2. 제관리 지침 생성");
			System.out.println("3. 제관리 지침 삭제");
			System.out.println("R. 돌아가기");
			String clientChoice = clientInputReader.readLine().trim();
			if (clientChoice.equals("1")) showRuleList();
			else if (clientChoice.equals("2")) createRule();
			else if (clientChoice.equals("3")) deleteRule();
			else if (clientChoice.equals("R")) {
				System.out.println("|*** 이전으로 돌아갑니다. ***|");
				return;
			}
			else System.out.println("유효하지 않은 메뉴 번호입니다.");
		}
	}
	private void createRule() throws IOException {
		
		System.out.println("--제관리 지침 정보를 입력하세요--");
		System.out.print("제관리 지침 ID: "); 
		String ruleID = dataValidation(clientInputReader.readLine().trim(), "Integer");	
		System.out.print("제관리 지침 이름: "); 
		String ruleName =dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("제관리 지침 내용: "); 
		String ruleDetail = dataValidation(clientInputReader.readLine().trim(), "Integer");
		
		Rule rule = new Rule();
		rule.setRuleID(Integer.parseInt(ruleID));
		rule.setRuleName(ruleName);
		rule.setRuleDetail(ruleDetail);

		try {
			System.out.println(employee.createRule(rule));
		} catch (DuplicateIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void deleteRule() throws IOException {
		System.out.print("제관리 지침 ID: "); 
		String ruleID = dataValidation(clientInputReader.readLine().trim(), "Integer");	
		try {
			System.out.println( employee.deleteRule(Integer.parseInt(ruleID)));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	private static void showRuleList() {
		int index=1;
		System.out.println("-- 제관리 지침 리스트 --");
		for(Rule rule : ruleListImpl.retrieveAll()) {
			System.out.println(index + ". 제관리지침 ID: " + rule.getRuleID() + " 제관리지침 이름: " + rule.getRuleName()+ " 제관리지침 내용: " + rule.getRuleDetail());
			index++;
		}
		
	}
	// -------------------------------------------------------------
	// 수금을 관리한다.
	private void setPaymentInfo() throws IOException, AuthenticationException {
		if (employee==null) {
			throw new AuthenticationException();
		}
		PaymentInfo paymentInfo = new PaymentInfo();
		System.out.println("--수금 정보 입력란--");
		System.out.print("계약 ID: "); String contractID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("결제정보 ID: "); String paymentInfoID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("월 납입금 : "); String fixedMonthlyPayment = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("월 납부일: "); String fixedMonthlyPaymentDate = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("납부 방법: "); String paymentType = dataValidation(clientInputReader.readLine().trim(), "String");
		paymentInfo.setPaymentInfoID(Integer.parseInt(paymentInfoID));
		paymentInfo.setFixedMonthlyPayment(Integer.parseInt(fixedMonthlyPayment));
		paymentInfo.setFixedMonthlyPaymentDate(fixedMonthlyPaymentDate);
		paymentInfo.setPaymentType(paymentType);
		if(paymentType.equals(Constant.paymentInfoCard)) {
			System.out.println("--카드 정보 입력란--");
			System.out.print("카드 번호: ");String cardNum= dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("cvc 번호: ");String cvcNum= dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("비밀번호 : ");String password= dataValidation(clientInputReader.readLine().trim(), "String");
			CardPayment cardPayment = new CardPayment();
			cardPayment.setCardNum(cardNum);
			cardPayment.setCvcNum(cvcNum);
			cardPayment.setPassword(password);
		}else if(paymentType.equals(Constant.paymentInfoBank)) {
			System.out.println("--계좌 정보 입력란--");
			System.out.print("계좌 주 이름: ");String payerName= dataValidation(clientInputReader.readLine().trim(), "String");;
			System.out.print("계좌 주 전화번호: ");String payerPhoneNum= dataValidation(clientInputReader.readLine().trim(), "String");
			BankPayment bankPayment = new BankPayment();
			bankPayment.setPayerName(payerName);
			bankPayment.setPayerPhoneNum(payerPhoneNum);
		}else if(paymentType.equals(Constant.paymentInfoAutomatic)) {
			System.out.println("--자동 이체 정보 입력란--");
			System.out.print("계좌 번호: ");String accountNum = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("신청자 명: ");String applicantName = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("신청자 RRN: ");String applicantRRN = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("은행 이름: ");String paymentCompanyName = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("신청인과의 관계: ");String relationshipToApplicant = dataValidation(clientInputReader.readLine().trim(), "String");
			AutomaticPayment automaticPayment = new AutomaticPayment();
			automaticPayment.setAccountNum(accountNum);
			automaticPayment.setApplicantName(applicantName);
			automaticPayment.setApplicantRRN(applicantRRN);
			automaticPayment.setPaymentCompanyName(paymentCompanyName);
			automaticPayment.setRelationshipToApplicant(relationshipToApplicant);
		}
		System.out.println("==특정 수금 정보==");
		System.out.println(paymentInfo.toString());
		
		System.out.println(employee.setPaymentInfo(contractID,paymentInfo));
	}
	// -------------------------------------------------------------
	// 미납을 관리한다.
	private void manageLatePayment() throws IOException, AuthenticationException {
		if (employee==null) {
			throw new AuthenticationException();
		}
		System.out.print("계약 ID: "); String contractID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.println("==특정 미납 정보==");
		System.out.println(contractListImpl.retrieveById(Integer.parseInt(contractID)).toString());
		try {
			System.out.println(employee.manageLatePayment(contractID));
		} catch (DuplicateIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// -------------------------------------------------------------

  	//// 보험료 납부 카테고리 - 보험료를 납부한다
	private void paymentCategory() throws IOException, AuthenticationException {
		if (customer == null) {
			throw new AuthenticationException();
		}
		while(true) {
			System.out.println("***************** 보험료 납부 카테고리 메뉴 *****************");
			System.out.println("1. 보험료 납부 리스트 조회");
			System.out.println("2. 납부 내역 리스트 조회");
			System.out.println("R. 돌아가기");
			
			String clientChoice = clientInputReader.readLine().trim();
			if (clientChoice.equals("1")) showPaymentList();
			else if (clientChoice.equals("2")) showProcessedPaymentList();
			else if (clientChoice.equals("R")) {
				System.out.println("|*** 본 홈페이지로 돌아갑니다. ***|");
				return;
			}
			else System.out.println("잘못된 입력입니다.");
		}
	}
	
	private void showPaymentList() throws IOException {
		ArrayList<Payment> paymentList = paymentListImpl.retrieveByCustomerID(customer.getCustomerID());
		if(paymentList.size() == 0) {
			System.out.println("[info] 납부해야 할 보험료가 없습니다.");
			return;
		}
		int index = 1;
		System.out.println("-- 보험료 납부 리스트 --");
		for(Payment payment : paymentList) {
			System.out.println(index + ". 납부 ID: " + payment.getPaymentID() + " 계약 ID: " + payment.getContractID()+ " 고객 ID: " + payment.getCustomerID()+ " 납부 금액: "+payment.getAmount()+" 납부 기한: "+payment.getDueDateOfPayment()+" 납부 상태: "+payment.isPaymentProcessed());
			index++;
		}
		System.out.println("납부하고자 하는 납부 ID를 입력해 주세요.");
		System.out.println("R. 돌아가기");
		
		String clientChoice = clientInputReader.readLine().trim();
		if (clientChoice.equals("R")) {
			System.out.println("|*** 본 홈페이지로 돌아갑니다. ***|");
			return;
		}
		Payment payment = paymentListImpl.retrieve(Integer.parseInt(clientChoice));
		if(payment == null) {
			System.out.println("[error] 납부 ID가 존재하지 않습니다.");
			return;
		}
		System.out.println("-- 결제 정보 입력 --");
		System.out.println("카드 번호: "); String cardNumber = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.println("CVC: "); String cvcNumber = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.println("카드 비밀번호 2자리: "); String password = dataValidation(clientInputReader.readLine().trim(), "Integer");

		System.out.println("해당 보험료를 납부하시겠습니까? [Y/N]: "); String result = clientInputReader.readLine().trim();
		if(result.equals("Y")) System.out.println(customer.payPremium(payment, Integer.parseInt(cardNumber), Integer.parseInt(cvcNumber), Integer.parseInt(password)));
		else if(result.equals("N")) System.out.println("|*** 본 페이지로 돌아갑니다. ***|");
		else System.out.println("잘못된 입력입니다.");
	}
	// 추후
	private void showProcessedPaymentList() {
		ArrayList<Payment> paymentList = paymentListImpl.retrieveByCustomerID(Integer.parseInt(TokenManager.getID(token)));
		if(paymentList.size() == 0) {
			System.out.println("[info] 납부해야 할 보험료가 없습니다.");
			return;
		}
	}
	// -------------------------------------------------------------
	
	//// 상담신청 일정 관리 카테고리 - 상담신청 일정을 관리한다
	private void counselScheduleCategory() throws IOException, AuthenticationException {
		if (employee == null) {
			throw new AuthenticationException();
		}
		while(true) {
			System.out.println("***************** 상담신청 일정 관리 카테고리 *****************");
			System.out.println("1. 신청된 상담 일정 조회");
			System.out.println("2. 확정한 상담 일정 조회");
			System.out.println("R. 돌아가기");
			
			String clientChoice = clientInputReader.readLine().trim();
			if (clientChoice.equals("1")) showRequestedCounselList();
			else if (clientChoice.equals("2")) showConfirmedCounselList();
			else if (clientChoice.equals("R")) {
				System.out.println("|*** 본 홈페이지로 돌아갑니다. ***|");
				return;
			}
			else System.out.println("잘못된 입력입니다.");
		}
	}
	
	private void showRequestedCounselList() throws IOException {
		ArrayList<Counsel> counselList = counselListImpl.retrieveAll();
		if(counselList.size() == 0) {
			System.out.println("[info] 신청된 상담이 없습니다.");
			return;
		}
		int index = 1;
		System.out.println("-- 상담신청 내역 리스트 --");
		for(Counsel counsel : counselList) {
			Customer retrieveCustomer = customerListImpl.retrieveById(counsel.getCustomerID());
			System.out.println(index + ". 상담 ID: "+ counsel.getCounselID() + "고객 ID: "+ retrieveCustomer.getCustomerID()+" 이름: "+ retrieveCustomer.getName()+" 전화번호: "+ retrieveCustomer.getPhone()+" 성별: "+retrieveCustomer.getGender()+" 생년월일: "+retrieveCustomer.getBirthDate()+" 직업: "+retrieveCustomer.getJob()+" 주소: "+retrieveCustomer.getAddress()+ 
					" 보험 종류: "+counsel.getInsuranceCategory()+" 상담 일자: "+counsel.getDateOfCounsel()+ " 상담 시간: "+counsel.getTimeOfCounsel()+" 처리 상태: "+counsel.isConfirmedCounsel());
			index++;
		}
		System.out.println();
		System.out.println("확정하고자 하는 상담 ID를 입력해 주세요.");
		System.out.println("R. 돌아가기");
		
		String clientChoice = clientInputReader.readLine().trim();
		if (clientChoice.equals("R")) {
			System.out.println("|*** 본 홈페이지로 돌아갑니다. ***|");
			return;
		}

		Counsel counsel = counselListImpl.retrieve(Integer.parseInt(clientChoice));
		if(counsel == null) {
			System.out.println("[error] 상담 ID가 존재하지 않습니다.");
			return;
		}
		
		System.out.println("해당 상담 일정을 확정하시겠습니까? [Y/N]: "); String result = clientInputReader.readLine().trim();
		if(result.equals("Y")) System.out.println(employee.confirmCounsel(counsel, employee.getEmployeeID()));
		else if(result.equals("N")) System.out.println("|*** 본 홈페이지로 돌아갑니다. ***|");
		else System.out.println("잘못된 입력입니다.");
	}
	private void showConfirmedCounselList() {
		ArrayList<Counsel> counselList = counselListImpl.retrieveConfirmedCounsel(employee.getEmployeeID());
		if(counselList.size() == 0) {
			System.out.println("[info] 확정한 상담이 없습니다.");
			return;
		}
		int index = 1;
		System.out.println("-- 확정한 상담 리스트 --");
		for(Counsel counsel : counselList) {
			Customer retrieveCustomer = customerListImpl.retrieveById(counsel.getCustomerID());
			System.out.println(index + ". 상담 ID: "+ counsel.getCounselID() + "고객 ID: "+ retrieveCustomer.getCustomerID()+" 이름: "+ retrieveCustomer.getName()+" 주소: "+retrieveCustomer.getAddress()+ " 전화번호: "+ retrieveCustomer.getPhone()+
					" 보험 종류: "+counsel.getInsuranceCategory()+" 상담 일자: "+counsel.getDateOfCounsel()+ " 상담 시간: "+counsel.getTimeOfCounsel()+" 직원 ID: "+counsel.getEmployeeID()+" 처리 상태: "+counsel.isConfirmedCounsel());
			index++;
		}
	}
	// -------------------------------------------------------------
	
	//// 상담 내역 관리 카테고리 - 고객과의 상담 내역을 관리한다
	private void counselDetailCategory() throws IOException, AuthenticationException {
		if (employee == null) {
			throw new AuthenticationException();
		}
		while(true) {
			ArrayList<Counsel> counselList = counselListImpl.retrieveByEmployeeId(employee.getEmployeeID());
			if(counselList.size() == 0) {
				System.out.println("[info] 상담한 내역이 없습니다.");
				return;
			}
			int index = 1;
			System.out.println("-- 상담 내역 리스트 --");
			for(Counsel counsel : counselList) {
				Customer retrieveCustomer = customerListImpl.retrieveById(counsel.getCustomerID());
				System.out.println(index + ". 상담 ID: "+ counsel.getCounselID() +" 고객 ID: "+retrieveCustomer.getCustomerID()+ " 고객 이름: "+ retrieveCustomer.getName()+" 전화번호: "+ retrieveCustomer.getPhone()+" 생년월일: "+retrieveCustomer.getBirthDate()+" 이메일: "+retrieveCustomer.getEmail()+
						" 보험 종류: "+counsel.getInsuranceCategory()+" 상담 내용: "+counsel.getCounselDetail()+" 상담 일자: "+counsel.getDateOfCounsel()+ " 상담 시간: "+counsel.getTimeOfCounsel()+" 직원 ID: "+ counsel.getEmployeeID()+ " 비고: "+counsel.getNote()+ " 처리 상태: "+counsel.isConfirmedCounsel());
				index++;
			}
			System.out.println("상담 내용을 추가하고자 하는 상담 ID를 입력해 주세요.");
			System.out.println("R. 돌아가기");
			
			String clientChoice = clientInputReader.readLine().trim();
			if (clientChoice.equals("R")) {
				System.out.println("|*** 본 홈페이지로 돌아갑니다. ***|");
				return;
			}
			Counsel counsel = counselListImpl.retrieve(Integer.parseInt(clientChoice));
			if(counsel == null) {
				System.out.println("[error] 상담 ID가 존재하지 않습니다.");
				return;
			}
			System.out.println("-- 상담 정보 저장 --");
			System.out.println("상담 내용: ");  String counselDetail = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.println("비고: ");  String note = dataValidation(clientInputReader.readLine().trim(), "String");
			
			System.out.println("상담 내용을 추가하시겠습니까? [Y/N]: "); String result = clientInputReader.readLine().trim();
			if(result.equals("Y")) System.out.println(employee.updateCounsel(counsel, counselDetail, note));
			else if(result.equals("N")) System.out.println("|*** 본 홈페이지로 돌아갑니다. ***|");
			else System.out.println("잘못된 입력입니다.");
		}
	}
	private void manageRevive() throws IOException, AuthenticationException {
		if (employee==null) {
			throw new AuthenticationException();
		}
		System.out.println("--부활 계약 정보--");
		// basic attribute settings
		System.out.print("계약ID: "); String contractID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		String contractStatus = Constant.contractStatus1;
		System.out.print("고객ID: "); String customerID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("만료일: "); String expirationDate = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("보험ID: "); String insuranceID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("부활 사유: "); String resurrectionReason = dataValidation(clientInputReader.readLine().trim(), "String");
		
		String resurrectionDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		boolean isConclude = false;
		boolean isPassUW = false;
		System.out.print("월 보험료: "); String monthlyPremium = dataValidation(clientInputReader.readLine().trim(), "Integer");

		// composition to whole settings
		PaymentInfo paymentInfo = new PaymentInfo();
		System.out.println("--결제 정보--");
		System.out.print("결제정보 ID: "); String paymentInfoID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("월 납입금 : "); String fixedMonthlyPayment = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("월 납부일: "); String fixedMonthlyPaymentDate = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("납부 방법: "); String paymentType = dataValidation(clientInputReader.readLine().trim(), "String");
		paymentInfo.setPaymentInfoID(Integer.parseInt(paymentInfoID));
		paymentInfo.setFixedMonthlyPayment(Integer.parseInt(fixedMonthlyPayment));
		paymentInfo.setFixedMonthlyPaymentDate(fixedMonthlyPaymentDate);
		paymentInfo.setPaymentType(paymentType);
		if(paymentType.equals(Constant.paymentInfoCard)) {
			System.out.println("--카드 정보 입력란--");
			System.out.print("카드 번호: ");String cardNum= dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("cvc 번호: ");String cvcNum= dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("비밀번호 : ");String password= dataValidation(clientInputReader.readLine().trim(), "String");
			CardPayment cardPayment = new CardPayment();
			cardPayment.setCardNum(cardNum);
			cardPayment.setCvcNum(cvcNum);
			cardPayment.setPassword(password);
		}else if(paymentType.equals(Constant.paymentInfoBank)) {
			System.out.println("--계좌 정보 입력란--");
			System.out.print("계좌 주 이름: ");String payerName= dataValidation(clientInputReader.readLine().trim(), "String");;
			System.out.print("계좌 주 전화번호: ");String payerPhoneNum= dataValidation(clientInputReader.readLine().trim(), "String");
			BankPayment bankPayment = new BankPayment();
			bankPayment.setPayerName(payerName);
			bankPayment.setPayerPhoneNum(payerPhoneNum);
		}else if(paymentType.equals(Constant.paymentInfoAutomatic)) {
			System.out.println("--자동 이체 정보 입력란--");
			System.out.print("계좌 번호: ");String accountNum = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("신청자 명: ");String applicantName = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("신청자 RRN: ");String applicantRRN = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("은행 이름: ");String paymentCompanyName = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("신청인과의 관계: ");String relationshipToApplicant = dataValidation(clientInputReader.readLine().trim(), "String");
			AutomaticPayment automaticPayment = new AutomaticPayment();
			automaticPayment.setAccountNum(accountNum);
			automaticPayment.setApplicantName(applicantName);
			automaticPayment.setApplicantRRN(applicantRRN);
			automaticPayment.setPaymentCompanyName(paymentCompanyName);
			automaticPayment.setRelationshipToApplicant(relationshipToApplicant);
		}
		
		// ListImpl Add
		Contract contract = new Contract();
		contract.setContractID(Integer.parseInt(contractID));
		contract.setContractStatus(contractStatus);
		contract.setCustomerID(Integer.parseInt(customerID));
		contract.setCreateContractEID(Integer.parseInt(TokenManager.getID(token)));
		contract.setExpirationDate(expirationDate);
		contract.setInsuranceID(Integer.parseInt(insuranceID));
		contract.setResurrectionDate(resurrectionDate);
		contract.setConclude(isConclude);
		contract.setResurrectionReason(resurrectionReason);
		contract.setPassUW(isPassUW);
		contract.setMonthlyPremium(Integer.parseInt(monthlyPremium));
		contract.setPaymentInfo(paymentInfo);
		
		System.out.println("==특정 부활 상세 정보 확인란==");
		System.out.println(contract.toString());
		
		System.out.println("부활 심사를 등록하시겠습니까? [Y/N]");
		String yOrN = dataValidation(clientInputReader.readLine().trim(), "String");
		if(yOrN.equals("Y")) {
			try {
				System.out.println(employee.permitRevive(contract));
			} catch (DuplicateIDException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			System.out.println(employee.revive(contract));
		} catch (DuplicateIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// -------------------------------------------------------------
	// 만기계약을 관리한다.
	private void manageExpirationContract() throws IOException, ParseException, AuthenticationException {
		if (employee==null) {
			throw new AuthenticationException();
		}
		System.out.print("만기 계약ID: "); String contractID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		Contract contract = contractListImpl.retrieveById(Integer.parseInt(contractID));
		System.out.println("==만기 계약 상세 정보 확인란==");
		System.out.println(contract.toString());
		String expirationDate = contract.getExpirationDate();
		try {
			System.out.println(employee.manageExpirationContract(contractID, expirationDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// -------------------------------------------------------------
	// 재계약을 관리한다.
	private void manageRenewalContract() throws IOException, AuthenticationException {
		if (employee==null) {
			throw new AuthenticationException();
		}
		System.out.print("재계약ID: "); String contractID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		Contract contract = contractListImpl.retrieveById(Integer.parseInt(contractID));
		System.out.println("==재계약 상세 정보 확인란==");
		System.out.println(contract.toString());
		
		try {
			System.out.println(employee.manageRenewal(contract));
		} catch (NotFoundProfileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// -------------------------------------------------------------
	// 배서를 관리한다.
	private void manageUpdate() throws IOException, AuthenticationException {
		if (employee==null) {
			throw new AuthenticationException();
		}
		System.out.print("계약ID: "); String contractID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		String contractStatus = Constant.contractStatus1;
		System.out.print("고객ID: "); String customerID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("만료일: "); String expirationDate = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("보험ID: "); String insuranceID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		boolean isConclude = false;
		boolean isPassUW = false;
		System.out.print("월 보험료: "); String monthlyPremium = dataValidation(clientInputReader.readLine().trim(), "Integer");

		// composition to whole settings
		PaymentInfo paymentInfo = new PaymentInfo();
		System.out.println("--결제 정보--");
		System.out.print("결제정보 ID: "); String paymentInfoID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("월 납입금 : "); String fixedMonthlyPayment = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("월 납부일: "); String fixedMonthlyPaymentDate = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("납부 방법: "); String paymentType = dataValidation(clientInputReader.readLine().trim(), "String");
		paymentInfo.setPaymentInfoID(Integer.parseInt(paymentInfoID));
		paymentInfo.setFixedMonthlyPayment(Integer.parseInt(fixedMonthlyPayment));
		paymentInfo.setFixedMonthlyPaymentDate(fixedMonthlyPaymentDate);
		paymentInfo.setPaymentType(paymentType);
		if(paymentType.equals(Constant.paymentInfoCard)) {
			System.out.println("--카드 정보 입력란--");
			System.out.print("카드 번호: ");String cardNum= dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("cvc 번호: ");String cvcNum= dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("비밀번호 : ");String password= dataValidation(clientInputReader.readLine().trim(), "String");
			CardPayment cardPayment = new CardPayment();
			cardPayment.setCardNum(cardNum);
			cardPayment.setCvcNum(cvcNum);
			cardPayment.setPassword(password);
		}else if(paymentType.equals(Constant.paymentInfoBank)) {
			System.out.println("--계좌 정보 입력란--");
			System.out.print("계좌 주 이름: ");String payerName= dataValidation(clientInputReader.readLine().trim(), "String");;
			System.out.print("계좌 주 전화번호: ");String payerPhoneNum= dataValidation(clientInputReader.readLine().trim(), "String");
			BankPayment bankPayment = new BankPayment();
			bankPayment.setPayerName(payerName);
			bankPayment.setPayerPhoneNum(payerPhoneNum);
		}else if(paymentType.equals(Constant.paymentInfoAutomatic)) {
			System.out.println("--자동 이체 정보 입력란--");
			System.out.print("계좌 번호: ");String accountNum = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("신청자 명: ");String applicantName = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("신청자 RRN: ");String applicantRRN = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("은행 이름: ");String paymentCompanyName = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("신청인과의 관계: ");String relationshipToApplicant = dataValidation(clientInputReader.readLine().trim(), "String");
			AutomaticPayment automaticPayment = new AutomaticPayment();
			automaticPayment.setAccountNum(accountNum);
			automaticPayment.setApplicantName(applicantName);
			automaticPayment.setApplicantRRN(applicantRRN);
			automaticPayment.setPaymentCompanyName(paymentCompanyName);
			automaticPayment.setRelationshipToApplicant(relationshipToApplicant);
		}
		Contract contract = new Contract();
		contract.setContractID(Integer.parseInt(contractID));
		contract.setContractStatus(contractStatus);
		contract.setCustomerID(Integer.parseInt(customerID));
		contract.setCreateContractEID(Integer.parseInt(TokenManager.getID(token)));
		contract.setExpirationDate(expirationDate);
		contract.setInsuranceID(Integer.parseInt(insuranceID));
		contract.setCreatedDate(createdDate);
		contract.setConclude(isConclude);
		contract.setPassUW(isPassUW);
		contract.setMonthlyPremium(Integer.parseInt(monthlyPremium));
		contract.setPaymentInfo(paymentInfo);
		System.out.println("==베서 정보==");
		System.out.println(contract.toString());
		
		System.out.println("배서 심사를 하시겠습니까?[Y/N]");
		String yOrN = dataValidation(clientInputReader.readLine().trim(), "String");
		if(yOrN.equals("Y")) {
			try {
				System.out.println(employee.permitUpdate(contract));
			} catch (NotFoundProfileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//// 고객 DB 서비스 카테고리 - 입수한 고객정보를 DB에 반영한다.
	private void showAllPaymentList() {
		if (!TokenManager.isValidToken(token)) {
			System.out.println("[error] please login first.");
			return;
		}
		int index = 1;
		ArrayList<Payment> paymentList = paymentListImpl.retrieveAll();
		if(paymentList.size() == 0) {
			System.out.println("No payment");
			return;
		}
		System.out.println("-- Payment List --");
		for(Payment payment : paymentList) {
			System.out.println(index + ". PaymentID: " + payment.getPaymentID() + " ContractID: " + payment.getContractID()+ " CustomerID: " + payment.getCustomerID()+" Status: "+payment.isPaymentProcessed());
			index++;
		}
	}
	private void showEmployeeList() {
		int index = 1;
		System.out.println();
		System.out.println("-- Employee List --");
		for(Employee employee : employeeListImpl.retrieveAll()) {
			System.out.println(index + ". ID: " + employee.getEmployeeID() + " Name: " + employee.getName() + " Gender: " + employee.getGender()+ " Email: " + employee.getEmail()+ " Phone: " + employee.getName()+ " type: " + employee.getType());
			index++;
		}
	}
	private void showAllContractList() {
		int index = 1;
		System.out.println();
		System.out.println("-- Contract List --");
		for(Contract contract : contractListImpl.retrieveAll()) {
			System.out.println(index + ". ContractID: " + contract.getContractID() +  " ContractStatus: " + contract.getContractStatus() +  " ContractCreateDate: " +contract.getCreatedDate()+" InsuranceID: " + contract.getInsuranceID()+ " CustomerID: " + contract.getCustomerID());
			index++;
		}
	}
	private void showCustomerList() {
		int index = 1;
		System.out.println("-- Customer List --");
		for(Customer customer : customerListImpl.retrieveAll()) {
			System.out.println(index + ". ID: " + customer.getCustomerID() + " Name: " + customer.getName());
			index++;
		}
	}
	private void showAllCounselList() {
		if (!TokenManager.isValidToken(token)) {
			System.out.println("[error] please login first.");
			return;
		}
		int index = 1;
		ArrayList<Counsel> counselList = counselListImpl.retrieveAll();
		if(counselList.size() == 0) {
			System.out.println("No counsel");
			return;
		}
		System.out.println("-- Counsel List --");
		for(Counsel counsel : counselList) {
			System.out.println(index + ". CounselID: " + counsel.getCounselID() + " CustomerID: " + counsel.getCustomerID()+ " Status: " + counsel.isConfirmedCounsel());
			index++;
		}
	}
	private void deleteContract() throws IOException {
		if (!TokenManager.isValidToken(token)) {
			System.out.println("[error] please login first.");
			return;
		}
		System.out.println("--Delete Contract Infomation--");
		System.out.print("contractID: "); String contractID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		Employee employee = employeeListImpl.retrieveById(Integer.parseInt(TokenManager.getID(token)));
		boolean response = employee.deleteContract(Integer.parseInt(contractID));
		if (response == true) System.out.println("[success] Successfully deleted this Contract!");
		else System.out.println("[error] The contract id does not exist.");
	}
	private void deleteCounsel() throws IOException {
		if (!TokenManager.isValidToken(token)) {
			System.out.println("[error] please login first.");
			return;
		}
		System.out.println("--Delete Counsel Infomation--");
		System.out.print("Counsel ID: "); String counselID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		
		Customer customer = customerListImpl.retrieveById(Integer.parseInt(TokenManager.getID(token)));
		boolean response = customer.deleteCounsel(Integer.parseInt(counselID));
		if(response) System.out.println("[success] Successfully deleted Counsel!");
		else System.out.println("[error] The counsel ID does not exist.");
		
	}
	//// 보상 카테고리 - 보상 신청, 보상 조회, 보상 리스트 조회, 보상 수정, 보상 삭제, 보험금 청구, 손해 조사, 보험금 산출, 보험금 지급
	private void compensationCategory() throws IOException, AuthenticationException, AuthorizationException, DuplicateIDException, NotFoundProfileException {
		if (customer==null&&employee==null) {
			throw new AuthenticationException();
		}
		while(true) {
			System.out.println("***************** 보상 카테고리 메뉴 *****************");
			System.out.println("1. 보상 신청");
			System.out.println("2. 보상 조회");
			System.out.println("3. 모든 고객의 보상 조회");
			System.out.println("4. 보상 수정");
			System.out.println("5. 보상 삭제");
			System.out.println("6. 보험금 청구");
			System.out.println("7. 손해 조사");
			System.out.println("8. 보험금 산출");
			System.out.println("9. 보험금 지급");
			System.out.println("R. 돌아가기");
			String clientChoice = clientInputReader.readLine().trim();
			if (clientChoice.equals("1")) createCompensation(Constant.Customer);
			else if (clientChoice.equals("2")) showCompensationList();
			else if (clientChoice.equals("3")) showAllCompensationList();
			else if (clientChoice.equals("4")) updateCompensation();
			else if (clientChoice.equals("5")) deleteCompensation();
			else if (clientChoice.equals("6")) requestInsuranceAmount();
			else if (clientChoice.equals("7")) investigateLoss();
			else if (clientChoice.equals("8")) calculateInsuranceAmount();
			else if (clientChoice.equals("9")) giveInsuranceAmount();
			else if (clientChoice.equals("R")) {
				System.out.println("|*** 이전으로 돌아갑니다. ***|");
				return;
			}
			else System.out.println("유효하지 않은 메뉴 번호입니다.");
		}
	}
	// 보상 신청
	private void createCompensation(String usertype) throws IOException, AuthorizationException, DuplicateIDException {
		System.out.println("-- 보상 정보 입력란 --");
		
		// basic attribute settings
		System.out.print("보상ID: "); String compensationID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("계약ID: "); String contractID = dataValidation(clientInputReader.readLine().trim(), "Integer");

		// composition to whole settings
		// Bill setting - 보험금 청구하기 전
		Bill bill = new Bill();
		bill.setBillID(0);
		bill.setBillReason(null);
		bill.setCustomerID(0);
		
		// Loss setting - 손해조사하기 전
		Loss loss = new Loss();
		loss.setLossID(0);
		loss.setAccidentID(0);
		loss.setEmployeeID(0);
		loss.setEmployeeOpinion(null);
		loss.setLossAmount(0);

		// ListImpl Add
		Compensation compensation = new Compensation();
		System.out.println("-- 보상을 신청하시겠습니까?[Y/N] --");
		String save = dataValidation(clientInputReader.readLine().trim(), "String");
		if(save.equals("Y")) {
			compensation.setCompensationID(Integer.parseInt(compensationID));
			compensation.setContractID(Integer.parseInt(contractID));
			compensation.setCustomerID(customer.getCustomerID());
			compensation.setInsuranceAmount(0);

			// composition to whole settings
			compensation.setBill(bill);
			compensation.setLoss(loss);
			
			// Association setting
			customer.setCompensationList(compensationListImpl);
			System.out.println(customer.createCompensation(compensation));
		}else {
			System.out.println("[info] 보상 신청을 취소했습니다. 본 페이지를 다시 출력합니다.");
		}
	}	
	// 보상 조회
	private void showCompensationList() {
		ArrayList<Compensation> compensationList = compensationListImpl.retrieveByCustomerID(customer.getCustomerID());
		int index = 1;	
		if(compensationList.size() == 0) {
			System.out.println("No Compensation");
			return;
		}
		
		System.out.println("-- 보상 리스트 --");
		for(Compensation compensation : compensationList) {
			System.out.println(index + ". 보상ID: " + compensation.getCompensationID() + " 고객ID: " + compensation.getCustomerID() + " 보험금: " + compensation.getInsuranceAmount() + " 계약ID: " + compensation.getContractID() + " 손해ID: " + compensation.getLoss().getLossID() + " 사고ID: " + compensation.getLoss().getAccidentID() + " 직원ID : " + compensation.getLoss().getEmployeeID() + " 직원 의견: " + compensation.getLoss().getEmployeeOpinion() + " 손해액 평가: " + compensation.getLoss().getLossAmount() + " 청구ID: " + compensation.getBill().getBillID() + " 청구 사유: " + compensation.getBill().getBillReason());
			index++;
		}
	}
	// 모든 고객의 보상 조회
	private void showAllCompensationList() throws AuthorizationException {
		if (!employee.getType().equals(Constant.CompensationProcessing)) {
			throw new AuthorizationException();
		}
		int index = 1;
		System.out.println();
		ArrayList<Compensation> compensationList = compensationListImpl.retrieveAll();
		if(compensationList.size() == 0) {
			System.out.println("No Compensation");
			return;
		}
		System.out.println("-- 보상 리스트 --");
		for(Compensation compensation : compensationList) {
			System.out.println(index + ". 보상ID: " + compensation.getCompensationID() + " 고객ID: " + compensation.getCustomerID() + " 보험금: " + compensation.getInsuranceAmount() + " 계약ID: " + compensation.getContractID() + " 손해ID: " + compensation.getLoss().getLossID() + " 사고ID: " + compensation.getLoss().getAccidentID() + " 직원ID : " + compensation.getLoss().getEmployeeID() + " 직원 의견: " + compensation.getLoss().getEmployeeOpinion() + " 손해액 평가: " + compensation.getLoss().getLossAmount() + " 청구ID: " + compensation.getBill().getBillID() + " 청구 사유: " + compensation.getBill().getBillReason());
			index++;
		}
	}
	// 보상 수정
	private void updateCompensation() throws IOException, AuthorizationException, NotFoundProfileException {
		if (!employee.getType().equals(Constant.CompensationProcessing)) {
			throw new AuthorizationException();
		}
	    System.out.println("-- 보상 정보 수정란 --");

	    // get compensationID to update
	    System.out.print("수정할 보상ID: ");
	    String compensationID = dataValidation(clientInputReader.readLine().trim(), "Integer");
	    Compensation compensation = compensationListImpl.retrieveById(Integer.parseInt(compensationID));
	    if (compensation == null) {
	        System.out.println("[error] 해당 보상ID의 보상이 존재하지 않습니다.");
	        return;
	    }

		// new attribute settings
 		System.out.print("계약ID: "); String contractID = dataValidation(clientInputReader.readLine().trim(), "Integer");
 		System.out.print("고객ID: "); String customerID = dataValidation(clientInputReader.readLine().trim(), "Integer");
 		System.out.print("보험금: "); String insuranceAmount = dataValidation(clientInputReader.readLine().trim(), "Integer");

 		// composition to whole settings
 		System.out.println("-- 청구 정보 입력란 --");
 		System.out.print("청구ID: "); String billID = dataValidation(clientInputReader.readLine().trim(), "Integer");
 		System.out.print("청구 사유: "); String billReason = dataValidation(clientInputReader.readLine().trim(), "String");
 		
 		Bill bill = new Bill();
 		bill.setBillID(Integer.parseInt(billID));
 		bill.setBillReason(billReason);
 		
		System.out.println("-- 손해 조사 정보 입력란 --");
 		System.out.print("손해조사ID: "); String lossID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("사고ID: "); String accidentID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("직원 의견: "); String employeeOpinion = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("손해액 평가 : "); String lossAmount = dataValidation(clientInputReader.readLine().trim(), "Integer");
 		
		Loss loss = new Loss();
		loss.setLossID(Integer.parseInt(lossID));
		loss.setAccidentID(Integer.parseInt(accidentID));
		loss.setEmployeeID(employee.getEmployeeID());
		loss.setEmployeeOpinion(employeeOpinion);
		loss.setLossAmount(Integer.parseInt(lossAmount));
		
	    System.out.println("-- 보상을 수정하시겠습니까?[Y/N] --");
	    String save = dataValidation(clientInputReader.readLine().trim(), "String");
	    if (save.equalsIgnoreCase("Y")) {
			compensation.setContractID(Integer.parseInt(contractID));
			compensation.setCustomerID(Integer.parseInt(customerID));
			compensation.setInsuranceAmount(Integer.parseInt(insuranceAmount));
			
			// composition to whole settings
			compensation.setBill(bill);
			compensation.setLoss(loss);
			
			// Association setting
			customer.setCompensationList(compensationListImpl);
			System.out.println(customer.updateCompensation(compensation, Integer.parseInt(compensationID)));
	    } else {
	        System.out.println("[info] 보상 수정을 취소했습니다. 본 페이지를 다시 출력합니다.");
	    }
	}
	// 보상 삭제
	private void deleteCompensation() throws IOException, AuthorizationException, NotFoundProfileException {
		if (!employee.getType().equals(Constant.CompensationProcessing)) {
			throw new AuthorizationException();
		}
		System.out.println("-- 보상 정보 입력란 --");
		System.out.print("보상ID: "); String compensationID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		
		// Association setting
		employee.setCompensationList(compensationListImpl);
		System.out.println(employee.deleteCompensation(Integer.parseInt(compensationID)));
	}
	// 보험금 청구
	private void requestInsuranceAmount() throws IOException, DuplicateIDException, NotFoundProfileException {
		System.out.print("보험금 청구할 보상ID: ");
	    String compensationID = dataValidation(clientInputReader.readLine().trim(), "Integer");
	    Compensation compensation = compensationListImpl.retrieveById(Integer.parseInt(compensationID));
	    if (compensation == null) {
	    	throw new NotFoundProfileException("[Exception] 해당 보상ID의 보상이 존재하지 않습니다. 다시 시도해주세요.");
	    }

		System.out.println("-- 청구 정보 입력란 --");
		System.out.print("청구ID: "); String billID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("청구 사유: "); String billReason = dataValidation(clientInputReader.readLine().trim(), "String");
		
		System.out.println("-- 보험금 청구를 신청하시겠습니까?[Y/N] --");
		String save = dataValidation(clientInputReader.readLine().trim(), "String");
		if(save.equals("Y")) {
			Bill bill = new Bill();
			bill.setBillID(Integer.parseInt(billID));
			bill.setBillReason(billReason);
			
			// composition to whole settings
			compensation.setBill(bill);
			
			// Association setting
			customer.setCompensationList(compensationListImpl);
			boolean response = customer.createBill(compensation, Integer.parseInt(compensationID));
			if(!response) throw new DuplicateIDException();
			else System.out.println("[success] 보험금 청구 신청이 완료되었습니다.");
		}else {
			System.out.println("[info] 보험금 청구 신청을 취소했습니다. 본 페이지를 다시 출력합니다.");
		}
	}
  	// 손해 조사
	private void investigateLoss() throws IOException, AuthorizationException, DuplicateIDException, NotFoundProfileException {
		if (!employee.getType().equals(Constant.CompensationProcessing)) {
			throw new AuthorizationException();
		}
		
		System.out.print("손해 조사할 보상ID: ");
	    String compensationID = dataValidation(clientInputReader.readLine().trim(), "Integer");
	    Compensation compensation = compensationListImpl.retrieveById(Integer.parseInt(compensationID));
	    if (compensation == null) {
        	throw new NotFoundProfileException("[Exception] 해당 보상ID가 존재하지 않습니다. 다시 시도해주세요.");
	    }

		System.out.println("-- 손해 조사 정보 입력란 --");
		System.out.print("손해조사ID: "); String lossID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("사고ID: "); String accidentID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		System.out.print("직원 의견: "); String employeeOpinion = dataValidation(clientInputReader.readLine().trim(), "String");
		System.out.print("손해액 평가 : "); String lossAmount = dataValidation(clientInputReader.readLine().trim(), "Integer");

		System.out.println("-- 손해 조사를 진행하시겠습니까?[Y/N] --");
		String save = dataValidation(clientInputReader.readLine().trim(), "String");
		if(save.equals("Y")) {
			Loss loss = new Loss();
			loss.setLossID(Integer.parseInt(lossID));
			loss.setAccidentID(Integer.parseInt(accidentID));
			loss.setEmployeeID(employee.getEmployeeID());
			loss.setEmployeeOpinion(employeeOpinion);
			loss.setLossAmount(Integer.parseInt(lossAmount));

			// composition to whole settings
			compensation.setLoss(loss);
			
			// Association setting
			employee.setCompensationList(compensationListImpl);
			boolean response = employee.createLoss(compensation, Integer.parseInt(compensationID));
			if(!response) throw new DuplicateIDException();
			else System.out.println("[success] 손해조사가 완료되었습니다.");
		} else {
			System.out.println("[info] 손해조사를 취소했습니다. 본 페이지를 다시 출력합니다.");
		}
	}
	// 보험금 산출
	private void calculateInsuranceAmount () throws IOException, AuthorizationException, NotFoundProfileException {
		if (!employee.getType().equals(Constant.CompensationProcessing)) {
			throw new AuthorizationException();
		}
		
		System.out.print("보험금 산출할 보상ID: ");
	    String compensationID = dataValidation(clientInputReader.readLine().trim(), "Integer");
	    Compensation compensation = compensationListImpl.retrieveById(Integer.parseInt(compensationID));
	    if (compensation == null) {
        	throw new NotFoundProfileException("[Exception] 해당 보상ID가 존재하지 않습니다. 다시 시도해주세요.");
	    }
	    
	    System.out.println("-- 손해 조사 내용을 불러와 보험금 산출을 진행하시겠습니까?[Y/N] --");
		String save = dataValidation(clientInputReader.readLine().trim(), "String");
		if(save.equals("Y")) {
			int calculatedInsuarnceAmount = compensation.getLoss().getLossAmount();
			compensation.setInsuranceAmount(calculatedInsuarnceAmount);
			
			// Association setting
			employee.setCompensationList(compensationListImpl);
			boolean response = employee.calculateInsuranceAmount(compensation, Integer.parseInt(compensationID));
			if(!response) throw new NotFoundProfileException("[Exception] 오류로 인해 보험금 산출이 중지되었습니다. 다시 시도해주세요.");
			else System.out.println("[success] 보험금 산출이 완료되었습니다.");
		} else {
			System.out.println("[info] 보험금 산출을 취소했습니다. 본 페이지를 다시 출력합니다.");
		}
		
	}
	// 보험금 지급
	private void giveInsuranceAmount() throws IOException, AuthorizationException, NotFoundProfileException {
		if (!employee.getType().equals(Constant.CompensationProcessing)) {
			throw new AuthorizationException();
		}
		
		System.out.print("보험금을 지급할 보상ID: ");
	    String compensationID = dataValidation(clientInputReader.readLine().trim(), "Integer");
	    Compensation compensation = compensationListImpl.retrieveById(Integer.parseInt(compensationID));
	    if (compensation == null) {
        	throw new NotFoundProfileException("[Exception] 해당 보상ID가 존재하지 않습니다. 다시 시도해주세요.");
	    }
	    
	    System.out.println("-- 보상 내용을 불러와 보험금 지급을 진행하시겠습니까?[Y/N] --");
		String save = dataValidation(clientInputReader.readLine().trim(), "String");
		if(save.equals("Y")) {
			System.out.println("[success] 보험금 지급이 완료되었습니다.");
			System.out.println("고객ID:  " + compensation.getCustomerID());
			System.out.println("보험금: " + compensation.getInsuranceAmount());			
		} else {
			System.out.println("[info] 보험금 지급을 취소했습니다. 본 페이지를 다시 출력합니다.");
		}	
	}
	// -------------------------------------------------------------
	private void deleteInsurance() throws IOException {
		if (!TokenManager.isValidToken(token)) {
			System.out.println("[error] please login first.");
			return;
		}
		System.out.println("--Delete Insurance Infomation--");
		System.out.print("Insurance ID: "); String insuranceID = dataValidation(clientInputReader.readLine().trim(), "Integer");
		try {
			System.out.println(employee.deleteInsurance(Integer.parseInt(insuranceID)));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateIDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void login(String userType) throws IOException, NotFoundProfileException {
		System.out.println("-- 로그인 정보 입력란 --");
		if (userType.equals(Constant.Customer)) {
			System.out.print("ID: "); String ID = dataValidation(clientInputReader.readLine().trim(), "Integer");
			System.out.print("PW: "); String PW = dataValidation(clientInputReader.readLine().trim(), "String");
			for(Customer customer : customerListImpl.retrieveAll()) {
				String customerID = Integer.toString(customer.getCustomerID());
				String customerPW = customer.getCustomerPW();
				if (customerID.equals(ID) && customerPW.equals(PW)) {
					ISMain.customer = customer;
					System.out.println("[success] " + customer.getCustomerID()+"님, 환영합니다. 로그인이 완료되었습니다.");
					return;
				}
			}
			throw new NotFoundProfileException();
		} else if (userType.equals(Constant.Employee)) {
			System.out.print("ID: "); String ID = dataValidation(clientInputReader.readLine().trim(), "Integer");
			System.out.print("PW: "); String PW = dataValidation(clientInputReader.readLine().trim(), "String");
			for(Employee employee : employeeListImpl.retrieveAll()) {
				String employeeID = Integer.toString(employee.getEmployeeID());
				String employeePW = employee.getEmployeePW();
				if (employeeID.equals(ID) && employeePW.equals(PW)) {
					ISMain.employee = employee;
					System.out.println("[success] " + employee.getEmployeeID()+"님, 환영합니다. 로그인이 완료되었습니다.");
					return;
				}
			}
			throw new NotFoundProfileException();
		}
	}
	private void signUp(String userType) throws IOException, DuplicateIDException {
		if (userType.equals(Constant.Customer)) {
			System.out.println("-- 회원가입 정보 입력란 --");
			
			// basic attribute settings
			System.out.print("이름: "); String name = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("ID: "); String customerID = dataValidation(clientInputReader.readLine().trim(), "Integer");
			System.out.print("PW: "); String customerPW = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("계좌: "); String account = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("주소: "); String address = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("나이: "); String age = dataValidation(clientInputReader.readLine().trim(), "Integer");
			System.out.print("생일: "); String birthDate = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("이메일: "); String email = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("성별: enter [M/W]"); String gender = dataValidation(clientInputReader.readLine().trim(), "gender");
			System.out.print("키: "); String height = dataValidation(clientInputReader.readLine().trim(), "Integer");
			System.out.print("직업: "); String job = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("폰번호: "); String phone = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("무게: "); String weight = dataValidation(clientInputReader.readLine().trim(), "Integer");
			
			// composition to whole settings
			// MedicalHistorys
			MedicalHistory medicalHistory = new MedicalHistory();
			ArrayList<String> diseases = new ArrayList<>();
			System.out.print("치료기간: "); String curePeriod = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("치료유무: enter [Y/N]"); String result = dataValidation(clientInputReader.readLine().trim(), "boolean");
			boolean isCured;
			if (result.equals("Y")) isCured = true;
			else isCured = false;
			System.out.print("질병: "); String diseasesString = dataValidation(clientInputReader.readLine().trim(), "multiValue");
			String[] diseasesStrings = diseasesString.split(" ");
			for (String disease : diseasesStrings) {
				diseases.add(disease);
			}
			medicalHistory.setCured(isCured);
			medicalHistory.setCurePeriod(curePeriod);
			medicalHistory.setDiseases(diseases);
			
			// ListImpl Add
			Customer customer = new Customer();
			customer.setAccount(account);
			customer.setAddress(address);
			customer.setAge(Integer.parseInt(age));
			customer.setBirthDate(birthDate);
			customer.setCustomerID(Integer.parseInt(customerID));
			customer.setCustomerPW(customerPW);
			customer.setEmail(email);
			customer.setGender(gender);
			customer.setHeight(Integer.parseInt(height));
			customer.setJob(job);
			customer.setMedicalHistory(medicalHistory);
			customer.setName(name);
			customer.setPhone(phone);
			customer.setWeight(Integer.parseInt(weight));
			
			// association setting
			customer.setCounselList(counselListImpl);
			customer.setPaymentList(paymentListImpl);
			customer.setInsuranceList(insuranceListImpl);
			System.out.println(customerListImpl.add(customer));	
		} else if (userType.equals(Constant.Employee)) {
			System.out.println("--SignUp Infomation--");
			
			// basic attribute settings
			System.out.print("이름: "); String name = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("ID: "); String employeeID = dataValidation(clientInputReader.readLine().trim(), "Integer");
			System.out.print("PW: "); String employeePW = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("폰번호: "); String phone = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("이메일: "); String email = dataValidation(clientInputReader.readLine().trim(), "String");
			System.out.print("성별: enter [M/W]"); String gender = dataValidation(clientInputReader.readLine().trim(), "gender");
			System.out.print("직원 타입: enter [ \"S\" / \"UW\" / \"CI\" / \"CP\" ]");
			System.out.print("S = 영업활동자, UW = UW팀, CI = 고객정보관리자, CP = CompensationProcessing");
			String type = dataValidation(clientInputReader.readLine().trim(), "type");
			
			// ListImpl Add
			Employee employee = new Employee();
			employee.setName(name);
			employee.setEmployeeID(Integer.parseInt(employeeID));
			employee.setEmployeePW(employeePW);
			employee.setPhone(phone);
			employee.setEmail(email);
			employee.setGender(gender);
			employee.setType(type);
			
			// association setting
			employee.setRuleList(ruleListImpl);
			employee.setContractList(contractListImpl);
			employee.setInsuranceList(insuranceListImpl);
			employee.setPaymentList(paymentListImpl);
			employee.setCounselList(counselListImpl);
			employee.setCustomerList(customerListImpl);
			System.out.println(employeeListImpl.add(employee));
		}
	}
	private void logout() {
		employee = null;
		customer = null;
		System.out.println("[success] 로그아웃 되었습니다. 본 페이지로 돌아갑니다.");
	}
	private void deleteMembership(String userType) throws IOException {
		if (!TokenManager.isValidToken(token)) {
			System.out.println("[error] 로그인을 먼저 진행해주세요.");
			return;
		}
		System.out.println("-- 회원탈퇴 정보 --");
		System.out.print("회원탈퇴를 진행하시겠습니까? [Y/N] : "); String result = dataValidation(clientInputReader.readLine().trim(), "boolean");
		if (result.equals("Y")) {
			if (userType.equals(Constant.Customer)) System.out.println(customerListImpl.deleteById(Integer.parseInt(TokenManager.getID(token))));
			else if (userType.equals(Constant.Employee)) System.out.println(employeeListImpl.deleteById(Integer.parseInt(TokenManager.getID(token))));;
			TokenManager.invalidateToken(token);
		} else System.out.println("[error] 회원탈퇴를 진행하지 않았습니다. 본 페이지로 돌아갑니다.");
	}
	private String dataValidation(String inputData, String type) throws IOException {
	    while (true) {
	        if (isValidInput(inputData, type)) return inputData;
	        else if ("Integer".equals(type)) System.out.println("[error] you must enter only numbers.\n re-enter: ");
	        else if ("Double".equals(type)) System.out.println("[error] you must enter a decimal number.\n re-enter: ");
	        else if ("multiValue".equals(type)) System.out.println("[error] you must enter null or have only String values However, there cannot be more than two consecutive blank spaces..\n re-enter: ");
	        else if("boolean".equals(type)) System.out.println("[error] you must enter only \"S\" / \"UW\" / \"CI\" / \"CP\" \n re-enter: ");
	        else if("type".equals(type)) System.out.println("[error] you must enter only 'Y' / 'N' \n re-enter: ");
	        else if("gender".equals(type)) System.out.println("[error] you must enter only 'M' / 'W' \n re-enter: ");
	        else System.out.println("[error] You can't enter spaces or empty values. \n re-enter: ");
	        inputData = clientInputReader.readLine().trim();
	    }
	}
	private boolean isValidInput(String inputData, String type) {
			if ("multiValue".equals(type)) {
				if (inputData.replaceAll("\\s+", "").isEmpty()) return true;
				else if(inputData.matches(".*\\s{2,}.*")) return false;
			    return true;
			} else if ("Integer".equals(type)) {
				if (inputData.matches("\\d+")) return true;
	            else return false;
			} else if ("Double".equals(type)) {
			    if (inputData.matches("\\d*\\.\\d+")) return true;
			    else return false;				
		    } else if ("boolean".equals(type)) {
				if (inputData.equals("Y") || inputData.equals("N")) return true;
	            else return false;
		} else if ("type".equals(type)) {
			if (inputData.equals(Constant.Sales) || inputData.equals(Constant.UnderWriting) || inputData.equals(Constant.CutomerInfomationManage) || inputData.equals(Constant.CompensationProcessing)) return true;
            else return false;
		} else if ("gender".equals(type)) {
			if (inputData.equals("M") || inputData.equals("W")) return true;
            else return false;
		}
		if (inputData.trim().isEmpty() || inputData.contains(" ")) return false;
	    return true;
	}
}