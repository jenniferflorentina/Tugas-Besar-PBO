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
import javax.swing.table.*;
import view.ConstantStyle;

public class HistoryScreen {
    JFrame historyMenuFrame = new JFrame("History Menu");
    JLabel judul;
    JTable table;

    public HistoryScreen() {
        historyMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        historyMenuFrame.setIconImage(ConstantStyle.icon);  
        
        judul = new JLabel("Daftar Transaksi :");
        judul.setBounds(50,50,200,100);
        judul.setFont(ConstantStyle.normal);
        
        DefaultTableModel model = controller.CheckController.getTransactionOut();
        table = new JTable(model);
        table.setBounds(100,150,1200,500); 
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(100,150,1200,500);
        
        historyMenuFrame.add(sp);
        historyMenuFrame.add(judul);
        historyMenuFrame.getContentPane().setBackground(Color.WHITE);
        historyMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        historyMenuFrame.setLocationRelativeTo(null);
        historyMenuFrame.setLayout(null);  
        historyMenuFrame.setVisible(true);
    }
}
