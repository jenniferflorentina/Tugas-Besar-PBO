/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CheckController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import static model.enums.TipeUserEnum.GUEST;
import static model.enums.TipeUserEnum.MEMBER;
import model.PersonManager;
import model.TransactionManager;
import view.helper.ConstantStyle;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class CancelBookingScreen implements ActionListener {

    JFrame cancelBookingFrame = new JFrame("Cancel Booking Menu");
    JLabel judul;
    JTable table;
    JButton back = new JButton("<< Back");

    public CancelBookingScreen() {
        cancelBookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cancelBookingFrame.setIconImage(ConstantStyle.icon);

        judul = new JLabel("Cancel Booking :");
        judul.setBounds(50, 50, 200, 100);
        judul.setFont(ConstantStyle.normal);

        DefaultTableModel model = controller.CheckController.getUserActiveTransaction(PersonManager.getInstance().getPerson().getId());
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
                    if (CheckController.CancelBooking(TransactionManager.getInstance().getTransaction())) {
                        long today = new Date().getTime();
                        long cI = TransactionManager.getInstance().getTransaction().getTanggalCheckIn().getTime();
                        int hours = (int) ((cI - today) / (1000 * 60 * 60));
                        if (hours < 0) {
                            JOptionPane.showMessageDialog(null, "Cancelation Successful.");
                        } else if (hours < 24) {
                            JOptionPane.showMessageDialog(null, "Cancelation Successful.\n" + ConstantStyle.kurensiIndonesia.format(TransactionManager.getInstance().getTransaction().getUangMuka() * 0.5) + " will be sent back to you.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Cancelation Successful.\n" + ConstantStyle.kurensiIndonesia.format(TransactionManager.getInstance().getTransaction().getUangMuka()) + " will be sent back to you.");
                        }
                        cancelBookingFrame.dispose();
                        if (PersonManager.getInstance().getPerson().getTipeUser() == GUEST) {
                            new UserMenuScreen();
                        }
                        if (PersonManager.getInstance().getPerson().getTipeUser() == MEMBER) {
                            new MemberMenuScreen();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cancelation Failed!", "Alert", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        back.setBounds(20, 700, 100, 30);
        back.addActionListener(this);

        cancelBookingFrame.add(back);
        cancelBookingFrame.add(judul);
        cancelBookingFrame.add(sp);
        cancelBookingFrame.getContentPane().setBackground(Color.WHITE);
        cancelBookingFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        cancelBookingFrame.setLocationRelativeTo(null);
        cancelBookingFrame.setLayout(null);
        cancelBookingFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        switch (button) {
            case "<< Back":
                cancelBookingFrame.dispose();
                if (PersonManager.getInstance().getPerson().getTipeUser() == GUEST) {
                    new UserMenuScreen();
                }
                if (PersonManager.getInstance().getPerson().getTipeUser() == MEMBER) {
                    new MemberMenuScreen();
                }
                break;
        }
    }
}
