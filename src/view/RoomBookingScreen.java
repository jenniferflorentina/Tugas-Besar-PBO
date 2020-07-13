/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class RoomBookingScreen implements ActionListener {

    JFrame roomBookingFrame = new JFrame("Room Booking");
    JLabel namaLabel, teleponLabel, ktpLabel, jumlahGuestLabel,
            tanggalCheckInLabel, tanggalCheckOutLabel, hotelLabel, kamarLabel;
    JTextField jumlahGuest, telepon, ktp, nama;
    JPanel leftPanel, rightPanel;
    JButton submitButton;
    JDatePickerImpl datePicker1, datePicker2;
    UtilDateModel model1, model2;
    Properties p1, p2;
    JDatePanelImpl datePanel1, datePanel2;

    public RoomBookingScreen() {
        roomBookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        roomBookingFrame.setIconImage(ConstantStyle.icon);

        leftPanel = new JPanel();
        leftPanel.setLayout(null);

        JLabel judul1 = new JLabel("Booking Information");
        judul1.setBounds(40, 40, 400, 40);
        judul1.setFont(ConstantStyle.normal);

        jumlahGuestLabel = new JLabel("Jumlah Guest                  :");
        hotelLabel = new JLabel("Phone                 :");
        kamarLabel = new JLabel("No KTP               :");

        jumlahGuestLabel.setBounds(40, 130, 150, 40);
        jumlahGuestLabel.setFont(ConstantStyle.small);
        jumlahGuest = new JTextField();
        jumlahGuest.setBounds(190, 130, 350, 40);
        jumlahGuest.setFont(ConstantStyle.small);
        jumlahGuest.setBorder(null);

        teleponLabel.setBounds(40, 190, 150, 40);
        teleponLabel.setFont(ConstantStyle.small);
        telepon = new JTextField();
        telepon.setBounds(190, 190, 350, 40);
        telepon.setFont(ConstantStyle.small);
        telepon.setBorder(null);

        ktpLabel.setBounds(40, 250, 150, 40);
        ktpLabel.setFont(ConstantStyle.small);
        ktp = new JTextField();
        ktp.setBounds(190, 250, 350, 40);
        ktp.setFont(ConstantStyle.small);
        ktp.setBorder(null);

        tanggalCheckInLabel = new JLabel("Check In               :");
        tanggalCheckInLabel.setBounds(40, 370, 150, 40);
        tanggalCheckInLabel.setFont(ConstantStyle.small);

        tanggalCheckOutLabel = new JLabel("Check Out             :");
        tanggalCheckOutLabel.setBounds(40, 430, 150, 40);
        tanggalCheckOutLabel.setFont(ConstantStyle.small);

        model1 = new UtilDateModel();
        p1 = new Properties();
        p1.put("text.today", "Today");
        p1.put("text.month", "Month");
        p1.put("text.year", "Year");
        datePanel1 = new JDatePanelImpl(model1, p1);
        datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
        datePicker1.setBounds(190, 370, 350, 40);
        datePicker1.setFont(ConstantStyle.small);
        datePicker1.setBackground(new Color(245, 252, 193));
        datePicker1.setBorder(null);

        model2 = new UtilDateModel();
        p2 = new Properties();
        p2.put("text.today", "Today");
        p2.put("text.month", "Month");
        p2.put("text.year", "Year");
        datePanel2 = new JDatePanelImpl(model2, p2);
        datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
        datePicker2.setBounds(190, 430, 350, 40);
        datePicker2.setFont(ConstantStyle.small);
        datePicker2.setBackground(new Color(245, 252, 193));
        datePicker2.setBorder(null);

        leftPanel.add(judul1);
        leftPanel.add(namaLabel);
        leftPanel.add(teleponLabel);
        leftPanel.add(ktpLabel);
        leftPanel.add(tanggalCheckInLabel);
        leftPanel.add(tanggalCheckOutLabel);
        leftPanel.add(datePicker1);
        leftPanel.add(datePicker2);
        leftPanel.add(nama);
        leftPanel.add(telepon);
        leftPanel.add(ktp);
        leftPanel.setBackground(new Color(245, 252, 193));

        rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(245, 252, 193));

        JLabel judul2 = new JLabel("Personal Information");
        judul1.setBounds(40, 40, 400, 40);
        judul1.setFont(ConstantStyle.normal);
        
        namaLabel = new JLabel("Name                  :");
        teleponLabel = new JLabel("Phone                 :");
        ktpLabel = new JLabel("No KTP               :");

        namaLabel.setBounds(40, 130, 150, 40);
        namaLabel.setFont(ConstantStyle.small);
        nama = new JTextField();
        nama.setBounds(190, 130, 350, 40);
        nama.setFont(ConstantStyle.small);
        nama.setBorder(null);

        teleponLabel.setBounds(40, 190, 150, 40);
        teleponLabel.setFont(ConstantStyle.small);
        telepon = new JTextField();
        telepon.setBounds(190, 190, 350, 40);
        telepon.setFont(ConstantStyle.small);
        telepon.setBorder(null);

        ktpLabel.setBounds(40, 250, 150, 40);
        ktpLabel.setFont(ConstantStyle.small);
        ktp = new JTextField();
        ktp.setBounds(190, 250, 350, 40);
        ktp.setFont(ConstantStyle.small);
        ktp.setBorder(null);

        submitButton = new JButton("Submit");
        submitButton.setBounds(500, 350, 150, 50);
        submitButton.addActionListener(this);
        submitButton.setFont(ConstantStyle.normal);

        rightPanel.add(judul2);
        rightPanel.add(namaLabel);
        rightPanel.add(teleponLabel);
        rightPanel.add(ktpLabel);
        rightPanel.add(submitButton);

        roomBookingFrame.add(leftPanel);
        roomBookingFrame.add(rightPanel);
        roomBookingFrame.getContentPane().setBackground(Color.WHITE);
        roomBookingFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        roomBookingFrame.setLocationRelativeTo(null);
        roomBookingFrame.setLayout(new GridLayout(1, 2, 10, 10));
        roomBookingFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.nama.getText();
        int jumlahGuest = Integer.parseInt(this.jumlahGuest.getText());
        String noTelepon = this.telepon.getText();
        String noKTP = this.ktp.getText();
        Date checkIn, checkOut = null;
        try {
            checkIn = new SimpleDateFormat("yyyy-MM-dd").parse(this.datePicker1.getJFormattedTextField().getText());
            checkOut = new SimpleDateFormat("yyyy-MM-dd").parse(this.datePicker2.getJFormattedTextField().getText());
        } catch (ParseException ex) {
            Logger.getLogger(RegisterScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
//        String alamat = this.alamat.getText();
//        String username = uname.getText();
//        String password = new String(pass.getPassword());
        int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
//        if(a==JOptionPane.YES_OPTION){  
//            if(nama.length()==0 || email.length()==0 || noTelepon.length()==0 || noKTP.length()==0 
//                    || alamat.length()==0|| username.length()==0 || password.length()==0 || birthday == null){
//                JOptionPane.showMessageDialog(null,"Input all the data!","Alert",JOptionPane.WARNING_MESSAGE);
//            }else{
//                User newUser = new User();
//                newUser.setUsername(username);
//                newUser.setPassword(password);
//                newUser.setAlamat(alamat);
//                newUser.setName(nama);
//                newUser.setEmail(email);
//                newUser.setNoKTP(noKTP);
//                newUser.setNoTelepon(noTelepon);
//                newUser.setDateOfBirth(birthday);
//                if(Controller.insertUser(newUser)){
//                    JOptionPane.showMessageDialog(null,"Registration Complete!\nPlease Login!");
//                    registrasiFrame.dispose();
//                    new LogInScreen();
//                }else{
//                    JOptionPane.showMessageDialog(null,"Data can't be inserted!","Alert",JOptionPane.WARNING_MESSAGE);
//                }
//            }
//        }  
    }

    public static void main(String[] args) {
        new RoomBookingScreen();
    }
}
