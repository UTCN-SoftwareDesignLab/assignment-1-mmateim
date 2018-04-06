package controller;

import service.client.ClientService;
import service.employee.EmployeeService;
import view.CRUDActionsView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CRUDActionsController extends java.util.Observable {

    private ClientService clientService;
    private EmployeeService employeeService;
    private CRUDActionsView crudActionsView;
    public enum Role {CLIENT, ADMIN}
    private Role role;

    public CRUDActionsController(ClientService clientService, EmployeeService employeeService, CRUDActionsView crudActionsView) {
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.crudActionsView = crudActionsView;
        crudActionsView.setBackListener(new BackListener());
        crudActionsView.setAddListener(new AddOpListener());
        crudActionsView.setRemoveListener(new DeleteOpListener());
        crudActionsView.setUpdateListener(new UpdateOpListener());
    }

    public void setVisible(Boolean flag) {
        crudActionsView.setVisible(flag);
    }

    public void clientBootstrap() {
        role = Role.CLIENT;
        DefaultTableModel model = clientService.findAllTable();
        crudActionsView.bootstrapClient(model);
    }

    public void employeeBootstrap() {
        role = Role.ADMIN;
        crudActionsView.bootstrapEmployee();
    }

    private class AddOpListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class UpdateOpListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class DeleteOpListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class BackListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
