package controller;

import Main.ComponentFactory;
import model.User;
import repository.user.UserRepository;
import service.user.UserServiceImpl;
import view.*;

import java.util.Observable;
import java.util.Observer;

import static database.Constants.Controller.*;

public class MainFlowController implements Observer {
    private ComponentFactory componentFactory;
    private MainController mainController;
    private LoginController loginController;
    private AdminActionController adminActionController;
    private ClientInfoController clientInfoController;
    private CRUDActionsController crudActionsController;
    private User currentUser;
    private boolean adminRights;

    public MainFlowController(ComponentFactory componentFactory) {
        this.componentFactory = componentFactory;
        mainController = new MainController(new MainForm());
        mainController.addObserver(this);
        loginController = new LoginController(new LoginView(), componentFactory.getAuthenticationService());
        loginController.addObserver(this);
        adminActionController = new AdminActionController(new AdminChooseAction());
        adminActionController.addObserver(this);
        clientInfoController = new ClientInfoController(new ClientInfoView(), componentFactory.getClientService(), componentFactory.getAccountService(), componentFactory.getBillService(), componentFactory.getActivityService());
        clientInfoController.addObserver(this);
        crudActionsController = new CRUDActionsController(componentFactory.getClientService(), componentFactory.getUserService(), new CRUDActionsView(), componentFactory.getAccountService());
        crudActionsController.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        switch (arg.toString()) {
            case MAINC_CREATE_ACC:
                System.out.println("MAIN form, create account action");
                mainController.setVisible(false);
                loginController.setButtonsVisible(false);
                loginController.setVisible(true);
                break;
            case MAINC_LOG_ACC:
                System.out.println("MAIN form, Log in action");
                mainController.setVisible(false);
                loginController.setButtonsVisible(true);
                loginController.setVisible(true);
                break;
            case LOGINC_REG_SUCC:
                System.out.println("LOGIN form, Successful registration");
                loginController.setVisible(false);
                adminRights = getAdminRights();
                UserServiceImpl userService = componentFactory.getUserService();
                currentUser = userService.findByUsername(loginController.getUsername());
                clientInfoController.setCurrentUser(currentUser);
                if (adminRights) {
                    adminActionController.setVisible(true);
                } else {
                    clientInfoController.setVisible(true);
                }
                break;
            case ADMINACT_CLIENT:
                System.out.println("Admin user chose Client Actions");
                clientInfoController.setVisible(true);
                break;
            case ADMINACT_ADMIN:
                System.out.println("Admin user chose Employee Actions");
                crudActionsController.employeeBootstrap();
                crudActionsController.setVisible(true);
                break;
            case CLIENT_INFO_BACK:
                System.out.println("From ClientViewInfo was chosen BACK");
                clientInfoController.setVisible(false);
                if(!adminRights){
                    mainController.setVisible(true);
                }
                break;
            case CLIENT_INFO_ADVANCED:
                System.out.println("From ClientViewInfo was chosen ADVANCED");
                clientInfoController.setVisible(false);
                crudActionsController.clientBootstrap();
                crudActionsController.setVisible(true);
                break;
            case CRUD_BACK:
                System.out.println("from CRUD window exit");
                crudActionsController.setVisible(false);
                if(!adminRights){
                    clientInfoController.setVisible(true);
                }
                break;
            case CLIENT_INFO_ADVANCED_ACCOUNTS :
                System.out.println("CRUD operations on account");
                clientInfoController.setVisible(false);
                crudActionsController.setVisible(true);
                crudActionsController.accountBootstrap();
                break;
        }
    }

    private Boolean getAdminRights() {
        UserRepository userRepository = componentFactory.getUserRepository();
        String role = userRepository.findRoleByUsername(loginController.getUsername());
        return role.equals("administrator");
    }
}
