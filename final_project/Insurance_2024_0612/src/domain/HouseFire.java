package domain;

public class HouseFire extends Insurance {

	private String categoryOfHouse;
	private int priceOfHouse;

	public HouseFire(){}

	public void finalize() throws Throwable {super.finalize();}

	// get & set
	public String getCategoryOfHouse() {return categoryOfHouse;}
	public void setCategoryOfHouse(String categoryOfHouse) {this.categoryOfHouse = categoryOfHouse;}
	public int getPriceOfHouse() {return priceOfHouse;}
	public void setPriceOfHouse(int priceOfHouse) {this.priceOfHouse = priceOfHouse;}

}