/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Helper;

import controller.CheckController;
import controller.RoomController;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Enums.BookingEnum;
import model.Room;
import model.Transaction;
import model.TransactionManager;
import view.AdminMenuScreen;
import view.TransactionViewScreen;

/**
 *
 * @author Jennifer Florentina
 */
public class ChooseRoomPopUp {

    JFrame chooseRoomPopUpFrame = new JFrame("Choose Room");
    JLabel judulPilihRoom;
    JTable table;

    public ChooseRoomPopUp(ArrayList<Room> listRoomKosong, int callingCode) {
        chooseRoomPopUpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        chooseRoomPopUpFrame.setIconImage(ConstantStyle.icon);

        judulPilihRoom = new JLabel("Choose New Room :");
        judulPilihRoom.setBounds(10, 10, 300, 30);
        judulPilihRoom.setFont(ConstantStyle.normal);

        DefaultTableModel model = new DefaultTableModel(new String[]{"No Kamar", "Tipe", "Batas Guest", "Harga"}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        for (int i = 0; i < listRoomKosong.size(); i++) {
            String noKamar = Integer.toString(listRoomKosong.get(i).getNoKamar());
            String tipe = listRoomKosong.get(i).getTipe();
            String batas = Integer.toString(listRoomKosong.get(i).getBatasGuest());
            String harga = ConstantStyle.kurensiIndonesia.format(listRoomKosong.get(i).getHarga());
            model.addRow(new Object[]{noKamar, tipe, batas, harga});
        }
        table = new JTable(model);
        table.setBounds(50, 80, 600, 400);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(50, 80, 600, 400);

        table.setRowSelectionAllowed(true);
        ListSelectionModel select = table.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String Data = null;
                String tipeKamar = null;
                int[] row = table.getSelectedRows();
                for (int i = 0; i < row.length; i++) {
                    Data = (String) table.getValueAt(row[i], 0);
                    tipeKamar = (String) table.getValueAt(row[i], 1);
                }
                int a = JOptionPane.showOptionDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (a == JOptionPane.YES_OPTION) {
                    int noKamar = Integer.parseInt(Data);
                    long millis = System.currentTimeMillis();
                    java.sql.Date date = new java.sql.Date(millis);
                    if (callingCode == 0) {
                        Room room = CheckController.getDataRoom(TransactionManager.getInstance().getTransaction().getIdHotel(), TransactionManager.getInstance().getTransaction().getNoKamar());
                        if (ConstantStyle.formatter.format(TransactionManager.getInstance().getTransaction().getTanggalCheckIn()).equals(ConstantStyle.formatter.format(date)) || room.getTipe().equals(tipeKamar)) {
                            if (RoomController.updateRoomBaru(noKamar, TransactionManager.getInstance().getTransaction().getIdTransaksi())) {
                                JOptionPane.showMessageDialog(null, "Update Room Succeed!!");
                                chooseRoomPopUpFrame.dispose();
                                new AdminMenuScreen();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Must proceed first transaction, will redirect to payment method!!");
                            new CheckOutPopUp(1, noKamar);
                            chooseRoomPopUpFrame.dispose();
                        }
                    } else if (callingCode == 1) {
                        Transaction transaksiBaru = TransactionManager.getInstance().getTransaction();
                        transaksiBaru.setIdJenisPembayaran(1);
                        transaksiBaru.setNoKamar(noKamar);
                        transaksiBaru.setTanggalBooking(date);
                        transaksiBaru.setTanggalCheckIn(date);
                        transaksiBaru.setUangMuka(0);
                        if (RoomController.makeNewTransaction(transaksiBaru, BookingEnum.BOOKED)) {
                            JOptionPane.showMessageDialog(null, "Make New Transaction Succeed!!");
                            new TransactionViewScreen();
                        } else {
                            JOptionPane.showMessageDialog(null, "Can't make new transaction!!");
                        }
                        chooseRoomPopUpFrame.dispose();
                        new AdminMenuScreen();
                    }
                }
            }
        });

        chooseRoomPopUpFrame.add(judulPilihRoom);
        chooseRoomPopUpFrame.add(sp);
        chooseRoomPopUpFrame.setSize(700, 650);
        chooseRoomPopUpFrame.setLocationRelativeTo(null);
        chooseRoomPopUpFrame.setLayout(null);
        chooseRoomPopUpFrame.setVisible(true);
    }
}
