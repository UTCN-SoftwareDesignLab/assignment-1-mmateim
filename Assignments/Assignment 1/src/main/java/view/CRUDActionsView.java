package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class CRUDActionsView extends JFrame {
    private JPanel ActionsPanel;
    private JTable table;
    private JButton addButton;
    private JButton updateButton;
    private JButton removeButton;
    private JButton backButton;
    private DefaultTableModel model;

    public CRUDActionsView() {
        super("Advanced CRUD operations");
        setSize(600, 400);
        setContentPane(ActionsPanel);
        setLocationRelativeTo(null);
        table.setAutoCreateRowSorter(true);
    }

    public void populateTable(DefaultTableModel model){
        this.model = model;
        table.removeAll();
        table.setModel(model);
        table.repaint();
    }

    public void setAddListener(ActionListener addListener){
        addButton.addActionListener(addListener);
    }

    public void setRemoveListener(ActionListener removeListener){
        removeButton.addActionListener(removeListener);
    }

    public void setUpdateListener(ActionListener updateListener){
        updateButton.addActionListener(updateListener);
    }

    public void setBackListener(ActionListener backListener){
        backButton.addActionListener(backListener);
    }
}


