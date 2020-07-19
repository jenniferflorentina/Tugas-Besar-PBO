/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.CheckController;
import static controller.DataController.*;
import static view.Helper.ConstantStyle.formatter;
import static view.Helper.ConstantStyle.kurensiIndonesia;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class TransactionManager {

    private static TransactionManager instance;
    private Transaction transaction;

    public static TransactionManager getInstance() {
        if (instance == null) {
            instance = new TransactionManager();
        }
        return instance;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        Person person = getPersonByID(this.transaction.getIdUser());
        Room room = CheckController.getDataRoom(transaction.getIdHotel(), transaction.getNoKamar());
        return "<html><pre>ID Transaksi : " + this.transaction.getIdTransaksi()
                + "<br/>Tanggal Booking : " + formatter.format(this.transaction.getTanggalBooking())
                + "<br/>Tanggal Check In : " + formatter.format(this.transaction.getTanggalCheckIn())
                + "<br/>Tanggal Check Out : " + formatter.format(this.transaction.getTanggalCheckOut())
                + "<br/>Lama Inap : " + this.transaction.getLamaInap() + " hari"
                + "<br/>Detail Hotel : <br/>      Nama : " + listHotel.get(this.transaction.getIdHotel() - 1).getNama()
                + "<br/>      Alamat : " + listHotel.get(this.transaction.getIdHotel() - 1).getLokasi()
                + "<br/>Detail Kamar : <br/>      No Kamar : " + this.transaction.getNoKamar()
                + "<br/>      Tipe Kamar : " + room.getTipe()
                + "<br/>      Harga Kamar : " + kurensiIndonesia.format(room.getHarga())
                + "<br/>Detail User : <br/>      ID User : " + this.transaction.getIdUser()
                + "<br/>      Telepon : " + person.getNoTelepon()
                + "<br/>      Nama : " + person.getName()
                + "<br/>Barang Rusak : <br/>"
                + this.transaction.printBarangRusak()
                + "<br/>Detail Pembayaran : <br/>      ID Pembayaran : " + this.transaction.getIdJenisPembayaran()
                + "<br/>      Jenis : " + listJenisPembayaran.get(this.transaction.getIdJenisPembayaran() - 1).getJenis()
                + "<br/>      Persen Diskon : " + listJenisPembayaran.get(this.transaction.getIdJenisPembayaran() - 1).getDiskon() * 100 + "%"
                + "<br/>      Harga Total : " + kurensiIndonesia.format(transaction.HitungTotalBayar())
                + "<br/>      Uang Muka : " + kurensiIndonesia.format(transaction.getUangMuka())
                + "<br/>      Diskon : " + kurensiIndonesia.format(transaction.HitungDiskon())
                + "<br/>      Harga akhir yang dibayar setelah diskon : " + kurensiIndonesia.format(transaction.getBill())
                + "<br/>Status Transaksi : " + this.transaction.getStatus() + "</pre></html>";
    }
}
