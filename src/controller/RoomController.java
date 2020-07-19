/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.DataController.listHotel;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import model.Enums.BookingEnum;
import model.Room;
import model.Transaction;
import model.TransactionManager;

/**
 *
 * @author Jennifer Florentina
 */
public class RoomController {

    static DatabaseHandler conn = new DatabaseHandler();

    public static boolean cekRoomExist(int idHotel, int noKamar) {
        conn.connect();
        boolean isExist = false;
        String query = "SELECT * FROM room WHERE idHotel = " + idHotel + " AND noKamar = " + noKamar;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                isExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (isExist);
    }

    public static ArrayList<Room> cekRoomKosong(int idHotel, String tipe) {
        ArrayList<Room> listRoomKosong = new ArrayList<>();
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        for (int i = 0; i < listHotel.get(idHotel - 1).getRoomList().size(); i++) {
            if (listHotel.get(idHotel - 1).getRoomList().get(i).getTipe().equals(tipe)) {
                if (!cekAdaTransaksi(idHotel, listHotel.get(idHotel - 1).getRoomList().get(i).getNoKamar(), date)) {
                    listRoomKosong.add(listHotel.get(idHotel - 1).getRoomList().get(i));
                }
            }
        }
        return (listRoomKosong);
    }

    public static boolean cekAdaTransaksi(int idHotel, int noKamar, Date date) {
        conn.connect();
        boolean isExist = false;
        String query = "SELECT * FROM booking_transaksi WHERE idHotel = " + idHotel + " AND noKamar = " + noKamar
                + " AND (status = '" + BookingEnum.BOOKED.toString() + "' OR status = '" + BookingEnum.CHECKEDIN.toString() + "')"
                + " AND check_in <= '" + date + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                isExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (isExist);
    }

    public static boolean cekRoomAvailableForStayOver(int idHotel, int noKamar, Date newDate, Date oldDate) {
        conn.connect();
        boolean isExist = true;
        long diff = newDate.getTime() - oldDate.getTime();
        float days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        String query;
        if (days == 1) {
            query = "SELECT * FROM booking_transaksi WHERE idHotel = " + idHotel + " AND noKamar = " + noKamar
                    + " AND status = '" + BookingEnum.BOOKED.toString() + "' AND check_in = '" + oldDate + "'";
        } else {
            query = "SELECT * FROM booking_transaksi WHERE idHotel = " + idHotel + " AND noKamar = " + noKamar
                    + " AND status = '" + BookingEnum.BOOKED.toString() + "' AND check_in >= '" + oldDate + "' AND  check_out <= '" + newDate + "'";
        }
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                isExist = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (isExist);
    }

    public static boolean updateRoomBaru(int noKamar, int idTransaksi) {
        conn.connect();
        String query = "UPDATE booking_transaksi SET noKamar = " + noKamar + " WHERE idTransaksi = " + idTransaksi;
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }

    }

    public static boolean makeNewTransaction(Transaction newTransaction, BookingEnum status) {
        conn.connect();
        String query = "INSERT INTO booking_transaksi (idHotel,noKamar,idUser,tanggalBooking,check_in,check_out,jumlahGuest,uangMuka,idJenisPembayaran,status) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt;
            stmt = conn.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, newTransaction.getIdHotel());
            stmt.setInt(2, newTransaction.getNoKamar());
            stmt.setInt(3, newTransaction.getIdUser());
            stmt.setDate(4, new java.sql.Date(newTransaction.getTanggalBooking().getTime()));
            stmt.setDate(5, new java.sql.Date(newTransaction.getTanggalCheckIn().getTime()));
            stmt.setDate(6, new java.sql.Date(newTransaction.getTanggalCheckOut().getTime()));
            stmt.setInt(7, newTransaction.getJumlahGuest());
            stmt.setInt(8, newTransaction.getUangMuka());
            stmt.setInt(9, newTransaction.getIdJenisPembayaran());
            stmt.setString(10, status.toString());
            stmt.executeUpdate();
            int idTransaksiBaru = 0;
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idTransaksiBaru = rs.getInt(1);
            }
            TransactionManager.getInstance().setTransaction(CheckController.getOneTransaction(idTransaksiBaru));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void makeCheckOutTransaction(int idTransaksi, int uangMuka) {
        conn.connect();
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String query = "UPDATE booking_transaksi SET check_out = '" + date + "' , uangMuka = " + uangMuka / 2 + " WHERE idTransaksi = " + idTransaksi;
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            TransactionManager.getInstance().setTransaction(CheckController.getOneTransaction(idTransaksi));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCheckOutDate(int idTransaksi, Date newDate) {
        conn.connect();
        String query = "UPDATE booking_transaksi SET check_out = '" + newDate + "' WHERE idTransaksi = " + idTransaksi;
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            TransactionManager.getInstance().setTransaction(CheckController.getOneTransaction(idTransaksi));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getRoomTypebyRN(int RN) {
        conn.connect();
        String query = "SELECT * FROM room WHERE noKamar='" + RN + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                return rs.getString("tipe");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
