/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Jennifer Florentina
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import view.ConstantStyle;

public class StayOverScreen {
    JFrame stayOverMenuFrame = new JFrame("Stay Over Menu");
    JLabel judul;
    JTable table;

    public StayOverScreen() {
        stayOverMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stayOverMenuFrame.setIconImage(ConstantStyle.icon);  
        
        judul = new JLabel("Daftar Transaksi :");
        judul.setBounds(50,50,200,100);
        judul.setFont(ConstantStyle.normal);
        
        DefaultTableModel model = controller.CheckController.getTransactionOut();
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
        
        stayOverMenuFrame.add(sp);
        stayOverMenuFrame.add(judul);
        stayOverMenuFrame.getContentPane().setBackground(Color.WHITE);
        stayOverMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        stayOverMenuFrame.setLocationRelativeTo(null);
        stayOverMenuFrame.setLayout(null);  
        stayOverMenuFrame.setVisible(true);
    }
}
