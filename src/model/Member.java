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
public class Member extends User{
    private int poinMember;
    private int membershipFee;
    private boolean hasPaidFee;

    public Member() {
    }

    public Member(int poinMember, int membershipFee, boolean hasPaidFee, Date dateOfBirth) {
        super(dateOfBirth);
        this.poinMember = poinMember;
        this.membershipFee = membershipFee;
        this.hasPaidFee = hasPaidFee;
    }
    
    public int getPoinMember() {
        return poinMember;
    }

    public boolean isHasPaidFee() {
        return hasPaidFee;
    }

    public void setPoinMember(int poinMember) {
        this.poinMember = poinMember;
    }

    public void setMembershipFee(int membershipFee) {
        this.membershipFee = membershipFee;
    }

    public void setHasPaidFee(boolean hasPaidFee) {
        this.hasPaidFee = hasPaidFee;
    }
    
    
}
