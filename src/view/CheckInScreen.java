/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.Helper.ConstantStyle;
import controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import model.*;

/**
 *
 * @author Jennifer Florentina
 */
public class CheckInScreen implements ActionListener, ItemListener{
    JFrame checkinMenuFrame = new JFrame("Check In Menu");
    JLabel judul, filterLabel;
    JTable table;
    JButton back = new JButton("<< Back");
    JComboBox filter;

    public CheckInScreen() {
        checkinMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkinMenuFrame.setIconImage(ConstantStyle.icon);  
        
        judul = new JLabel("Daftar Transaksi :");
        judul.setBounds(20,10,200,80);
        judul.setFont(ConstantStyle.normal);
        
        filterLabel = new JLabel("Filter : ");
        filterLabel.setBounds(20, 105,80,20);  
        filterLabel.setFont(ConstantStyle.small);
        filter=new JComboBox();    
        filter.setFont(ConstantStyle.small);
        filter.setBounds(100, 105,200,20); 
        filter.addItem("All");
        for (int i = 0; i < DataController.listHotel.size(); i++) {
            filter.addItem(DataController.listHotel.get(i).getNama());
        }
        filter.addItemListener(this);
        
        DefaultTableModel model = controller.CheckController.getTransaction(0);
        table = new JTable(model);
        table.setBounds(100,150,1200,500); 
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(100,150,1200,500);
        
        table.setRowSelectionAllowed(true);  
        ListSelectionModel select= table.getSelectionModel();  
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        select.addListSelectionListener(new ListSelectionListener() {  
              @Override
              public void valueChanged(ListSelectionEvent e) {  
                String Data = null;  
                int[] row = table.getSelectedRows();  
                for (int i = 0; i < row.length; i++) {  
                    Data = (String) table.getValueAt(row[i], 0);  
                }
                int a=JOptionPane.showOptionDialog(null,"Are you sure?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if(a==JOptionPane.YES_OPTION){  
                    int idTransaksi = Integer.parseInt(Data);
                    TransactionManager.getInstance().setTransaction(CheckController.getOneTransaction(idTransaksi));
                    Hotel hotel = DataController.listHotel.get(a);
                    Room room = null;
                    for (int i = 0; i < hotel.getRoomList().size(); i++) {
                        if(hotel.getRoomList().get(i).getNoKamar() == TransactionManager.getInstance().getTransaction().getNoKamar()){
                            room = hotel.getRoomList().get(i);
                            break;
                        }
                    }
                    String str = "ID Transaksi : "+TransactionManager.getInstance().getTransaction().getIdTransaksi()+"\nRincian Kamar : "
                            + room.toString();
                    JOptionPane.showMessageDialog(null,str);
                    checkinMenuFrame.dispose();
                    new AdminMenuScreen();
                }  
              }       
            });  
        
        back.setBounds(20,700,100,30);
        back.addActionListener(this);
        checkinMenuFrame.add(sp);
        checkinMenuFrame.add(back);
        checkinMenuFrame.add(filterLabel);
        checkinMenuFrame.add(filter);
        checkinMenuFrame.add(judul);
        checkinMenuFrame.getContentPane().setBackground(Color.WHITE);
        checkinMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        checkinMenuFrame.setLocationRelativeTo(null);
        checkinMenuFrame.setLayout(null);  
        checkinMenuFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        switch(button){
            case "<< Back":
                checkinMenuFrame.dispose();
                new AdminMenuScreen();
                break;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        int idH=0;
        for (int i = 0; i < DataController.listHotel.size(); i++) {
            if(filter.getSelectedItem().equals(DataController.listHotel.get(i).getNama())){
                idH = DataController.listHotel.get(i).getIdHotel();
                break;
            }
        }
        DefaultTableModel model = controller.CheckController.getTransaction(idH);
        table.setModel(model);
    }
    
}
