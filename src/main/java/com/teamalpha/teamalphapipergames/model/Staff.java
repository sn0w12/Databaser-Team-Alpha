
package com.teamalpha.teamalphapipergames.model;

import javax.persistence.*;

@Entity
@Table(name = "staff")

public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private int staffId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "address")
    private String address;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "postal_address")
    private String postalAddress;

    @Column(name = "country")
    private String country;

    @Column(name = "email")
    private String email;


    public Staff() {

    }

    public Staff(int staffId) {
        this.staffId = staffId;
    }

    public Staff(int staffId, String firstName, String lastName, String nickname, String address, String zipCode, String postalAddress, String country, String email) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.address = address;
        this.zipCode = zipCode;
        this.postalAddress = postalAddress;
        this.country = country;
        this.email = email;
    }

    public Staff(String firstName, String lastName, String nickname, String address, String zipCode, String postalAddress, String country, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.address = address;
        this.zipCode = zipCode;
        this.postalAddress = postalAddress;
        this.country = country;
        this.email = email;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
