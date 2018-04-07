package controller;


import view.AdminChooseAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static database.Constants.Controller.ADMINACT_ADMIN;
import static database.Constants.Controller.ADMINACT_CLIENT;

public class AdminActionController extends java.util.Observable {

    AdminChooseAction adminChooseAction;

    public AdminActionController(AdminChooseAction adminChooseAction) {
        this.adminChooseAction = adminChooseAction;
        adminChooseAction.setClientButtonListener(new ClientOpListener());
        adminChooseAction.setEmployeeButtonListener(new EmployeeOpListener());
    }

    private class ClientOpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers(ADMINACT_CLIENT);
        }
    }

    private class EmployeeOpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers(ADMINACT_ADMIN);
        }
    }

    public void setVisible(Boolean flag){
        adminChooseAction.setVisible(flag);
    }
}