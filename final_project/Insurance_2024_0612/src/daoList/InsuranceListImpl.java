
package daoList;

import java.util.ArrayList;

import IF.InsuranceList;
import domain.Insurance;

public class InsuranceListImpl implements InsuranceList {

	private ArrayList<Insurance> insuranceList;
	public Insurance m_Insurance;

	public InsuranceListImpl(){
		this.insuranceList = new ArrayList<>();
	}

	public void finalize() throws Throwable {

	}

	public boolean add(Insurance insurance){
		for (Insurance i: insuranceList) {
			if (i.getInsuranceID() == insurance.getInsuranceID())
				return false;
		}
		this.insuranceList.add(insurance);
		return true;
	}

	public boolean delete(int insuranceID) {
		for (Insurance insurance : insuranceList) {
			if (insurance.getInsuranceID() == insuranceID) {
				insuranceList.remove(insurance);
				return true;
			}
		}
		return false;
	}

	public Insurance retrieve(int insuranceID) {
		for (Insurance insurance: insuranceList) {
			if (insurance.getInsuranceID() == insuranceID) {
				return insurance;
			}
		}
		return null;
	}
	
	public ArrayList<Insurance> retrieveAll(){
		return this.insuranceList;
	}

	public boolean update(int insuranceID, Insurance insurance){
		return false;
	}

	public ArrayList<Insurance> retrieveTypeAll(String clientChoice) {
		ArrayList<Insurance> insuranceTypeList = new ArrayList<>();
		for (Insurance insurance: insuranceList) {
			if (insurance.getCategory().equals(clientChoice)) {
				insuranceTypeList.add(insurance);
			}
		}
		return insuranceTypeList;
	}
}