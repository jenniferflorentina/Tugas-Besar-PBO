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
public class UpgradeToMemberScreen {
    JFrame upgradeToMemberFrame = new JFrame("Upgrade To Member Menu");
    JLabel judul;

    public UpgradeToMemberScreen() {
        upgradeToMemberFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        upgradeToMemberFrame.setIconImage(ConstantStyle.icon);  
        
        judul = new JLabel("Upgrade To Member :");
        judul.setBounds(50,50,200,100);
        judul.setFont(ConstantStyle.normal);
        
        
        upgradeToMemberFrame.add(judul);
        upgradeToMemberFrame.getContentPane().setBackground(Color.WHITE);
        upgradeToMemberFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        upgradeToMemberFrame.setLocationRelativeTo(null);
        upgradeToMemberFrame.setLayout(null);  
        upgradeToMemberFrame.setVisible(true);
    }
}
