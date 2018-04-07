package controller;

import DTO.UserDTO;
import model.Client;
import model.User;
import service.client.ClientService;
import service.user.AuthenticationService;
import service.user.UserServiceMySQL;
import view.CRUDActionsView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class CRUDActionsController extends java.util.Observable {

    private ClientService clientService;
    private UserServiceMySQL userService;
    private CRUDActionsView crudActionsView;
    public enum Role {CLIENT, ADMIN}
    private Role role;

    public CRUDActionsController(ClientService clientService, UserServiceMySQL userService, CRUDActionsView crudActionsView) {
        this.clientService = clientService;
        this.crudActionsView = crudActionsView;
        this.userService = userService;
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
        List<Client> clientList = clientService.findAll();
        if(clientList == null)
            crudActionsView.populateTable(null);
        String[] columnNames = {"id", "name", "CNP", "address"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Client client : clientList){
            Vector row = new Vector();
            row.addElement(client.getId());
            row.addElement(client.getName());
            row.addElement(client.getCnp());
            row.addElement(client.getAddress());
            model.addRow(row);
        }
        crudActionsView.populateTable(model);
    }

    public void employeeBootstrap() {
        role = Role.ADMIN;
        List<UserDTO> userList = userService.findAll();
        if(userList == null)
            crudActionsView.populateTable(null);
        String[] columnNames = {"username", "roles"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (UserDTO user : userList){
            Vector row = new Vector();
            row.addElement(user.getUsername());
            row.addElement(user.getRoles());
            model.addRow(row);
        }
        crudActionsView.populateTable(model);
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
