/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Enums;

/**
 *
 * @author 1119034 Eirenika Joanna Grace Lendeng
 */
public enum BookingEnum {

    BOOKED("BOOKED"), CHECKEDIN("CHECKEDIN"), CHECKEDOUT("CHECKEDOUT"), CANCELLED("CANCELLED");

    private final String name;

    private BookingEnum(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false 
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
