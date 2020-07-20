/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.helper;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static model.enums.TipeUserEnum.GUEST;
import static model.enums.TipeUserEnum.MEMBER;
import model.Member;
import model.PersonManager;
import view.MemberMenuScreen;
import view.UserMenuScreen;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class StopBeingMemberPopUp implements ActionListener {

    JFrame upgradeToMemberFrame = new JFrame("Stop Being Member");
    JLabel judul, header;
    ButtonGroup buttonGroup = new ButtonGroup();
    JButton back = new JButton("<< Back");
    JButton nextButton;
    int penanda, noKamar;

    public StopBeingMemberPopUp() {
        upgradeToMemberFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        upgradeToMemberFrame.setIconImage(ConstantStyle.icon);
        
        header = new JLabel("Stop Being Member :");
        header.setBounds(425, 30, 500, 100);
        header.setFont(ConstantStyle.bigger);
        
        
        judul = new JLabel("<html>After you stop being a member,<br> all of your point will be gone.</html>");
        judul.setBounds(400, 150, 750, 200);
        judul.setFont(ConstantStyle.big);
        
        nextButton = new JButton("Continue");
        nextButton.setBounds(550, 400, 150, 75);
        nextButton.setFont(ConstantStyle.normal);
        nextButton.addActionListener(this);

        back.setBounds(20, 500, 100, 60);
        back.addActionListener(this);

        upgradeToMemberFrame.add(back);
        upgradeToMemberFrame.add(judul);
        upgradeToMemberFrame.add(header);
        upgradeToMemberFrame.add(nextButton);
        upgradeToMemberFrame.getContentPane().setBackground(Color.WHITE);
        upgradeToMemberFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        upgradeToMemberFrame.setLocationRelativeTo(null);
        upgradeToMemberFrame.setLayout(null);
        upgradeToMemberFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();
        switch (choice) {
            case "<< Back":
                upgradeToMemberFrame.dispose();
                if (PersonManager.getInstance().getPerson().getTipeUser() == GUEST) {
                    new UserMenuScreen();
                }
                if (PersonManager.getInstance().getPerson().getTipeUser() == MEMBER) {
                    new MemberMenuScreen();
                }
                break;
            case "Continue":
                nextButtonAction();
                break;
        }
    }

    public void nextButtonAction() {
        int a = JOptionPane.showOptionDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (a == JOptionPane.YES_OPTION) {
            Member member = (Member) PersonManager.getInstance().getPerson();
            if (controller.Controller.stopBeingMember(member)) {
                JOptionPane.showMessageDialog(null, "You have stop being a member!");
                upgradeToMemberFrame.dispose();
                new UserMenuScreen();
            } else {
                JOptionPane.showMessageDialog(null, "Action Failed! Please try again", "Alert", JOptionPane.WARNING_MESSAGE);
            }

        }
    }
}
