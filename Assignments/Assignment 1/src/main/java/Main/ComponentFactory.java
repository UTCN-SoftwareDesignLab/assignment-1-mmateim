package Main;

import database.DBConnectionFactory;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.activity.ActivityRepository;
import repository.activity.ActivityRepositoryMySQL;
import repository.bill.BillRepository;
import repository.bill.BillRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.activity.ActivityService;
import service.activity.ActivityServiceImpl;
import service.bill.BillService;
import service.bill.BillServiceImpl;
import service.client.ClientService;
import service.client.ClientServiceImpl;
import service.user.AuthenticationService;
import service.user.AuthenticationImpl;
import service.user.UserServiceImpl;

import java.sql.Connection;

/**
 * Created by Alex on 18/03/2017.
 */
public class ComponentFactory {

    private final AuthenticationService authenticationService;
    private final ClientService clientService;
    private final UserServiceImpl userService;
    private final AccountService accountService;
    private final BillService billService;
    private final ActivityService activityService;

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final BillRepository billRepository;
    private final ActivityRepository activityRepository;

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
        this.authenticationService = new AuthenticationImpl(this.userRepository, this.rightsRolesRepository);
        this.accountRepository = new AccountRepositoryMySQL(connection);
        this.clientRepository = new ClientRepositoryMySQL(connection);
        this.clientService = new ClientServiceImpl(this.clientRepository);
        this.userService = new UserServiceImpl(this.userRepository, this.rightsRolesRepository);
        this.accountService = new AccountServiceImpl(this.accountRepository, this.userRepository);
        this.billRepository = new BillRepositoryMySQL(connection);
        this.billService = new BillServiceImpl(this.billRepository);
        this.activityRepository = new ActivityRepositoryMySQL(connection);
        this.activityService = new ActivityServiceImpl(this.activityRepository);
    }

    public ActivityService getActivityService() {
        return activityService;
    }

    public BillService getBillService() {
        return billService;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public ClientService getClientService() {
        return clientService;
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
