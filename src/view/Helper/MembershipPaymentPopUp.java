/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Helper;

import controller.DataController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import static model.Enums.TipeUserEnum.GUEST;
import static model.Enums.TipeUserEnum.MEMBER;
import model.Member;
import model.PersonManager;
import model.TransactionManager;
import view.MemberMenuScreen;
import view.UserMenuScreen;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class MembershipPaymentPopUp implements ActionListener {

    JFrame membershipPaymentFrame = new JFrame("Membership Payment");
    JPanel panelPembayaran;
    JLabel judulBagianPembayaran, content;
    ArrayList<JRadioButton> listRButtonPembayaran = new ArrayList<>();
    ButtonGroup buttonGroup = new ButtonGroup();
    JButton back = new JButton("<< Back");
    JButton nextButton;

    public MembershipPaymentPopUp() {
        membershipPaymentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        membershipPaymentFrame.setIconImage(ConstantStyle.icon);

        judulBagianPembayaran = new JLabel("Pembayaran : ");
        judulBagianPembayaran.setBounds(20, 15, 300, 40);
        judulBagianPembayaran.setFont(ConstantStyle.normal);

        Member member = (Member) PersonManager.getInstance().getPerson();
        if (member.isHasPaidFee()) {
            String txt = "<html><p>Thank You!\nYou have paid the membership fee</p></html>";
            content = new JLabel(txt);
            content.setFont(ConstantStyle.normal);
            membershipPaymentFrame.add(content);
        } else {
            int height = 60;
            int j = 0;
            for (int i = 1; i < DataController.listJenisPembayaran.size(); i++) {
                String jenisBayar = DataController.listJenisPembayaran.get(i).getJenis();
                listRButtonPembayaran.add(new JRadioButton(jenisBayar));
                listRButtonPembayaran.get(j).setFont(ConstantStyle.small);
                listRButtonPembayaran.get(j).setBounds(20, height, 200, 30);
                height += 38;
                buttonGroup.add(listRButtonPembayaran.get(j));
                membershipPaymentFrame.add(listRButtonPembayaran.get(j));
                j++;
            }
            nextButton = new JButton("Next >>");
            nextButton.setBounds(500, 320, 150, 30);
            nextButton.setEnabled(true);
            nextButton.addActionListener(this);
            membershipPaymentFrame.add(judulBagianPembayaran);
            membershipPaymentFrame.add(nextButton);
        }

        back.setBounds(20, 500, 100, 30);
        back.addActionListener(this);

        membershipPaymentFrame.add(back);
        membershipPaymentFrame.setBackground(Color.WHITE);

        membershipPaymentFrame.setSize(700, 750);
        membershipPaymentFrame.setLocationRelativeTo(null);
        membershipPaymentFrame.setLayout(null);
        membershipPaymentFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();
        switch (choice) {
            case "<< Back":
                membershipPaymentFrame.dispose();
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
        int a = JOptionPane.showOptionDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (a == JOptionPane.YES_OPTION) {
            boolean pilihan = false;
            for (int i = 0; i < listRButtonPembayaran.size(); i++) {
                if (listRButtonPembayaran.get(i).isSelected()) {
                    TransactionManager.getInstance().getTransaction().setIdJenisPembayaran(i + 1);
                    pilihan = true;
                    break;
                }
            }
            if (pilihan) {
                a = JOptionPane.showOptionDialog(null, "Total yang harus dibayar : Rp200.000,-\nLanjutkan Pembayaran?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (a == JOptionPane.YES_OPTION) {
                    Member member = (Member) PersonManager.getInstance().getPerson();
                    if (controller.Controller.paidMembershipFee(member)) {
                        JOptionPane.showMessageDialog(null, "Payment succeed!!");
                        membershipPaymentFrame.dispose();
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
