/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import javax.swing.*;
import view.Helper.ConstantStyle;
/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class RescheduleBookingScreen {
    JFrame RescheduleBookingFrame = new JFrame("Reschedule Booking Menu");
    JLabel judul;

    public RescheduleBookingScreen() {
        RescheduleBookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RescheduleBookingFrame.setIconImage(ConstantStyle.icon);  
        
        judul = new JLabel("Reschedule Booking :");
        judul.setBounds(50,50,200,100);
        judul.setFont(ConstantStyle.normal);
        
        
        RescheduleBookingFrame.add(judul);
        RescheduleBookingFrame.getContentPane().setBackground(Color.WHITE);
        RescheduleBookingFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        RescheduleBookingFrame.setLocationRelativeTo(null);
        RescheduleBookingFrame.setLayout(null);  
        RescheduleBookingFrame.setVisible(true);
    }
}
