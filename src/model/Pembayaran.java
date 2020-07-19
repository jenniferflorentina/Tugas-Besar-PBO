/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class Pembayaran {

    private int idJenisPembayaran;
    private String jenis;
    private double diskon;

    public Pembayaran(int idJenisPembayaran, String jenis, double diskon) {
        this.idJenisPembayaran = idJenisPembayaran;
        this.jenis = jenis;
        this.diskon = diskon;
    }

    public Pembayaran() {
    }

    public int getIdJenisPembayaran() {
        return idJenisPembayaran;
    }

    public void setIdJenisPembayaran(int idJenisPembayaran) {
        this.idJenisPembayaran = idJenisPembayaran;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public double getDiskon() {
        return diskon;
    }

    public void setDiskon(double diskon) {
        this.diskon = diskon;
    }
}
