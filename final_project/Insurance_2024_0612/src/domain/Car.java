package domain;


/**
 * @author Win10
 * @version 1.0
 * @created 16-5-2024 오후 6:54:55
 */
public class Car extends Insurance {

	private boolean hasBlackbox;
	private String model;
	private int priceOfCar;
	private String VIN;

	public Car(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public boolean isHasBlackbox() {
		return hasBlackbox;
	}

	public void setHasBlackbox(boolean hasBlackbox) {
		this.hasBlackbox = hasBlackbox;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPriceOfCar() {
		return priceOfCar;
	}

	public void setPriceOfCar(int priceOfCar) {
		this.priceOfCar = priceOfCar;
	}

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String vIN) {
		VIN = vIN;
	}

}