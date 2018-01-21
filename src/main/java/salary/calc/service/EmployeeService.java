package salary.calc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import salary.calc.dao.EmployeeDao;
import salary.calc.domain.Employee;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    public Optional<Employee> getEmployeeById(long id) {
        return employeeDao.getEmployeeById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    public void deleteEmployee(long id) {
        employeeDao.deleteEmployee(id);
    }

    public void updateEmployee(long id, Employee employee) {
        employeeDao.updateEmployee(id, employee);
    }

    public boolean isEmployeePresent(long id) {
        return employeeDao.getEmployeeById(id).isPresent();
    }
}
