/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;   
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


/**
 *
 * @author Jennifer Florentina
 */
public class RegisterScreen implements ActionListener{
    JFrame registrasiFrame = new JFrame("Registrasi");
    JLabel usernameLabel, passwordLabel, namaLabel, emailLabel, teleponLabel, ktpLabel,alamatLabel, birthdayLabel;
    JTextField uname, email,telepon,ktp,nama;
    JTextArea alamat;
    JPasswordField pass;
    JPanel leftPanel, rightPanel;
    JButton submitButton;
 
    public RegisterScreen(){
       registrasiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       registrasiFrame.setIconImage(ConstantStyle.icon);  
       
       leftPanel= new JPanel();
       leftPanel.setLayout(null);
       
       JLabel judul1 = new JLabel("Personal Informartion");
       judul1.setBounds(40, 40, 400, 40);
       judul1.setFont(ConstantStyle.normal);
       
       namaLabel = new JLabel("Name                  :");
       emailLabel = new JLabel("Email                  :");
       teleponLabel = new JLabel("Phone                 :");
       alamatLabel = new JLabel("Alamat               :");
       ktpLabel = new JLabel("No KTP               :");
       
       namaLabel.setBounds(40, 130, 150, 40);
       namaLabel.setFont(ConstantStyle.small);
       nama = new JTextField();
       nama.setBounds(190,130,350,40);
       nama.setFont(ConstantStyle.small);
       nama.setBorder(null);
       
       emailLabel.setBounds(40, 190, 150, 40);
       emailLabel.setFont(ConstantStyle.small);
       email = new JTextField();
       email.setBounds(190,190,350,40);
       email.setFont(ConstantStyle.small);
       email.setBorder(null);
       
       teleponLabel.setBounds(40, 250, 150, 40);
       teleponLabel.setFont(ConstantStyle.small);
       telepon = new JTextField();
       telepon.setBounds(190,250,350,40);
       telepon.setFont(ConstantStyle.small);
       telepon.setBorder(null);
       
       alamatLabel.setBounds(40, 430, 150, 40);
       alamatLabel.setFont(ConstantStyle.small);
       alamat = new JTextArea();
       alamat.setBounds(190,430,350,200);
       alamat.setFont(ConstantStyle.small);
       alamat.setBorder(null);
       
       
       ktpLabel.setBounds(40, 310, 150, 40);
       ktpLabel.setFont(ConstantStyle.small);
       ktp = new JTextField();
       ktp.setBounds(190,310,350,40);
       ktp.setFont(ConstantStyle.small);
       ktp.setBorder(null);
       
       birthdayLabel = new JLabel("Date of Birth       :");
       birthdayLabel.setBounds(40, 370, 150, 40);
       birthdayLabel.setFont(ConstantStyle.small);
       
        UtilDateModel model = new UtilDateModel();
        //model.setDate(20,04,2014);
        // Need this...
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        // Don't know about the formatter, but there it is...
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
       datePicker.setBounds(190, 370, 350, 40);
       datePicker.setFont(ConstantStyle.small);
       datePicker.setBackground(new Color(245,252,193));
       datePicker.setBorder(null);
        
       leftPanel.add(judul1);
       leftPanel.add(birthdayLabel);
       leftPanel.add(namaLabel);
       leftPanel.add(teleponLabel);
       leftPanel.add(alamatLabel);
       leftPanel.add(ktpLabel);
       leftPanel.add(emailLabel);
       leftPanel.add(datePicker);
       leftPanel.add(nama);
       leftPanel.add(telepon);
       leftPanel.add(ktp);
       leftPanel.add(email);
       leftPanel.add(alamat);
       leftPanel.setBackground(new Color(245,252,193));
       
       rightPanel= new JPanel();
       rightPanel.setLayout(null);
       rightPanel.setBackground(new Color(245,252,193));
       
       JLabel judul2 = new JLabel("~ Usernama and Password ~");
       judul2.setBounds(40, 40, 400, 40);
       judul2.setFont(ConstantStyle.normal);
       
       usernameLabel = new JLabel("Username     : ");
       usernameLabel.setBounds(40,130,130,40);
       usernameLabel.setFont(ConstantStyle.small);
       
       passwordLabel = new JLabel("Password      : ");
       passwordLabel.setBounds(40,190,130,40);
       passwordLabel.setFont(ConstantStyle.small);
       
       uname = new JTextField(100);
       uname.setBounds(180,130,300,40);
       uname.setFont(ConstantStyle.small);
       uname.setBorder(null);
       
       pass = new JPasswordField(100);   
       pass.setBounds(180,190,300,40);
       pass.setFont(ConstantStyle.small);
       pass.setBorder(null);
       
       submitButton = new JButton("Submit");
       submitButton.setBounds(500,350,150,50);
       submitButton.addActionListener(this);
       submitButton.setFont(ConstantStyle.normal);
       
       rightPanel.add(judul2);
       rightPanel.add(usernameLabel);
       rightPanel.add(uname);
       rightPanel.add(passwordLabel);
       rightPanel.add(pass);
       rightPanel.add(submitButton);
       
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
        
    }
}
