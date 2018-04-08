package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionListener;
import java.util.Vector;

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

    public void populateTable(DefaultTableModel model) {
        this.model = model;
        table.removeAll();
        table.setModel(model);
        addRowToTable();
        table.repaint();
    }

    private void addRowToTable() {
        if (table.getRowCount() > 0) {
            Vector lastRow = (Vector)model.getDataVector().get(table.getRowCount() - 1);
            if (!lastRow.get(0).toString().equals("")) {
                model.addRow(new Vector(model.getColumnCount()));
            }
        }
        else {
            model.addRow(new Vector(model.getColumnCount()));
        }

    }

    public Vector getLastRow() {
        return (Vector) model.getDataVector().get(table.getRowCount() - 1);
    }

    public Vector getSelectedRow() {
        return (Vector) model.getDataVector().get(table.getSelectedRow());
    }

    public void setAddListener(ActionListener addListener) {
        addButton.addActionListener(addListener);
    }

    public void setRemoveListener(ActionListener removeListener) {
        removeButton.addActionListener(removeListener);
    }

    public void setUpdateListener(ActionListener updateListener) {
        updateButton.addActionListener(updateListener);
    }

    public void setBackListener(ActionListener backListener) {
        backButton.addActionListener(backListener);
    }
}


