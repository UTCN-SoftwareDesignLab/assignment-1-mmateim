package controller;

import Main.ComponentFactory;
import repository.user.UserRepository;
import view.AdminChooseAction;
import view.LoginView;
import view.MainForm;
import view.ViewClientInfo;

import java.util.Observable;
import java.util.Observer;

import static database.Constants.Controller.*;

public class MainFlowController implements Observer {
    private ComponentFactory componentFactory;
    private MainController mainController;
    private LoginController loginController;
    private AdminActionController adminActionController;
    private ClientInfoController clientInfoController;

    public MainFlowController(ComponentFactory componentFactory){
        this.componentFactory = componentFactory;
        mainController = new MainController(new MainForm());
        mainController.addObserver(this);
        loginController = new LoginController(new LoginView(), componentFactory.getAuthenticationService());
        loginController.addObserver(this);
        adminActionController = new AdminActionController(new AdminChooseAction());
        adminActionController.addObserver(this);
        clientInfoController = new ClientInfoController(new ViewClientInfo(), componentFactory.getClientRepository());
        clientInfoController.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        boolean adminRights;
        switch (arg.toString()){
            case MAINC_CREATE_ACC : System.out.println("MAIN form, create account action");
                mainController.setVisible(false);
                loginController.setButtonsVisible(false);
                loginController.setVisible(true);
                break;
            case MAINC_LOG_ACC : System.out.println("MAIN form, Log in action");
                mainController.setVisible(false);
                loginController.setButtonsVisible(true);
                loginController.setVisible(true);
                break;
            case LOGINC_REG_SUCC : System.out.println("LOGIN form, Successful registration");
                loginController.setVisible(false);
                adminRights = getAdminRights();
                if(adminRights){
                    adminActionController.setVisible(true);
                }
                else {
                    clientInfoController.setVisible(true);
                }
                break;
            case ADMINACT_CLIENT : System.out.println("Admin user chose Client Actions");
                adminActionController.setVisible(false);
                clientInfoController.setVisible(true);
                break;
        }
    }
    private Boolean getAdminRights() {
        UserRepository userRepository = componentFactory.getUserRepository();
        String role = userRepository.findRoleByUsername(loginController.getUsername());
        return role.equals("administrator");
    }
}
