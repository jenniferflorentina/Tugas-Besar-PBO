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
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import static model.enums.TipeUserEnum.GUEST;
import static model.enums.TipeUserEnum.MEMBER;
import model.PersonManager;
import model.TransactionManager;
import view.helper.ConstantStyle;
import view.helper.RescheduleBookingPopUp;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class RescheduleBookingScreen implements ActionListener {

    JFrame RescheduleBookingFrame = new JFrame("Reschedule Booking Menu");
    JLabel judul, tanggalCheckInLabel, tanggalCheckOutLabel;
    JTable table;
    JButton submitButton;
    JButton back = new JButton("<< Back");

    public RescheduleBookingScreen() {
        RescheduleBookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RescheduleBookingFrame.setIconImage(ConstantStyle.icon);

        judul = new JLabel("Reschedule Booking :");
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
                    RescheduleBookingFrame.dispose();
                    new RescheduleBookingPopUp();
                }
            }
        });

        back.setBounds(20, 700, 100, 30);
        back.addActionListener(this);

        RescheduleBookingFrame.add(judul);
        RescheduleBookingFrame.add(sp);
        RescheduleBookingFrame.add(back);
        RescheduleBookingFrame.getContentPane().setBackground(Color.WHITE);
        RescheduleBookingFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        RescheduleBookingFrame.setLocationRelativeTo(null);
        RescheduleBookingFrame.setLayout(null);
        RescheduleBookingFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        switch (button) {
            case "<< Back":
                RescheduleBookingFrame.dispose();
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
