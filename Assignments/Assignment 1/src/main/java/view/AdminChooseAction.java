package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AdminChooseAction extends JFrame {
    private JButton clientOperationsButton;
    private JButton employeeOperationsButton;
    private JPanel ActionPane;

    public AdminChooseAction() {
        super("Choose action");
        setSize(600, 400);
        setContentPane(ActionPane);
        setLocationRelativeTo(null);
    }

    public void setClientButtonListener(ActionListener clientOpListener){
        clientOperationsButton.addActionListener(clientOpListener);
    }

    public void setEmployeeButtonListener(ActionListener employeeOpListener){
        employeeOperationsButton.addActionListener(employeeOpListener);
    }
}
