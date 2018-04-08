package controller;

import DTO.UserDTO;
import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import service.account.AccountService;
import service.client.ClientService;
import service.user.UserServiceImpl;
import view.CRUDActionsView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import static database.Constants.Controller.CRUD_BACK;
import static database.Constants.Controller.LOGINC_REG_SUCC;
import static database.Constants.Controller.MAINC_CREATE_ACC;

public class CRUDActionsController extends java.util.Observable {

    private AccountService accountService;
    private ClientService clientService;
    private UserServiceImpl userService;
    private CRUDActionsView crudActionsView;

    public enum Role {CLIENT, ADMIN, ACCOUNT}

    private Role role;

    public CRUDActionsController(ClientService clientService, UserServiceImpl userService, CRUDActionsView crudActionsView, AccountService accountService) {
        this.clientService = clientService;
        this.crudActionsView = crudActionsView;
        this.userService = userService;
        this.accountService = accountService;
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
        if (clientList == null)
            crudActionsView.populateTable(null);
        String[] columnNames = {"id", "name", "CNP", "address"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Client client : clientList) {
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
        if (userList == null)
            crudActionsView.populateTable(null);
        String[] columnNames = {"username", "roles"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (UserDTO user : userList) {
            Vector row = new Vector();
            row.addElement(user.getUsername());
            row.addElement(user.getRoles());
            model.addRow(row);
        }
        crudActionsView.populateTable(model);
    }

    public void accountBootstrap() {
        role = Role.ACCOUNT;
        List<Account> accounts = accountService.findAll();
        if (accounts == null)
            crudActionsView.populateTable(null);
        String[] columnNames = {"id", "iban", "balance", "holder_id", "type", "creationDate"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Account account : accounts) {
            Vector row = new Vector();
            row.addElement(account.getId());
            row.addElement(account.getIban());
            row.addElement(account.getBalance());
            row.addElement(account.getHolderID());
            row.addElement(account.getType());
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            row.addElement(df.format(account.getCreationDate()));
            model.addRow(row);
        }
        crudActionsView.populateTable(model);
    }

    private class AddOpListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Vector v = crudActionsView.getLastRow();
            if(v == null) {
                System.out.println("null row selected");
                return;
            }
            if (role.equals(Role.ADMIN)) {
                setChanged();
                notifyObservers(MAINC_CREATE_ACC);
                return;
            }
            if (role.equals(Role.CLIENT)) {
                Client client = rowToClient(v);
                clientService.save(client);
            }
            if (role.equals(Role.ACCOUNT)) {
                Account account = rowToAccount(v);
                accountService.save(account);
            }
            setChanged();
            notifyObservers(CRUD_BACK);
        }
    }

    private class UpdateOpListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Vector v = crudActionsView.getSelectedRow();
            if(v == null) {
                System.out.println("null row selected");
                return;
            }
            if (role.equals(Role.ADMIN)) {
                String username = v.get(0).toString();
                userService.update(userService.findByUsername(username).getId(), v.get(1).toString());
                setChanged();
                notifyObservers(LOGINC_REG_SUCC);
                return;
            }
            if (role.equals(Role.CLIENT)) {
                String id = v.get(0).toString();
                Client client = rowToClient(v);
                clientService.update(id, client);
            }
            if (role.equals(Role.ACCOUNT)) {
                Long id = Long.parseLong(v.get(0).toString());
                Account account = rowToAccount(v);
                accountService.update(id, account);
            }
            setChanged();
            notifyObservers(CRUD_BACK);
        }
    }

    private Account rowToAccount(Vector v) {
        Account account = new AccountBuilder().setIBAN(v.get(1).toString())
                .setBalance(Float.parseFloat(v.get(2).toString()))
                .setHolderID(Long.parseLong(v.get(3).toString()))
                .setType(v.get(4).toString())
                .build();
        return account;
    }

    private Client rowToClient(Vector v) {
        Client client = new ClientBuilder().setName(v.get(1).toString())
                .setCNP(v.get(2).toString())
                .setAddress(v.get(3).toString())
                .build();
        return client;
    }

    private class DeleteOpListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Vector v = crudActionsView.getSelectedRow();
            if(v == null) {
                System.out.println("null row selected");
                return;
            }
            if (role.equals(Role.ADMIN)) {
                String username = v.get(0).toString();
                userService.delete(userService.findByUsername(username));
            }
            if (role.equals(Role.CLIENT)) {
                String id = v.get(0).toString();
                clientService.delete(id);
            }
            if (role.equals(Role.ACCOUNT)) {
                Long id = Long.parseLong(v.get(0).toString());
                accountService.delete(id);
            }
            setChanged();
            notifyObservers(CRUD_BACK);
        }
    }

    private class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers(CRUD_BACK);
        }
    }

}
