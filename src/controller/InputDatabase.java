/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class InputDatabase {
    static DatabaseHandler conn = new DatabaseHandler();
    
    public static boolean insertUsers() {
        conn.connect();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String query = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(2, 0);
            stmt.setString(3, "Jenni");
            stmt.setString(4, "admin");
            stmt.setString(5, "admin@secret.hotel.co");
            stmt.setString(6, "123123456492");
            stmt.setString(7, "085123456789");
            stmt.setString(8, "Jennifer Florentina");
            stmt.setString(9, "Bandung");
            stmt.executeUpdate();
            stmt.setInt(2, 0);
            stmt.setString(3, "Joanna");
            stmt.setString(4, "admin");
            stmt.setString(5, "admin@secret.hotel.co");
            stmt.setString(6, "123123456492");
            stmt.setString(7, "085123456789");
            stmt.setString(8, "Eirenika Joanna");
            stmt.setString(9, "Bandung");
            stmt.executeUpdate();
            stmt.setInt(2, 1);
            stmt.setString(3, "Melvin");
            stmt.setString(4, "guest");
            stmt.setString(5, "guest@secret.hotel.co");
            stmt.setString(6, "1232231231241");
            stmt.setString(7, "081123123123");
            stmt.setString(8, "Melvin Sebastian");
            String dob = "12-12-2000";
            Date date = null;
            try {
                date = (Date) formatter.parse(dob);
            } catch (ParseException ex) {
                Logger.getLogger(InputDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
            stmt.setDate(10, date);
            stmt.executeUpdate();
            stmt.setInt(2, 1);
            stmt.setString(3, "Densel");
            stmt.setString(4, "guest");
            stmt.setString(5, "guest@secret.hotel.co");
            stmt.setString(6, "1232231231241");
            stmt.setString(7, "081123123123");
            stmt.setString(8, "Densel Fabian");
            dob = "21-02-2000";
            try {
                date = (Date) formatter.parse(dob);
            } catch (ParseException ex) {
                Logger.getLogger(InputDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
            stmt.setDate(10, date);
            stmt.executeUpdate();
            stmt.setInt(2, 2);
            stmt.setString(3, "Shohei");
            stmt.setString(4, "member");
            stmt.setString(5, "member@secret.hotel.co");
            stmt.setString(6, "4524341213133");
            stmt.setString(7, "121243523521");
            stmt.setString(8, "Shohei Ohtani");
            dob = "01-01-1990";
            try {
                date = (Date) formatter.parse(dob);
            } catch (ParseException ex) {
                Logger.getLogger(InputDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
            stmt.setDate(10, date);
            stmt.setInt(11, 100);
            stmt.setInt(12, 200000);
            int val = (true) ? 1 : 0;
            stmt.setInt(13, val);
            stmt.executeUpdate();
            stmt.setInt(2, 2);
            stmt.setString(3, "Yadi");
            stmt.setString(4, "member");
            stmt.setString(5, "member@secret.hotel.co");
            stmt.setString(6, "4524341213133");
            stmt.setString(7, "121243523521");
            stmt.setString(8, "Yadier Molina");
            dob = "31-07-1980";
            try {
                date = (Date) formatter.parse(dob);
            } catch (ParseException ex) {
                Logger.getLogger(InputDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
            stmt.setDate(10, date);
            stmt.setInt(11, 300);
            stmt.setInt(12, 200000);
            val = (true) ? 1 : 0;
            stmt.setInt(13, val);
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    public static void main(String[] args) {
       insertUsers();
      
    }
}
