/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Jennifer Florentina
 */
public class AdminMenuScreen {
    
    JFrame adminMenuFrame = new JFrame("Admin Menu");
    
 
    public AdminMenuScreen(){
       adminMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       adminMenuFrame.setIconImage(ConstantStyle.icon);  
       
       
       adminMenuFrame.getContentPane().setBackground(Color.WHITE);
       adminMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
       adminMenuFrame.setLocationRelativeTo(null);
       adminMenuFrame.setLayout(null);  
       adminMenuFrame.setVisible(true);
    }
    
}
