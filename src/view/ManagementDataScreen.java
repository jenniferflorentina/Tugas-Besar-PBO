package view;

import controller.CheckController;
import controller.DataController;
import controller.RoomController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Room;
import view.Helper.ConstantStyle;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jennifer Florentina
 */
public class ManagementDataScreen implements ActionListener {

    JFrame managementDataFrame = new JFrame("Management Data Room");
    JLabel judul, filterLabel;
    JPanel createPanel, updatePanel;
    JLabel noKamarLabel, tipeLabel, batasGuestLabel, hargaLabel;
    JLabel noKamarLabel2, tipeLabel2, batasGuestLabel2, hargaLabel2;
    JTextField noKamar, tipe, harga;
    JTextField tipe2, harga2;
    JSpinner batasGuest, batasGuest2;
    JButton createButton, updateButton;
    JButton back = new JButton("<< Back");
    JComboBox filterHotel, filterRoom;
    int idH = 1;

    public ManagementDataScreen() {
        managementDataFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        managementDataFrame.setIconImage(ConstantStyle.icon);

        judul = new JLabel("Room Management ~");
        judul.setBounds(20, 10, 500, 40);
        judul.setFont(ConstantStyle.normal);

        filterLabel = new JLabel("Choose Hotel : ");
        filterLabel.setBounds(20, 75, 200, 30);
        filterLabel.setFont(ConstantStyle.small);
        filterHotel = new JComboBox();
        filterHotel.setFont(ConstantStyle.small);
        filterHotel.setBounds(200, 75, 200, 30);
        for (int i = 0; i < DataController.listHotel.size(); i++) {
            filterHotel.addItem(DataController.listHotel.get(i).getNama());
        }
        filterHotel.addItemListener((e) -> {
            for (int i = 0; i < DataController.listHotel.size(); i++) {
                if (filterHotel.getSelectedItem().equals(DataController.listHotel.get(i).getNama())) {
                    idH = DataController.listHotel.get(i).getIdHotel();
                    break;
                }
            }
            filterRoom.removeAllItems();
            filterRoom.addItem("Choose Room");
            for (int i = 0; i < DataController.listHotel.get(idH - 1).getRoomList().size(); i++) {
                filterRoom.addItem(DataController.listHotel.get(idH - 1).getRoomList().get(i).getNoKamar());
            }
        });

        filterRoom = new JComboBox();
        filterRoom.setFont(ConstantStyle.small);
        filterRoom.setBounds(230, 10, 350, 40);
        filterRoom.addItem("Choose Room");
        for (int i = 0; i < DataController.listHotel.get(idH - 1).getRoomList().size(); i++) {
            filterRoom.addItem(DataController.listHotel.get(idH - 1).getRoomList().get(i).getNoKamar());
        }
        filterRoom.addItemListener((e) -> {
            if (filterRoom.getItemCount() != 0) {
                if (!filterRoom.getSelectedItem().equals("Choose Room")) {
                    Room room = CheckController.getDataRoom(idH, (int) filterRoom.getSelectedItem());
                    tipe.setText(room.getTipe());
                    batasGuest.getModel().setValue(room.getBatasGuest());
                    harga.setText(Integer.toString(room.getHarga()));
                }
            }
        });

        noKamarLabel = new JLabel("Nomor Kamar (number) :");
        noKamarLabel.setBounds(10, 10, 200, 40);
        noKamarLabel.setFont(ConstantStyle.small);
        noKamarLabel2 = new JLabel("Nomor Kamar (number) :");
        noKamarLabel2.setBounds(10, 10, 200, 40);
        noKamarLabel2.setFont(ConstantStyle.small);
        noKamar = new JTextField();
        noKamar.setBounds(230, 10, 350, 40);
        noKamar.setFont(ConstantStyle.small);
        noKamar.setBorder(null);

        tipeLabel = new JLabel("Tipe :");
        tipeLabel.setBounds(10, 70, 150, 40);
        tipeLabel.setFont(ConstantStyle.small);
        tipe = new JTextField();
        tipe.setBounds(180, 70, 350, 40);
        tipe.setFont(ConstantStyle.small);
        tipe.setBorder(null);

        tipeLabel2 = new JLabel("Tipe :");
        tipeLabel2.setBounds(10, 70, 150, 40);
        tipeLabel2.setFont(ConstantStyle.small);
        tipe2 = new JTextField();
        tipe2.setBounds(180, 70, 350, 40);
        tipe2.setFont(ConstantStyle.small);
        tipe2.setBorder(null);

        batasGuestLabel = new JLabel("Batas Guest :");
        batasGuestLabel.setBounds(10, 130, 150, 40);
        batasGuestLabel.setFont(ConstantStyle.small);
        SpinnerModel value = new SpinnerNumberModel(0, 0, 10, 1);
        batasGuest = new JSpinner(value);
        batasGuest.setBounds(180, 130, 350, 40);
        batasGuest.setFont(ConstantStyle.small);
        batasGuest.setBorder(null);

        batasGuestLabel2 = new JLabel("Batas Guest :");
        batasGuestLabel2.setBounds(10, 130, 150, 40);
        batasGuestLabel2.setFont(ConstantStyle.small);
        batasGuest2 = new JSpinner(value);
        batasGuest2.setBounds(180, 130, 350, 40);
        batasGuest2.setFont(ConstantStyle.small);
        batasGuest2.setBorder(null);

        hargaLabel = new JLabel("Harga (Rupiah) :");
        hargaLabel.setBounds(10, 190, 150, 40);
        hargaLabel.setFont(ConstantStyle.small);
        harga = new JTextField();
        harga.setBounds(180, 190, 350, 40);
        harga.setFont(ConstantStyle.small);
        harga.setBorder(null);

        hargaLabel2 = new JLabel("Harga (Rupiah) :");
        hargaLabel2.setBounds(10, 190, 150, 40);
        hargaLabel2.setFont(ConstantStyle.small);
        harga2 = new JTextField();
        harga2.setBounds(180, 190, 350, 40);
        harga2.setFont(ConstantStyle.small);
        harga2.setBorder(null);

        createButton = new JButton("CREATE");
        createButton.setBounds(1000, 400, 150, 40);
        createButton.addActionListener(this);
        createButton.setFont(ConstantStyle.small);
        updateButton = new JButton("UPDATE");
        updateButton.setBounds(1000, 400, 150, 40);
        updateButton.addActionListener(this);
        updateButton.setFont(ConstantStyle.small);

        createPanel = new JPanel();
        createPanel.add(noKamarLabel2);
        createPanel.add(noKamar);
        createPanel.add(tipeLabel2);
        createPanel.add(tipe2);
        createPanel.add(batasGuestLabel2);
        createPanel.add(batasGuest2);
        createPanel.add(hargaLabel2);
        createPanel.add(harga2);
        createPanel.add(createButton);
        createPanel.setLayout(null);

        updatePanel = new JPanel();
        updatePanel.add(noKamarLabel);
        updatePanel.add(filterRoom);
        updatePanel.add(tipeLabel);
        updatePanel.add(tipe);
        updatePanel.add(batasGuestLabel);
        updatePanel.add(batasGuest);
        updatePanel.add(hargaLabel);
        updatePanel.add(harga);
        updatePanel.add(updateButton);
        updatePanel.setLayout(null);

        JTabbedPane tp = new JTabbedPane();
        tp.setBounds(100, 150, 1200, 500);
        tp.add("CREATE ROOM", createPanel);
        tp.add("UPDATE ROOM", updatePanel);

        back.setBounds(20, 700, 100, 30);
        back.addActionListener(this);
        managementDataFrame.add(back);
        managementDataFrame.add(judul);
        managementDataFrame.add(filterLabel);
        managementDataFrame.add(tp);
        managementDataFrame.add(filterHotel);
        managementDataFrame.getContentPane().setBackground(Color.WHITE);
        managementDataFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        managementDataFrame.setLocationRelativeTo(null);
        managementDataFrame.setLayout(null);
        managementDataFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        switch (button) {
            case "<< Back":
                managementDataFrame.dispose();
                new AdminMenuScreen();
                break;
            case "CREATE":
                int a = JOptionPane.showOptionDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (a == JOptionPane.YES_OPTION) {
                    String noKamar = this.noKamar.getText();
                    String tipe = this.tipe2.getText();
                    int batasGuest = (int) this.batasGuest2.getValue();
                    String harga = this.harga2.getText();
                    if (noKamar.length() == 0 || tipe.length() == 0 || harga.length() == 0) {
                        JOptionPane.showMessageDialog(null, "Input all the data!", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (RoomController.cekRoomExist(idH, Integer.parseInt(noKamar))) {
                            JOptionPane.showMessageDialog(null, "There is already room with that number!", "Alert", JOptionPane.WARNING_MESSAGE);
                        } else {
                            Room room = new Room(tipe, Integer.parseInt(noKamar), Integer.parseInt(harga), batasGuest);
                            JOptionPane.showMessageDialog(null, "Room Data : \n idHotel : " + idH + "\n noKamar : " + noKamar + "\n Tipe : " + tipe
                                    + "\n Batas Guest :" + batasGuest + "\n Harga " + ConstantStyle.kurensiIndonesia.format(room.getHarga()));
                            if (DataController.insertNewRoom(room, idH)) {
                                JOptionPane.showMessageDialog(null, "Create New Room Succeed");
                                managementDataFrame.dispose();
                                new AdminMenuScreen();
                            } else {
                                JOptionPane.showMessageDialog(null, "Insert failed!", "Alert", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                }
                break;
            case "UPDATE":
                a = JOptionPane.showOptionDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (a == JOptionPane.YES_OPTION) {
                    int noKamar = (int) this.filterRoom.getSelectedItem();
                    String tipe = this.tipe.getText();
                    int batasGuest = (int) this.batasGuest.getValue();
                    String harga = this.harga.getText();
                    if (tipe.length() == 0 || harga.length() == 0) {
                        JOptionPane.showMessageDialog(null, "Input all the data!", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        Room room = new Room(tipe, noKamar, Integer.parseInt(harga), batasGuest);
                        JOptionPane.showMessageDialog(null, "Room Data : \n idHotel : " + idH + "\n noKamar : " + noKamar + "\n Tipe : " + tipe
                                + "\n Batas Guest :" + batasGuest + "\n Harga " + ConstantStyle.kurensiIndonesia.format(room.getHarga()));
                        if (DataController.updateRoom(room, idH)) {
                            JOptionPane.showMessageDialog(null, "Update Room Succeed");
                            managementDataFrame.dispose();
                            new AdminMenuScreen();
                        } else {
                            JOptionPane.showMessageDialog(null, "Update failed!", "Alert", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                break;
        }
    }
}
