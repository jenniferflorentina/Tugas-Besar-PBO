/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.TipeUserEnum;

/**
 *
 * @author Jennifer Florentina
 */
public class Person {
    private int id;
    private TipeUserEnum tipeUser;
    private String username;
    private String password;
    private String name;
    private String email;
    private String noKTP;
    private String noTelepon;
    private String alamat;

    public int getId() {
        return id;
    }

    public TipeUserEnum getTipeUser() {
        return tipeUser;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipeUser(TipeUserEnum tipeUser) {
        this.tipeUser = tipeUser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNoKTP(String noKTP) {
        this.noKTP = noKTP;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public String toString() {
        return username +" \n"+ password; //To change body of generated methods, choose Tools | Templates.
    }
    
    
     
}   
