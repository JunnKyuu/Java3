package domain;


/**
 * @author Win10
 * @version 1.0
 * @created 16-5-2024 오후 6:54:55
 */
public class CancerHealth extends Insurance {

	private String categoryOfCancer;

	public CancerHealth(){

	}

	public String getCategoryOfCancer() {
		return categoryOfCancer;
	}

	public void setCategoryOfCancer(String categoryOfCancer) {
		this.categoryOfCancer = categoryOfCancer;
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}