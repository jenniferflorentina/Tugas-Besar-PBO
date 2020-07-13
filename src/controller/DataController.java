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

import static controller.Controller.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.*;
import static model.Enums.TipeUserEnum.*;

public class DataController {
    static DatabaseHandler conn = new DatabaseHandler();
    public static ArrayList<Hotel> listHotel = getListHotel();
    public static ArrayList<Pembayaran>  listJenisPembayaran = getAllPembayaran();
    public static ArrayList<Barang>  listBarang = getAllBarang();
    
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
     // GET JENIS BARANG
    public static ArrayList<Barang> getAllBarang() {
        ArrayList<Barang> barang = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM barang";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                barang.add(new Barang(rs.getInt("idBarang"),rs.getInt("harga"),rs.getInt("jumlah"),rs.getString("nama")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (barang);
    }
    
     //Get User By ID
    public static Person getPersonByID(int idUser) {
        Person user = null;
        conn.connect();
        String query = "SELECT * FROM user WHERE idUser="+idUser;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int tipeUser = rs.getInt("tipeUser");
                switch(tipeUser){
                    case 0:
                        user = new Person();
                        user.setTipeUser(ADMIN);
                        break;
                    case 1:
                        user = new User(rs.getDate("dateOfBirth"));
                        user.setTipeUser(GUEST);
                        break;    
                    case 2:
                        user = new Member(rs.getInt("poinMember"),rs.getInt("membershipFee"),rs.getBoolean("bayarMembership"),rs.getDate("dateOfBirth"));
                        user.setTipeUser(MEMBER);
                        break;
                    default:
                        user = new Person();
                        break;
                }
                user.setId(rs.getInt("idUser"));
                user.setName(rs.getString("nama"));
                user.setAlamat(rs.getString("alamat"));
                user.setNoTelepon(rs.getString("noTelepon"));
                user.setNoKTP(rs.getString("noKTP"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (user);
    }
}
