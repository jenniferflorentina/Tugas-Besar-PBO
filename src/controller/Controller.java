/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static model.Enums.TipeUserEnum.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.*;
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.sql.Date;
import java.sql.PreparedStatement;
/**
 *
 * @author Jennifer Florentina
 */
public class Controller {
    static DatabaseHandler conn = new DatabaseHandler();

    //Get User Login Data
    public static Person getPerson(String username) {
        Person user = null;
        conn.connect();
        String query = "SELECT * FROM user WHERE username='"+username+"'";
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
    
     // CHECK USERNAME ADA ATAU GA
    public static boolean cekUsername(String username) {
        ArrayList<Person> users = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM user WHERE username='"+username+"'";
        boolean isExist = false;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                isExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExist;
    }
    
    public static boolean cekPassword(String username, String password) {
        ArrayList<Person> users = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM user WHERE username='"+username+"'";
        boolean isMatch = false;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if(rs.getString("password").equals(getMd5(password))){
                    isMatch = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isMatch;
    }
    
    private static String getMd5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 

    public static boolean insertUser(User user) {
        conn.connect();
        String query = "INSERT INTO user (tipeUser,username,password, email, noKTP, noTelepon,nama,alamat, dateOfBirth) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 1);
            stmt.setString(2, user.getUsername());
            stmt.setString(3, getMd5(user.getPassword()));
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getNoKTP());
            stmt.setString(6, user.getNoTelepon());
            stmt.setString(7, user.getName());
            stmt.setString(8, user.getAlamat());
            stmt.setDate(9, new java.sql.Date(user.getDateOfBirth().getTime()));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Untuk Insert Admin
    public static boolean insertNewAdmin(User user) {
        conn.connect();
        String query = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            //stmt.setInt(1, user.getId());
            stmt.setInt(2, 0);
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getNoKTP());
            stmt.setString(7, user.getNoTelepon());
            stmt.setString(8, user.getName());
            stmt.setString(9, user.getAlamat());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    //Untuk Insert User Baru(Guest)
    public static boolean insertNewUser(User user) {
        conn.connect();
        String query = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            //stmt.setInt(1, user.getId());
            stmt.setInt(2, 1);
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getNoKTP());
            stmt.setString(7, user.getNoTelepon());
            stmt.setString(8, user.getName());
            stmt.setDate(10, (Date) user.getDateOfBirth());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    //Untuk Insert Member Baru
    public static boolean insertNewMember(Member user) {
        conn.connect();
        String query = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            //stmt.setInt(1, user.getId());
            stmt.setInt(2, 2);
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getNoKTP());
            stmt.setString(7, user.getNoTelepon());
            stmt.setString(8, user.getName());
            stmt.setDate(10, (Date) user.getDateOfBirth());
            stmt.setInt(11, user.getPoinMember());
            stmt.setInt(12, user.getMembershipFee());
            int val = (user.isHasPaidFee()) ? 1 : 0;
            stmt.setInt(13, val);
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    //Check Booking 
    //Belum check validasi bookingan
    public static ArrayList cekBooking(int idUser) {
        ArrayList<Transaction> bookingList = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM booking_transaksi WHERE idUser='"+idUser+"'";
        
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Transaction a = new Transaction();
                a.setIdHotel(rs.getInt("idHotel"));
                a.setIdJenisPembayaran(rs.getInt("idJenisPembayaran"));
                a.setJumlahGuest(rs.getInt("jumlahGuest"));
                //a.setTanggalBooking((Date)rs.getString("tanggalBooking"));
                //a.setTanggalCheckIn((Date)rs.getString("check_in"));
                //a.setTanggalCheckOut((Date)rs.getString("check_out"));
                a.setUangMuka(rs.getInt("uangMuka"));
                bookingList.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }
}
