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
public class Room extends Hotel{
    private String tipe;
    private String noKamar;
    private int harga;
    private int batasGuest;

    public Room(String tipe, String noKamar, int harga, int batasGuest, int idHotel, String lokasi, String nama, double minimumDP) {
        super(idHotel, lokasi, nama, minimumDP);
        this.tipe = tipe;
        this.noKamar = noKamar;
        this.harga = harga;
        this.batasGuest = batasGuest;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getNoKamar() {
        return noKamar;
    }

    public void setNoKamar(String noKamar) {
        this.noKamar = noKamar;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getBatasGuest() {
        return batasGuest;
    }

    public void setBatasGuest(int batasGuest) {
        this.batasGuest = batasGuest;
    }
    
}
