/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Jennifer Florentina
 */
public class User extends Person{
    private Date dateOfBirth;

    public User(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public User() {
    }
    
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
}
