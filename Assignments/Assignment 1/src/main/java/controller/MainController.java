package controller;

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
        mainForm.setLogAccountListener(new LogAccountListener());
    }

    private class CreateAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers("MainC_CreateAcc");
           // new LoginController(new LoginView(false));
        }
    }

    private class LogAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers("MainC_LogAcc");
        }
    }

    public void setVisible(Boolean flag){
        mainForm.setVisible(flag);
    }
}
