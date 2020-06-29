/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class Transaction {
    private int idTransaksi;
    private int idHotel;
    private int idJenisPembayaran;
    private String idUser;
    private String noKamar;
    private int jumlahGuest;
    private int uangMuka;
    private Date tanggalCheckIn;
    private Date tanggalCheckOut;
    private Date tanggalBooking;

    public Transaksi(int idTransaksi, int idHotel, int idJenisPembayaran, String idUser, String noKamar, int jumlahGuest, int uangMuka, Date tanggalCheckIn, Date tanggalCheckOut, Date tanggalBooking) {
        this.idTransaksi = idTransaksi;
        this.idHotel = idHotel;
        this.idJenisPembayaran = idJenisPembayaran;
        this.idUser = idUser;
        this.noKamar = noKamar;
        this.jumlahGuest = jumlahGuest;
        this.uangMuka = uangMuka;
        this.tanggalCheckIn = tanggalCheckIn;
        this.tanggalCheckOut = tanggalCheckOut;
        this.tanggalBooking = tanggalBooking;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getIdJenisPembayaran() {
        return idJenisPembayaran;
    }

    public void setIdJenisPembayaran(int idJenisPembayaran) {
        this.idJenisPembayaran = idJenisPembayaran;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNoKamar() {
        return noKamar;
    }

    public void setNoKamar(String noKamar) {
        this.noKamar = noKamar;
    }

    public int getJumlahGuest() {
        return jumlahGuest;
    }

    public void setJumlahGuest(int jumlahGuest) {
        this.jumlahGuest = jumlahGuest;
    }

    public int getUangMuka() {
        return uangMuka;
    }

    public void setUangMuka(int uangMuka) {
        this.uangMuka = uangMuka;
    }

    public Date getTanggalCheckIn() {
        return tanggalCheckIn;
    }

    public void setTanggalCheckIn(Date tanggalCheckIn) {
        this.tanggalCheckIn = tanggalCheckIn;
    }

    public Date getTanggalCheckOut() {
        return tanggalCheckOut;
    }

    public void setTanggalCheckOut(Date tanggalCheckOut) {
        this.tanggalCheckOut = tanggalCheckOut;
    }

    public Date getTanggalBooking() {
        return tanggalBooking;
    }

    public void setTanggalBooking(Date tanggalBooking) {
        this.tanggalBooking = tanggalBooking;
    }

    
}
