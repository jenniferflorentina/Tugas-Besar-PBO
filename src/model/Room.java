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
public class Room{
    private String tipe;
    private int noKamar;
    private int harga;
    private int batasGuest;

    public Room(String tipe, int noKamar, int harga, int batasGuest) {
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

    public int getNoKamar() {
        return noKamar;
    }

    public void setNoKamar(int noKamar) {
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

    @Override
    public String toString() {
        return "\nNo Kamar : " + noKamar + "\nTipe : " + tipe +  "\nHarga : " + harga + "\nBatas Guest : " + batasGuest;
    }
    
}
