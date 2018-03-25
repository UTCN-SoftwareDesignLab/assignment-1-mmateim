package controller;

import view.MainForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private MainForm mainForm;

    public MainController()
    {
        mainForm = new MainForm();
        mainForm.setCreateAccountListener(new CreateAccountListener());
        mainForm.setClientAccountListener(new ClientAccountListener());
        mainForm.setEmployeeAccountListener(new EmployeeAccountListener());
        mainForm.setAdminAccountListener(new AdminAccountListener());
    }

    private class CreateAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            mainForm.setVisible(false);
        }
    }

    private class ClientAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            mainForm.setVisible(false);
        }
    }

    private class EmployeeAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            mainForm.setVisible(false);
        }
    }

    private class AdminAccountListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            mainForm.setVisible(false);
        }
    }

}
