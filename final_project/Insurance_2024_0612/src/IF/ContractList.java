package IF;
import java.util.ArrayList;
import domain.Contract;
public interface ContractList {
	public boolean add(Contract Contract);
	public boolean deleteById(int id);
	public Contract retrieveById(int id);
	public ArrayList<Contract> retrieveAll();
	public void update(int id, Contract Contract);
	public ArrayList<Contract> retrieveByContractStatus(String contractStatus);
	public ArrayList<Contract> retrieveByCustomerId(int customerId);
	public boolean contains(Contract contract);
	public ArrayList<Contract> retrieveRequestedContractList(int customerId);
}