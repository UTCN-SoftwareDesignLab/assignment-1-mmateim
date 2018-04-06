package view;

import model.Client;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewClientInfo extends JFrame{
    private JComboBox comboBoxCNP;
    private JPanel ClientInfoPanel;
    private JTextArea txtInfoClient;
    private JTextArea txtNameClient;
    private JButton buttonSearch;

    public ViewClientInfo(){
        super("View Client Info");
        setSize(600, 400);
        setContentPane(ClientInfoPanel);
        setLocationRelativeTo(null);
        txtInfoClient.setText("");
        txtNameClient.setText("");
        comboBoxCNP.removeAllItems();
    }

    public void populateComboBox(List<Client> clientList){
        comboBoxCNP.removeAllItems();
        System.out.println("Populate Combobox Clients");
        for(Client client:clientList) {
            comboBoxCNP.addItem(client.getCnp());
        }
    }

    public String getComboBoxCNP() {
        return comboBoxCNP.getSelectedItem().toString();
    }

    public String getTxtNameClient() {
        return txtNameClient.getText();
    }

    public void setClientSearchListener (ActionListener searchClientNameListener){
        buttonSearch.addActionListener(searchClientNameListener);
    }

    public void setClientComboListener (ActionListener clientComboListener){
        comboBoxCNP.addActionListener(clientComboListener);
    }

    public void setTxtInfoClient(String infoClient) {
        txtInfoClient.setText(infoClient);
    }

    public String getChosenCNP()
    {
        return comboBoxCNP.getSelectedItem().toString();
    }
}
