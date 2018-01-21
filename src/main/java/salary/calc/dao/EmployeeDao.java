package salary.calc.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import salary.calc.domain.Employee;

import java.util.List;
import java.util.Optional;

import static org.jooq.util.maven.example.Tables.EMPLOYEE;

@Repository
public class EmployeeDao {

    @Autowired
    DSLContext dslContext;

    public List<Employee> getAllEmployees() {
        return dslContext.selectFrom(EMPLOYEE).fetchInto(Employee.class);
    }

    public Optional<Employee> getEmployeeById(long id) {
        return dslContext.selectFrom(EMPLOYEE)
                .where(EMPLOYEE.ID.eq(id))
                .fetchOptionalInto(Employee.class);
    }

    public void addEmployee(Employee employee) {
        dslContext.insertInto(EMPLOYEE, EMPLOYEE.NAME, EMPLOYEE.HOURLY_WAGE, EMPLOYEE.TAX_RATE)
                .values(employee.getName(), employee.getHourlyWage(), employee.getTaxRate())
                .execute();
    }

    public void deleteEmployee(long id) {
        dslContext.deleteFrom(EMPLOYEE)
                .where(EMPLOYEE.ID.eq(id))
                .execute();
    }

    public void updateEmployee(long id, Employee employee) {
        dslContext.update(EMPLOYEE)
                .set(EMPLOYEE.NAME, employee.getName())
                .set(EMPLOYEE.HOURLY_WAGE, employee.getHourlyWage())
                .set(EMPLOYEE.TAX_RATE, employee.getTaxRate())
                .where(EMPLOYEE.ID.eq(id))
                .execute();
    }

}
