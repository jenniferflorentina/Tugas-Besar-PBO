/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class DataUserDatabase {

    static DatabaseHandler conn = new DatabaseHandler();

    public static boolean insertUsers() {
        conn.connect();
        String query = "INSERT INTO user (tipeUser, username, password, email, noKTP, noTelepon, nama, alamat) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 0);
            stmt.setString(2, "Jenni");
            stmt.setString(3, getMd5("admin"));
            stmt.setString(4, "admin@secret.hotel.co");
            stmt.setString(5, "123123456492");
            stmt.setString(6, "085123456789");
            stmt.setString(7, "Jennifer Florentina");
            stmt.setString(8, "Bandung");
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 0);
            stmt.setString(2, "Joanna");
            stmt.setString(3, getMd5("admin"));
            stmt.setString(4, "admin@secret.hotel.co");
            stmt.setString(5, "123123456492");
            stmt.setString(6, "085123456789");
            stmt.setString(7, "Eirenika Joanna");
            stmt.setString(8, "Bandung");
            stmt.executeUpdate();
            query = "INSERT INTO user (tipeUser, username, password, email, noKTP, noTelepon, nama, dateOfBirth) VALUES(?,?,?,?,?,?,?,?)";
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 1);
            stmt.setString(2, "Melvin");
            stmt.setString(3, getMd5("guest"));
            stmt.setString(4, "guest@secret.hotel.co");
            stmt.setString(5, "1232231231241");
            stmt.setString(6, "081123123123");
            stmt.setString(7, "Melvin Sebastian");
            String dob = "2000-12-12";
            Date date = Date.valueOf(dob);
            stmt.setDate(8, date);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 1);
            stmt.setString(2, "Densel");
            stmt.setString(3, getMd5("guest"));
            stmt.setString(4, "guest@secret.hotel.co");
            stmt.setString(5, "1232231231241");
            stmt.setString(6, "081123123123");
            stmt.setString(7, "Densel Fabian");
            dob = "2000-02-20";
            date = Date.valueOf(dob);
            stmt.setDate(8, date);
            stmt.executeUpdate();
            query = "INSERT INTO user (tipeUser, username, password, email, noKTP, noTelepon, nama, dateOfBirth, poinMember, membershipFee, bayarMembership) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 2);
            stmt.setString(2, "Shohei");
            stmt.setString(3, getMd5("member"));
            stmt.setString(4, "member@secret.hotel.co");
            stmt.setString(5, "4524341213133");
            stmt.setString(6, "121243523521");
            stmt.setString(7, "Shohei Ohtani");
            dob = "1990-01-21";
            date = Date.valueOf(dob);
            stmt.setDate(8, date);
            stmt.setInt(9, 100);
            stmt.setInt(10, 200000);
            int val = (true) ? 1 : 0;
            stmt.setInt(11, val);
            stmt.executeUpdate();
            stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 2);
            stmt.setString(2, "Yadi");
            stmt.setString(3, getMd5("member"));
            stmt.setString(4, "member@secret.hotel.co");
            stmt.setString(5, "4524341213133");
            stmt.setString(6, "121243523521");
            stmt.setString(7, "Yadier Molina");
            dob = "1983-12-20";
            date = Date.valueOf(dob);
            stmt.setDate(8, date);
            stmt.setInt(9, 300);
            stmt.setInt(10, 200000);
            val = (true) ? 1 : 0;
            stmt.setInt(11, val);
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    private static String getMd5(String input) {
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
        } // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        insertUsers();
    }
}
