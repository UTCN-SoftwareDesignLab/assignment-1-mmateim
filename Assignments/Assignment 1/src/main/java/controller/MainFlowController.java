package controller;

import Main.ComponentFactory;
import view.LoginView;
import view.MainForm;

import java.util.Observable;
import java.util.Observer;

public class MainFlowController implements Observer {
    private ComponentFactory componentFactory;
    private MainController mainController;
    private LoginController loginController;

    public MainFlowController(ComponentFactory componentFactory){
        this.componentFactory = componentFactory;
        mainController = new MainController(new MainForm());
        mainController.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        switch (arg.toString()){
            case "MainC_CreateAcc" : System.out.println("MAIN form, create account action");
                loginController = new LoginController(new LoginView(false));
                break;
            case "MainC_EmplAcc" : System.out.println("MAIN form, Employee account action");
                loginController = new LoginController(new LoginView(true));
                break;
            case "MainC_AdminAcc" : System.out.println("MAIN form, Administrator account action");
                loginController = new LoginController(new LoginView(true));
        }
    }
}
