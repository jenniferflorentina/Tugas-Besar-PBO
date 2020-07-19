/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    // INSERT

    public static void insertRoom() {
        conn.connect();
        String query = "INSERT INTO room (idHotel,tipe,batasGuest,harga,noKamar) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 1);
            stmt.setString(2, "Tower Room");
            stmt.setInt(3, 2);
            stmt.setInt(4, 1899000);
            stmt.setInt(5, 1101);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 1);
            stmt.setString(2, "Tower Room");
            stmt.setInt(3, 2);
            stmt.setInt(4, 1899000);
            stmt.setInt(5, 1102);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 1);
            stmt.setString(2, "Suite Room");
            stmt.setInt(3, 2);
            stmt.setInt(4, 2999000);
            stmt.setInt(5, 2101);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 1);
            stmt.setString(2, "Suite Room");
            stmt.setInt(3, 2);
            stmt.setInt(4, 2999000);
            stmt.setInt(5, 2102);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 2);
            stmt.setString(2, "Executive Room");
            stmt.setInt(3, 2);
            stmt.setInt(4, 1549000);
            stmt.setInt(5, 1201);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 2);
            stmt.setString(2, "Executive Room");
            stmt.setInt(3, 2);
            stmt.setInt(4, 1549000);
            stmt.setInt(5, 1202);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 2);
            stmt.setString(2, "Tower Room");
            stmt.setInt(3, 2);
            stmt.setInt(4, 1899000);
            stmt.setInt(5, 2201);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 2);
            stmt.setString(2, "Tower Room");
            stmt.setInt(3, 2);
            stmt.setInt(4, 1899000);
            stmt.setInt(5, 2202);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 3);
            stmt.setString(2, "Deluxe Room");
            stmt.setInt(3, 2);
            stmt.setInt(4, 1049000);
            stmt.setInt(5, 1301);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 3);
            stmt.setString(2, "Deluxe Room");
            stmt.setInt(3, 2);
            stmt.setInt(4, 1049000);
            stmt.setInt(5, 1302);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 3);
            stmt.setString(2, "Executive Room");
            stmt.setInt(3, 2);
            stmt.setInt(4, 1549000);
            stmt.setInt(5, 2301);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 3);
            stmt.setString(2, "Executive Room");
            stmt.setInt(3, 2);
            stmt.setInt(4, 1549000);
            stmt.setInt(5, 2302);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        insertRoom();

    }

}
