/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public class Hotel {
    private int idHotel;
    private String lokasi;
    private String nama;
    private double minimumDP;
    private ArrayList<Room> roomList;

    public Hotel(int idHotel, String lokasi, String nama, double minimumDP, ArrayList<Room> roomList) {
        this.idHotel = idHotel;
        this.lokasi = lokasi;
        this.nama = nama;
        this.minimumDP = minimumDP;
        this.roomList = roomList;
    }

    public Hotel(){}
    
    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getMinimumDP() {
        return minimumDP;
    }

    public void setMinimumDP(double minimumDP) {
        this.minimumDP = minimumDP;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }
    
    
}
