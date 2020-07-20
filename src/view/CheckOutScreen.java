package view;

import controller.CheckController;
import controller.DataController;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import model.enums.BookingEnum;
import model.TransactionManager;
import view.helper.CheckOutPopUp;
import view.helper.ConstantStyle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jennifer Florentina
 */
public class CheckOutScreen implements ItemListener, ActionListener {

    JFrame checkoutnMenuFrame = new JFrame("Check Out Menu");
    JLabel judul, filterLabel;
    JTable table;
    JButton back = new JButton("<< Back");
    JComboBox filter;

    public CheckOutScreen() {
        checkoutnMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkoutnMenuFrame.setIconImage(ConstantStyle.icon);

        judul = new JLabel("Daftar Transaksi :");
        judul.setBounds(20, 10, 200, 80);
        judul.setFont(ConstantStyle.normal);

        filterLabel = new JLabel("Filter : ");
        filterLabel.setBounds(20, 105, 80, 20);
        filterLabel.setFont(ConstantStyle.small);
        filter = new JComboBox();
        filter.setFont(ConstantStyle.small);
        filter.setBounds(100, 105, 200, 20);
        filter.addItem("All");
        for (int i = 0; i < DataController.listHotel.size(); i++) {
            filter.addItem(DataController.listHotel.get(i).getNama());
        }
        filter.addItemListener(this);

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
                    new CheckOutPopUp(0, 0);
                }
            }
        });
        back.setBounds(20, 700, 100, 30);
        back.addActionListener(this);
        checkoutnMenuFrame.add(back);
        checkoutnMenuFrame.add(filterLabel);
        checkoutnMenuFrame.add(filter);
        checkoutnMenuFrame.add(sp);
        checkoutnMenuFrame.add(judul);
        checkoutnMenuFrame.getContentPane().setBackground(Color.WHITE);
        checkoutnMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        checkoutnMenuFrame.setLocationRelativeTo(null);
        checkoutnMenuFrame.setLayout(null);
        checkoutnMenuFrame.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        int idH = 0;
        for (int i = 0; i < DataController.listHotel.size(); i++) {
            if (filter.getSelectedItem().equals(DataController.listHotel.get(i).getNama())) {
                idH = DataController.listHotel.get(i).getIdHotel();
                break;
            }
        }
        DefaultTableModel model = controller.CheckController.getTransactionByStatus(idH, BookingEnum.CHECKEDIN);
        table.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        switch (button) {
            case "<< Back":
                checkoutnMenuFrame.dispose();
                new AdminMenuScreen();
                break;
        }
    }
}
