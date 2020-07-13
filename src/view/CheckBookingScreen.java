/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import model.PersonManager;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class CheckBookingScreen {
    JFrame CheckBookingFrame = new JFrame("Check Booking Menu");
    JLabel judul;
    JTable table;

    public CheckBookingScreen() {
        CheckBookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CheckBookingFrame.setIconImage(ConstantStyle.icon);  
        
        judul = new JLabel("Daftar Booking :");
        judul.setBounds(50,50,200,100);
        judul.setFont(ConstantStyle.normal);
        
        DefaultTableModel model = null;
        table = new JTable(model);
        table.setBounds(100,150,1200,500); 
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(100,150,1200,500);
        
        CheckBookingFrame.add(sp);
        CheckBookingFrame.add(judul);
        CheckBookingFrame.getContentPane().setBackground(Color.WHITE);
        CheckBookingFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        CheckBookingFrame.setLocationRelativeTo(null);
        CheckBookingFrame.setLayout(null);  
        CheckBookingFrame.setVisible(true);
    }
}
