/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import view.Helper.ConstantStyle;
/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class UserMenuScreen implements ActionListener{
    
    JFrame userMenuFrame = new JFrame("User Menu");
    JButton roomBookingButton, checkBookingButton, cancelBookingButton, rescheduleBookingButton, upgradeToMemberButton;
    JLabel greetingImage, greetingText;
 
    public UserMenuScreen(){
       userMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       userMenuFrame.setIconImage(ConstantStyle.icon);  
       
       roomBookingButton = new JButton("Room Booking");
       roomBookingButton.setBounds(150,400,300,100);
       roomBookingButton.addActionListener(this);
       roomBookingButton.setFont(ConstantStyle.normal);
       
       checkBookingButton = new JButton("Check Booking");
       checkBookingButton.setBounds(550,400,300,100);
       checkBookingButton.addActionListener(this);
       checkBookingButton.setFont(ConstantStyle.normal);
       
       cancelBookingButton = new JButton("Cancel Booking");
       cancelBookingButton.setBounds(150,520,300,100);
       cancelBookingButton.addActionListener(this);
       cancelBookingButton.setFont(ConstantStyle.normal);
       
       rescheduleBookingButton = new JButton("Reschedule Booking");
       rescheduleBookingButton.setBounds(550,520,300,100);
       rescheduleBookingButton.addActionListener(this);
       rescheduleBookingButton.setFont(ConstantStyle.normal);
       
       upgradeToMemberButton = new JButton("Upgrade To Member");
       upgradeToMemberButton.setBounds(950,400,300,100);
       upgradeToMemberButton.addActionListener(this);
       upgradeToMemberButton.setFont(ConstantStyle.normal);
       
       Image logo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../asset/img/logo.png"));
       greetingImage = new JLabel(new ImageIcon(logo));
       greetingImage.setBounds(380,100,200,200);
       greetingText = new JLabel("User Menu");
       greetingText.setBounds(650,100,500,200);
       greetingText.setFont(new Font("Verdana", Font.PLAIN, 32));
       
       userMenuFrame.add(greetingImage);
       userMenuFrame.add(greetingText);
       userMenuFrame.getContentPane().add(roomBookingButton);
       userMenuFrame.getContentPane().add(checkBookingButton);
       userMenuFrame.getContentPane().add(cancelBookingButton);
       userMenuFrame.getContentPane().add(rescheduleBookingButton);
       userMenuFrame.getContentPane().add(upgradeToMemberButton);
       
       userMenuFrame.getContentPane().setBackground(Color.WHITE);
       userMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
       userMenuFrame.setLocationRelativeTo(null);
       userMenuFrame.setLayout(null);  
       userMenuFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String perintah = e.getActionCommand();
        switch(perintah){
            case "Room Booking":
                userMenuFrame.dispose();
                break;
            case "Check Booking":
                userMenuFrame.dispose();
                break;
            case "Cancel Booking":
                userMenuFrame.dispose();
                break;
            case "Reschedule Booking":
                userMenuFrame.dispose();
                break;
            case "Upgrade To Member":
                userMenuFrame.dispose();
                break;
        }
    }
    public static void main(String[] args) {
        new UserMenuScreen();
    }
}
