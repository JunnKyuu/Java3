package IF;
import java.util.ArrayList;
import domain.Employee;
import exception.DuplicateIDException;
public interface EmployeeList {
	public String add(Employee Employee) throws DuplicateIDException;
	public String deleteById(int id);
	public Employee retrieveById(int id);
	public ArrayList<Employee> retrieveAll();
	public void update(int id, Employee Employee);
}