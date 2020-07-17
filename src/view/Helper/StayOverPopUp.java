/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Helper;

import controller.CheckController;
import controller.RoomController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import model.Room;
import model.TransactionManager;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import view.AdminMenuScreen;
import view.RegisterScreen;
/**
 *
 * @author Jennifer Florentina
 */
public class StayOverPopUp implements ActionListener {
    JFrame stayOverPopUpFrame = new JFrame("Choose A Date");
    JLabel judul;
    JButton submitButton;
    JDatePickerImpl datePicker;
    UtilDateModel model;
    Properties p;
    JDatePanelImpl datePanel;
    public StayOverPopUp(){      
        stayOverPopUpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        stayOverPopUpFrame.setIconImage(ConstantStyle.icon);
        
        judul = new JLabel("Choose A Date : ");
        judul.setBounds(20, 25, 200, 50);
        judul.setFont(ConstantStyle.small);
        
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
        datePicker.setBounds(240,40, 250, 50);
        datePicker.setFont(ConstantStyle.small);
        datePicker.setBorder(null);
        
        submitButton = new JButton("Next>>");
        submitButton.setBounds(400, 100, 150, 30);
        submitButton.addActionListener(this);
        submitButton.setFont(ConstantStyle.small);
        
        stayOverPopUpFrame.add(judul);
        stayOverPopUpFrame.add(datePicker);
        stayOverPopUpFrame.add(submitButton);
        stayOverPopUpFrame.setSize(600, 200); 
        stayOverPopUpFrame.setLocationRelativeTo(null);
        stayOverPopUpFrame.setLayout(null);  
        stayOverPopUpFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Date newCheckOut = null;
        try {
            newCheckOut = new SimpleDateFormat("yyyy-MM-dd").parse(this.datePicker.getJFormattedTextField().getText());
        } catch (ParseException ex) {
            Logger.getLogger(RegisterScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(RoomController.cekRoomAvailableForStayOver(TransactionManager.getInstance().getTransaction().getIdHotel(),TransactionManager.getInstance().getTransaction().getNoKamar(), new java.sql.Date(newCheckOut.getTime()), new java.sql.Date(TransactionManager.getInstance().getTransaction().getTanggalCheckOut().getTime()) )){
            RoomController.updateCheckOutDate(TransactionManager.getInstance().getTransaction().getIdTransaksi(), new java.sql.Date(newCheckOut.getTime()) );
            JOptionPane.showMessageDialog(null, "Check Out Date Updated!!");
            stayOverPopUpFrame.dispose();
            new AdminMenuScreen();
        }else{
            JOptionPane.showMessageDialog(null, "Room is Already Booked!\nMake new transaction for another room", "Alert", JOptionPane.WARNING_MESSAGE);
            TransactionManager.getInstance().getTransaction().setTanggalCheckOut(newCheckOut);
            Room room;
            room = CheckController.getDataRoom(TransactionManager.getInstance().getTransaction().getIdHotel(), TransactionManager.getInstance().getTransaction().getNoKamar());
            ArrayList<Room> listRoomKosong = RoomController.cekRoomKosong(TransactionManager.getInstance().getTransaction().getIdHotel(), room.getTipe());
            stayOverPopUpFrame.dispose();
            new ChooseRoomPopUp(listRoomKosong,1);
        }
    }
}
