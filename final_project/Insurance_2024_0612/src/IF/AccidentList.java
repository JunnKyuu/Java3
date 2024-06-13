package IF;

import java.util.ArrayList;

import domain.Accident;
import domain.Contract;

public interface AccidentList {
	public boolean add(Accident accident);
	public boolean deleteById(int accidentID);
	public Accident retrieveById(int accidentID);
	public ArrayList<Accident> retrieveByCustomerId(int customerId);
	public ArrayList<Accident> retrieveAll();
	public boolean update(int accidentId, Accident accident);
}