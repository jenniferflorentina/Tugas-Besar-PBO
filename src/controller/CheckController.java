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
import model.Enums.BookingEnum;
import static model.Enums.BookingEnum.*;
import model.Hotel;
import model.Room;
import model.Transaction;
import model.TransactionManager;
import static view.Helper.ConstantStyle.formatter;

/**
 *
 * @author Jennifer Florentina
 */
public class CheckController {
    static DatabaseHandler conn = new DatabaseHandler();
    
    //GET ROOM INDEX IN ARRAY HOTEL
    public static Room getDataRoom(int idHotel, int noKamar){
        Hotel hotel = DataController.listHotel.get(idHotel-1);
        Room room = null;
        for (int i = 0; i < hotel.getRoomList().size(); i++) {
            if(hotel.getRoomList().get(i).getNoKamar() == noKamar){
                room = hotel.getRoomList().get(i);
                break;
            }
        }
        return room;
    }

    // SELECT ALL TRANSACTION
    public static DefaultTableModel getAllTransaction() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Transaksi", "ID Hotel", "ID User","No. Kamar", "Tanggal Booking","Check In","Check Out","Jumlah Guest","Uang Muka","ID Pembayaran","Status"}, 0){ 
            
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
                String noKamar = Integer.toString(rs.getInt("noKamar"));
                String idU = Integer.toString(rs.getInt("idUser"));
                String booking = formatter.format(rs.getDate("tanggalBooking"));
                String checkin = formatter.format(rs.getDate("check_in"));
                String checkout = formatter.format(rs.getDate("check_out"));
                String jumlahGuest = Integer.toString(rs.getInt("jumlahGuest"));
                String uangMuka = Integer.toString(rs.getInt("uangMuka"));
                String idJenisPembayaran = Integer.toString(rs.getInt("idJenisPembayaran"));
                String status = rs.getString("status");
                model.addRow(new Object[]{idT,idH,idU,noKamar,booking,checkin,checkout,jumlahGuest,uangMuka,idJenisPembayaran,status});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (model);
    }
    // SELECT TRANSACTION BY STATUS
    public static DefaultTableModel getTransactionByStatus(int idHotel, BookingEnum status) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Transaksi", "ID Hotel", "ID User","Tipe Kamar","Tanggal Booking","Check In","Check Out"}, 0){ 
            
            @Override
            public boolean isCellEditable(int row, int column){
                return false;//This causes all cells to be not editable
            }
       };
        conn.connect();
        String query = "SELECT * FROM booking_transaksi WHERE status = '"+ status.toString()+"'";
        if(idHotel > 0){
            query = "SELECT * FROM booking_transaksi WHERE status = '"+ status.toString()+"' AND idHotel = "+idHotel;
        }
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String idT = Integer.toString(rs.getInt("idTransaksi"));
                String idH = Integer.toString(rs.getInt("idHotel"));
                String idU = Integer.toString(rs.getInt("idUser"));
                String tipe = getDataRoom(rs.getInt("idHotel"), rs.getInt("noKamar")).getTipe();
                String booking = formatter.format(rs.getDate("tanggalBooking"));
                String checkin = formatter.format(rs.getDate("check_in"));
                String checkout = formatter.format(rs.getDate("check_out"));
                model.addRow(new Object[]{idT,idH,idU,tipe,booking,checkin,checkout});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (model);
    }
 
    // GET ONE TRANSACTION
    public static Transaction getOneTransaction(int idTransaksi) {
        Transaction transaksi = new Transaction();
        conn.connect();
        
        String query = "SELECT * FROM booking_transaksi WHERE idTransaksi = "+idTransaksi;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                transaksi.setIdTransaksi(rs.getInt("idTransaksi"));
                transaksi.setIdHotel(rs.getInt("idHotel"));
                transaksi.setIdUser(rs.getInt("idUser"));
                transaksi.setNoKamar(rs.getInt("noKamar"));
                transaksi.setTanggalBooking(rs.getDate("tanggalBooking"));
                transaksi.setTanggalCheckIn(rs.getDate("check_in"));
                transaksi.setTanggalCheckOut(rs.getDate("check_out"));
                transaksi.setJumlahGuest(rs.getInt("jumlahGuest"));
                transaksi.setUangMuka(rs.getInt("uangMuka"));
                transaksi.setIdJenisPembayaran(rs.getInt("idJenisPembayaran"));
                transaksi.setStatus(BookingEnum.valueOf(rs.getString("status")));
                if(BookingEnum.valueOf(rs.getString("status")).equals(CHECKEDOUT)){
                   transaksi.setListBarangRusak(DataController.getBarangRusak(rs.getInt("idTransaksi")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (transaksi);
    }
    
    // UPDATE CHECK IN STATUS
    public static boolean updateCheckIn(int idTransaksi) {
        conn.connect();
        
        String query = "UPDATE booking_transaksi SET status = '"+ BookingEnum.CHECKEDIN.toString() +"' WHERE idTransaksi = "+idTransaksi;
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    // UPDATE CHECK OUT STATUS
    public static boolean updateCheckOut(int idTransaksi) {
        conn.connect();
        
        String query = "UPDATE booking_transaksi SET status = '"+ BookingEnum.CHECKEDOUT.toString() +"' WHERE idTransaksi = "+idTransaksi;
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
