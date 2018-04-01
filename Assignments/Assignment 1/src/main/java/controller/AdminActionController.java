package controller;


import view.AdminChooseAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            notifyObservers("AdminAct_Client");
        }
    }

    private class EmployeeOpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers("AdminAct_Employee");
        }
    }

    public void setVisible(Boolean flag){
        adminChooseAction.setVisible(flag);
    }
}