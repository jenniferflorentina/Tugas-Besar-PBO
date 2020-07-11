/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Jennifer Florentina
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.*;

public class DataController {
    static DatabaseHandler conn = new DatabaseHandler();
    public static ArrayList<Hotel> listHotel = getListHotel();
    public static ArrayList<Pembayaran>  listJenisPembayaran = getAllPembayaran();
    
    //SELECT ALL HOTEL
    public static ArrayList<Hotel> getListHotel(){
        ArrayList<Hotel> listHotel = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM hotel";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int idHotel = rs.getInt("idHotel");
                listHotel.add(new Hotel(idHotel,rs.getString("lokasi"),rs.getString("nama"),rs.getDouble("minimumDP"),getListRoom(idHotel)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (listHotel);
    }
     // SELECT ALL ROOM IN ONE HOTEL
    public static ArrayList<Room> getListRoom(int idHotel) {
        ArrayList<Room> listRoom = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM room WHERE idHotel='"+idHotel+"'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                listRoom.add(new Room(rs.getString("tipe"),rs.getInt("noKamar") , rs.getInt("harga"), rs.getInt("batasGuest")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listRoom;
    }
       
    // GET JENIS PEMBAYARAN
    public static ArrayList<Pembayaran> getAllPembayaran() {
        ArrayList<Pembayaran> pembayaran = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM jenis_pembayaran";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Pembayaran jenis = new Pembayaran();
                jenis.setIdJenisPembayaran(rs.getInt("idJenisPembayaran"));
                jenis.setJenis(rs.getString("jenis"));
                jenis.setDiskon(rs.getDouble("diskon"));
                pembayaran.add(jenis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (pembayaran);
    }
}
