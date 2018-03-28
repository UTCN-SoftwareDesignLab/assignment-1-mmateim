package view;

import service.user.AuthenticationService;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class MainForm extends JFrame {
    private JButton administratorButton;
    private JButton employeeButton;
    private JButton createAccountButton;
    private JPanel mainPanel;

    public MainForm(){
        super("Banking App");
        setSize(800, 600);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setCreateAccountListener(ActionListener createAccountListener) {
        createAccountButton.addActionListener(createAccountListener);
    }

    public void setEmployeeAccountListener(ActionListener employeeAccountListener){
        employeeButton.addActionListener(employeeAccountListener);
    }

    public void setAdminAccountListener(ActionListener adminAccountListener){
        administratorButton.addActionListener(adminAccountListener);
    }

    public void setVisible(Boolean bool)
    {
        setVisible(bool);
    }
}
