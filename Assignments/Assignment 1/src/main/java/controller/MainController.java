package controller;

import view.MainForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import static database.Constants.Controller.MAINC_CREATE_ACC;
import static database.Constants.Controller.MAINC_LOG_ACC;

public class MainController extends Observable {
    private MainForm mainForm;

    public MainController(MainForm mainForm)
    {
        this.mainForm = mainForm;
        mainForm.setCreateAccountListener(new CreateAccountListener());
        mainForm.setLogAccountListener(new LogAccountListener());
    }

    private class CreateAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers(MAINC_CREATE_ACC);
           // new LoginController(new LoginView(false));
        }
    }

    private class LogAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers(MAINC_LOG_ACC);
        }
    }

    public void setVisible(Boolean flag){
        mainForm.setVisible(flag);
    }
}
