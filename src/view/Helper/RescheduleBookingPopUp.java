/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Helper;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import static model.Enums.TipeUserEnum.GUEST;
import static model.Enums.TipeUserEnum.MEMBER;
import model.PersonManager;
import model.TransactionManager;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import view.MemberMenuScreen;
import view.UserMenuScreen;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class RescheduleBookingPopUp implements ActionListener {

    JFrame recheduleBookingPopUpFrame = new JFrame("Reschedule Booking");
    JButton nextButton;
    JPanel leftPanel;
    JButton submitButton;
    JButton back = new JButton("<< Back");
    JDatePickerImpl datePicker1, datePicker2;
    UtilDateModel model1, model2;
    Properties p1, p2;
    JDatePanelImpl datePanel1, datePanel2;
    JLabel jumlahGuestLabel, judul, tanggalCheckInLabel, tanggalCheckOutLabel, hotelLabel, kamarLabel;

    public RescheduleBookingPopUp() {
        recheduleBookingPopUpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        recheduleBookingPopUpFrame.setIconImage(ConstantStyle.icon);

        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBounds(0, 0, 900, 700);

        JLabel judul1 = new JLabel("Booking Information");
        judul1.setBounds(40, 40, 400, 40);
        judul1.setFont(ConstantStyle.normal);

        tanggalCheckInLabel = new JLabel("Check In               :");
        tanggalCheckInLabel.setBounds(40, 130, 150, 40);
        tanggalCheckInLabel.setFont(ConstantStyle.small);

        tanggalCheckOutLabel = new JLabel("Check Out             :");
        tanggalCheckOutLabel.setBounds(40, 190, 150, 40);
        tanggalCheckOutLabel.setFont(ConstantStyle.small);

        model1 = new UtilDateModel();
        p1 = new Properties();
        p1.put("text.today", "Today");
        p1.put("text.month", "Month");
        p1.put("text.year", "Year");
        datePanel1 = new JDatePanelImpl(model1, p1);
        datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
        datePicker1.setBounds(190, 130, 350, 40);
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
        datePicker2.setBounds(190, 190, 350, 40);
        datePicker2.setFont(ConstantStyle.small);
        datePicker2.setBackground(new Color(245, 252, 193));
        datePicker2.setBorder(null);

        nextButton = new JButton("Next >>");
        nextButton.setBounds(500, 250, 150, 30);
        nextButton.addActionListener(this);

        back.setBounds(20, 500, 100, 30);
        back.addActionListener(this);

        leftPanel.add(judul1);
        leftPanel.add(nextButton);
        leftPanel.add(back);
        leftPanel.add(tanggalCheckInLabel);
        leftPanel.add(tanggalCheckOutLabel);
        leftPanel.add(datePicker1);
        leftPanel.add(datePicker2);
        leftPanel.setBackground(new Color(245, 252, 193));

        recheduleBookingPopUpFrame.add(leftPanel);
        recheduleBookingPopUpFrame.setSize(700, 750);
        recheduleBookingPopUpFrame.setLocationRelativeTo(null);
        recheduleBookingPopUpFrame.setLayout(null);
        recheduleBookingPopUpFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();
        switch (choice) {
            case "<< Back":
                recheduleBookingPopUpFrame.dispose();
                if (PersonManager.getInstance().getPerson().getTipeUser() == GUEST) {
                    new UserMenuScreen();
                }
                if (PersonManager.getInstance().getPerson().getTipeUser() == MEMBER) {
                    new MemberMenuScreen();
                }
                break;
            case "Next >>":
                nextButtonAction();
                break;
        }
    }

    public void nextButtonAction() {
        String checkInStr = this.datePicker1.getJFormattedTextField().getText();
        String checkOutStr = this.datePicker2.getJFormattedTextField().getText();
        java.sql.Date checkIn = java.sql.Date.valueOf(checkInStr), checkOut = java.sql.Date.valueOf(checkOutStr);
        int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
        if (a == JOptionPane.YES_OPTION) {
            if (checkIn == null || checkOut == null) {
                JOptionPane.showMessageDialog(null, "Input all the data!", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                TransactionManager.getInstance().getTransaction().setTanggalCheckIn(checkIn);
                TransactionManager.getInstance().getTransaction().setTanggalCheckOut(checkOut);
                if (controller.CheckController.RescheduleBooking(TransactionManager.getInstance().getTransaction())) {
                    JOptionPane.showMessageDialog(null, "Data updated!");
                    recheduleBookingPopUpFrame.dispose();
                    if (PersonManager.getInstance().getPerson().getTipeUser() == GUEST) {
                        new UserMenuScreen();
                    }
                    if (PersonManager.getInstance().getPerson().getTipeUser() == MEMBER) {
                        new MemberMenuScreen();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Data can't be inserted!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}
