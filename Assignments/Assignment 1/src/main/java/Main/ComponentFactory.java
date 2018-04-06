package Main;

import database.DBConnectionFactory;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.employee.EmployeeRepository;
import repository.employee.EmployeeRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.client.ClientService;
import service.client.ClientServiceMySQL;
import service.employee.EmployeeService;
import service.employee.EmployeeServiceMySQL;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;

import java.sql.Connection;

/**
 * Created by Alex on 18/03/2017.
 */
public class ComponentFactory {

    private final AuthenticationService authenticationService;
    private final ClientService clientService;
    private final EmployeeService employeeService;

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    private static ComponentFactory instance;

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.rightsRolesRepository);
        this.accountRepository = new AccountRepositoryMySQL(connection);
        this.clientRepository = new ClientRepositoryMySQL(connection);
        this.clientService = new ClientServiceMySQL(this.clientRepository);
        this.employeeRepository = new EmployeeRepositoryMySQL(connection);
        this.employeeService = new EmployeeServiceMySQL(this.employeeRepository);
    }

    public ClientService getClientService() {
        return clientService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }
}
