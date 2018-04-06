package view;

import javax.swing.*;

public class AddUpdateClient extends JFrame {
    private JTextField textCNP;
    private JTextField textAddress;
    private JTextField textName;
    private JButton button1;
    private JPanel AddUpdatePanel;

    public AddUpdateClient() {
        super("Add/Update Client");
        setSize(800, 600);
        setContentPane(AddUpdatePanel);
        setLocationRelativeTo(null);
        textName.setText("");
        textCNP.setText("");
        textAddress.setText("");
    }

}
