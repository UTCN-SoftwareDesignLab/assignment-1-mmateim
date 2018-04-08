package view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientInfoView extends JFrame {
    private JComboBox comboBoxCNP;
    private JPanel ClientInfoPanel;
    private JTextArea txtInfoClient;
    private JTextArea txtNameClient;
    private JButton buttonSearch;
    private JButton buttonBack;
    private JButton buttonAdvancedClient;
    private JTextField billAmount;
    private JTextField billDescription;
    private JButton buttonPay;
    private JComboBox comboAccounts;
    private JButton buttonTransfer;
    private JButton buttonAdvancedAccount;
    private JComboBox comboBoxReceiver;

    public ClientInfoView() {
        super("View Client Info");
        setSize(600, 400);
        setContentPane(ClientInfoPanel);
        setLocationRelativeTo(null);
        txtInfoClient.setText("");
        txtNameClient.setText("");
        billAmount.setText("");
        billDescription.setText("");
        comboAccounts.removeAllItems();
        comboBoxCNP.removeAllItems();
    }

    public void populateComboBoxCNP(List<String> clientList) {
        comboBoxCNP.removeAllItems();
        for (String cnp : clientList) {
            comboBoxCNP.addItem(cnp);
        }
        comboBoxCNP.setSelectedItem(null);
    }

    public void populateComboBoxAccounts(List<String> accountList) {
        comboAccounts.removeAllItems();
        for (String account : accountList) {
            comboAccounts.addItem(account);
        }
        comboAccounts.setSelectedItem(null);
    }

    public void populateComboBoxReceiver(List<String> accountList) {
        comboBoxReceiver.removeAllItems();
        for (String account : accountList) {
            comboBoxReceiver.addItem(account);
        }
        comboBoxReceiver.setSelectedItem(null);
    }

    public void setAdvancedAccountListener(ActionListener accountListener){
        buttonAdvancedAccount.addActionListener(accountListener);
    }

    public void setComboReiceiverListener(ActionListener actionListener){
        comboAccounts.addActionListener(actionListener);
    }

    public float getBillAmount() {
        return Float.parseFloat(billAmount.getText());
    }

    public String getBillDescription() {
        return billDescription.getText();
    }

    public String getTxtNameClient() {
        return txtNameClient.getText();
    }

    public void setPayListener(ActionListener payListener) {
        buttonPay.addActionListener(payListener);
    }

    public void setTransferListener(ActionListener transferListener) {
        buttonTransfer.addActionListener(transferListener);
    }

    public void setAdvancedListener(ActionListener advancedListener) {
        buttonAdvancedClient.addActionListener(advancedListener);
    }

    public void setClientSearchListener(ActionListener searchClientNameListener) {
        buttonSearch.addActionListener(searchClientNameListener);
    }

    public void setBackListener(ActionListener backListener) {
        buttonBack.addActionListener(backListener);
    }

    public void setClientComboListener(ActionListener clientComboListener) {
        comboBoxCNP.addActionListener(clientComboListener);
    }

    public void setTxtInfoClient(String infoClient) {
        txtInfoClient.setText(infoClient);
    }

    public String getChosenCNP() {
        if (comboBoxCNP.getSelectedItem() == null)
            return null;
        return comboBoxCNP.getSelectedItem().toString();
    }

    public String getChosenIban() {
        if(comboAccounts.getSelectedItem() == null)
            return null;
        return comboAccounts.getSelectedItem().toString();
    }

    public String getChosenIbanRec() {
        if(comboBoxReceiver.getSelectedItem() == null)
            return null;
        return comboBoxReceiver.getSelectedItem().toString();
    }
}