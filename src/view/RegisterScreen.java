/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.Helper.DateLabelFormatter;
import view.Helper.ConstantStyle;
import controller.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;   
import javax.swing.event.*;
import model.User;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


/**
 *
 * @author Jennifer Florentina
 */
public class RegisterScreen implements ActionListener{
    JFrame registrasiFrame = new JFrame("Registrasi");
    JLabel usernameLabel, passwordLabel, namaLabel, emailLabel, teleponLabel, ktpLabel,alamatLabel, birthdayLabel, message;
    JTextField uname, email,telepon,ktp,nama;
    JTextArea alamat;
    JPasswordField pass;
    JPanel leftPanel, rightPanel;
    JButton submitButton;
    JDatePickerImpl datePicker;
    UtilDateModel model;
    Properties p;
    JDatePanelImpl datePanel;
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
       
       model = new UtilDateModel();
        //model.setDate(20,04,2014);
        // Need this...
       p = new Properties();
       p.put("text.today", "Today");
       p.put("text.month", "Month");
       p.put("text.year", "Year");
       datePanel = new JDatePanelImpl(model, p);
       // Don't know about the formatter, but there it is...
       datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
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
       
       message = new JLabel();
       message.setBounds(40,250,400,40);
       message.setFont(ConstantStyle.small);
       message.setForeground(Color.red);
       
       uname = new JTextField(100);
       uname.setBounds(180,130,300,40);
       uname.setFont(ConstantStyle.small);
       uname.setBorder(null);
       uname.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(Controller.cekUsername(uname.getText())){
                    submitButton.setEnabled(false);
                    message.setText("username already taken");
                }else{
                    submitButton.setEnabled(true);
                    message.setText("");
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(Controller.cekUsername(uname.getText())){
                    submitButton.setEnabled(false);
                    message.setText("username already taken");
                }else{
                    submitButton.setEnabled(true);
                    message.setText("");
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
       
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
       rightPanel.add(message);
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
        String nama = this.nama.getText();
        String email = this.email.getText();
        String noTelepon = this.telepon.getText();
        String noKTP = this.ktp.getText();
        Date birthday = null;
        try {
            birthday = new SimpleDateFormat("yyyy-MM-dd").parse(this.datePicker.getJFormattedTextField().getText());
        } catch (ParseException ex) {
            Logger.getLogger(RegisterScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        String alamat = this.alamat.getText();
        String username = uname.getText();
        String password = new String(pass.getPassword());
        int a=JOptionPane.showOptionDialog(null,"Are you sure?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if(a==JOptionPane.YES_OPTION){  
            if(nama.length()==0 || email.length()==0 || noTelepon.length()==0 || noKTP.length()==0 
                    || alamat.length()==0|| username.length()==0 || password.length()==0 || birthday == null){
                JOptionPane.showMessageDialog(null,"Input all the data!","Alert",JOptionPane.WARNING_MESSAGE);
            }else{
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setPassword(password);
                newUser.setAlamat(alamat);
                newUser.setName(nama);
                newUser.setEmail(email);
                newUser.setNoKTP(noKTP);
                newUser.setNoTelepon(noTelepon);
                newUser.setDateOfBirth(birthday);
                if(Controller.insertUser(newUser)){
                    JOptionPane.showMessageDialog(null,"Registration Complete!\nPlease Login!");
                    registrasiFrame.dispose();
                    new LogInScreen();
                }else{
                    JOptionPane.showMessageDialog(null,"Data can't be inserted!","Alert",JOptionPane.WARNING_MESSAGE);
                }
            }
        }  
        
    }
}
