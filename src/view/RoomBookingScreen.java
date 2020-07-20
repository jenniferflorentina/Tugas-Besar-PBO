/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.helper.DateLabelFormatter;
import view.helper.RoomBookingPopUp;
import view.helper.ConstantStyle;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import model.enums.BookingEnum;
import static model.enums.TipeUserEnum.GUEST;
import static model.enums.TipeUserEnum.MEMBER;
import model.PersonManager;
import model.Room;
import model.Transaction;
import model.TransactionManager;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class RoomBookingScreen implements ActionListener, ItemListener {

    JFrame roomBookingFrame = new JFrame("Room Booking");
    JLabel namaLabel, teleponLabel, ktpLabel, jumlahGuestLabel,
            tanggalCheckInLabel, tanggalCheckOutLabel, hotelLabel, kamarLabel;
    JTextField telepon, ktp, nama, hotel, kamar;
    JPanel leftPanel, rightPanel;
    JButton submitButton;
    JButton back = new JButton("<< Back");
    JSpinner jumlahGuest;
    JDatePickerImpl datePicker1, datePicker2;
    UtilDateModel model1, model2;
    Properties p1, p2;
    JDatePanelImpl datePanel1, datePanel2;
    JComboBox c1, c2;

    public RoomBookingScreen() {
        roomBookingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        roomBookingFrame.setIconImage(ConstantStyle.icon);

        leftPanel = new JPanel();
        leftPanel.setLayout(null);

        JLabel judul1 = new JLabel("Booking Information");
        judul1.setBounds(40, 40, 400, 40);
        judul1.setFont(ConstantStyle.normal);

        jumlahGuestLabel = new JLabel("Jumlah Guest     :");
        hotelLabel = new JLabel("Hotel              :");
        kamarLabel = new JLabel("Kamar              :");

        jumlahGuestLabel.setBounds(40, 250, 150, 40);
        jumlahGuestLabel.setFont(ConstantStyle.small);

        SpinnerModel value = new SpinnerNumberModel(0, 0, 10, 1);
        jumlahGuest = new JSpinner(value);
        jumlahGuest.setBounds(190, 250, 350, 40);
        jumlahGuest.setFont(ConstantStyle.small);
        jumlahGuest.setBorder(null);

        c1 = new JComboBox(controller.Controller.getHotelNameList());
        c1.setSelectedIndex(0);
        c1.addItemListener(this);

        hotelLabel.setBounds(40, 130, 150, 40);
        hotelLabel.setFont(ConstantStyle.small);
        c1.setBounds(190, 130, 350, 40);
        c1.setFont(ConstantStyle.small);
        c1.setBorder(null);

        c2 = new JComboBox();
        int i = controller.DataController.getListRoom(controller.Controller.getHotelIDbyName(String.valueOf(c1.getSelectedItem()))).size();
        String[] listNamaKamar = controller.Controller.getRoomNameList(String.valueOf(c1.getSelectedItem()));
        for (int counter = 0; counter < i; counter++) {
            if (!"".equals(listNamaKamar[counter])) {
                c2.addItem(listNamaKamar[counter]);
            }
        }
        c2.setSelectedIndex(0);
        c2.addItemListener(this);

        kamarLabel.setBounds(40, 190, 150, 40);
        kamarLabel.setFont(ConstantStyle.small);
        c2.setBounds(190, 190, 350, 40);
        c2.setFont(ConstantStyle.small);
        c2.setBorder(null);

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

        back.setBounds(20, 700, 100, 30);
        back.addActionListener(this);

        leftPanel.add(judul1);
        leftPanel.add(jumlahGuestLabel);
        leftPanel.add(jumlahGuest);
        leftPanel.add(hotelLabel);
        leftPanel.add(kamarLabel);
        leftPanel.add(c1);
        leftPanel.add(c2);
        leftPanel.add(back);
        leftPanel.add(tanggalCheckInLabel);
        leftPanel.add(tanggalCheckOutLabel);
        leftPanel.add(datePicker1);
        leftPanel.add(datePicker2);
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
        nama.setText(PersonManager.getInstance().getPerson().getName());
        nama.setBounds(190, 130, 350, 40);
        nama.setFont(ConstantStyle.small);
        nama.setBorder(null);

        teleponLabel.setBounds(40, 190, 150, 40);
        teleponLabel.setFont(ConstantStyle.small);
        telepon = new JTextField();
        telepon.setText(PersonManager.getInstance().getPerson().getNoTelepon());
        telepon.setBounds(190, 190, 350, 40);
        telepon.setFont(ConstantStyle.small);
        telepon.setBorder(null);

        ktpLabel.setBounds(40, 250, 150, 40);
        ktpLabel.setFont(ConstantStyle.small);
        ktp = new JTextField();
        ktp.setText(PersonManager.getInstance().getPerson().getNoKTP());
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
        rightPanel.add(ktp);
        rightPanel.add(telepon);
        rightPanel.add(nama);
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
    public void itemStateChanged(ItemEvent e) {
        // if the state combobox is changed 
        if (e.getSource() == c1) {
            c1.setSelectedItem(e);
            c2.removeAllItems();
            int i = controller.DataController.getListRoom(controller.Controller.getHotelIDbyName(String.valueOf(c1.getSelectedItem()))).size();
            String[] listNamaKamar = controller.Controller.getRoomNameList(String.valueOf(c1.getSelectedItem()));
            for (int counter = 0; counter < i; counter++) {
                if (listNamaKamar[counter] != "") {
                    c2.addItem(listNamaKamar[counter]);
                }
            }
            c2.setSelectedIndex(0);
        } else if (e.getSource() == c2) {
            c2.setSelectedItem(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        switch (button) {
            case "<< Back":
                roomBookingFrame.dispose();
                if (PersonManager.getInstance().getPerson().getTipeUser() == GUEST) {
                    new UserMenuScreen();
                }
                if (PersonManager.getInstance().getPerson().getTipeUser() == MEMBER) {
                    new MemberMenuScreen();
                }
                break;
            case "Submit":
                book();
                break;
        }
    }

    public void book() {
        String nama = this.nama.getText();
        int jumlahGuest = (int) this.jumlahGuest.getValue();
        String noTelepon = this.telepon.getText();
        String noKTP = this.ktp.getText();
        String checkInStr = this.datePicker1.getJFormattedTextField().getText();
        String checkOutStr = this.datePicker2.getJFormattedTextField().getText();
        java.sql.Date checkIn = null, checkOut = null;
        checkIn = java.sql.Date.valueOf(checkInStr);
        checkOut = java.sql.Date.valueOf(checkOutStr);
        int lamaInap = checkOut.compareTo(checkIn);

        int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
        if (a == JOptionPane.YES_OPTION) {
            if (nama.length() == 0 || noTelepon.length() == 0 || noKTP.length() == 0 || checkIn == null || checkOut == null || jumlahGuest == 0) {
                JOptionPane.showMessageDialog(null, "Input all the data!", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                Transaction trans = new Transaction();
                trans.setIdHotel(controller.Controller.getHotelIDbyName(String.valueOf(c1.getSelectedItem())));
                trans.setIdUser(PersonManager.getInstance().getPerson().getId());
                trans.setJumlahGuest(jumlahGuest);
                trans.setTanggalBooking(new Date());
                int idHotel = controller.Controller.getHotelIDbyName(String.valueOf(c1.getSelectedItem()));
                ArrayList listKamarKosong = controller.RoomController.cekRoomKosong(idHotel, String.valueOf(c2.getSelectedItem()));
                if (listKamarKosong.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Room Full! Please select another room", "Alert", JOptionPane.WARNING_MESSAGE);
                } else {
                    Room kamar = (Room) listKamarKosong.remove(0);
                    trans.setNoKamar(kamar.getNoKamar());
                    int dp = (int) (kamar.getHarga() * controller.DataController.listHotel.get(idHotel - 1).getMinimumDP()) * lamaInap;
                    trans.setUangMuka(dp);
                    trans.setTanggalCheckIn(checkIn);
                    trans.setTanggalCheckOut(checkOut);
                    trans.setStatus(BookingEnum.BOOKED);
                    TransactionManager.getInstance().setTransaction(trans);
                    if (jumlahGuest > kamar.getBatasGuest()) {
                        JOptionPane.showMessageDialog(null, "Sorry we only permit " + kamar.getBatasGuest() + " in one room", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (controller.Controller.bookingKamar(trans)) {
                            JOptionPane.showMessageDialog(null, "Please pay to confirm your booking");
                            roomBookingFrame.dispose();
                            new RoomBookingPopUp();
                        } else {
                            JOptionPane.showMessageDialog(null, "Data can't be inserted!", "Alert", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        }
    }
}
