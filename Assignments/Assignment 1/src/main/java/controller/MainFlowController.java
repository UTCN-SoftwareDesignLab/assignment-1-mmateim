package controller;

import Main.ComponentFactory;
import view.AdminChooseAction;
import view.LoginView;
import view.MainForm;
import view.ViewClientInfo;

import java.util.Observable;
import java.util.Observer;

public class MainFlowController implements Observer {
    private ComponentFactory componentFactory;
    private MainController mainController;
    private LoginController loginController;
    private AdminActionController adminActionController;
    private ClientInfoController clientInfoController;
    private boolean adminRights;

    public MainFlowController(ComponentFactory componentFactory){
        this.componentFactory = componentFactory;
        mainController = new MainController(new MainForm());
        mainController.addObserver(this);
        loginController = new LoginController(new LoginView(), componentFactory.getAuthenticationService());
        loginController.addObserver(this);
        adminActionController = new AdminActionController(new AdminChooseAction());
        adminActionController.addObserver(this);
        clientInfoController = new ClientInfoController(new ViewClientInfo());
        clientInfoController.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        switch (arg.toString()){
            case "MainC_CreateAcc" : System.out.println("MAIN form, create account action");
                mainController.setVisible(false);
                loginController.setButtonsVisible(false);
                loginController.setVisible(true);
                break;
            case "MainC_LogAcc" : System.out.println("MAIN form, Log in action");
                mainController.setVisible(false);
                loginController.setButtonsVisible(true);
                loginController.setVisible(true);
                break;
            case "LoginC_RegSucc" : System.out.println("LOG IN form, Successful registration");
                loginController.setVisible(false);
                adminRights = getAdminRights();
                if(adminRights){
                    adminActionController.setVisible(true);
                }
                else {
                    clientInfoController.setVisible(true);
                }
                break;
            case "AdminAct_Client" : System.out.println("Admin user chose Client Actions");
                adminActionController.setVisible(false);
                clientInfoController.setVisible(true);
                break;
        }
    }
    private Boolean getAdminRights(){
        String role = componentFactory.getUserRepository().findRoleByUsername(loginController.getUsername());
        if(role.equals("administrator")){
            return true;
        }
        return false;
    }
}
