/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Hotel;

/**
 *
 * @author Jennifer Florentina
 */
public class InsertDatabase {
    static DatabaseHandler conn = new DatabaseHandler();
    
    // INSERT
    public static void insertHotel() {
        conn.connect();
        String query = "INSERT INTO hotel (nama,lokasi,minimumDP) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, "HOTEL Victoria Secret");
            stmt.setString(2, "Jalan Dago City no.101 , Bandung");
            stmt.setDouble(3, 0.5);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setString(1, "HOTEL Emma Secret");
            stmt.setString(2, "Jalan Pasir City no.101 , Bandung");
            stmt.setDouble(3, 0.3);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setString(1, "HOTEL Jess Secret");
            stmt.setString(2, "Jalan Kopo City no.101 , Bandung");
            stmt.setDouble(3, 0.25);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertBarang() {
        conn.connect();
        String query = "INSERT INTO barang (nama,harga,jumlah) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, "Bantal");
            stmt.setInt(2, 220000);
            stmt.setInt(3, 4);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setString(1, "Handuk");
            stmt.setInt(2, 100000);
            stmt.setInt(3, 2);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setString(1, "TV");
            stmt.setInt(2, 2000000);
            stmt.setInt(3, 1);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setString(1, "Electric kettle");
            stmt.setInt(2, 200000);
            stmt.setInt(3, 1);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public static void insertJenisPembayaran() {
        conn.connect();
        String query = "INSERT INTO jenis_pembayaran (jenis,diskon) VALUES(?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, "Cash");
            stmt.setDouble(2, 0.05);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setString(1, "Debit Card");
            stmt.setDouble(2, 0);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setString(1, "Credit Card");
            stmt.setDouble(2, 0.05);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setString(1, "OVO");
            stmt.setDouble(2, 0.10);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setString(1, "Gopay");
            stmt.setDouble(2, 0.15);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
       insertHotel();
       insertBarang();
       insertJenisPembayaran();
       
      
    }
    
}
