package view;

import model.Client;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientInfoView extends JFrame{
    private JComboBox comboBoxCNP;
    private JPanel ClientInfoPanel;
    private JTextArea txtInfoClient;
    private JTextArea txtNameClient;
    private JButton buttonSearch;
    private JButton buttonBack;
    private JButton buttonAdvanced;

    public ClientInfoView(){
        super("View Client Info");
        setSize(600, 400);
        setContentPane(ClientInfoPanel);
        setLocationRelativeTo(null);
        txtInfoClient.setText("");
        txtNameClient.setText("");
        comboBoxCNP.removeAllItems();
    }

    public void populateComboBox(List<String> clientList){
        comboBoxCNP.removeAllItems();
        for(String cnp:clientList) {
            comboBoxCNP.addItem(cnp);
        }
        comboBoxCNP.setSelectedItem(null);
    }

    public String getTxtNameClient() {
        return txtNameClient.getText();
    }

    public void setAdvancedListener(ActionListener advancedListener){
        buttonAdvanced.addActionListener(advancedListener);
    }

    public void setClientSearchListener (ActionListener searchClientNameListener){
        buttonSearch.addActionListener(searchClientNameListener);
    }

    public void setBackListener(ActionListener backListener){
        buttonBack.addActionListener(backListener);
    }

    public void setClientComboListener (ActionListener clientComboListener){
        comboBoxCNP.addActionListener(clientComboListener);
    }

    public void setTxtInfoClient(String infoClient) {
        txtInfoClient.setText(infoClient);
    }

    public String getChosenCNP()
    {
        if(comboBoxCNP.getSelectedItem()== null)
            return null;
        return comboBoxCNP.getSelectedItem().toString();
    }
}