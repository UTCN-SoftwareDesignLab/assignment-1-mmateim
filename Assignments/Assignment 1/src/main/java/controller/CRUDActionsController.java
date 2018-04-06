package controller;

import service.client.ClientService;
import service.employee.EmployeeService;
import view.CRUDActionsView;

public class CRUDActionsController extends java.util.Observable {

    private ClientService clientService;
    private EmployeeService employeeService;
    private CRUDActionsView crudActionsView;

    public CRUDActionsController(ClientService clientService, EmployeeService employeeService, CRUDActionsView crudActionsView) {
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.crudActionsView = crudActionsView;
    }

    public void setVisible(Boolean flag) {
        crudActionsView.setVisible(flag);
    }

    public void clientBootstrap() {

    }

    public void employeeBootstrap() {

    }
}
