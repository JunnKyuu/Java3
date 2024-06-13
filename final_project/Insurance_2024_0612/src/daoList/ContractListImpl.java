
package daoList;
import java.util.ArrayList;

import IF.ContractList;
import constant.Constant;
import domain.Contract;
public class ContractListImpl implements ContractList {
	private ArrayList<Contract> ContractList;
	public Contract m_Contract;
	public ContractListImpl(){
		ContractList = new ArrayList<>();
	}
	public void finalize() throws Throwable {

	}
	public ArrayList<Contract> retrieveAll(){
		return ContractList;
	}
	public boolean add(Contract Contract){
		for (Contract contract : ContractList) {
			if (contract.getContractID() == Contract.getContractID()) {
				return false;
			}
		}
		this.ContractList.add(Contract);
		return true;
	}
	public boolean deleteById(int id){
		for (Contract Contract : ContractList) {
			if (Contract.getContractID() == id) {
				ContractList.remove(Contract);
				return true;
			}
		}
		return false;
	}
	public Contract retrieveById(int id){
		for (Contract Contract : ContractList) {
			if (Contract.getContractID() == id) {
				return Contract;
			}
		}
		return null;
	}
	public void update(int id, Contract Contract){
		int index =ContractList.indexOf(Contract);
		ContractList.set(index, Contract);
	}
	@Override
	public ArrayList<Contract> retrieveByContractStatus(String contractStatus) {
		ArrayList<Contract> contractListByStatus = new ArrayList<>();
		for (Contract Contract : ContractList) {
			if (Contract.getContractStatus().equals(contractStatus)) {
				contractListByStatus.add(Contract);
			}
		}
		return contractListByStatus;
	}
	public ArrayList<Contract> retrieveByCustomerId(int customerId) {
		ArrayList<Contract> customerContractList = new ArrayList<>();
		for (Contract Contract : ContractList) {
			if (Contract.getCustomerID() == customerId) {
				customerContractList.add(Contract);
			}
		}
		return customerContractList;
  }
	@Override
	public boolean contains(Contract contract) {
		return ContractList.contains(contract);
	}

	public ArrayList<Contract> retrieveRequestedContractList(int customerId) {
		ArrayList<Contract> requestedContractList = new ArrayList<>();
		for(Contract contract: retrieveByCustomerId(customerId)){
			if(contract.getContractStatus() == Constant.contractStatus1)
				requestedContractList.add(contract);
		}
		return requestedContractList;
	}
}