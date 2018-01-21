package salary.calc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import salary.calc.service.EmployeeService;
import salary.calc.validator.EmployeeValidator;
import salary.calc.domain.Employee;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sc")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private EmployeeValidator employeeValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(employeeValidator);
    }

    @GetMapping("/emp")
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/emp/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId) {
        if (employeeService.isEmployeePresent(employeeId)) {
            return new ResponseEntity<>(employeeService.getEmployeeById(employeeId).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/emp")
    public void addEmployee(@RequestBody @Valid Employee employee) {
        employeeService.addEmployee(employee);
    }

    @DeleteMapping("/emp/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @PutMapping("/emp/{employeeId}")
    public void updateEmployee(@PathVariable long employeeId, @RequestBody @Valid Employee employee) {
        employeeService.updateEmployee(employeeId, employee);
    }
}
