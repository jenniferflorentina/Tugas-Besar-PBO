/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.TipeUserEnum.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.*;
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
/**
 *
 * @author Jennifer Florentina
 */
public class Controller {
    static DatabaseHandler conn = new DatabaseHandler();

    // SELECT USER BY USERNAME
    public static ArrayList<Person> getAllUsers() {
        ArrayList<Person> users = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM user";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int tipeUser = rs.getInt("tipeUser");
                Person user;
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
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (users);
    }
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
}
