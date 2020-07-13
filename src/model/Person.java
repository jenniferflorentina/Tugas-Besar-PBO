/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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

    public Person() {
    }

    public Person(int id, TipeUserEnum tipeUser, String username, String password, String name, String email, String noKTP, String noTelepon, String alamat) {
        this.id = id;
        this.tipeUser = tipeUser;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.noKTP = noKTP;
        this.noTelepon = noTelepon;
        this.alamat = alamat;
    }

    public Person(TipeUserEnum tipeUser, String username, String password, String name, String email, String noKTP, String noTelepon, String alamat) {
        this.tipeUser = tipeUser;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.noKTP = noKTP;
        this.noTelepon = noTelepon;
        this.alamat = alamat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipeUserEnum getTipeUser() {
        return tipeUser;
    }

    public void setTipeUser(TipeUserEnum tipeUser) {
        this.tipeUser = tipeUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoKTP() {
        return noKTP;
    }

    public void setNoKTP(String noKTP) {
        this.noKTP = noKTP;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public String toString() {
        return username + " \n" + password; //To change body of generated methods, choose Tools | Templates.
    }
}
