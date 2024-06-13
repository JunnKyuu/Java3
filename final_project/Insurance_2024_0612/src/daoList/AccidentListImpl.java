
package daoList;

import java.util.ArrayList;

import IF.AccidentList;
import domain.Accident;
import domain.Contract;
import domain.Counsel;
import domain.Payment;

public class AccidentListImpl implements AccidentList {

	private ArrayList<Accident> accidentList;
	public Accident m_Accident;

	public AccidentListImpl() {
		this.accidentList = new ArrayList<Accident>();

	}

	public void finalize() throws Throwable {

	}

	public boolean add(Accident accident) {
		for (Accident ac : accidentList) {
			if (ac.getAccidentID() == accident.getAccidentID()) {
				return false;
			}
		}
		this.accidentList.add(accident);
		return true;
	}
	
	public boolean deleteById(int accidentID){
		Accident deletedAccident = new Accident();
		for (Accident accident : accidentList) {
			if (accident.getAccidentID() == accidentID) {
				deletedAccident = accident;
			}
		}
		return accidentList.remove(deletedAccident);
	}

	public Accident retrieveById(int accidentID){
		for (Accident accident : accidentList) {
			if (accident.getAccidentID() == accidentID) {
				return accident;
			}
		}
		return null;
	}

	public ArrayList<Accident> retrieveByCustomerId(int customerID){
		ArrayList<Accident> customerAccident = new ArrayList<Accident>();
		for (Accident accident: accidentList) {
			if (accident.getCustomerID() == customerID) {
				customerAccident.add(accident);
			}
		}
		return customerAccident;
	}
	
	public ArrayList<Accident> retrieveAll(){
		return this.accidentList;
	}

    public boolean update(int accidentId, Accident updatedAccident) {
        for (int i = 0; i < accidentList.size(); i++) {
            Accident accident = accidentList.get(i);
            if (accident.getAccidentID() == accidentId) {
                accidentList.set(i, updatedAccident);
                return true;
            }
        }
        return false;
    }
}