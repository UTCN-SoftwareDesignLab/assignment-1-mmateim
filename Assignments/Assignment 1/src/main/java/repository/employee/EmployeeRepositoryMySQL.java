package repository.employee;

import java.sql.Connection;

public class EmployeeRepositoryMySQL implements EmployeeRepository{

    private final Connection connection;

    public EmployeeRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }
}
