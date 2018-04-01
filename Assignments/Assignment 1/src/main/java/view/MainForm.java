package view;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private JButton logButton;
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

    public void setLogAccountListener(ActionListener logAccountListener){
        logButton.addActionListener(logAccountListener);
    }
}
