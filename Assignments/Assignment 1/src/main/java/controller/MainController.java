package controller;

import Main.ComponentFactory;
import view.LoginView;
import view.MainForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private MainForm mainForm;
    private ComponentFactory componentFactory;

    public MainController(MainForm mainForm, ComponentFactory componentFactory)
    {
        this.componentFactory = componentFactory;
        this.mainForm = mainForm;
        mainForm.setCreateAccountListener(new CreateAccountListener());
        mainForm.setEmployeeAccountListener(new EmployeeAccountListener());
        mainForm.setAdminAccountListener(new AdminAccountListener());
    }

    private class CreateAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            mainForm.setVisible(false);
            new LoginController(new LoginView(false), componentFactory.getAuthenticationService());
        }
    }

    private class EmployeeAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            mainForm.setVisible(false);
            new LoginController(new LoginView(true), componentFactory.getAuthenticationService());
        }
    }

    private class AdminAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            mainForm.setVisible(false);
            new LoginController(new LoginView(true), componentFactory.getAuthenticationService());
        }
    }

}
