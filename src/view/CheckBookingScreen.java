/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import static model.enums.TipeUserEnum.GUEST;
import static model.enums.TipeUserEnum.MEMBER;
import model.PersonManager;
import view.helper.ConstantStyle;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class CheckBookingScreen implements ActionListener {

    JFrame CheckBookingFrame = new JFrame("Check Booking Menu");
    JLabel judul;
    JTable table;
    JButton back = new JButton("<< Back");

    public CheckBookingScreen() {
        CheckBookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CheckBookingFrame.setIconImage(ConstantStyle.icon);

        judul = new JLabel("Daftar Booking :");
        judul.setBounds(50, 50, 200, 100);
        judul.setFont(ConstantStyle.normal);

        DefaultTableModel model = controller.CheckController.getUserActiveTransaction(PersonManager.getInstance().getPerson().getId());
        table = new JTable(model);
        table.setBounds(100, 150, 1200, 500);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(100, 150, 1200, 500);

        table.setRowSelectionAllowed(false);

        back.setBounds(20, 700, 100, 30);
        back.addActionListener(this);

        CheckBookingFrame.add(sp);
        CheckBookingFrame.add(judul);
        CheckBookingFrame.add(back);
        CheckBookingFrame.getContentPane().setBackground(Color.WHITE);
        CheckBookingFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        CheckBookingFrame.setLocationRelativeTo(null);
        CheckBookingFrame.setLayout(null);
        CheckBookingFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        switch (button) {
            case "<< Back":
                CheckBookingFrame.dispose();
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
