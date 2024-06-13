package domain;


/**
 * @author Win10
 * @version 1.0
 * @created 16-5-2024 오후 6:54:56
 */
public class InternationalTravel extends Insurance {

	private String travelCountry;
	private int travelPeriod;

	public InternationalTravel(){

	}

	public String getTravelCountry() {
		return travelCountry;
	}

	public void setTravelCountry(String travelCountry) {
		this.travelCountry = travelCountry;
	}

	public int getTravelPeriod() {
		return travelPeriod;
	}

	public void setTravelPeriod(int travelPeriod) {
		this.travelPeriod = travelPeriod;
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}