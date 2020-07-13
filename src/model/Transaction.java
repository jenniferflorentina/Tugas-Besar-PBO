/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static controller.DataController.*;
import java.util.ArrayList;
import model.Enums.BookingEnum;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class Transaction {
    private int idTransaksi;
    private int idHotel;
    private int idJenisPembayaran;
    private int idUser;
    private int noKamar;
    private int jumlahGuest;
    private int uangMuka;
    private Date tanggalCheckIn;
    private Date tanggalCheckOut;
    private Date tanggalBooking;
    private ArrayList<Barang> listBarangRusak = new ArrayList<>();
    private BookingEnum status;
    
    public Transaction(){}

    public Transaction(int idTransaksi, int idHotel, int idJenisPembayaran, int idUser, int noKamar, int jumlahGuest, int uangMuka, Date tanggalCheckIn, Date tanggalCheckOut, Date tanggalBooking, BookingEnum status) {
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
        this.status = status;
    }
    
    public Transaction(int idHotel, int idJenisPembayaran, int idUser, int noKamar, int jumlahGuest, int uangMuka, Date tanggalCheckIn, Date tanggalCheckOut, Date tanggalBooking, BookingEnum status) {
        this.idHotel = idHotel;
        this.idJenisPembayaran = idJenisPembayaran;
        this.idUser = idUser;
        this.noKamar = noKamar;
        this.jumlahGuest = jumlahGuest;
        this.uangMuka = uangMuka;
        this.tanggalCheckIn = tanggalCheckIn;
        this.tanggalCheckOut = tanggalCheckOut;
        this.tanggalBooking = tanggalBooking;
        this.status = status;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getNoKamar() {
        return noKamar;
    }

    public void setNoKamar(int noKamar) {
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

    public ArrayList<Barang> getListBarangRusak() {
        return listBarangRusak;
    }
    
    //Salah tempat seharusnya di Controller
    public void bookingKamar(int idJenisPembayaran, int noKamar, int jumlahGuest, int uangMuka, Date tanggalCheckIn, Date tanggalCheckOut){
        //idHotel = HotelManager.getInstance().getHotel().getIdHotel();
        this.idJenisPembayaran = idJenisPembayaran;
        idUser = PersonManager.getInstance().getPerson().getId();
        this.noKamar = noKamar;
        this.jumlahGuest = jumlahGuest;
        this.uangMuka = uangMuka;
        this.tanggalCheckIn = tanggalCheckIn;
        this.tanggalCheckOut = tanggalCheckOut;
        this.tanggalBooking = new Date();
        TransactionManager.getInstance().setTransaction(this);
    }   
        
    public BookingEnum getStatus() {
        return status;
    }

    public void setStatus(BookingEnum status) {
        this.status = status;
    }
    
    public void addBarangRusak(Barang barang){
        this.listBarangRusak.add(barang);
    }
    
    public int getLamaInap(){
        long diffInMillies = Math.abs(this.tanggalCheckOut.getTime() - this.tanggalCheckIn.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return (int) diff;
    }
    
    public int HitungTotalBayar(){
        return getLamaInap()*listHotel.get(this.idHotel-1).getRoomList().get(this.noKamar-1).getHarga();
    }
    
    public int HitungDiskon(){
        if(idJenisPembayaran == 1 && HitungTotalBayar()<2000000){        
            return 0;
        }else{
            return (int) Math.round(HitungTotalBayar()*listJenisPembayaran.get(idJenisPembayaran-1).getDiskon());
        }
    }
    
    public int getBill(){
        return (HitungTotalBayar()-this.uangMuka)- HitungDiskon();
    }
    
    
}
