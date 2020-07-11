/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jennifer Florentina
 */
public class CheckController {
    static DatabaseHandler conn = new DatabaseHandler();
    
    // SELECT ALL TRANSACTION
    public static DefaultTableModel getAllTransaction() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Transaksi", "ID Hotel", "ID User", "Tanggal Booking","Check In","Check Out"}, 0){ 
            
            @Override
            public boolean isCellEditable(int row, int column){
                return false;//This causes all cells to be not editable
            }
       };
        conn.connect();
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
        
        String query = "SELECT * FROM booking_transaksi";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String idT = Integer.toString(rs.getInt("idTransaksi"));
                String idH = Integer.toString(rs.getInt("idHotel"));
                String idU = Integer.toString(rs.getInt("idUser"));
                String booking = formatter.format(rs.getDate("tanggalBooking"));
                String checkin = formatter.format(rs.getDate("check_in"));
                String checkout = formatter.format(rs.getDate("check_out"));
                model.addRow(new Object[]{idT,idH,idU,booking,checkin,checkout});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (model);
    }
    // SELECT TRANSACTION
    public static DefaultTableModel getTransaction() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Transaksi", "ID Hotel", "ID User", "Tanggal Booking","Check In","Check Out"}, 0){ 
            
            @Override
            public boolean isCellEditable(int row, int column){
                return false;//This causes all cells to be not editable
            }
       };
        conn.connect();
        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis); 
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
        
        String query = "SELECT * FROM booking_transaksi WHERE check_in >"+date;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String idT = Integer.toString(rs.getInt("idTransaksi"));
                String idH = Integer.toString(rs.getInt("idHotel"));
                String idU = Integer.toString(rs.getInt("idUser"));
                String booking = formatter.format(rs.getDate("tanggalBooking"));
                String checkin = formatter.format(rs.getDate("check_in"));
                String checkout = formatter.format(rs.getDate("check_out"));
                model.addRow(new Object[]{idT,idH,idU,booking,checkin,checkout});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (model);
    }
    // SELECT TRANSACTION FOR CHECKOUT
    public static DefaultTableModel getTransactionOut() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Transaksi", "ID Hotel", "ID User", "Tanggal Booking","Check In","Check Out"}, 0){ 
            
            @Override
            public boolean isCellEditable(int row, int column){
                return false;//This causes all cells to be not editable
            }
       };
        conn.connect();
        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis); 
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
        
        String query = "SELECT * FROM booking_transaksi WHERE check_in < "+date+" AND check_out > "+date;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String idT = Integer.toString(rs.getInt("idTransaksi"));
                String idH = Integer.toString(rs.getInt("idHotel"));
                String idU = Integer.toString(rs.getInt("idUser"));
                String booking = formatter.format(rs.getDate("tanggalBooking"));
                String checkin = formatter.format(rs.getDate("check_in"));
                String checkout = formatter.format(rs.getDate("check_out"));
                model.addRow(new Object[]{idT,idH,idU,booking,checkin,checkout});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (model);
    }
}
