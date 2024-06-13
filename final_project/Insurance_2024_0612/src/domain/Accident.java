package domain;

public class Accident {
	private int customerID;
	private int accidentID;
	private String accidentDate;
	private String accidentLocation;
	private String accidentType;
	private String carInformation;
	private int carNumber;
	
	public Accident(){}
	public void finalize() throws Throwable {}
	
	// get&set
	public String getAccidentDate() { return accidentDate; }
	public void setAccidentDate(String accidentDate) { this.accidentDate = accidentDate; }
	public int getAccidentID() { return accidentID; }
	public void setAccidentID(int accidentID) { this.accidentID = accidentID; }
	public String getAccidentLocation() { return accidentLocation; }
	public void setAccidentLocation(String accidentLocation) { this.accidentLocation = accidentLocation; }
	public String getAccidentType() { return accidentType; }
	public void setAccidentType(String accidentType) { this.accidentType = accidentType; }
	public String getCarInformation() { return carInformation; }
	public void setCarInformation(String carInformation) { this.carInformation = carInformation; }
	public int getCarNumber() { return carNumber; }
	public void setCarNumber(int carNumber) { this.carNumber = carNumber; }
	public int getCustomerID() { return customerID; }
	public void setCustomerID(int customerID) { this.customerID = customerID; }
}