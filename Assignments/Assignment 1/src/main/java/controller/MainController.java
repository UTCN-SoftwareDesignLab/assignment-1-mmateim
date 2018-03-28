package controller;

import Main.ComponentFactory;
import view.LoginView;
import view.MainForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class MainController extends Observable {
    private MainForm mainForm;

    public MainController(MainForm mainForm)
    {
        this.mainForm = mainForm;
        mainForm.setCreateAccountListener(new CreateAccountListener());
        mainForm.setEmployeeAccountListener(new EmployeeAccountListener());
        mainForm.setAdminAccountListener(new AdminAccountListener());
    }

    private class CreateAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            mainForm.setVisible(false);
            setChanged();
            notifyObservers("MainC_CreateAcc");
           // new LoginController(new LoginView(false));
        }
    }

    private class EmployeeAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            mainForm.setVisible(false);
            setChanged();
            notifyObservers("MainC_EmplAcc");
            //new LoginController(new LoginView(true));
        }
    }

    private class AdminAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            mainForm.setVisible(false);
            setChanged();
            notifyObservers("MainC_AdminAcc");
            //new LoginController(new LoginView(true));
        }
    }

}
