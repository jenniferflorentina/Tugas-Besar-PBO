/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Member;
import model.PersonManager;
import view.helper.ConstantStyle;
import view.helper.MembershipPaymentPopUp;
import view.helper.StopBeingMemberPopUp;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class MemberManagementScreen implements ActionListener {

    JFrame memberManagementFrame = new JFrame("Member Management Menu");
    JLabel judul;
    JButton cekPointButton, bayarMembershipButton, stopBeingMemberButton;
    JButton back = new JButton("<< Back");

    public MemberManagementScreen() {
        memberManagementFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        memberManagementFrame.setIconImage(ConstantStyle.icon);

        judul = new JLabel("Member Management :");
        judul.setBounds(500, 50, 500, 200);
        judul.setFont(ConstantStyle.big);

        cekPointButton = new JButton("Check Point");
        cekPointButton.setBounds(150, 250, 300, 100);
        cekPointButton.addActionListener(this);
        cekPointButton.setFont(ConstantStyle.normal);

        bayarMembershipButton = new JButton("Membership Payment");
        bayarMembershipButton.setBounds(550, 250, 300, 100);
        bayarMembershipButton.addActionListener(this);
        bayarMembershipButton.setFont(ConstantStyle.normal);

        stopBeingMemberButton = new JButton("Stop Being Member");
        stopBeingMemberButton.setBounds(950, 250, 300, 100);
        stopBeingMemberButton.addActionListener(this);
        stopBeingMemberButton.setFont(ConstantStyle.normal);

        back.setBounds(20, 500, 100, 30);
        back.addActionListener(this);

        memberManagementFrame.add(back);
        memberManagementFrame.add(judul);
        memberManagementFrame.getContentPane().add(cekPointButton);
        memberManagementFrame.getContentPane().add(bayarMembershipButton);
        memberManagementFrame.getContentPane().add(stopBeingMemberButton);
        memberManagementFrame.getContentPane().setBackground(Color.WHITE);
        memberManagementFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        memberManagementFrame.setLocationRelativeTo(null);
        memberManagementFrame.setLayout(null);
        memberManagementFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String perintah = e.getActionCommand();
        Member member = (Member) PersonManager.getInstance().getPerson();
        switch (perintah) {
            case "Check Point":
                JOptionPane.showMessageDialog(null, "Point Anda saat ini adalah " + member.getPoinMember());
                break;
            case "Membership Payment":
                memberManagementFrame.dispose();
                new MembershipPaymentPopUp();
                break;
            case "Stop Being Member":
                memberManagementFrame.dispose();
                new StopBeingMemberPopUp();
                break;
            case "<< Back":
                memberManagementFrame.dispose();
                new MemberMenuScreen();
                break;
        }
    }
}
