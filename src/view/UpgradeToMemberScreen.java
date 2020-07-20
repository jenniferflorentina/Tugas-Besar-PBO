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
import static model.enums.TipeUserEnum.GUEST;
import static model.enums.TipeUserEnum.MEMBER;
import model.PersonManager;
import view.helper.ConstantStyle;
import view.helper.UpgradeToMemberPopUp;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class UpgradeToMemberScreen implements ActionListener {

    JFrame upgradeToMemberFrame = new JFrame("Upgrade To Member Menu");
    JPanel leftPanel;
    JLabel judul, header;
    JButton back = new JButton("<< Back");
    JButton joinButton = new JButton("JOIN NOW!");

    public UpgradeToMemberScreen() {
        upgradeToMemberFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        upgradeToMemberFrame.setIconImage(ConstantStyle.icon);
        header = new JLabel("Upgrade To Member :");
        header.setBounds(425, 30, 500, 100);
        header.setFont(ConstantStyle.bigger);
        
        judul = new JLabel("<html>By Paying Rp200.000 per month you'll enable the membership point,"
                + "<br>which you can exchange for more discount!!!!</html>");
        judul.setBounds(300, 150, 750, 200);
        judul.setFont(ConstantStyle.big);

        joinButton.setBounds(500, 400, 250, 150);
        joinButton.addActionListener(this);
        joinButton.setFont(ConstantStyle.big);

        back.setBounds(20, 700, 100, 30);
        back.addActionListener(this);

        upgradeToMemberFrame.add(back);
        upgradeToMemberFrame.add(header);
        upgradeToMemberFrame.add(joinButton);
        upgradeToMemberFrame.add(judul);
        upgradeToMemberFrame.getContentPane().setBackground(Color.WHITE);
        upgradeToMemberFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        upgradeToMemberFrame.setLocationRelativeTo(null);
        upgradeToMemberFrame.setLayout(null);
        upgradeToMemberFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        switch (button) {
            case "<< Back":
                upgradeToMemberFrame.dispose();
                if (PersonManager.getInstance().getPerson().getTipeUser() == GUEST) {
                    new UserMenuScreen();
                }
                if (PersonManager.getInstance().getPerson().getTipeUser() == MEMBER) {
                    new MemberMenuScreen();
                }
                break;
            case "JOIN NOW!":
                upgradeToMemberFrame.dispose();
                new UpgradeToMemberPopUp();
                break;
        }
    }
}
