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
public class RegisterScreen implements ActionListener{
    JFrame registrasiFrame = new JFrame("Registrasi");
    JLabel usernameLabel, passwordLabel, namaLabel, emailLabel, teleponLabel, ktpLabel,alamatLabel, birthdayLabel;
    JTextField uname, email,telepon,ktp,nama,birthday;
    JPasswordField pass;
    JButton calendarPopUp;
    JPanel leftPanel, rightPanel;
 
    public RegisterScreen(){
       registrasiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       registrasiFrame.setIconImage(ConstantStyle.icon);  
       
       leftPanel= new JPanel();
       leftPanel.setLayout(null);
       
       birthdayLabel = new JLabel("Date of Birth    :");
       calendarPopUp = new JButton("Pick a Date");
       birthday = new JTextField(20);
       
       birthdayLabel.setBounds(10, 50, 150, 30);
       birthdayLabel.setFont(ConstantStyle.small);
       
       birthday.setBounds(170,50,200,30);
       birthday.setFont(ConstantStyle.small);
       birthday.setBorder(null);
       
       calendarPopUp.setBounds(380,50,150,30);
       calendarPopUp.addActionListener(this);
       calendarPopUp.setFont(ConstantStyle.small);
       
       leftPanel.add(birthday);
       leftPanel.add(birthdayLabel);
       leftPanel.add(calendarPopUp);
       leftPanel.setBackground(new Color(245,252,193));
       
       rightPanel= new JPanel();
       rightPanel.setLayout(null);
       rightPanel.setBackground(new Color(245,252,193));
       
       usernameLabel = new JLabel("Username     : ");
       usernameLabel.setBounds(20,20,130,30);
       usernameLabel.setFont(ConstantStyle.small);
       
       passwordLabel = new JLabel("Password      : ");
       passwordLabel.setBounds(20,70,130,30);
       passwordLabel.setFont(ConstantStyle.small);
       
       uname = new JTextField(100);
       uname.setBounds(160,20,300,30);
       uname.setFont(ConstantStyle.small);
       uname.setBorder(null);
       
       pass = new JPasswordField(100);   
       pass.setBounds(160,70,300,30);
       pass.setFont(ConstantStyle.small);
       pass.setBorder(null);
       
       rightPanel.add(usernameLabel);
       rightPanel.add(uname);
       rightPanel.add(passwordLabel);
       rightPanel.add(pass);
       
       registrasiFrame.add(leftPanel);
       registrasiFrame.add(rightPanel);
       registrasiFrame.getContentPane().setBackground(Color.WHITE);
       registrasiFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
       registrasiFrame.setLocationRelativeTo(null);
       registrasiFrame.setLayout(new GridLayout(1, 2, 10, 10));  
       registrasiFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        switch(button){
            case "Pick a Date" :
                birthday.setText(new DatePicker(registrasiFrame).setPickedDate());
                break;
        }
    }
}
