package controller;

import model.*;
import service.account.AccountService;
import service.activity.ActivityService;
import service.bill.BillService;
import service.client.ClientService;
import view.ClientInfoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import static database.Constants.Controller.CLIENT_INFO_ADVANCED;
import static database.Constants.Controller.CLIENT_INFO_ADVANCED_ACCOUNTS;
import static database.Constants.Controller.CLIENT_INFO_BACK;

public class ClientInfoController extends Observable {
    private ClientInfoView clientInfoView;
    private ClientService clientService;
    private AccountService accountService;
    private BillService billService;
    private ActivityService activityService;

    private User currentUser;

    public ClientInfoController(ClientInfoView clientInfoView, ClientService clientService, AccountService accountService, BillService billService, ActivityService activityService) {
        this.clientInfoView = clientInfoView;
        this.clientService = clientService;
        this.accountService = accountService;
        this.billService = billService;
        this.activityService = activityService;
        clientInfoView.setClientSearchListener(new SearchClientNameListener());
        clientInfoView.setBackListener(new BackListener());
        clientInfoView.setAdvancedListener(new AdvancedOperationsListener());
        clientInfoView.setPayListener(new PayBillListener());
        clientInfoView.setAdvancedAccountListener(new AdvancedAccountsListener());
        clientInfoView.setComboReiceiverListener(new AccountComboListener());
        clientInfoView.setTransferListener(new TransferListener());
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private class TransferListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String amountS = JOptionPane.showInputDialog(clientInfoView.getContentPane(), "Amount of money to transfer");
            if(amountS == null || amountS.equals(""))
            {
                JOptionPane.showMessageDialog(clientInfoView.getContentPane(), "Wrong input information");
                return;
            }
            Float amount = Float.parseFloat(amountS);
            String ibanSender = clientInfoView.getChosenIban();
            String ibanReceiver = clientInfoView.getChosenIbanRec();
            if (amount > 0 && ibanSender != null && ibanReceiver != null) {
                Account sender = accountService.findByIban(ibanSender);
                if(sender.getBalance() > amount){
                    float oldBalance = sender.getBalance();
                    sender.setBalance(oldBalance - amount);
                    accountService.update(sender.getId(), sender);
                    Account receiver = accountService.findByIban(ibanReceiver);
                    oldBalance = receiver.getBalance();
                    receiver.setBalance(oldBalance + amount);
                    accountService.update(receiver.getId(), receiver);
                    Activity activity = new Activity(new Date(), "Transfered " + amount + " from iban: " + sender.getIban()+ " to " + ibanReceiver, currentUser.getId());
                    activityService.add(activity);
                    JOptionPane.showMessageDialog(clientInfoView.getContentPane(), "Transfer finished");
                }
                else {
                    JOptionPane.showMessageDialog(clientInfoView.getContentPane(), "Not enough money in the account");
                }
            }
            else {
                JOptionPane.showMessageDialog(clientInfoView.getContentPane(), "Wrong input information");
            }
        }
    }

    private class AdvancedAccountsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers(CLIENT_INFO_ADVANCED_ACCOUNTS);
        }
    }

    private class AdvancedOperationsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers(CLIENT_INFO_ADVANCED);
        }
    }

    private class SearchClientNameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String clientName = clientInfoView.getTxtNameClient();
            clientInfoView.populateComboBoxCNP(clientService.findByName(clientName));
            clientInfoView.setClientComboListener(new ClientComboListener());
        }
    }

    private class PayBillListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Bill bill = new Bill(clientInfoView.getBillDescription(), clientInfoView.getBillAmount(), new Date());
            Client client = clientService.findByCNP(clientInfoView.getChosenCNP());
            bill.setClientId(client.getId());
            bill.setEmployeeId(currentUser.getId());
            bill.setAccountId(accountService.findByIban(clientInfoView.getChosenIban()).getId());
            if (!billService.addBill(bill)) {
                System.out.println("ERROR at creating bill");
            } else {
                System.out.println("Bill successfully created");
                String description = "Finished processing the utility bill (" + bill.getInformation() + " ) for client " + client.getName();
                activityService.add(new Activity(bill.getDate(), description, currentUser.getId()));
            }
        }
    }

    private class ClientComboListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cnp = clientInfoView.getChosenCNP();
            if (cnp != null) {
                String clientInfo = clientService.findByCNP(cnp).toString();
                clientInfoView.setTxtInfoClient(clientInfo);
                List<Account> accounts = accountService.findByUser(cnp);
                clientInfoView.populateComboBoxAccounts(getIbans(accounts));
            } else {
                clientInfoView.setTxtInfoClient("");
            }
        }
    }

    private class AccountComboListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cnp = clientInfoView.getChosenCNP();
            String clientInfo = clientService.findByCNP(cnp).toString();
            clientInfoView.setTxtInfoClient(clientInfo);
            List<Account> accounts = accountService.findByUser(cnp);
            List<String> ibans = getIbans(accounts);
            String iban = clientInfoView.getChosenIban();
            ibans.remove(iban);
            clientInfoView.populateComboBoxReceiver(ibans);
        }
    }

    List<String> getIbans(List<Account> accounts) {
        List<String> ibans = new ArrayList<>();
        for (Account account : accounts) {
            ibans.add(account.getIban());
        }
        return ibans;
    }

    private class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setChanged();
            notifyObservers(CLIENT_INFO_BACK);
        }
    }

    public void setVisible(Boolean flag) {
        clientInfoView.setVisible(flag);
    }
}
