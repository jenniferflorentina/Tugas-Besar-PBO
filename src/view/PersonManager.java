/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Person;

/**
 *
 * @author Jennifer Florentina
 */
public class PersonManager {
    private static PersonManager instance;
    private Person person;

    public static PersonManager getInstance() {
        if (instance == null) {
            instance = new PersonManager();
        }
        return instance;
    }
    
    public Person getUser() {
        return person;
    }

    public void setUser(Person person) {
        this.person = person;
    }
}
