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
public class Member extends User {

    private int poinMember;
    private int membershipFee;
    private boolean hasPaidFee;

    public Member(int poinMember, int membershipFee, boolean hasPaidFee, Date dateOfBirth, TipeUserEnum tipeUser, String username, String password, String name, String email, String noKTP, String noTelepon, String alamat) {
        super(dateOfBirth, tipeUser, username, password, name, email, noKTP, noTelepon, alamat);
        this.poinMember = poinMember;
        this.membershipFee = membershipFee;
        this.hasPaidFee = hasPaidFee;
    }

    public Member(int poinMember, int membershipFee, boolean hasPaidFee, Date dateOfBirth, int id, TipeUserEnum tipeUser, String username, String password, String name, String email, String noKTP, String noTelepon, String alamat) {
        super(dateOfBirth, id, tipeUser, username, password, name, email, noKTP, noTelepon, alamat);
        this.poinMember = poinMember;
        this.membershipFee = membershipFee;
        this.hasPaidFee = hasPaidFee;
    }

    public Member() {
    }

    public Member(int poinMember, int membershipFee, boolean hasPaidFee, Date dateOfBirth) {
        this.poinMember = poinMember;
        this.membershipFee = membershipFee;
        this.hasPaidFee = hasPaidFee;
        this.setDateOfBirth(dateOfBirth);
    }

    public int getPoinMember() {
        return poinMember;
    }

    public void setPoinMember(int poinMember) {
        this.poinMember = poinMember;
    }

    public int getMembershipFee() {
        return membershipFee;
    }

    public void setMembershipFee(int membershipFee) {
        this.membershipFee = membershipFee;
    }

    public boolean isHasPaidFee() {
        return hasPaidFee;
    }

    public void setHasPaidFee(boolean hasPaidFee) {
        this.hasPaidFee = hasPaidFee;
    }

}
