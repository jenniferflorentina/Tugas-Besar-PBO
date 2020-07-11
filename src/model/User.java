/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.TipeUserEnum;
import java.util.Date;

/**
 *
 * @author Jennifer Florentina
 */
public class User extends Person{
    private Date dateOfBirth;


    public User(Date dateOfBirth, TipeUserEnum tipeUser, String username, String password, String name, String email, String noKTP, String noTelepon, String alamat) {
        super(tipeUser, username, password, name, email, noKTP, noTelepon, alamat);
        this.dateOfBirth = dateOfBirth;
    }

    public User(Date dateOfBirth, int id, TipeUserEnum tipeUser, String username, String password, String name, String email, String noKTP, String noTelepon, String alamat) {
        super(id, tipeUser, username, password, name, email, noKTP, noTelepon, alamat);
        this.dateOfBirth = dateOfBirth;
    }

    public User(){}
    
    public User(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public void upgradeToMember(){
        this.setTipeUser(TipeUserEnum.MEMBER);
    }
    
}
