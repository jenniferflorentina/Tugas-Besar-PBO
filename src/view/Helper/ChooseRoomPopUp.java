/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Helper;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Enums.BookingEnum;
import model.Room;


/**
 *
 * @author Jennifer Florentina
 */
public class ChooseRoomPopUp {
    JFrame chooseRoomPopUpFrame = new JFrame("Choose Room");
    JLabel judulPilihRoom;
    JTable table;
    
    public ChooseRoomPopUp(ArrayList<Room> listRoomKosong){
        chooseRoomPopUpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        chooseRoomPopUpFrame.setIconImage(ConstantStyle.icon);  
        
        judulPilihRoom = new JLabel("Choose New Room :");
        judulPilihRoom.setBounds(10, 10, 300, 30);
        judulPilihRoom.setFont(ConstantStyle.normal);
        
        DefaultTableModel model = new DefaultTableModel(new String[]{"No Kamar", "Tipe", "Batas Guest","Harga"}, 0){ 
            
            @Override
            public boolean isCellEditable(int row, int column){
                return false;//This causes all cells to be not editable
            }
        };
        for (int i = 0; i < listRoomKosong.size(); i++) {
            String noKamar = Integer.toString(listRoomKosong.get(i).getNoKamar());
            String tipe = listRoomKosong.get(i).getTipe();
            String batas = Integer.toString(listRoomKosong.get(i).getBatasGuest());
            String harga = ConstantStyle.kurensiIndonesia.format(listRoomKosong.get(i).getHarga());
            model.addRow(new Object[]{noKamar,tipe,batas,harga});
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
                int[] row = table.getSelectedRows();
                for (int i = 0; i < row.length; i++) {
                    Data = (String) table.getValueAt(row[i], 0);
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
