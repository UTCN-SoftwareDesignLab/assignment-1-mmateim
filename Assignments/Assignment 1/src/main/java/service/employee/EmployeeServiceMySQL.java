package service.employee;

import repository.employee.EmployeeRepository;

public class EmployeeServiceMySQL implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceMySQL(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}
