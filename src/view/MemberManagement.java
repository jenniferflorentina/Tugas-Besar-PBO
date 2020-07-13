/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class MemberManagement {
    JFrame memberManagementFrame = new JFrame("Member Management Menu");
    JLabel judul;

    public MemberManagement() {
        memberManagementFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        memberManagementFrame.setIconImage(ConstantStyle.icon);  
        
        judul = new JLabel("Member Management :");
        judul.setBounds(50,50,200,100);
        judul.setFont(ConstantStyle.normal);
        
        
        memberManagementFrame.add(judul);
        memberManagementFrame.getContentPane().setBackground(Color.WHITE);
        memberManagementFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        memberManagementFrame.setLocationRelativeTo(null);
        memberManagementFrame.setLayout(null);  
        memberManagementFrame.setVisible(true);
    }
}