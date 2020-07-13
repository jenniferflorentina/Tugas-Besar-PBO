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
public class Barang {
    private int idBarang;
    private int harga;
    private int jumlah;
    private String namaBarang;

    public Barang(int idBarang, int harga, int jumlah, String namaBarang) {
        this.idBarang = idBarang;
        this.harga = harga;
        this.jumlah = jumlah;
        this.namaBarang = namaBarang;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }
    
    public int getTotalBayar(){
        return this.jumlah*this.harga;
    }
}
