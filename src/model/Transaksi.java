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
public class Transaksi {
    private int idTransaksi;
    private int jumlahGuest;
    private int uangMuka;
    private Date tanggalCheckIn;
    private Date tanggalCheckOut;
    private Date tanggalBooking;

    public Transaksi(int idTransaksi, int jumlahGuest, int uangMuka, Date tanggalCheckIn, Date tanggalCheckOut, Date tanggalBooking) {
        this.idTransaksi = idTransaksi;
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
