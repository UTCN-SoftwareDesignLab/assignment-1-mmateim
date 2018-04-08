package view;

import model.User;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdminChooseAction extends JFrame {
    private JButton clientOperationsButton;
    private JButton employeeOperationsButton;
    private JPanel ActionPane;
    private JComboBox comboBoxUsers;
    private JButton generateReportButton;
    private JTextField date;

    public AdminChooseAction() {
        super("Choose action");
        setSize(600, 400);
        setContentPane(ActionPane);
        setLocationRelativeTo(null);
    }

    public void populateComboBox(List<String> users){
        comboBoxUsers.removeAllItems();
        for(String username : users){
            comboBoxUsers.addItem(username);
        }
        comboBoxUsers.setSelectedItem(null);
    }

    public String getSelectedUser(){
        return comboBoxUsers.getSelectedItem() != null ? comboBoxUsers.getSelectedItem().toString() : null;
    }

    public Date getDate(){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date d = df.parse(date.getText());
            return d;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setReportListener(ActionListener listener){
        generateReportButton.addActionListener(listener);
    }

    public void setClientButtonListener(ActionListener clientOpListener){
        clientOperationsButton.addActionListener(clientOpListener);
    }

    public void setEmployeeButtonListener(ActionListener employeeOpListener){
        employeeOperationsButton.addActionListener(employeeOpListener);
    }
}
