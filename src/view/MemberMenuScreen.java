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
public class MemberMenuScreen implements ActionListener {

    JFrame memberMenuFrame = new JFrame("User Menu");
    JButton roomBookingButton, checkBookingButton, cancelBookingButton, rescheduleBookingButton, memberManagementButton;
    JLabel greetingImage, greetingText;

    public MemberMenuScreen() {
        memberMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        memberMenuFrame.setIconImage(ConstantStyle.icon);

        roomBookingButton = new JButton("Room Booking");
        roomBookingButton.setBounds(150, 400, 300, 100);
        roomBookingButton.addActionListener(this);
        roomBookingButton.setFont(ConstantStyle.normal);

        checkBookingButton = new JButton("Check Booking");
        checkBookingButton.setBounds(550, 400, 300, 100);
        checkBookingButton.addActionListener(this);
        checkBookingButton.setFont(ConstantStyle.normal);

        cancelBookingButton = new JButton("Cancel Booking");
        cancelBookingButton.setBounds(150, 520, 300, 100);
        cancelBookingButton.addActionListener(this);
        cancelBookingButton.setFont(ConstantStyle.normal);

        rescheduleBookingButton = new JButton("Reschedule Booking");
        rescheduleBookingButton.setBounds(550, 520, 300, 100);
        rescheduleBookingButton.addActionListener(this);
        rescheduleBookingButton.setFont(ConstantStyle.normal);

        memberManagementButton = new JButton("Member Management");
        memberManagementButton.setBounds(950, 400, 300, 100);
        memberManagementButton.addActionListener(this);
        memberManagementButton.setFont(ConstantStyle.normal);

        Image logo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../asset/img/logo.png"));
        greetingImage = new JLabel(new ImageIcon(logo));
        greetingImage.setBounds(380, 100, 200, 200);
        greetingText = new JLabel("Member Menu");
        greetingText.setBounds(650, 100, 500, 200);
        greetingText.setFont(new Font("Verdana", Font.PLAIN, 32));

        memberMenuFrame.add(greetingImage);
        memberMenuFrame.add(greetingText);
        memberMenuFrame.getContentPane().add(roomBookingButton);
        memberMenuFrame.getContentPane().add(checkBookingButton);
        memberMenuFrame.getContentPane().add(cancelBookingButton);
        memberMenuFrame.getContentPane().add(rescheduleBookingButton);
        memberMenuFrame.getContentPane().add(memberManagementButton);

        memberMenuFrame.getContentPane().setBackground(Color.WHITE);
        memberMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        memberMenuFrame.setLocationRelativeTo(null);
        memberMenuFrame.setLayout(null);
        memberMenuFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String perintah = e.getActionCommand();
        switch (perintah) {
            case "Room Booking":
                memberMenuFrame.dispose();
                new RoomBookingScreen();
                break;
            case "Check Booking":
                memberMenuFrame.dispose();
                new CheckBookingScreen();
                break;
            case "Cancel Booking":
                memberMenuFrame.dispose();
                new CancelBookingScreen();
                break;
            case "Reschedule Booking":
                memberMenuFrame.dispose();
                new RescheduleBookingScreen();
                break;
            case "Member Management":
                memberMenuFrame.dispose();
                new MemberManagementScreen();
                break;
        }
    }
}
