package IF;

import java.util.ArrayList;

import domain.Compensation;

public interface CompensationList {
	public boolean add(Compensation compensation);
	public boolean deleteById(int compensationID);
	public Compensation retrieveById(int compensationID);
	public ArrayList<Compensation> retrieveByCustomerID(int customerID);
	public ArrayList<Compensation> retrieveAll();
	public boolean update(Compensation compensation, int compensationID);
}