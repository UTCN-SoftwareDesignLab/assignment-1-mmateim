package view;

import controller.ClientInfoController;

import javax.swing.*;
import java.awt.event.ActionListener;

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
        setVisible(true);
    }

    public void setClientSearchListener (ActionListener searchClientNameListener){
        buttonSearch.addActionListener(searchClientNameListener);
    }
}
