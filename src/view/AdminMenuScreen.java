/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.helper.ConstantStyle;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Jennifer Florentina
 */
public class AdminMenuScreen implements ActionListener {

    JFrame adminMenuFrame = new JFrame("Admin Menu");
    JButton checkinButton, checkoutButton, roomchangeButton, stayoverButton, historyButton, managementButton, logOutButton;
    JLabel greetingImage, greetingText;

    public AdminMenuScreen() {
        adminMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminMenuFrame.setIconImage(ConstantStyle.icon);

        checkinButton = new JButton("Check In");
        checkinButton.setBounds(300, 400, 200, 100);
        checkinButton.addActionListener(this);
        checkinButton.setFont(ConstantStyle.normal);

        checkoutButton = new JButton("Check Out");
        checkoutButton.setBounds(600, 400, 200, 100);
        checkoutButton.addActionListener(this);
        checkoutButton.setFont(ConstantStyle.normal);

        roomchangeButton = new JButton("Stay Over");
        roomchangeButton.setBounds(300, 520, 200, 100);
        roomchangeButton.addActionListener(this);
        roomchangeButton.setFont(ConstantStyle.normal);

        stayoverButton = new JButton("Room Change");
        stayoverButton.setBounds(600, 520, 200, 100);
        stayoverButton.addActionListener(this);
        stayoverButton.setFont(ConstantStyle.normal);

        historyButton = new JButton("History");
        historyButton.setBounds(900, 400, 200, 100);
        historyButton.addActionListener(this);
        historyButton.setFont(ConstantStyle.normal);

        managementButton = new JButton("Management Data");
        managementButton.setBounds(900, 520, 200, 100);
        managementButton.addActionListener(this);
        managementButton.setFont(ConstantStyle.small);

        Image logo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../asset/img/logo.png"));
        greetingImage = new JLabel(new ImageIcon(logo));
        greetingImage.setBounds(380, 100, 200, 200);
        greetingText = new JLabel("Admin Menu");
        greetingText.setBounds(650, 100, 500, 200);
        greetingText.setFont(new Font("Verdana", Font.PLAIN, 32));
        
        logOutButton = new JButton("Log Out");
        logOutButton.setBounds(20, 650, 150, 50);
        logOutButton.addActionListener(this);
        logOutButton.setFont(ConstantStyle.small);
        
        adminMenuFrame.add(greetingImage);
        adminMenuFrame.add(logOutButton);
        adminMenuFrame.add(greetingText);
        adminMenuFrame.getContentPane().add(checkinButton); // Adds Button to content pane of frame
        adminMenuFrame.getContentPane().add(checkoutButton);
        adminMenuFrame.getContentPane().add(roomchangeButton);
        adminMenuFrame.getContentPane().add(stayoverButton);
        adminMenuFrame.getContentPane().add(historyButton);
        adminMenuFrame.getContentPane().add(managementButton);

        adminMenuFrame.getContentPane().setBackground(Color.WHITE);
        adminMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        adminMenuFrame.setLocationRelativeTo(null);
        adminMenuFrame.setLayout(null);
        adminMenuFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String perintah = e.getActionCommand();
        switch (perintah) {
            case "Check In":
                adminMenuFrame.dispose();
                new CheckInScreen();
                break;
            case "Check Out":
                adminMenuFrame.dispose();
                new CheckOutScreen();
                break;
            case "Stay Over":
                adminMenuFrame.dispose();
                new StayOverScreen();
                break;
            case "Room Change":
                adminMenuFrame.dispose();
                new RoomChangeScreen();
                break;
            case "History":
                adminMenuFrame.dispose();
                new HistoryScreen();
                break;
            case "Management Data":
                adminMenuFrame.dispose();
                new ManagementDataScreen();
                break;
            case "Log Out":
                int jawab = JOptionPane.showOptionDialog(null, 
                    "Ingin Keluar?", 
                    "Keluar", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
    
                if(jawab == JOptionPane.YES_OPTION){
                    adminMenuFrame.dispose();
                    new LogInScreen();
                }
                
                break;
        }
    }
}
