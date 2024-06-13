package IF;

import java.util.ArrayList;

import domain.Insurance;

public interface InsuranceList {

	public boolean add(Insurance insurance);
	public boolean delete(int insuranceID);
	public Insurance retrieve(int insuranceID);
	public boolean update(int insuranceID, Insurance insurance);
	public ArrayList<Insurance> retrieveAll();
	public ArrayList<Insurance> retrieveTypeAll(String clientChoice);

}