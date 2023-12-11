package com.teamalpha.teamalphapipergames.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "staff_id")
  private int id;
  @Column(name = "first_name", length = 40)
  private String firstName;

  @Column(name = "last_name", length = 40)
  private String lastName;

  @Column(name = "nick_name", length = 40)
  private String nickName;

  @Column(name = "address", length = 40)
  private String address;

  @Column(name = "zip_code", length = 40)
  private String zipCode;

  @Column(name = "postal_adress", length = 40)
  private String postalAddress;

  @Column(name = "country", length = 40)
  private String country;

  @Column(name = "email", length = 40)
  private String eMail;

  // Empty constructor
  public Staff() {
  }

  // Constructor w/o id
  public Staff(String firstName, String lastName, String nickName, String address, String zipCode, String postalAddress, String country, String eMail) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickName = nickName;
    this.address = address;
    this.zipCode = zipCode;
    this.postalAddress = postalAddress;
    this.country = country;
    this.eMail = eMail;
  }

  // full
  public Staff(int id, String firstName, String lastName, String nickName, String address, String zipCode, String postalAddress, String country, String eMail) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickName = nickName;
    this.address = address;
    this.zipCode = zipCode;
    this.postalAddress = postalAddress;
    this.country = country;
    this.eMail = eMail;
  }

  // methods
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "game")
  private List<Team> allStaff = new ArrayList<>();

  // Setters and getters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
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

  public String geteMail() {
    return eMail;
  }

  public void seteMail(String eMail) {
    this.eMail = eMail;
  }

  public List<Team> getAllStaff() {
    return allStaff;
  }

  public void setAllStaff(List<Team> allStaff) {
    this.allStaff = allStaff;
  }
}