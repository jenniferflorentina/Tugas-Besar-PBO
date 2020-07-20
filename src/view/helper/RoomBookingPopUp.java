package view.helper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import controller.DataController;
import controller.PembayaranController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import static model.enums.TipeUserEnum.GUEST;
import static model.enums.TipeUserEnum.MEMBER;
import model.PersonManager;
import model.TransactionManager;
import view.MemberMenuScreen;
import view.UserMenuScreen;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class RoomBookingPopUp implements ActionListener {

    JFrame roomBookingPopUpFrame = new JFrame("Booking Payment");
    JLabel judulBagianPembayaran, dpLabel;
    ArrayList<JRadioButton> listRButtonPembayaran = new ArrayList<>();
    ButtonGroup buttonGroup = new ButtonGroup();
    JButton nextButton;
    JButton back = new JButton("<< Back");
    int penanda, noKamar;

    public RoomBookingPopUp() {
        roomBookingPopUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        roomBookingPopUpFrame.setIconImage(ConstantStyle.icon);

        judulBagianPembayaran = new JLabel("Pembayaran : ");
        judulBagianPembayaran.setBounds(20, 15, 300, 40);
        judulBagianPembayaran.setFont(ConstantStyle.normal);

        String str = "<html>Total yang harus dibayar " + ConstantStyle.kurensiIndonesia.format(TransactionManager.getInstance().getTransaction().getUangMuka()) + "</html>";
        dpLabel = new JLabel(str);
        dpLabel.setBounds(20, 50, 500, 100);
        dpLabel.setFont(ConstantStyle.normal);

        int height = 150;
        int j = 0;
        for (int i = 1; i < DataController.listJenisPembayaran.size(); i++) {
            String jenisBayar = DataController.listJenisPembayaran.get(i).getJenis();
            listRButtonPembayaran.add(new JRadioButton(jenisBayar));
            listRButtonPembayaran.get(j).setFont(ConstantStyle.small);
            listRButtonPembayaran.get(j).setBounds(20, height, 200, 30);
            height += 38;
            buttonGroup.add(listRButtonPembayaran.get(j));
            roomBookingPopUpFrame.add(listRButtonPembayaran.get(j));
            j++;
        }

        nextButton = new JButton("Next >>");
        nextButton.setBounds(500, 350, 150, 30);
        nextButton.setEnabled(true);
        nextButton.addActionListener(this);

        back.setBounds(20, 500, 100, 30);
        back.addActionListener(this);

        roomBookingPopUpFrame.add(back);
        roomBookingPopUpFrame.add(dpLabel);
        roomBookingPopUpFrame.add(judulBagianPembayaran);
        roomBookingPopUpFrame.add(nextButton);
        roomBookingPopUpFrame.setBackground(Color.WHITE);
        roomBookingPopUpFrame.setSize(700, 750);
        roomBookingPopUpFrame.setLocationRelativeTo(null);
        roomBookingPopUpFrame.setLayout(null);
        roomBookingPopUpFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand();
        switch (choice) {
            case "<< Back":
                roomBookingPopUpFrame.dispose();
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
                a = JOptionPane.showOptionDialog(null, "Total yang harus dibayar : " + ConstantStyle.kurensiIndonesia.format(TransactionManager.getInstance().getTransaction().getUangMuka()) + "\nLanjutkan Pembayaran?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (a == JOptionPane.YES_OPTION) {
                    if (!PembayaranController.updatePembayaran(TransactionManager.getInstance().getTransaction().getIdTransaksi(), TransactionManager.getInstance().getTransaction().getIdJenisPembayaran())) {
                        JOptionPane.showMessageDialog(null, "Failed to make payment!", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Payment succeed!!");
                        roomBookingPopUpFrame.dispose();
                        if (PersonManager.getInstance().getPerson().getTipeUser() == GUEST) {
                            new UserMenuScreen();
                        }
                        if (PersonManager.getInstance().getPerson().getTipeUser() == MEMBER) {
                            new MemberMenuScreen();
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Choose payment!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
