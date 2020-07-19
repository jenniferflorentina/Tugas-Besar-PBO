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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import model.Enums.BookingEnum;
import model.TransactionManager;
import view.Helper.ConstantStyle;
import view.Helper.StayOverPopUp;

public class StayOverScreen implements ActionListener {

    JFrame stayOverMenuFrame = new JFrame("Stay Over Menu");
    JLabel judul;
    JTable table;
    JButton back = new JButton("<< Back");

    public StayOverScreen() {
        stayOverMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stayOverMenuFrame.setIconImage(ConstantStyle.icon);

        judul = new JLabel("Daftar Transaksi :");
        judul.setBounds(50, 50, 200, 100);
        judul.setFont(ConstantStyle.normal);

        DefaultTableModel model = controller.CheckController.getTransactionByStatus(0, BookingEnum.CHECKEDIN);
        table = new JTable(model);
        table.setBounds(100, 150, 1200, 500);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(100, 150, 1200, 500);

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
                    new StayOverPopUp();
                    stayOverMenuFrame.dispose();
                }
            }
        });
        back.setBounds(20, 700, 100, 30);
        back.addActionListener(this);
        stayOverMenuFrame.add(back);
        stayOverMenuFrame.add(sp);
        stayOverMenuFrame.add(judul);
        stayOverMenuFrame.getContentPane().setBackground(Color.WHITE);
        stayOverMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        stayOverMenuFrame.setLocationRelativeTo(null);
        stayOverMenuFrame.setLayout(null);
        stayOverMenuFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stayOverMenuFrame.dispose();
        new AdminMenuScreen();
    }
}
