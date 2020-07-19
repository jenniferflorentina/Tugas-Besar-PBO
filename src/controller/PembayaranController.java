package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static model.Enums.TipeUserEnum.ADMIN;
import static model.Enums.TipeUserEnum.GUEST;
import static model.Enums.TipeUserEnum.MEMBER;
import model.Member;
import model.Person;
import model.TransactionManager;
import model.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jennifer Florentina
 */
public class PembayaranController {

    static DatabaseHandler conn = new DatabaseHandler();

    //Get User Login Data
    public static Person getPersonById(int idUser) {
        Person user = null;
        conn.connect();
        String query = "SELECT * FROM user WHERE idUser = " + idUser;
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

    //UPDATE POIN MEMBER KE DATABASE
    public static boolean updatePoin(int idUser, int poinMember) {
        conn.connect();

        String query = "UPDATE user SET poinMember = " + poinMember + " WHERE idUser = " + idUser;
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static void tambahPoinMember(int idUser) {
        Person person = getPersonById(idUser);
        if (person instanceof Member) {
            Member member = (Member) person;
            int poin = TransactionManager.getInstance().getTransaction().HitungTotalBayar() / 500000;
            member.setPoinMember(member.getPoinMember() + poin);
            if (updatePoin(idUser, member.getPoinMember())) {
                JOptionPane.showMessageDialog(null, "Poin Member bertambah " + poin + "\n\nTotal poin : " + member.getPoinMember());
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add poin!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // UPDATE PEMBAYARAN
    public static boolean updatePembayaran(int idTransaksi, int idPembayaran) {
        conn.connect();

        String query = "UPDATE booking_transaksi SET idJenisPembayaran = " + idPembayaran + " WHERE idTransaksi = " + idTransaksi;
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
