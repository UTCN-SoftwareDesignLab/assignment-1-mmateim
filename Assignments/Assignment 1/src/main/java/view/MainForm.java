package view;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class MainForm extends JFrame {
    private JButton administratorButton;
    private JButton employeeButton;
    private JButton clientButton;
    private JButton createAccountButton;
    private JPanel mainPanel;

    public MainForm() throws HeadlessException{
        super("Banking App");
        setSize(800, 600);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setCreateAccountListener(ActionListener createAccountListener) {
        createAccountButton.addActionListener(createAccountListener);
    }

    public void setClientAccountListener(ActionListener clientAccountListener){
        clientButton.addActionListener(clientAccountListener);
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
