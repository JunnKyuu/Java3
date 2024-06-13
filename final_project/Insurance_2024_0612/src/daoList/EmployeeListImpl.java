
package daoList;
import java.util.ArrayList;
import IF.EmployeeList;
import domain.Customer;
import domain.Employee;
import exception.DuplicateIDException;
public class EmployeeListImpl implements EmployeeList {
	private ArrayList<Employee> EmployeeList;
	public Employee m_Employee;
	public EmployeeListImpl(){
		EmployeeList = new ArrayList<>();
	}
	public void finalize() throws Throwable {
	}
	public ArrayList<Employee> retrieveAll(){
		return EmployeeList;
	}
	public String add(Employee Employee) throws DuplicateIDException{
		for (Employee employee : EmployeeList) {
			if (employee.getEmployeeID() == Employee.getEmployeeID()) {
				throw new DuplicateIDException();
			}
		}
		this.EmployeeList.add(Employee);
		return "[success] 회원가입에 성공하였습니다.";
	}
	public String deleteById(int id){
		Employee deleteEmployee = null;
		for (Employee employee : EmployeeList) {
			if (employee.getEmployeeID() == id) {
				deleteEmployee = employee;
			}
		}
		if (deleteEmployee==null) {
			return "[error] 해당 id의 직원정보가 존재하기 않습니다.";
		}
		EmployeeList.remove(deleteEmployee);
		return "[success] 해당 직원정보가 삭제되었습니다.";
	}
	public Employee retrieveById(int id){
		for (Employee employee : EmployeeList) {
			if (employee.getEmployeeID() == id) {
				return employee;
			}
		}
		return null;
	}
	public void update(int id, Employee Employee){

	}
}