package view;

import javax.swing.*;

public class CRUDActionsView extends JFrame {
    private JPanel ActionsPanel;
    private JTable tableClients;
    private JButton addButton;
    private JButton updateButton;
    private JButton removeButton;
    private JButton closeButton;

    public CRUDActionsView() {
        super("Advanced CRUD operations");
        setSize(600, 400);
        setContentPane(ActionsPanel);
        setLocationRelativeTo(null);
        tableClients = new JTable();
        tableClients.setAutoCreateRowSorter(true);
    }
}


