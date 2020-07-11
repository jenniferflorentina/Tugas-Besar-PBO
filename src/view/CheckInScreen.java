/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 *
 * @author Jennifer Florentina
 */
public class CheckInScreen implements ActionListener{
    JFrame checkinMenuFrame = new JFrame("Check In Menu");
    JLabel judul;
    JTable table;
    JButton back = new JButton("<< Back");

    public CheckInScreen() {
        checkinMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkinMenuFrame.setIconImage(ConstantStyle.icon);  
        
        judul = new JLabel("Daftar Transaksi :");
        judul.setBounds(50,50,200,100);
        judul.setFont(ConstantStyle.normal);
        
        DefaultTableModel model = controller.CheckController.getTransaction();
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
                
              }       
            });  
        
        back.setBounds(20,700,100,30);
        back.addActionListener(this);
        checkinMenuFrame.add(sp);
        checkinMenuFrame.add(back);
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
    
}
