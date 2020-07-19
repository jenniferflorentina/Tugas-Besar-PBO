/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Jennifer Florentina
 */
import controller.CheckController;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import model.TransactionManager;
import view.Helper.ConstantStyle;

public class HistoryScreen implements ActionListener {

    JFrame historyMenuFrame = new JFrame("History Menu");
    JLabel judul;
    JTable table;
    JButton back = new JButton("<< Back");

    public HistoryScreen() {
        historyMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        historyMenuFrame.setIconImage(ConstantStyle.icon);

        judul = new JLabel("Daftar Transaksi :");
        judul.setBounds(50, 50, 200, 100);
        judul.setFont(ConstantStyle.normal);

        DefaultTableModel model = controller.CheckController.getAllTransaction();
        table = new JTable(model);
        table.setBounds(100, 150, 1200, 500);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table.setRowSelectionAllowed(true);
        ListSelectionModel select = table.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String Data = null;
                int row = table.getSelectedRow();
                Data = (String) table.getValueAt(row, 0);
                int a = JOptionPane.showOptionDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (a == JOptionPane.YES_OPTION) {
                    int idTransaksi = Integer.parseInt(Data);
                    TransactionManager.getInstance().setTransaction(CheckController.getOneTransaction(idTransaksi));
                    new TransactionViewScreen();
                }
            }
        });
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(100, 150, 1200, 500);

        back.setBounds(20, 700, 100, 30);
        back.addActionListener(this);
        historyMenuFrame.add(sp);
        historyMenuFrame.add(judul);
        historyMenuFrame.add(back);
        historyMenuFrame.getContentPane().setBackground(Color.WHITE);
        historyMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        historyMenuFrame.setLocationRelativeTo(null);
        historyMenuFrame.setLayout(null);
        historyMenuFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        historyMenuFrame.dispose();
        new AdminMenuScreen();
    }
}
