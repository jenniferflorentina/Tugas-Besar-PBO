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
import model.TransactionManager;
import view.Helper.ConstantStyle;

public class TransactionViewScreen {
    JFrame transactionViewFrame = new JFrame("Transaction");
    JLabel judul,isi;

    public TransactionViewScreen() {
        transactionViewFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        transactionViewFrame.setIconImage(ConstantStyle.icon);  
        
        judul = new JLabel("Detail Transaksi :");
        judul.setBounds(10,10,200,100);
        judul.setFont(ConstantStyle.normal);
        
        isi = new JLabel();
        isi.setFont(ConstantStyle.small);
        isi.setBounds(10,120,690,500);
        isi.setText(TransactionManager.getInstance().toString());

        transactionViewFrame.add(judul);
        transactionViewFrame.add(isi);
        transactionViewFrame.setSize(700, 700);  
        transactionViewFrame.getContentPane().setBackground(Color.WHITE);
        transactionViewFrame.setLocationRelativeTo(null);
        transactionViewFrame.setLayout(null);  
        transactionViewFrame.setVisible(true);
    }
}
