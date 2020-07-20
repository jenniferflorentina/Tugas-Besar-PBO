/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static model.enums.TipeUserEnum.*;
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
import java.util.Calendar;

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
        String query = "SELECT * FROM user WHERE username='" + username + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int tipeUser = rs.getInt("tipeUser");
                switch (tipeUser) {
                    case 0:
                        user = new Person();
                        user.setTipeUser(ADMIN);
                        break;
                    case 1:
                        user = new User(rs.getDate("dateOfBirth"));
                        user.setTipeUser(GUEST);
                        break;
                    case 2:
                        user = new Member(rs.getInt("poinMember"), rs.getInt("membershipFee"), rs.getBoolean("bayarMembership"), rs.getDate("dateOfBirth"));
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
        String query = "SELECT * FROM user WHERE username='" + username + "'";
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
        String query = "SELECT * FROM user WHERE username='" + username + "'";
        boolean isMatch = false;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (rs.getString("password").equals(getMd5(password))) {
                    isMatch = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isMatch;
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
        String query = "INSERT INTO user (tipeUser, username, password, email, noKTP, noTelepon, nama, alamat) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 0);
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getNoKTP());
            stmt.setString(6, user.getNoTelepon());
            stmt.setString(7, user.getName());
            stmt.setString(8, user.getAlamat());
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
        String query = "INSERT INTO user (tipeUser, username, password, email, noKTP, noTelepon, nama, dateOfBirth, poinMember, membershipFee, bayarMembership) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, 2);
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getNoKTP());
            stmt.setString(6, user.getNoTelepon());
            stmt.setString(7, user.getName());
            stmt.setDate(8, (Date) user.getDateOfBirth());
            stmt.setInt(9, user.getPoinMember());
            stmt.setInt(10, user.getMembershipFee());
            int val = (user.isHasPaidFee()) ? 1 : 0;
            stmt.setInt(11, val);
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static int getHotelIDbyName(String name) {
        conn.connect();
        String query = "SELECT * FROM hotel WHERE nama='" + name + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Hotel a = new Hotel();
                return rs.getInt("idHotel");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int getRoomIDbyName(String name) {
        conn.connect();
        String query = "SELECT * FROM hotel WHERE nama='" + name + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt("idHotel");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String[] getHotelNameList() {
        String[] listNamaHotel = new String[DataController.listHotel.size()];
        for (int counter = 0; counter < DataController.listHotel.size(); counter++) {
            listNamaHotel[counter] = DataController.listHotel.get(counter).getNama();
        }
        return listNamaHotel;
    }

    public static String[] getRoomNameList(String namaHotel) {
        int a = getHotelIDbyName(String.valueOf(namaHotel));
        int i = DataController.getListRoom(a).size();
        String[] listNamaKamar = new String[i];
        for (int j = 0; j < i; j++) {
            listNamaKamar[j] = "";
        }
        ArrayList<Room> listRoom = controller.DataController.getListRoom(a);
        int j = 0, counter2 = 0;
        for (int counter = 0; counter < i; counter++) {
            boolean isThere = false;
            while (isThere == false && counter2 < i) {
                if (listNamaKamar[counter2].equals(listRoom.get(counter).getTipe())) {
                    isThere = true;
                }
                counter2++;
            }
            if (isThere == false) {
                listNamaKamar[j] = listRoom.get(counter).getTipe();
                j++;
            }
            counter2 = 0;
        }
        return listNamaKamar;
    }

    public static int getBatasGuest(int idKamar) {
        conn.connect();
        String query = "SELECT * FROM room WHERE idKamar='" + idKamar + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Hotel a = new Hotel();
                return rs.getInt("batasGuest");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //Untuk insert booking kamar
    public static boolean bookingKamar(Transaction trans) {
        conn.connect();
        String query = "INSERT INTO booking_transaksi (idHotel, noKamar, idUser, tanggalBooking, check_in, check_out, jumlahGuest, uangMuka, idJenisPembayaran, status) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, trans.getIdHotel());
            stmt.setInt(2, trans.getNoKamar());
            stmt.setInt(3, trans.getIdUser());
            stmt.setDate(4, new java.sql.Date(trans.getTanggalBooking().getTime()));
            stmt.setDate(5, new java.sql.Date(trans.getTanggalCheckIn().getTime()));
            stmt.setDate(6, new java.sql.Date(trans.getTanggalCheckOut().getTime()));
            stmt.setInt(7, trans.getJumlahGuest());
            stmt.setInt(8, trans.getUangMuka());
            stmt.setInt(9, trans.getIdJenisPembayaran());
            stmt.setString(10, String.valueOf(model.enums.BookingEnum.BOOKED));
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    //Untuk upgrade user menjadi member
    public static boolean upgradeToMember(User user) {
        conn.connect();
        String query = "UPDATE user SET tipeUser = '2', poinMember = '50', membershipFee = '200000', bayarMembership = '1' WHERE idUser='" + user.getId() + "'";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.executeUpdate();
            Member member = new Member();
            member.setId(user.getId());
            member.setAlamat(user.getAlamat());
            member.setTipeUser(model.enums.TipeUserEnum.MEMBER);
            member.setDateOfBirth(user.getDateOfBirth());
            member.setEmail(user.getEmail());
            member.setNoKTP(user.getNoKTP());
            member.setNoTelepon(user.getNoTelepon());
            member.setUsername(user.getUsername());
            member.setPassword(user.getPassword());
            member.setName(user.getName());
            member.setPoinMember(50);
            member.setMembershipFee(200000);
            member.setHasPaidFee(true);
            PersonManager.getInstance().setPerson(member);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    //Untuk berhenti jadi member
    public static boolean stopBeingMember(Member member) {
        conn.connect();
        String query = "UPDATE user SET tipeUser = '1', poinMember = '0', membershipFee = '0', bayarMembership = '0' WHERE idUser='" + member.getId() + "'";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.executeUpdate();
            User user = new User();
            user.setId(member.getId());
            user.setAlamat(member.getAlamat());
            user.setTipeUser(model.enums.TipeUserEnum.GUEST);
            user.setDateOfBirth(member.getDateOfBirth());
            user.setEmail(member.getEmail());
            user.setNoKTP(member.getNoKTP());
            user.setNoTelepon(member.getNoTelepon());
            user.setUsername(member.getUsername());
            user.setPassword(member.getPassword());
            user.setName(member.getName());
            PersonManager.getInstance().setPerson(user);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    //Untuk membayar membership
    public static boolean membershipPayment(Member member) {
        conn.connect();
        String query = "UPDATE user SET bayarMembership = '1' WHERE idUser='" + member.getId() + "'";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.executeUpdate();
            member.setHasPaidFee(true);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    //Untuk membayar membership
    public static boolean paidMembershipFee(Member member) {
        conn.connect();
        String query = "UPDATE user SET bayarMembership = '1' WHERE idUser='" + member.getId() + "'";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.executeUpdate();
            member.setHasPaidFee(true);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    //Mengganti status bayar member tiap awal bulan ke false
    public static void MembershipFeeReset() {
        java.util.Date date = new java.util.Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (day == 1) {
            conn.connect();
            String query = "UPDATE user SET bayarMembership = '0' WHERE bayarMembership = '1'";
            try {
                PreparedStatement stmt = conn.con.prepareStatement(query);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
