package controller;

import model.*;
import service.account.AccountService;
import service.activity.ActivityService;
import service.bill.BillService;
import service.client.ClientService;
import view.ClientInfoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import static database.Constants.Controller.CLIENT_INFO_ADVANCED;
import static database.Constants.Controller.CLIENT_INFO_BACK;

public class ClientInfoController extends Observable{
    private ClientInfoView clientInfoView;
    private ClientService clientService;
    private AccountService accountService;
    private BillService billService;
    private ActivityService activityService;

    private User currentUser;

    public ClientInfoController(ClientInfoView clientInfoView, ClientService clientService, AccountService accountService, BillService billService, ActivityService activityService){
        this.clientInfoView = clientInfoView;
        this.clientService = clientService;
        this.accountService = accountService;
        this.billService = billService;
        this.activityService = activityService;
        clientInfoView.setClientSearchListener(new SearchClientNameListener());
        clientInfoView.setBackListener(new BackListener());
        clientInfoView.setAdvancedListener(new AdvancedOperationsListener());
        clientInfoView.setPayListener(new PayBillListener());
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private class AdvancedOperationsListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers(CLIENT_INFO_ADVANCED);
        }
    }

    private class SearchClientNameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String clientName = clientInfoView.getTxtNameClient();
            clientInfoView.populateComboBoxCNP(clientService.findByName(clientName));
            clientInfoView.setClientComboListener(new ClientComboListener());
        }
    }

    private class PayBillListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Bill bill = new Bill(clientInfoView.getBillDescription(), clientInfoView.getbillAmount(), new Date());
            Client client = clientService.findByCNP(clientInfoView.getChosenCNP());
            bill.setClientId(client.getId());
            bill.setEmployeeId(currentUser.getId());
            bill.setAccountId(accountService.findByIban(clientInfoView.getChosenIban()).getId());
            if(!billService.addBill(bill)){
                System.out.println("ERROR at creating bill");
            }
            else {
                System.out.println("Bill successfully created");
                activityService.add(new Activity(bill.getDate(), "Finished processing the utility bill with id" + bill.getId(), currentUser.getId()));
            }
        }
    }

    private class ClientComboListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String cnp = clientInfoView.getChosenCNP();
            if(cnp != null) {
                String clientInfo = clientService.findByCNP(cnp).toString();
                clientInfoView.setTxtInfoClient(clientInfo);
                List<Account> accounts = accountService.findByUser(cnp);
                clientInfoView.populateComboBoxAccounts(getIbans(accounts));
            }
            else {
                clientInfoView.setTxtInfoClient("");
            }
        }
    }

    List<String> getIbans(List<Account> accounts){
        List<String> ibans = new ArrayList<>();
        for(Account account : accounts){
            ibans.add(account.getIban());
        }
        return ibans;
    }

    private class BackListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers(CLIENT_INFO_BACK);
        }
    }

    public void setVisible(Boolean flag){
        clientInfoView.setVisible(flag);
    }
}
