/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Helper;

import controller.DataController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import model.PersonManager;
import model.User;
import view.MemberMenuScreen;
import view.UserMenuScreen;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class UpgradeToMemberPopUp implements ActionListener {

    JFrame upgradeToMemberFrame = new JFrame("Upgrade To Member Payment");
    JLabel judulBagianPembayaran;
    ArrayList<JRadioButton> listRButtonPembayaran = new ArrayList<>();
    ButtonGroup buttonGroup = new ButtonGroup();
    JButton back = new JButton("<< Back");
    JButton nextButton;

    public UpgradeToMemberPopUp() {
        upgradeToMemberFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        upgradeToMemberFrame.setIconImage(ConstantStyle.icon);

        judulBagianPembayaran = new JLabel("Pembayaran : ");
        judulBagianPembayaran.setBounds(20, 15, 300, 40);
        judulBagianPembayaran.setFont(ConstantStyle.normal);

        int height = 60;
        int j = 0;
        for (int i = 1; i < DataController.listJenisPembayaran.size(); i++) {
            String jenisBayar = DataController.listJenisPembayaran.get(i).getJenis();
            listRButtonPembayaran.add(new JRadioButton(jenisBayar));
            listRButtonPembayaran.get(j).setFont(ConstantStyle.small);
            listRButtonPembayaran.get(j).setBounds(20, height, 200, 30);
            height += 38;
            buttonGroup.add(listRButtonPembayaran.get(j));
            upgradeToMemberFrame.add(listRButtonPembayaran.get(j));
            j++;
        }

        nextButton = new JButton("Next >>");
        nextButton.setBounds(500, 320, 150, 30);
        nextButton.setEnabled(true);
        nextButton.addActionListener(this);

        back.setBounds(20, 500, 100, 30);
        back.addActionListener(this);

        upgradeToMemberFrame.add(back);
        upgradeToMemberFrame.add(judulBagianPembayaran);
        upgradeToMemberFrame.add(nextButton);
        upgradeToMemberFrame.setBackground(Color.WHITE);
        upgradeToMemberFrame.setSize(700, 750);
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
                new UserMenuScreen();
                break;
            case "Next >>":
                nextButtonAction();
                break;
        }
    }

    public void nextButtonAction() {
        int a = JOptionPane.showOptionDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (a == JOptionPane.YES_OPTION) {
            boolean pilihan = false;
            for (int i = 0; i < listRButtonPembayaran.size(); i++) {
                if (listRButtonPembayaran.get(i).isSelected()) {
                    pilihan = true;
                    break;
                }
            }
            if (pilihan) {
                a = JOptionPane.showOptionDialog(null, "Total yang harus dibayar : Rp200.000,-\nLanjutkan Pembayaran?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (a == JOptionPane.YES_OPTION) {
                    User user = (User) PersonManager.getInstance().getPerson();
                    if (controller.Controller.upgradeToMember(user)) {
                        JOptionPane.showMessageDialog(null, "Payment succeed!!");
                        upgradeToMemberFrame.dispose();
                        new MemberMenuScreen();
                    } else {
                        JOptionPane.showMessageDialog(null, "Payment Failed! Please try again", "Alert", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Choose payment!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
