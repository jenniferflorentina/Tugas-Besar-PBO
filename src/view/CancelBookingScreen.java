/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import view.Helper.ConstantStyle;
/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class CancelBookingScreen {
    JFrame cancelBookingFrame = new JFrame("Cancel Booking Menu");
    JLabel judul;

    public CancelBookingScreen() {
        cancelBookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cancelBookingFrame.setIconImage(ConstantStyle.icon);  
        
        judul = new JLabel("Cancel Booking :");
        judul.setBounds(50,50,200,100);
        judul.setFont(ConstantStyle.normal);
        
        
        cancelBookingFrame.add(judul);
        cancelBookingFrame.getContentPane().setBackground(Color.WHITE);
        cancelBookingFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        cancelBookingFrame.setLocationRelativeTo(null);
        cancelBookingFrame.setLayout(null);  
        cancelBookingFrame.setVisible(true);
    }
}
